package com.neotrick.callinfos.calllog_section;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.app_preference_section.AppPreference;
import com.neotrick.callinfos.home_section.call_message_section.model.call_meassage_response.CallMessageResponse;
import com.neotrick.callinfos.home_section.call_message_section.model.call_message_request.CallMessageRequest;
import com.neotrick.callinfos.home_section.call_message_section.model.call_message_request.CallMessagereqdata;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_request.Callsreqdata;
import com.neotrick.callinfos.home_section.call_records.model.call_response.CallsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerClassForUploadingCalls  extends Worker {
    private AppPreference mAppPref;

    public WorkerClassForUploadingCalls(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mAppPref=new AppPreference(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        String mobNo=getInputData().getString("mobNo");
        String userId=getInputData().getString("userId");
        String callType=getInputData().getString("callType");
        CallMessageRequest request =new CallMessageRequest();
        CallMessagereqdata callReqData=new CallMessagereqdata();
        callReqData.setMobile(mobNo);
        callReqData.setUserId(userId);
        callReqData.setCallType(callType);
        request.setJsondata(callReqData);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CallMessageResponse> call = apiService.getCallMessageDetails (request);
        call.enqueue(new Callback<CallMessageResponse>() {
            @Override
            public void onResponse(Call<CallMessageResponse>call, Response<CallMessageResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        Log.d("CallRecorded status-",response.body().getData().getMessage());
                        if(response.body().getData().getMessage().equalsIgnoreCase("0")){
                            // no need to send sms
                        }
                        else{
                            // send sms
                            try {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(mobNo, null, mAppPref.getMessageToSend(), null, null);
                            } catch (Exception ex) {
                            }
                        }
                        //onCallFinished.onCallSuccess (response.body().getData());
                    } else {
                        Log.d("WorkerClassForUploadingCalls","Error");
                        //onCallFinished.onCallFailed ("Invalid Call Details");
                    }
                } else {
                    Log.d("WorkerClassForUploadingCalls","Error");
                    //onCallFinished.onCallFailed ("Invalid Call Details");
                }
            }

            @Override
            public void onFailure(Call<CallMessageResponse>call, Throwable t) {
                //onCallFinished.onCallFailed ("");
                Log.d("WorkerClassForUploadingCalls","Error");
            }
        });
        return Result.success();
    }
}
