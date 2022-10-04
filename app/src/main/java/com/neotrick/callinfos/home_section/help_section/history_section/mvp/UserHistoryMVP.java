package com.neotrick.callinfos.home_section.help_section.history_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;

import com.neotrick.callinfos.home_section.help_section.history_section.model.history_request.HistoryRequest;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.Data;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.HistoryResponse;

import java.util.List;


public class UserHistoryMVP {

    public interface UserHistoryView extends MvpView {
        void onUserHistorySuccess(List<Data> result);
        void onUserHistoryFailed(String errorMsg);
    }

    public interface UserHistoryPresenter{
        void onAttach(UserHistoryView mView);
        void onDestroyView();

        void onUserHistory(HistoryRequest historyRequest);
    }


    public interface UserHistoryModel{
        void processUserHistory(HistoryRequest historyRequest, OnUserHistoryFinished onUserHistoryFinished);

        interface OnUserHistoryFinished{
            void onUserHistorySuccess(List<Data> result);
            void onUserHistoryFailed(String errorMsg);
        }
    }
}
