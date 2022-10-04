package com.neotrick.callinfos.home_section.call_records.mvp;

import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_response.Data;


public class CallPresenterImplementer implements CallMVP.CallPresenter, CallMVP.CallModel.OnCallFinished {
    private CallMVP.CallView mViews;
    private CallModelImplementer mModel=new CallModelImplementer();

    public CallPresenterImplementer(CallMVP.CallView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(CallMVP.CallView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onProcessCall(CallsRequest callsRequest) {
        if(mViews!=null){
            mModel.processCall (callsRequest,this);
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
