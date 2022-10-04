package com.neotrick.callinfos.home_section.profile_section.mvp;



import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;

import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_response.ViewProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileModelImplementer implements ViewProfileMVP.ViewProfileModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processViewProfile(ViewProfileRequest viewProfileRequest, OnViewProfileFinished onViewProfileFinished) {
        Call<ViewProfileResponse> call = apiService.getViewProfileDetails (viewProfileRequest);
        call.enqueue(new Callback<ViewProfileResponse> () {
            @Override
            public void onResponse(Call<ViewProfileResponse>call, Response<ViewProfileResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onViewProfileFinished.onViewProfileSuccess (response.body().getData());
                    } else {
                        onViewProfileFinished.onViewProfileFailed ("Invalid Profile Details");
                    }
                } else {
                    onViewProfileFinished.onViewProfileFailed ("Invalid Profile Details");
                }
            }

            @Override
            public void onFailure(Call<ViewProfileResponse>call, Throwable t) {
                onViewProfileFinished.onViewProfileFailed ("");
            }
        });
    }
}
