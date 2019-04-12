package com.wd.tech.page.loginandregistpage;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.wxapi.WXLoginUtils;

/**
 * 登录页
 *
 * */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView btnFastregist;
    private ImageView btnLoginWX;

    /**
     * 5.初始化数据
     *
     *   非必须实现的 方法
     * */
    @Override
    public void initViews() {
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


}
