package com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.mvp;

import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_request.WhatsappImageRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_image_section.model.whatsapp_image_response.Data;

import java.io.File;


public class WhatsappImageMVP {

    public interface  WhatsappImageView extends MvpView {
        void onWhatsappImageSuccess(String result);
        void onWhatsappImageFailed(String errorMsg);


    }


    public interface WhatsappImagePresenter {
        void onAttach(WhatsappImageView mView);
        void onDestroyView();

        void onProcessWhatsappImage(String userId, File imageFile);


    }


    public interface WhatsappImageModel {
        void processWhatsappImage(String userId, File imageFile, OnWhatsappImageFinished onWhatsappImageFinished);

        interface OnWhatsappImageFinished {
            void onWhatsappImageSuccess(Data result);

            void onWhatsappImageFailed(String errorMsg);
        }


    }


}
