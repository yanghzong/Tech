package com.wd.tech.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

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
        //2 初始化Fresco
        initFresco();

    }

    /**
     * 2 初始化Fresco
     *
     * */
    private void initFresco() {
        Fresco.initialize(this);
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
