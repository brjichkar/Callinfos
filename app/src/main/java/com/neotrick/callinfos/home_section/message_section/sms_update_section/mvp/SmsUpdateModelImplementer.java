package com.neotrick.callinfos.home_section.message_section.sms_update_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.SmsUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_response.SmsUpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsUpdateModelImplementer implements SmsUpdateMVP.SmsUpdateModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processSmsUpdate(SmsUpdateRequest smsupdateRequest, OnSmsUpdateFinished onSmsUpdateFinished) {
        Call<SmsUpdateResponse> call = apiService.getShowUpdateDetails (smsupdateRequest);
        call.enqueue(new Callback<SmsUpdateResponse> () {
            @Override
            public void onResponse(Call<SmsUpdateResponse>call, Response<SmsUpdateResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onSmsUpdateFinished.onSmsUpdateSuccess (response.body().getData());
                    } else {
                        onSmsUpdateFinished.onSmsUpdateFailed ("Invalid SMS Details");
                    }
                } else {
                    onSmsUpdateFinished.onSmsUpdateFailed ("Invalid SMS Details");
                }
            }

            @Override
            public void onFailure(Call<SmsUpdateResponse>call, Throwable t) {
                onSmsUpdateFinished.onSmsUpdateFailed ("");
            }
        });
    }
}
