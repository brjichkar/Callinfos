package com.neotrick.callinfos.login_section.mvp;


import com.neotrick.callinfos.base_class_section.MvpView;


import com.neotrick.callinfos.login_section.model.login_request.LoginRequest;
import com.neotrick.callinfos.login_section.model.login_response.Data;

public class LoginMVP {

    public interface LoginView extends MvpView {
        void onLoginSuccess(Data result);
        void onLoginFailed(String errorMsg);
    }

    public interface LoginPresenter{
        void onAttach(LoginView mView);
        void onDestroyView();

        void onProcessLogin(LoginRequest loginRequest);
    }


    public interface LoginModel{
        void processLogin(LoginRequest loginRequest, OnLoginFinished onLoginFinished);

        interface OnLoginFinished{
            void onLoginSuccess(Data result);
            void onLoginFailed(String errorMsg);
        }
    }
}
