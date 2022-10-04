package com.neotrick.callinfos.home_section.help_section;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neotrick.callinfos.R;
import com.neotrick.callinfos.app_preference_section.AppPreference;
import com.neotrick.callinfos.base_class_section.BaseFragment;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.ComplaintRequest;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.Complaintreqdata;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_response.Data;
import com.neotrick.callinfos.home_section.help_section.complaint_section.mvp.UserComplaintMVP;
import com.neotrick.callinfos.home_section.help_section.complaint_section.mvp.UserComplaintPresenterImplementer;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.WhatsappUpdateRequest;
import com.neotrick.callinfos.home_section.message_section.whatsapp_update_section.model.whatsapp_update_request.Whatsappupdatereqdata;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.Viewreqdata;
import com.neotrick.callinfos.login_section.LogInActivity;


public class HelpFragment extends BaseFragment implements View.OnClickListener, UserComplaintMVP.UserComplaintView
{

        EditText idEdtcomplaint;
        Button idBtnsubmit;
          ImageView iv_webview;
        TextView tv_logout;
        RelativeLayout rl_history;


        View rootView;
        private AppPreference mAppPreference;
        private UserComplaintMVP.UserComplaintPresenter mPresenter;

    public HelpFragment() {
            // Required empty public constructor
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView=inflater.inflate(R.layout.fragment_help, container, false);
            setData(rootView);
            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume ();
            mPresenter.onAttach (this);


        }

        @Override
        public void onDestroy() {
            super.onDestroy ();
            mPresenter.onDestroyView ();

        }

        private void setData(View sampleView){

            idEdtcomplaint=rootView.findViewById(R.id.idEdtcomplaint);
            idBtnsubmit = rootView.findViewById(R.id.idBtnsubmit);
            rl_history = rootView.findViewById(R.id.rl_history);
            tv_logout= rootView.findViewById(R.id.tv_logout);
            iv_webview =rootView.findViewById(R.id.iv_webview);

            idBtnsubmit.setOnClickListener(this);
            iv_webview.setOnClickListener(this);
            tv_logout.setOnClickListener(this);
            rl_history.setOnClickListener(this);


            mAppPreference=new AppPreference(this.getActivity());
            mPresenter=new UserComplaintPresenterImplementer(this);



        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.rl_history: {
                    Fragment fragment = new HistoryFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    break;
                }
                case R.id.idBtnsubmit: {

                    ComplaintRequest complaintRequest = new ComplaintRequest();
                    Complaintreqdata reqdata = new Complaintreqdata();
                    reqdata.setUserId(mAppPreference.getUserId());
                    reqdata.setMessage(idEdtcomplaint.getText().toString());
                    complaintRequest.setJsondata(reqdata);
                    mPresenter.onUserComplaint(complaintRequest);
                    showMessage("Message Submitted Successfully");
                }
                break;
                case R.id.tv_logout:{
                    mAppPreference.clearAllPreferences();
                    Intent intent = new Intent(getActivity(), LogInActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(intent);
                }
                break;

                case R.id.iv_webview: {
                    Intent intent = new Intent(getActivity(), WebviewActivity.class);
                    getActivity().startActivity(intent);
                    }
                break;
            }

        }



    @Override
    public void onUserComplaintSuccess(Data result) {
        idEdtcomplaint.setText("");
    }

    @Override
    public void onUserComplaintFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }
}