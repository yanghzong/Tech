package com.wd.tech.page.loginandregistpage;

import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;

/**
 * 登录页
 *
 * */
public class LoginActivity extends BaseActivity {

    /**
     * 5.初始化数据
     *
     *   非必须实现的 方法
     * */
    @Override
    public void initViews() {

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
