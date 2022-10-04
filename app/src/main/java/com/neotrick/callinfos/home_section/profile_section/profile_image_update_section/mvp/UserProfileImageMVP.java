package com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.mvp;

import com.neotrick.callinfos.base_class_section.MvpView;

import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.model.profile_image_response.Data;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.http.Part;


public class UserProfileImageMVP {

    public interface  UserProfileImageView extends MvpView {
        void onUserProfileImageSuccess(Data result);
        void onUserProfileImageFailed(String errorMsg);


    }


    public interface  UserProfileImagePresenter {
        void onAttach(UserProfileImageView mView);
        void onDestroyView();

        void onProcessUserProfileImage(String userId, File imageFile);



    }


    public interface UserProfileImageModel {
        void processUserProfileImage(String userId, File imageFile, OnUserProfileImageFinished onUserProfileImageFinished);

        interface OnUserProfileImageFinished {
            void onUserProfileImageSuccess(Data result);

            void onUserProfileImageFailed(String errorMsg);
        }


    }


}
