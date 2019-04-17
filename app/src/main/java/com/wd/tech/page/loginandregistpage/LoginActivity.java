package com.wd.tech.page.loginandregistpage;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.HomeActivity;
import com.wd.tech.page.loginandregistpage.bean.UserLoginBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;
import com.wd.tech.utils.encryptionverificationutil.RegularVerification;
import com.wd.tech.utils.encryptionverificationutil.RsaCoder;
import com.wd.tech.utils.storageutil.SPUtil;
import com.wd.tech.wxapi.WXLoginUtils;

import java.lang.reflect.Type;
import java.util.Map;

import retrofit2.http.HEAD;

/**
 * 登录页
 *
 * */
public class LoginActivity extends BaseActivity implements View.OnClickListener,IView {

    private Button btnLogin;
    private TextView btnFastregist;
    private ImageView btnLoginWX;
    private EditText userPhone;
    private EditText userPaw;
    private Presenter presenter;
    private SPUtil spUtil;

    /**
     * 5.初始化数据
     *
     *   非必须实现的 方法
     * */
    @Override
    public void initViews() {
        //初始化Presenter
        presenter = new Presenter();
        presenter.attach(this);
        //SP存储userid sessionid
        spUtil = new SPUtil();
        //初始化控件
        userPhone = findViewById(R.id.userPhono);
        userPaw = findViewById(R.id.userPaw);

        btnFastregist = findViewById(R.id.btn_fastregist);//快速注册
        btnFastregist.setOnClickListener(this);
        btnLogin = findViewById(R.id.btn_login);//登录
        btnLogin.setOnClickListener(this);
        btnLoginWX = findViewById(R.id.login_weixin);//微信第三方登录
        btnLoginWX.setOnClickListener(this);

    }

    /**
     * 点击事件
     *
     * */
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_fastregist://快速注册
               startAvtivity(RegisterActivity.class);
               break;

           case R.id.btn_login://登录按钮
               //先获取用户输入
               String userphone = userPhone.getText().toString().trim();
               String userpaw = userPaw.getText().toString().trim();
               try {
                   //2 首先判断用户输入的是否合法
                   if(!RegularVerification.JudgePhone(userphone) && !userphone.equals("")){//手机号
                       showShortToast("输入的手机号不合法");
                       break;
                   }else if(!RegularVerification.JudgePassword(userpaw) && !userPaw.equals("")){//密码
                       showShortToast("密码要以字母开头，长度在6~18之间，只能包含字母、数字和下划线！");
                       break;
                   }else{
                       //2 用户注册泛型
                       Type typeUserRegister = new TypeToken<UserLoginBean>() {
                       }.getType();
                       //3 进行密码加密
                       String signPwd = RsaCoder.encryptByPublicKey(userpaw);
                       //4 进行 map封装
                       Map map = getMap("phone",userphone,"pwd",signPwd);
                       //5 调用Presenter进行请求
                       //在进行登录注册的请求时，因为不需要拦截器，需要将标识改变
                       SPUtil.putBoolean(mcontext,"TOLOGIN",true);
                       presenter.doPostMapP(API.APIUserLoginUrl,map,typeUserRegister);
                   }


               } catch (Exception e) {
                   e.printStackTrace();
               }

               break;

           case R.id.login_weixin://微信第三方登录按钮
               if (!WXLoginUtils.success(this)) {
                   showLongToast("请先安装应用");
               } else {
                   //  验证
                   SendAuth.Req req = new SendAuth.Req();
                   req.scope = "snsapi_userinfo";
                   req.state = "wechat_sdk_demo_test";
                   WXLoginUtils.reg(LoginActivity.this).sendReq(req);
               }
               break;
       }
    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    protected int getContentViewID() {
        return R.layout.activity_login;
    }

    /**
     * 成功方法
     *
     * */
    @Override
    public void onSuccessIV(Object o) {
        //登录
        if(o instanceof UserLoginBean){
            UserLoginBean userLoginBean = (UserLoginBean) o;
            if(userLoginBean!=null){
                String status = userLoginBean.getStatus();
                String message = userLoginBean.getMessage();
                Log.i("登录打印", "onSuccessIV: "+status+"=========="+message);
                showShortToast(message);
                // 拿到 身份验证
                spUtil.putInt(this,"userId",userLoginBean.getResult().getUserId());
                spUtil.putString(this,"sessionId",userLoginBean.getResult().getSessionId()+"");
                startAvtivity(HomeActivity.class);
                if(status.equals("0000")){//如果登录成功 就进行用户信息存储  和 跳转
                    UserLoginBean.ResultBean result = userLoginBean.getResult();
                    if(result!=null){
                        String sessionId = result.getSessionId();
                        int userId = result.getUserId();
                        SPUtil.putString(mcontext,"SESSIONID",sessionId);
                        SPUtil.putInt(mcontext,"USERID",userId);
                        showShortToast(message);
                        //改变标识 登录布局改变
                        SPUtil.putBoolean(mcontext,"ISLOGIN",true);
                        //成功后 跳转到Home界面
                        startAvtivity(HomeActivity.class);
                        showShortToast(message);//提示
                        finish();
                    }
                    showShortToast(message);
                }
            }
        }
    }

    /**
     * 失败方法
     *
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
