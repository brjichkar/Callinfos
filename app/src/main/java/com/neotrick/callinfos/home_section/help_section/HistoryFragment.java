package com.neotrick.callinfos.home_section.help_section;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neotrick.callinfos.R;
import com.neotrick.callinfos.app_preference_section.AppPreference;
import com.neotrick.callinfos.base_class_section.BaseFragment;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.ComplaintRequest;
import com.neotrick.callinfos.home_section.help_section.complaint_section.model.complaint_request.Complaintreqdata;

import com.neotrick.callinfos.home_section.help_section.history_section.model.history_request.HistoryRequest;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_request.Historyreqdata;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.Data;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.HistoryResponse;
import com.neotrick.callinfos.home_section.help_section.history_section.mvp.UserHistoryMVP;
import com.neotrick.callinfos.home_section.help_section.history_section.mvp.UserHistoryPresenterImplementer;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.ViewProfileRequest;
import com.neotrick.callinfos.home_section.profile_section.model.view_profile_request.Viewreqdata;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends BaseFragment implements View.OnClickListener, UserHistoryMVP.UserHistoryView
{

    RecyclerView rv_chat;

    AdapterHistoryChat mchatAdapter;
    ArrayList<Data> mOnlineShop=new ArrayList<> ();

    View rootView;
    private AppPreference mAppPreference;
    private UserHistoryMVP.UserHistoryPresenter mPresenter;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_history, container, false);
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


        rv_chat =  sampleView.findViewById(R.id.rv_chat);
        mchatAdapter = new AdapterHistoryChat(mOnlineShop,getActivity ());
        RecyclerView.LayoutManager mLayoutManagerone = new LinearLayoutManager(getActivity ());
        rv_chat.setLayoutManager(mLayoutManagerone);
        rv_chat.setItemAnimator(new DefaultItemAnimator());
        rv_chat.setAdapter(mchatAdapter);


        mAppPreference=new AppPreference(this.getActivity());
        mPresenter=new UserHistoryPresenterImplementer(this);

        HistoryRequest historyRequest = new HistoryRequest();
        Historyreqdata reqdata=new Historyreqdata();
        reqdata.setUserId(mAppPreference.getUserId());
        historyRequest.setJsondata(reqdata);
        mPresenter.onUserHistory(historyRequest);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idBtnsubmit: {


            }

        }
    }

    @Override
    public void onUserHistorySuccess(List<Data> result) {
        if(result.size ()>0){
            mOnlineShop.clear ();
            mOnlineShop.addAll (result);
            mchatAdapter.notifyDataSetChanged ();
        }
    }

    @Override
    public void onUserHistoryFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }
}