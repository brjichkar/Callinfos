package com.neotrick.callinfos.home_section.help_section.history_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;

import com.neotrick.callinfos.home_section.help_section.history_section.model.history_request.HistoryRequest;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.HistoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHistoryModelImplementer implements UserHistoryMVP.UserHistoryModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processUserHistory(HistoryRequest historyRequest, OnUserHistoryFinished onUserHistoryFinished) {
        Call<HistoryResponse> call = apiService.getHistoryDetails (historyRequest);
        call.enqueue(new Callback<HistoryResponse> () {
            @Override
            public void onResponse(Call<HistoryResponse>call, Response<HistoryResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onUserHistoryFinished.onUserHistorySuccess (response.body().getData());
                    } else {
                        onUserHistoryFinished.onUserHistoryFailed ("Invalid User History Details");
                    }
                } else {
                    onUserHistoryFinished.onUserHistoryFailed ("Invalid User History Details");
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse>call, Throwable t) {
                onUserHistoryFinished.onUserHistoryFailed("");
            }
        });
    }
}
