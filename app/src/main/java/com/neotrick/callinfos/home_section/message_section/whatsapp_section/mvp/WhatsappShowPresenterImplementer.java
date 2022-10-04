package com.neotrick.callinfos.home_section.message_section.whatsapp_section.mvp;


import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_request.WhatsappShowRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_response.Data;


public class WhatsappShowPresenterImplementer implements WhatsappShowMVP.WhatsappShowPresenter, WhatsappShowMVP.WhatsappShowModel.OnWhatsappShowFinished {
    private WhatsappShowMVP.WhatsappShowView mViews;
    private WhatsappShowModelImplementer mModel=new WhatsappShowModelImplementer();

    public WhatsappShowPresenterImplementer(WhatsappShowMVP.WhatsappShowView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(WhatsappShowMVP.WhatsappShowView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onProcessWhatsappShow(WhatsappShowRequest whatsappShowRequest) {
        if(mViews!=null){
            mModel.processWhatsappShow (whatsappShowRequest,this);
        }
    }



    @Override
    public void onWhatsappShowSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onWhatsappShowSuccess (result);
        }
    }

    @Override
    public void onWhatsappShowFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onWhatsappShowFailed (errorMsg);
        }
    }
}
