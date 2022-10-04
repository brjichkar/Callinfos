package com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;

import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.model.profile_image_response.ProfileImageResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import kotlin.io.TextStreamsKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileImageModelImple implements UserProfileImageMVP.UserProfileImageModel {
    ApiInterface apiService = ApiClient.getClient ().create (ApiInterface.class);


    @Override
        public void processUserProfileImage(String userId, File imageFile, UserProfileImageMVP.UserProfileImageModel.OnUserProfileImageFinished onUserProfileImageFinished) {
        RequestBody imageRequestBody= RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part part= MultipartBody.Part.createFormData("profileimage",imageFile.getName(),imageRequestBody);
        try {
            Call<ProfileImageResponse> call = apiService.getProfileImageDetails (part,Integer.parseInt(userId));
            call.enqueue (new Callback<ProfileImageResponse> () {
                @Override
                public void onResponse(Call<ProfileImageResponse> call, Response<ProfileImageResponse> response) {
                    if (response.code () == 200) {
                        if (response.body()!=null && response.body ().getData ()!=null) {
                            onUserProfileImageFinished.onUserProfileImageSuccess (response.body ().getData ());
                        } else {
                            onUserProfileImageFinished.onUserProfileImageFailed ("");
                        }
                    }
                    else {
                        if(response.errorBody()!=null){
                            JSONObject jsonObj = null;
                            try {
                                jsonObj = new JSONObject(TextStreamsKt.readText(response.errorBody().charStream()));
                                onUserProfileImageFinished.onUserProfileImageFailed  (jsonObj.getString("message"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                onUserProfileImageFinished.onUserProfileImageFailed  ("");
                            }
                        }else{
                            onUserProfileImageFinished.onUserProfileImageFailed  ("");
                        }
                    }
                }
                @Override
                public void onFailure(Call<ProfileImageResponse> call, Throwable t) {
                    onUserProfileImageFinished.onUserProfileImageFailed  ("");
                }
            });
        } catch (Exception e) {
            onUserProfileImageFinished.onUserProfileImageFailed ("");
        }
    }
}