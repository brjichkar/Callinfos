package com.neotrick.callinfos.home_section.message_section.sms_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.SmsShowRequest;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_response.SmsShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsShowModelImplementer implements SmsShowMVP.SmsShowModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processSmsShow(SmsShowRequest smsShowRequest, OnSmsShowFinished onSmsShowFinished) {
        Call<SmsShowResponse> call = apiService.getShowSmsDetails (smsShowRequest);
        call.enqueue(new Callback<SmsShowResponse> () {
            @Override
            public void onResponse(Call<SmsShowResponse>call, Response<SmsShowResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onSmsShowFinished.onSmsShowSuccess (response.body().getData());
                    } else {
                        onSmsShowFinished.onSmsShowFailed ("Invalid SMS Details");
                    }
                } else {
                    onSmsShowFinished.onSmsShowFailed ("Invalid SMS Details");
                }
            }

            @Override
            public void onFailure(Call<SmsShowResponse>call, Throwable t) {
                onSmsShowFinished.onSmsShowFailed ("");
            }
        });
    }
}
