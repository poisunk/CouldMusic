package com.example.couldmusic.login.view;

import android.annotation.SuppressLint;
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
import com.example.couldmusic.bean.LoginBean;
import com.example.couldmusic.main.fragment.MainFragment;
import com.example.couldmusic.util.HttpUtil;
import com.example.couldmusic.util.ToastUtil;
import com.example.couldmusic.util.Utility;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginFragment extends Fragment implements View.OnClickListener{


    private Button mButtonBack;
    private Button mButtonLogin;


    private EditText mEditPhone;
    private EditText mEditPassword;


    public static LoginFragment newInstance() {
        LoginFragment fragment=new LoginFragment();
        return fragment;
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_login_back_button:
                backToMain(new MainFragment());
                break;
            case R.id.fragment_login_login_button:
                if(!TextUtils.isEmpty(mEditPassword.getText()) &&!TextUtils.isEmpty(mEditPhone.getText())){
                    String password=mEditPassword.getText().toString().trim();
                    String phone=mEditPhone.getText().toString().trim();
                    loginRequest(phone,password);
                }else{
                    ToastUtil.show("手机号或密码不能为空!");
                }
                break;
        }
    }

    public void loginRequest(String phone,String password){
        String address="http://redrock.udday.cn:2022/login/cellphone?phone="+phone+"&password="+password;
        HttpUtil.sendOkHttpRequest(address,
                new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"网络请求失败!",Toast.LENGTH_SHORT).show();
                            }
                        });
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        final String responseText= Objects.requireNonNull(response.body()).string();
                        final LoginBean loginBean = Utility.handleLoginByPhone(responseText);
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(loginBean!=null&&loginBean.getCode()==200){
                                    FragmentManager fm= requireActivity().getSupportFragmentManager();
                                    Fragment fragment=fm.findFragmentById(R.id.fragment_content_drawer_layout);
                                    if (fragment==null){
                                        fragment=new MainFragment();
                                    }
                                    /**
                                     将获取到的数据传回MainFragment中
                                     */
                                    Bundle args=new Bundle();
                                    args.putSerializable("loginBean",loginBean);
                                    fragment.setArguments(args);
                                    backToMain(fragment);
                                }else{
                                    Toast.makeText(getContext(),"登录失败!请检查您输入的手机号与密码",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
    }
    /**
     * 转换页面到主界面
     */
    public void backToMain(Fragment fragment){
        FragmentManager fragmentManager=requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.add(R.id.included_interface,fragment);
        transaction.remove(this);
        transaction.commit();
    }

}
