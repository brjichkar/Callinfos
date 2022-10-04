package com.neotrick.callinfos.home_section.message_section.sms_update_section.mvp;



import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.SmsUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_response.Data;


public class SmsUpdatePresenterImplementer implements SmsUpdateMVP.SmsUpdatePresenter, SmsUpdateMVP.SmsUpdateModel.OnSmsUpdateFinished {
    private SmsUpdateMVP.SmsUpdateView mViews;
    private SmsUpdateModelImplementer mModel=new SmsUpdateModelImplementer();

    public SmsUpdatePresenterImplementer(SmsUpdateMVP.SmsUpdateView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(SmsUpdateMVP.SmsUpdateView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onSmsUpdate(SmsUpdateRequest smsUpdateRequest) {
        if(mViews!=null){
            mModel.processSmsUpdate (smsUpdateRequest,this);
        }
    }



    @Override
    public void onSmsUpdateSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onSmsUpdateSuccess (result);
        }
    }

    @Override
    public void onSmsUpdateFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onSmsUpdateFailed (errorMsg);
        }
    }
}