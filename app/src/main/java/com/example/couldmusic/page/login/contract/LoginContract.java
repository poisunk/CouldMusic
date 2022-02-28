package com.example.couldmusic.page.login.contract;

import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.page.search.view.SearchResultFragment;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public interface LoginContract {

    interface LoginView{
        void onLoginSucceed(LoginBean loginBean);

        void onLoginFailed(String failMessage);
    }

    interface LoginModel{

        interface onFinishedListener {
            void onLoginSucceed(LoginBean loginBean);

            void onLoginFailed();

            void onRequestFailed();
        }

        void login(String phone, String password,final onFinishedListener listener);

    }

    interface LoginPresenter{

        void login(String phone, String password);

    }
}
