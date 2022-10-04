package com.neotrick.callinfos.home_section.profile_section.mvp;


import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_response.Data;

public class ViewProfilePresenterImplementer implements ViewProfileMVP.ViewProfilePresenter, ViewProfileMVP.ViewProfileModel.OnViewProfileFinished {
    private ViewProfileMVP.ViewProfileView mViews;
    private ViewProfileModelImplementer mModel=new ViewProfileModelImplementer();

    public ViewProfilePresenterImplementer(ViewProfileMVP.ViewProfileView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(ViewProfileMVP.ViewProfileView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onProcessViewProfile(ViewProfileRequest viewProfileRequest) {
        if(mViews!=null){
            mModel.processViewProfile (viewProfileRequest,this);
        }
    }



    @Override
    public void onViewProfileSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onViewProfileSuccess (result);
        }
    }

    @Override
    public void onViewProfileFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onViewProfileFailed (errorMsg);
        }
    }
}
