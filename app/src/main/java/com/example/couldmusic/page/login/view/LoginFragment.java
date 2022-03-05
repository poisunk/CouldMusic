package com.example.couldmusic.page.login.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.couldmusic.R;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.page.login.contract.LoginContract;
import com.example.couldmusic.page.login.model.LoginModel;
import com.example.couldmusic.page.login.presenter.LoginPresenter;
import com.example.couldmusic.page.main.fragment.MainFragment;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.Utility;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author:created by $[poisunk]
 * 邮箱：1714480752@qq.com
 */
public class LoginFragment extends Fragment implements View.OnClickListener, LoginContract.LoginView {

    @SuppressLint("StaticFieldLeak")
    private static LoginFragment loginFragment=new LoginFragment();

    private final String ARG_LOGIN_BEAN="loginBean";

    private Button mButtonBack;
    private Button mButtonLogin;


    private EditText mEditPhone;
    private EditText mEditPassword;

    private LoginPresenter presenter;

    public static LoginFragment newInstance() {
        loginFragment=new LoginFragment();
        return loginFragment;
    }

    public static LoginFragment getInstance(){
        return loginFragment;
    }

    public LoginFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new LoginPresenter(this,new LoginModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initView(View view){
        mButtonBack=(Button) view.findViewById(R.id.fragment_login_back_button);
        mButtonLogin=(Button) view.findViewById(R.id.fragment_login_login_button);
        mEditPassword=(EditText) view.findViewById(R.id.fragment_login_password_edit);
        mEditPhone=(EditText) view.findViewById(R.id.fragment_login_phone_edit);
    }

    private void initEvent(){
        mButtonLogin.setOnClickListener(this);
        mButtonBack.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_login_back_button:
                back();
                break;
            case R.id.fragment_login_login_button:
                //手机号与密码不能为空
                if(!TextUtils.isEmpty(mEditPassword.getText()) &&!TextUtils.isEmpty(mEditPhone.getText())){
                    String password=mEditPassword.getText().toString().trim();
                    String phone=mEditPhone.getText().toString().trim();
                    presenter.login(phone,password);
                }else{
                    Toast.makeText(requireContext(),"手机号或密码不能为空!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * 转换页面到主界面
     */
    public void back(){
        FragmentManager fragmentManager=requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStackImmediate();
    }

    @Override
    public void onLoginSucceed(LoginBean loginBean) {
        FragmentManager fm= requireActivity().getSupportFragmentManager();
        SharedPreferences.Editor editor= PreferenceManager.
                getDefaultSharedPreferences(requireContext()).edit();
        editor.putString(ARG_LOGIN_BEAN,new Gson().toJson(loginBean));
        editor.apply();
        back();
    }

    @Override
    public void onLoginFailed(String failMessage) {
        Toast.makeText(getContext(),failMessage,Toast.LENGTH_SHORT).show();
    }
}
