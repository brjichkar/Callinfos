package com.neotrick.callinfos.home_section.profile_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_response.Data;


public class ViewProfileMVP {

    public interface ViewProfileView extends MvpView {
        void onViewProfileSuccess(Data result);
        void onViewProfileFailed(String errorMsg);
    }

    public interface ViewProfilePresenter{
        void onAttach(ViewProfileView mView);
        void onDestroyView();

        void onProcessViewProfile(ViewProfileRequest viewProfileRequest);
    }


    public interface ViewProfileModel{
        void processViewProfile(ViewProfileRequest callsRequest, OnViewProfileFinished onCallFinished);

        interface OnViewProfileFinished{
            void onViewProfileSuccess(Data result);
            void onViewProfileFailed(String errorMsg);
        }
    }
}
