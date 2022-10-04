package com.neotrick.callinfos.home_section.profile_section.profile_update_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;

import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request.ProfileUpdateRequest;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_response.Data;


public class UpdateProfileMVP {

    public interface UpdateProfileView extends MvpView {
        void onUpdateProfileSuccess(Data result);
        void onUpdateProfileFailed(String errorMsg);
    }

    public interface UpdateProfilePresenter{
        void onAttach(UpdateProfileView mView);
        void onDestroyView();

        void onProcessUpdateProfile(ProfileUpdateRequest profileUpdateRequest);
    }


    public interface UpdateProfileModel{
        void processUpdateProfile(ProfileUpdateRequest profileUpdateRequest, OnUpdateProfileFinished onUpdateProfileFinished);

        interface OnUpdateProfileFinished{
            void onUpdateProfileSuccess(Data result);
            void onUpdateProfileFailed(String errorMsg);
        }
    }
}
