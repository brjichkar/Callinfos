package com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.WhatsappUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_response.WhatsappUpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatsappUpdateModelImplementer implements WhatsappUpdateMVP.WhatsappUpdateModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processWhatsappUpdate(WhatsappUpdateRequest whatsappUpdateRequest, OnWhatsappUpdateFinished onWhatsappUpdateFinished) {
        Call<WhatsappUpdateResponse> call = apiService.getWhatsappUpdateDetails (whatsappUpdateRequest);
        call.enqueue(new Callback<WhatsappUpdateResponse> () {
            @Override
            public void onResponse(Call<WhatsappUpdateResponse>call, Response<WhatsappUpdateResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onWhatsappUpdateFinished.onWhatsappUpdateSuccess (response.body().getData());
                    } else {
                        onWhatsappUpdateFinished.onWhatsappUpdateFailed ("Invalid Whatsapp Update Details");
                    }
                } else {
                    onWhatsappUpdateFinished.onWhatsappUpdateFailed ("Invalid Whatsapp Update Details");
                }
            }

            @Override
            public void onFailure(Call<WhatsappUpdateResponse>call, Throwable t) {
                onWhatsappUpdateFinished.onWhatsappUpdateFailed ("");
            }
        });
    }
}
