package com.example.couldmusic.login.presenter;

import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.login.contract.LoginContract;
import com.example.couldmusic.login.model.LoginModel;

public class LoginPresenter extends LoginContract.Presenter {


    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        this.mModel = new LoginModel();
    }

    @Override
    public void login(String phone, String password) {
        LoginBean loginBean=null;
        loginBean=mModel.login(phone,password);
        if(loginBean==null){
            mView.onLoginFail();
        }else{
            mView.onLoginSuccess(loginBean);
        }
    }
}
