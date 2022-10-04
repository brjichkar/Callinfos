package com.neotrick.callinfos.home_section.help_section.complaint_section.mvp;



import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.ComplaintRequest;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_response.Data;

public class UserComplaintPresenterImplementer implements UserComplaintMVP.UserComplaintPresenter, UserComplaintMVP.UserComplaintModel.OnUserComplaintFinished {
    private UserComplaintMVP.UserComplaintView mViews;
    private UserComplaintModelImplementer mModel=new UserComplaintModelImplementer();

    public UserComplaintPresenterImplementer(UserComplaintMVP.UserComplaintView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(UserComplaintMVP.UserComplaintView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onUserComplaint(ComplaintRequest complaintRequest) {
        if(mViews!=null){
            mModel.processUserComplaint(complaintRequest,this);
        }
    }



    @Override
    public void onUserComplaintSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUserComplaintSuccess (result);
        }
    }

    @Override
    public void onUserComplaintFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUserComplaintFailed (errorMsg);
        }
    }
}