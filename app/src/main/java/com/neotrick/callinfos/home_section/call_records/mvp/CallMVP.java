package com.neotrick.callinfos.home_section.call_records.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.call_records.model.call_response.Data;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;

public class CallMVP {

    public interface CallView extends MvpView {
        void onCallSuccess(Data result);
        void onCallFailed(String errorMsg);
    }

    public interface CallPresenter{
        void onAttach(CallView mView);
        void onDestroyView();

        void onProcessCall(CallsRequest callsRequest);
    }


    public interface CallModel{
        void processCall(CallsRequest callsRequest, OnCallFinished onCallFinished);

        interface OnCallFinished{
            void onCallSuccess(Data result);
            void onCallFailed(String errorMsg);
        }
    }
}
