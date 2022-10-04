package com.neotrick.callinfos.home_section.message_section.sms_section.mvp;



import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.SmsShowRequest;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_response.Data;


public class SmsShowPresenterImplementer implements SmsShowMVP.SmsShowPresenter, SmsShowMVP.SmsShowModel.OnSmsShowFinished {
    private SmsShowMVP.SmsShowView mViews;
    private SmsShowModelImplementer mModel=new SmsShowModelImplementer();

    public SmsShowPresenterImplementer(SmsShowMVP.SmsShowView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(SmsShowMVP.SmsShowView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onSmsShow(SmsShowRequest smsShowRequest) {
        if(mViews!=null){
            mModel.processSmsShow (smsShowRequest,this);
        }
    }



    @Override
    public void onSmsShowSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onSmsShowSuccess (result);
        }
    }

    @Override
    public void onSmsShowFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onSmsShowFailed (errorMsg);
        }
    }
}