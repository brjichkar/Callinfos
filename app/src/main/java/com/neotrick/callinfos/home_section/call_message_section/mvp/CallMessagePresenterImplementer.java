package com.neotrick.callinfos.home_section.call_message_section.mvp;

import com.neotrick.callinfos.home_section.call_message_section.model.call_meassage_response.Data;
import com.neotrick.callinfos.home_section.call_message_section.model.call_message_request.CallMessageRequest;




public class CallMessagePresenterImplementer implements CallMessageMVP.CallPresenter, CallMessageMVP.CallModel.OnCallFinished {
    private CallMessageMVP.CallView mViews;
    private CallMessageModelImplementer mModel=new CallMessageModelImplementer();

    public CallMessagePresenterImplementer(CallMessageMVP.CallView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(CallMessageMVP.CallView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onProcessCall(CallMessageRequest callMessageRequest) {
        if(mViews!=null){
            mModel.processCall (callMessageRequest,this);
        }
    }



    @Override
    public void onCallSuccess(Data result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onCallSuccess (result);
        }
    }

    @Override
    public void onCallFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onCallFailed (errorMsg);
        }
    }
}
