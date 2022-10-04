package com.neotrick.callinfos.home_section.call_message_section.mvp;



import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.call_message_section.model.call_meassage_response.CallMessageResponse;
import com.neotrick.callinfos.home_section.call_message_section.model.call_message_request.CallMessageRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_response.CallsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallMessageModelImplementer implements CallMessageMVP.CallModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processCall(CallMessageRequest callMessageRequest, OnCallFinished onCallFinished) {
        Call<CallMessageResponse> call = apiService.getCallMessageDetails (callMessageRequest);
        call.enqueue(new Callback<CallMessageResponse> () {
            @Override
            public void onResponse(Call<CallMessageResponse>call, Response<CallMessageResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onCallFinished.onCallSuccess (response.body().getData());
                    } else {
                        onCallFinished.onCallFailed ("Invalid Call Details");
                    }
                } else {
                    onCallFinished.onCallFailed ("Invalid Call Details");
                }
            }

            @Override
            public void onFailure(Call<CallMessageResponse>call, Throwable t) {
                onCallFinished.onCallFailed ("");
            }
        });
    }
}
