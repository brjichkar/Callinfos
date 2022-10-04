package com.neotrick.callinfos.home_section.message_section.whatsapp_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_request.WhatsappShowRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_section.model.whatsapp_show_response.Data;


public class WhatsappShowMVP {

    public interface WhatsappShowView extends MvpView {
        void onWhatsappShowSuccess(Data result);
        void onWhatsappShowFailed(String errorMsg);
    }

    public interface WhatsappShowPresenter{
        void onAttach(WhatsappShowView mView);
        void onDestroyView();

        void onProcessWhatsappShow(WhatsappShowRequest whatsappShowRequest);
    }


    public interface WhatsappShowModel{
        void processWhatsappShow(WhatsappShowRequest whatsappShowRequest, OnWhatsappShowFinished onWhatsappShowFinished);

        interface OnWhatsappShowFinished{
            void onWhatsappShowSuccess(Data result);
            void onWhatsappShowFailed(String errorMsg);
        }
    }
}
