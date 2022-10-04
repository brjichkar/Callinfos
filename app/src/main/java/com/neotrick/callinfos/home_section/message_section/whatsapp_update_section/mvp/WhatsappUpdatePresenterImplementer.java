package com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.mvp;



;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.WhatsappUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_response.Data;


public class WhatsappUpdatePresenterImplementer implements WhatsappUpdateMVP.WhatsappUpdatePresenter, WhatsappUpdateMVP.WhatsappUpdateModel.OnWhatsappUpdateFinished {
    private WhatsappUpdateMVP.WhatsappUpdateView mViews;
    private WhatsappUpdateModelImplementer mModel=new WhatsappUpdateModelImplementer();

    public WhatsappUpdatePresenterImplementer(WhatsappUpdateMVP.WhatsappUpdateView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(WhatsappUpdateMVP.WhatsappUpdateView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }

    @Override
    public void onWhatsappUpdate(WhatsappUpdateRequest whatsappUpdateRequest) {
        if(mViews!=null){
            mModel.processWhatsappUpdate (whatsappUpdateRequest,this);
        }
    }


    @Override
    public void onWhatsappUpdateSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onWhatsappUpdateSuccess (result);
        }
    }

    @Override
    public void onWhatsappUpdateFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onWhatsappUpdateFailed (errorMsg);
        }
    }
}