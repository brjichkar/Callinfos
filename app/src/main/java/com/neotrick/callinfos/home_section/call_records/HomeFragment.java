package com.neotrick.callinfos.home_section.call_records;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neotrick.callinfos.R;

import com.neotrick.callinfos.app_preference_section.AppPreference;
import com.neotrick.callinfos.base_class_section.BaseFragment;
import com.neotrick.callinfos.home_section.call_records.model.call_request.CallsRequest;
import com.neotrick.callinfos.home_section.call_records.model.call_request.Callsreqdata;
import com.neotrick.callinfos.home_section.call_records.model.call_response.Data;
import com.neotrick.callinfos.home_section.call_records.mvp.CallMVP;
import com.neotrick.callinfos.home_section.call_records.mvp.CallPresenterImplementer;


public class HomeFragment extends BaseFragment implements View.OnClickListener, CallMVP.CallView {


    LinearLayout mtoday,mweekly,mmonthly;
    TextView in_count1, in_count2, in_count3;
    TextView out_count1,out_count2, out_count3 ;

    View rootView;
    private CallMVP.CallPresenter mPresenter;
    private AppPreference mAppPreference;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_home, container, false);
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


        mtoday=rootView.findViewById(R.id.ll_today);
        mweekly = rootView.findViewById(R.id.ll_monthly);
        mmonthly = rootView.findViewById(R.id.ll_all);


        in_count1= rootView.findViewById(R.id.inc_count1);
        in_count2= rootView.findViewById(R.id.inc_count2);
        in_count3= rootView.findViewById(R.id.inc_count3);

        out_count1= rootView.findViewById(R.id.out_count1);
        out_count2= rootView.findViewById(R.id.out_count2);
        out_count3= rootView.findViewById(R.id.out_count3);


        mAppPreference=new AppPreference(this.getActivity());
        mPresenter=new CallPresenterImplementer(this);

        CallsRequest callsRequest = new CallsRequest();
        Callsreqdata callsreqdata=new Callsreqdata();
        callsreqdata.setMobile(mAppPreference.getPhoneNumber());
        callsreqdata.setToken(mAppPreference.getAccessToken());
        callsRequest.setJsondata(callsreqdata);
        if(isNetworkConnected()){
            showLoading();
            mPresenter.onProcessCall(callsRequest);
        }
        else{
            hideLoading();
            onError("Network issue");
        }

    }



    @Override
    public void onCallSuccess(Data result) {

        in_count1.setText(result.getDayIncoming().toString());
        out_count1.setText(result.getDayOutgoing().toString());
        in_count2.setText(result.getMonthIncoming().toString());
        out_count2.setText(result.getMonthOutgoing().toString());
        in_count3.setText(result.getMonthIncoming().toString());
        out_count3.setText(result.getMonthOutgoing().toString());
    }

    @Override
    public void onCallFailed(String errorMsg) {
        if(errorMsg.isEmpty ()){
            onError (getResources ().getString (R.string.some_error));
        }
        else {
            onError (errorMsg);
        }
    }

    @Override
    public void onClick(View view) {

    }
}