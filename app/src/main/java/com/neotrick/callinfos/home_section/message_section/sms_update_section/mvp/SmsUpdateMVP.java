package com.neotrick.callinfos.home_section.message_section.sms_update_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_request.SmsUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.sms_update_section.model.sms_update_response.Data;


public class SmsUpdateMVP {

    public interface SmsUpdateView extends MvpView {
        void onSmsUpdateSuccess(Data result);
        void onSmsUpdateFailed(String errorMsg);
    }

    public interface SmsUpdatePresenter{
        void onAttach(SmsUpdateView mView);
        void onDestroyView();

        void onSmsUpdate(SmsUpdateRequest smsUpdateRequest);
    }


    public interface SmsUpdateModel{
        void processSmsUpdate(SmsUpdateRequest smsUpdateRequest, OnSmsUpdateFinished onSmsUpdateFinished);

        interface OnSmsUpdateFinished{
            void onSmsUpdateSuccess(Data result);
            void onSmsUpdateFailed(String errorMsg);
        }
    }
}
