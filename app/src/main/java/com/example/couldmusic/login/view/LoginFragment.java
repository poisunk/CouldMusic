package com.example.couldmusic.login.view;

import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.couldmusic.base.BaseFragment;
import com.example.couldmusic.base.BasePresenter;
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.login.contract.LoginContract;
import com.example.couldmusic.login.presenter.LoginPresenter;
import com.example.couldmusic.main.fragment.MainFragment;

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {


    private Button mButtonBack;
    private Button mButtonLogin;


    private EditText mEditPhone;
    private EditText mEditPassword;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment(){

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_login_back_button:
                backToMain(new MainFragment());
                break;
            case R.id.fragment_login_login_button:
                if(!TextUtils.isEmpty((mEditPassword.getText())) &&!TextUtils.isEmpty((mEditPhone.getText()))){
                    String password=mEditPassword.getText().toString();
                    String phone=mEditPhone.getText().toString();
                    mPresenter.login(phone,password);
                }else{
                    Toast.makeText(mContext,"手机号或密码不能为空!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {




        backToMain(new MainFragment());
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(mContext,"登录失败!请检查您输入的手机号与密码",Toast.LENGTH_SHORT).show();
    }


    /**
     * 转换页面到主界面
     */
    public void backToMain(Fragment fragment){
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.included_interface,fragment);
        transaction.commit();
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }
}
