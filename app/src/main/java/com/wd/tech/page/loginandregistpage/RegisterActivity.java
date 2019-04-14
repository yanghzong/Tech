package com.wd.tech.page.loginandregistpage;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.loginandregistpage.bean.UserRegisterBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;
import com.wd.tech.utils.encryptionverificationutil.RegularVerification;
import com.wd.tech.utils.encryptionverificationutil.RsaCoder;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.Map;

/**
 * 用户注册
 *
 *
 * */

public class RegisterActivity extends BaseActivity implements View.OnClickListener,IView {

    private EditText edRegisterNikename;
    private EditText edRegisterNiPhone;
    private EditText edRegisterNiPwd;
    private TextView btnRegistNote;
    private Button btnRegist;
    private String registerNikeName;
    private String registerPhone;
    private String registerNikePwd;
    private Presenter presenter;
    private Type typeUserRegister;

    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    public void initViews() {
        //初始化Presenter对象
        presenter = new Presenter();
        presenter.attach(this);

        //初始化控件
        edRegisterNikename = findViewById(R.id.ed_register_nikename);//用户名
        edRegisterNiPhone = findViewById(R.id.ed_register_phone);//手机号
        edRegisterNiPwd = findViewById(R.id.ed_register_pwd);//密码
        btnRegistNote = findViewById(R.id.btn_regist_note);//短信注册
        btnRegistNote.setOnClickListener(this);
        btnRegist = findViewById(R.id.btn_regist);//注册按钮
        btnRegist.setOnClickListener(this);

    }

    /**
     * 点击事件
     *
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ed_register_nikename://短信注册

                break;

            case R.id.btn_regist://注册按钮
                //1 获取用户输入的内容
                registerNikeName = edRegisterNikename.getText().toString().trim();//用户名
                registerPhone = edRegisterNiPhone.getText().toString().trim();//手机号
                registerNikePwd = edRegisterNiPwd.getText().toString().trim();//密码
                try {
                    //2 首先判断用户输入的是否合法
                    if(TextUtils.isEmpty(registerNikeName)){//名称
                        showShortToast("用户名不能为空");
                        break;
                    }else if(!RegularVerification.JudgePhone(registerPhone) && !registerPhone.equals("")){//手机号
                        showShortToast("输入的手机号不合法");
                        break;
                    }else if(!RegularVerification.JudgePassword(registerNikePwd) && !registerPhone.equals("")){//密码
                        showShortToast("密码要以字母开头，长度在6~18之间，只能包含字母、数字和下划线！");
                        break;
                    }else{
                        //2 用户注册泛型
                        typeUserRegister = new TypeToken<UserRegisterBean>(){}.getType();
                        //3 进行密码加密
                        String signPwd = RsaCoder.encryptByPublicKey(registerNikePwd);
                        //4 进行 map封装
                        Map map = getMap("phone", registerPhone,"nickName",registerNikeName,"pwd",signPwd);
                        //5 调用Presenter进行请求
                        presenter.doPostMapP(API.APIUserRegisterUrl,map,typeUserRegister);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }
    }

    /**
     * 5 初始化数据
     *
     * */
    @Override
    protected void initData() {
        super.initData();



    }

    /**
     * 6.处理事件
     *   比如点击事件等等
     *   非必须
     * */
    @Override
    protected void setListener() {
        super.setListener();

    }

    /**
     * 7.其他操作
     *    比如实现网络操作等
     *    非必须
     * */
    @Override
    protected void setMoreAction() {
        super.setMoreAction();

    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    protected int getContentViewID() {
        return R.layout.activity_register;
    }

    /**
     * 成功的方法
     *
     * */
    @Override
    public void onSuccessIV(Object o) {
       //注册
       if(o instanceof UserRegisterBean){
           UserRegisterBean userRegisterBean = (UserRegisterBean) o;
           if(userRegisterBean!=null){
               String status = userRegisterBean.getStatus();
               String message = userRegisterBean.getMessage();
               Log.i("注册打印", "onSuccessIV: "+status+"=========="+message);
               showShortToast(message);
           }
       }
    }

    /**
     * 失败方法
     * */
    @Override
    public void onFailed(String message) {
        if(message!=null){
            showShortToast(message);
        }
    }


    /**
     * 销毁
     *
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.datach();
        }
    }

}
