package com.example.couldmusic.page.login.presenter;

import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.page.login.contract.LoginContract;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class LoginPresenter implements LoginContract.LoginPresenter,LoginContract.LoginModel.onFinishedListener {

    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel model;

    private String REQUEST_FAIL="网络请求失败!";
    private String LOGIN_FAIL="登录失败!请检查您输入的手机号与密码";

    public LoginPresenter(LoginContract.LoginView loginView, LoginContract.LoginModel model){
        this.loginView=loginView;
        this.model=model;
    }

    @Override
    public void login(String phone, String password) {
        model.login(phone,password,this);
    }

    @Override
    public void onLoginSucceed(LoginBean loginBean) {
        loginView.onLoginSucceed(loginBean);
    }

    @Override
    public void onLoginFailed() {
        loginView.onLoginFailed(LOGIN_FAIL);
    }

    @Override
    public void onRequestFailed() {
        loginView.onLoginFailed(REQUEST_FAIL);
    }
}
