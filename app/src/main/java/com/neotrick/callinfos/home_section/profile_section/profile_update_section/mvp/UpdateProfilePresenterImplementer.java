package com.neotrick.callinfos.home_section.profile_section.profile_update_section.mvp;



import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_request.ProfileUpdateRequest;
import com.neotrick.callinfos.home_section.profile_section.profile_update_section.model.profile_update_response.Data;


public class UpdateProfilePresenterImplementer implements UpdateProfileMVP. UpdateProfilePresenter, UpdateProfileMVP. UpdateProfileModel.OnUpdateProfileFinished {
    private UpdateProfileMVP. UpdateProfileView mViews;
    private UpdateProfileModelImplementer mModel=new UpdateProfileModelImplementer();

    public UpdateProfilePresenterImplementer(UpdateProfileMVP.UpdateProfileView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(UpdateProfileMVP. UpdateProfileView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onProcessUpdateProfile(ProfileUpdateRequest profileUpdateRequest) {
        if(mViews!=null){
            mModel.processUpdateProfile (profileUpdateRequest,this);
        }
    }



    @Override
    public void onUpdateProfileSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUpdateProfileSuccess (result);
        }
    }

    @Override
    public void onUpdateProfileFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUpdateProfileFailed (errorMsg);
        }
    }
}
