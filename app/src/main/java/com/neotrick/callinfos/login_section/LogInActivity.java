package com.neotrick.callinfos.login_section;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.neotrick.callinfos.otp_section.OTPActivity;
import com.neotrick.callinfos.R;
import com.neotrick.callinfos.app_preference_section.AppPreference;
import com.neotrick.callinfos.base_class_section.BaseActivity;

import com.neotrick.callinfos.login_section.model.login_request.LoginReqdata;
import com.neotrick.callinfos.login_section.model.login_request.LoginRequest;
import com.neotrick.callinfos.login_section.model.login_response.Data;
import com.neotrick.callinfos.login_section.mvp.LoginMVP;
import com.neotrick.callinfos.login_section.mvp.LoginPresenterImplementer;

public class LogInActivity extends BaseActivity  implements View.OnClickListener, LoginMVP.LoginView {

    // variable for FirebaseAuth class
    private FirebaseAuth mAuth;
    private EditText edtPhone, edtOTP;
    private Button verifyOTPBtn, generateOTPBtn;
    public  String user_id;
    public String mobileid;
    private Boolean isDisabled=true;

    private AppPreference mAppPreference;
    private LoginMVP.LoginPresenter mPresenter;


    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        edtPhone = findViewById(R.id.idEdtPhoneNumber);

        mAppPreference=new AppPreference(this);
        mPresenter=new LoginPresenterImplementer(this);

        generateOTPBtn = findViewById(R.id.idBtnGetOtp);
        generateOTPBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                LoginRequest loginRequest = new LoginRequest();
                LoginReqdata logReq=new LoginReqdata();
                logReq.setMobile(edtPhone.getText ().toString ());
//                if (!isValidMobile(mobileid)){
//                    edtPhone.setError("No User is Available");
//                }
                loginRequest.setLoginRequest(logReq);

                mPresenter.onProcessLogin(loginRequest);

            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume ();
        mPresenter.onAttach (this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        mPresenter.onDestroyView ();

    }

//    private boolean SetValidation() {
//        boolean status;
//        if (edtPhone.getText ().toString ().trim ().isEmpty ()) {
//            status = false;
//            Toast.makeText (getApplicationContext (), "No User is Available", Toast.LENGTH_SHORT).show ();
//        } else {
//            status = true;
//        }
//        return status;
//    }

    private boolean isValidMobile(String mobileid) {
        if (!TextUtils.isEmpty(mobileid)) {
            return Patterns.PHONE.matcher(mobileid).matches();
        }
        return false;
    }
    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }


    @Override
    public void onLoginSuccess(Data result) {

        Intent logIntent = new Intent(LogInActivity.this, OTPActivity.class);
        Bundle b =new Bundle();
        b.putString("mobile",edtPhone.getText().toString());
        mAppPreference.setUserId(result.getUserId());
        logIntent.putExtras(b);
        startActivity(logIntent);
        finish();
      //
    }

    @Override
    public void onLoginFailed(String errorMsg) {

        if(errorMsg.equals ("")){
            errorMsg=getResources ().getString (R.string.some_error);
        }
        onError (errorMsg);
    }
}




