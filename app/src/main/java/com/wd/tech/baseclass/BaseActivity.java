package com.wd.tech.baseclass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : 张自力
 * Created on time.
 *
 *  Activity 基类抽取
 *
 */

public abstract class BaseActivity extends AppCompatActivity{

    /**
     * 0.全局上下文
     *
     *  可以直接拿来使用
     *  比如:  需要传代表当前页面的this时,可以直接使用mcontext代替
     * */
    protected Context mcontext;
    protected Intent intent;
    protected Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //0 前处理
        pretreatmentAction();
        //1  全局context
        mcontext = this;
        //2声明Intent 和 Bundle
        declarration();
        //3 初始化布局文件
        setContentView(getContentViewID());
        //4 初始化控件
        initViews();
        //5 初始化数据
        initData();
        //6 处理事件
        setListener();
        //7 其他操作
        setMoreAction();
    }


    /**
     * 13.Map封装  两个参数
     *    @param key1  键1
     *    @param key2  键2
     *
     *    @param value1 值1
     *    @param value2 值2
     *
     *    非必须
     * */
    public Map getMap(String key1, String value1,String key2, String value2) {
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        return map;
    }


    /**
     * 12. Map封装  三个参数
     *    @param key1  键1
     *    @param key2  键2
     *    @param key3  键3
     *
     *    @param value1 值1
     *    @param value2 值2
     *    @param value3 值3
     *
     *    非必须
     * */
    public Map getMap(String key1, String value1,String key2, String value2,String key3, String value3) {
        Map<String,String> map = new HashMap<>();
        map.put(key1,value1);
        map.put(key2,value2);
        map.put(key3,value3);
        return map;
    }


    /**
     * 11. Intent跳转 传值
     *   非必须  实现页面跳转 并且 传值过去
     *   @param clazz 要跳转的页面
     *   @param valuename 要传的值的标识
     *   @param bundle 要传的bundle值
     *
     *   注意: 存值时,直接使用  bundle.put类型("值的标识名",值); 即可
     * */
    protected void startAvtivity(Class<?> clazz,String valuename,Bundle bundle){
        intent.setClass(mcontext,clazz);
        intent.putExtra(valuename,bundle);
        startActivity(intent);
    }


    /**
     * 10. Intent跳转
     *   非必须  实现页面跳转
     *   @param clazz 要跳转的页面
     * */
    protected void startAvtivity(Class<?> clazz){
        intent.setClass(mcontext,clazz);
        startActivity(intent);
    }


    /**
     * 9. 长吐丝
     *   非必须  实现提示等
     *   @param message  要吐司的消息
     * */
    protected void showLongToast(String message){
        Toast.makeText(mcontext,message,Toast.LENGTH_LONG).show();
    }

    /**
     * 8. 短吐丝
     *   非必须  实现提示等
     *   @param message  要吐司的消息
     * */
    protected void showShortToast(String message){
        Toast.makeText(mcontext,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 7.其他操作
     *    比如实现网络操作等
     *    非必须
     * */
    protected void setMoreAction() {

    }

    /**
     * 6.处理事件
     *   比如点击事件等等
     *   非必须
     * */
    protected void setListener() {

    }

    /**
     * 5.初始化数据
     *
     *   非必须实现的 方法
     * */
    protected void initData(){

    }

    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    public abstract void initViews();

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    protected abstract int getContentViewID();

    /**
     * 2. 声明Intent 和 Bundle
     *
     *  非必须
     *  可以直接使用对象intent  和 bundle
     *
     * */
    protected void declarration() {
        intent = new Intent();
        if(intent!=null){
            bundle = intent.getExtras();
        }
    }

    /**
     * 0.前处理
     *
     *   设置沉浸式等等
     *   全局前处理 不用实现
     *   将逻辑放在本Base中即可
     * */
    public void pretreatmentAction() {
        StatusBarUtil.setTransparent(this);
        //取得ActionBar对象
        ActionBar actionBar =getSupportActionBar();
        //调用hide方法，隐藏actionbar
        //actionBar.hide();  //注: 白天和页模式切换冲突,注释掉即可

        //透明设置
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        actionBar = getSupportActionBar();
        //actionBar.hide();
        //状态栏设置
        onWindowFocusChanged(true);
    }
}
