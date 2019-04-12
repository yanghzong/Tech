package com.wd.tech.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.wd.tech.utils.storageutil.SPUtil;

/**
 * Author : 张自力
 * Created on time.
 *
 * 全局初始化
 *
 */

public class MyApplication extends Application {

    //1 全局context
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //1. 全局context设置
        setGlobalContext();
        //2 内存泄漏监听
        initLeakCanary();
        //3 初始化Fresco
        initFresco();
        //4 白天夜间模式切换
        initDayAndNight();

    }

    /**
     * 3 白天夜间模式切换
     *
     * */
    private void initDayAndNight() {
        //获取标识
        boolean DAYANDNIGHT = SPUtil.getBoolean(getApplicationContext(), "DAYANDNIGHT", true);
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(DAYANDNIGHT){//为true代表用户设置为白天
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//白天模式
        }else{//否则设置为黑夜模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);//夜间模式
        }
    }

    /**
     * 3 初始化Fresco
     *
     * */
    private void initFresco() {
        Fresco.initialize(this);
    }

    /**
     *2  内存泄漏监听o
     *
     * */
    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    /**
     * 1. 全局context设置
     *
     * */
    private void setGlobalContext() {
        //定义一个全局context
        applicationContext = this;
    }
}
