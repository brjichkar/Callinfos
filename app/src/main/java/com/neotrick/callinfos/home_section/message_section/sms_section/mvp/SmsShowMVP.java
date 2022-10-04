package com.neotrick.callinfos.home_section.message_section.sms_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.SmsShowRequest;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_response.Data;


public class SmsShowMVP {

    public interface SmsShowView extends MvpView {
        void onSmsShowSuccess(Data result);
        void onSmsShowFailed(String errorMsg);
    }

    public interface SmsShowPresenter{
        void onAttach(SmsShowView mView);
        void onDestroyView();

        void onSmsShow(SmsShowRequest smsshowRequest);
    }


    public interface SmsShowModel{
        void processSmsShow(SmsShowRequest smsshowRequest, OnSmsShowFinished onCallFinished);

        interface OnSmsShowFinished{
            void onSmsShowSuccess(Data result);
            void onSmsShowFailed(String errorMsg);
        }
    }
}
