package com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.mvp;


import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_request.WhatsappImageRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_response.Data;

import java.io.File;


public class WhatsappImagePresenterImple implements WhatsappImageMVP.WhatsappImagePresenter, WhatsappImageMVP.WhatsappImageModel.OnWhatsappImageFinished {
    private WhatsappImageMVP.WhatsappImageView mViews;
    private WhatsappImageModelImple mModel=new WhatsappImageModelImple();

    public WhatsappImagePresenterImple(WhatsappImageMVP.WhatsappImageView mViews)
    {
        this.mViews = mViews;
    }



    @Override
    public void onAttach(WhatsappImageMVP.WhatsappImageView mView)
    {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }

    @Override
    public void onProcessWhatsappImage(String userId, File imageFile) {
        if(mViews!=null){
            if(mViews.isNetworkConnected ()){
                mViews.showLoading ();
                mModel.processWhatsappImage (userId,imageFile,this);
            }
            else{
                mViews.hideLoading ();
                mViews.onError ("Please connect with Internet.");
            }
        }
    }


    @Override
    public void onWhatsappImageSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onWhatsappImageSuccess (String.valueOf(result));
        }

    }

    @Override
    public void onWhatsappImageFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onWhatsappImageFailed (errorMsg);
        }
    }


}
