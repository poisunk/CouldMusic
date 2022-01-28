package com.example.couldmusic.login.contract;

import com.example.couldmusic.base.BaseModel;
import com.example.couldmusic.base.BasePresenter;
import com.example.couldmusic.base.BaseView;
import com.example.couldmusic.bean.LoginBean;

public interface LoginContract {
    interface View extends BaseView{
        void onLoginSuccess(LoginBean bean);

        void onLoginFail();
    }

    interface Model extends BaseModel {
        LoginBean login(String phone, String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String phone, String password);
    }
}
