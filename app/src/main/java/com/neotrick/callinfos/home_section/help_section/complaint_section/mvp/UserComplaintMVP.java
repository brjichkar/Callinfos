package com.neotrick.callinfos.home_section.help_section.complaint_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.ComplaintRequest;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_response.Data;


public class UserComplaintMVP {

    public interface UserComplaintView extends MvpView {
        void onUserComplaintSuccess(Data result);
        void onUserComplaintFailed(String errorMsg);
    }

    public interface UserComplaintPresenter{
        void onAttach(UserComplaintView mView);
        void onDestroyView();

        void onUserComplaint(ComplaintRequest complaintRequest);
    }


    public interface UserComplaintModel{
        void processUserComplaint(ComplaintRequest complaintRequest, OnUserComplaintFinished onUserComplaintFinished);

        interface OnUserComplaintFinished{
            void onUserComplaintSuccess(Data result);
            void onUserComplaintFailed(String errorMsg);
        }
    }
}
