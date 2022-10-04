package com.neotrick.callinfos.home_section.call_records.mvp;



import android.telecom.CallScreeningService;

import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_response.CallsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallModelImplementer implements CallMVP.CallModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processCall(CallsRequest callsRequest, OnCallFinished onCallFinished) {
        Call<CallsResponse> call = apiService.getCallDetails (callsRequest);
        call.enqueue(new Callback<CallsResponse> () {
            @Override
            public void onResponse(Call<CallsResponse>call, Response<CallsResponse> response) {
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
            public void onFailure(Call<CallsResponse>call, Throwable t) {
                onCallFinished.onCallFailed ("");
            }
        });
    }
}
