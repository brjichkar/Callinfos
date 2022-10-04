package com.neotrick.callinfos.home_section.profile_section.profile_update_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request.ProfileUpdateRequest;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_response.ProfileUpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileModelImplementer implements UpdateProfileMVP.UpdateProfileModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processUpdateProfile(ProfileUpdateRequest profileUpdateRequest, OnUpdateProfileFinished onUpdateProfileFinished) {
        Call<ProfileUpdateResponse> call = apiService.getProfileUpdateDetails (profileUpdateRequest);
        call.enqueue(new Callback<ProfileUpdateResponse> () {
            @Override
            public void onResponse(Call<ProfileUpdateResponse>call, Response<ProfileUpdateResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onUpdateProfileFinished.onUpdateProfileSuccess (response.body().getData());
                    } else {
                        onUpdateProfileFinished.onUpdateProfileFailed ("Invalid Profile  Details");
                    }
                } else {
                    onUpdateProfileFinished.onUpdateProfileFailed ("Invalid Profile Details");
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdateResponse>call, Throwable t) {
                onUpdateProfileFinished.onUpdateProfileFailed ("");
            }
        });
    }
}
