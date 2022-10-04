package com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.WhatsappUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_response.Data;


public class WhatsappUpdateMVP {

    public interface WhatsappUpdateView extends MvpView {
        void onWhatsappUpdateSuccess(Data result);
        void onWhatsappUpdateFailed(String errorMsg);
    }

    public interface WhatsappUpdatePresenter{
        void onAttach(WhatsappUpdateView mView);
        void onDestroyView();

        void onWhatsappUpdate(WhatsappUpdateRequest whatsappUpdateRequest);
    }


    public interface WhatsappUpdateModel{
        void processWhatsappUpdate(WhatsappUpdateRequest whatsappUpdateRequest, OnWhatsappUpdateFinished onWhatsappUpdateFinished);

        interface OnWhatsappUpdateFinished{
            void onWhatsappUpdateSuccess(Data result);
            void onWhatsappUpdateFailed(String errorMsg);
        }
    }
}
