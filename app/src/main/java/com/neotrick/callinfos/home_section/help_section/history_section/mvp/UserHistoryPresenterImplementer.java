package com.neotrick.callinfos.home_section.help_section.history_section.mvp;




import com.neotrick.callinfos.home_section.help_section.history_section.model.history_request.HistoryRequest;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.Data;

import java.util.List;

public class UserHistoryPresenterImplementer implements UserHistoryMVP.UserHistoryPresenter, UserHistoryMVP.UserHistoryModel.OnUserHistoryFinished {
    private UserHistoryMVP.UserHistoryView mViews;
    private UserHistoryModelImplementer mModel=new UserHistoryModelImplementer();

    public UserHistoryPresenterImplementer(UserHistoryMVP.UserHistoryView mViews) {
        this.mViews = mViews;
    }

    @Override
    public void onAttach(UserHistoryMVP.UserHistoryView mView) {
        mViews=mView;
    }

    @Override
    public void onDestroyView() {
        mViews=null;
    }


    @Override
    public void onUserHistory(HistoryRequest historyRequest) {
        if(mViews!=null){
            mModel.processUserHistory(historyRequest,this);
        }
    }



    @Override
    public void onUserHistorySuccess(List<Data> result) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUserHistorySuccess (result);
        }
    }

    @Override
    public void onUserHistoryFailed(String errorMsg) {
        if(mViews!=null){
            mViews.hideLoading ();
            mViews.onUserHistoryFailed (errorMsg);
        }
    }
}