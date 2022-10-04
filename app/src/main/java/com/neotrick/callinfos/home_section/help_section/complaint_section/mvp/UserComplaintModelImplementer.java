package com.neotrick.callinfos.home_section.help_section.complaint_section.mvp;


import com.neotrick.callinfos.api_section.ApiClient;
import com.neotrick.callinfos.api_section.ApiInterface;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.ComplaintRequest;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_response.ComplaintResponse;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_request.SmsShowRequest;
import com.neotrick.callinfos.home_section.message_section.sms_section.model.sms_show_response.SmsShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserComplaintModelImplementer implements UserComplaintMVP.UserComplaintModel {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    @Override
    public void processUserComplaint(ComplaintRequest complaintRequest, OnUserComplaintFinished onUserComplaintFinished) {
        Call<ComplaintResponse> call = apiService.getComplaintDetails (complaintRequest);
        call.enqueue(new Callback<ComplaintResponse> () {
            @Override
            public void onResponse(Call<ComplaintResponse>call, Response<ComplaintResponse> response) {
                if (response.code() == 200 ) {
                    if (response.body()!=null && response.body ().getData()!=null) {
                        onUserComplaintFinished.onUserComplaintSuccess (response.body().getData());
                    } else {
                        onUserComplaintFinished.onUserComplaintFailed ("Invalid Complaint Details");
                    }
                } else {
                    onUserComplaintFinished.onUserComplaintFailed ("Invalid Complaint Details");
                }
            }

            @Override
            public void onFailure(Call<ComplaintResponse>call, Throwable t) {
                onUserComplaintFinished.onUserComplaintFailed ("");
            }
        });
    }
}
