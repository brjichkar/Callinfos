package com.neotrick.callinfos.home_section.message_section.whatsapp_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_request.WhatsappShowRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_response.WhatsappShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatsappShowModelImplementer implements WhatsappShowMVP.WhatsappShowModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processWhatsappShow(WhatsappShowRequest whatsappShowRequest, OnWhatsappShowFinished onWhatsappShowFinished) {
        Call<WhatsappShowResponse> call = apiService.getShowWhatsappDetails (whatsappShowRequest);
        call.enqueue(new Callback<WhatsappShowResponse> () {
            @Override
            public void onResponse(Call<WhatsappShowResponse>call, Response<WhatsappShowResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onWhatsappShowFinished.onWhatsappShowSuccess (response.body().getData());
                    } else {
                        onWhatsappShowFinished.onWhatsappShowFailed ("Invalid Whatsapp Details");
                    }
                } else {
                    onWhatsappShowFinished.onWhatsappShowFailed ("Invalid Whatsapp Details");
                }
            }

            @Override
            public void onFailure(Call<WhatsappShowResponse>call, Throwable t) {
                onWhatsappShowFinished.onWhatsappShowFailed ("");
            }
        });
    }
}
