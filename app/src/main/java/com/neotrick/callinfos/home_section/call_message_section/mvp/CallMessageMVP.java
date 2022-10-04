package com.neotrick.callinfos.home_section.call_message_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.call_message_section.model.call_meassage_response.Data;
import com.neotrick.callinfos.home_section.call_message_section.model.call_message_request.CallMessageRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;


public class CallMessageMVP {

    public interface CallView extends MvpView {
        void onCallSuccess(Data result);
        void onCallFailed(String errorMsg);
    }

    public interface CallPresenter{
        void onAttach(CallView mView);
        void onDestroyView();

        void onProcessCall(CallMessageRequest callMessageRequest);
    }


    public interface CallModel{
        void processCall(CallMessageRequest callMessageRequest, OnCallFinished onCallFinished);

        interface OnCallFinished{
            void onCallSuccess(Data result);
            void onCallFailed(String errorMsg);
        }
    }
}
