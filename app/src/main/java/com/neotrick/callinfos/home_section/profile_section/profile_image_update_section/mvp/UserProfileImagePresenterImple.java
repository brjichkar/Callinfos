package com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.mvp;


import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.model.profile_image_request.ProfileImageRequest;
import com.neotrick.callinfos.home_section.profile_section.profile_image_update_section.model.profile_image_response.Data;

import java.io.File;

public class UserProfileImagePresenterImple implements UserProfileImageMVP.UserProfileImagePresenter, UserProfileImageMVP.UserProfileImageModel.OnUserProfileImageFinished {
    private UserProfileImageMVP.UserProfileImageView mViews;
    private UserProfileImageModelImple mModel=new UserProfileImageModelImple();

    public UserProfileImagePresenterImple(UserProfileImageMVP.UserProfileImageView mViews)
    {
        this.mViews = mViews;
    }



    @Override
    public void onAttach(UserProfileImageMVP.UserProfileImageView mView)
    {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }

    @Override
    public void onProcessUserProfileImage(String userId, File imageFile) {
        if(mViews!=null){
            if(mViews.isNetworkConnected ()){
                mViews.showLoading ();
                mModel.processUserProfileImage (userId,imageFile,this);
            }
            else{
                mViews.hideLoading ();
                mViews.onError ("Please connect with Internet.");
            }
        }
    }

    @Override
    public void onUserProfileImageSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUserProfileImageSuccess ((result));
        }

    }

    @Override
    public void onUserProfileImageFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUserProfileImageFailed (errorMsg);
        }

    }
}
