package com.wd.tech.baseclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : 张自力
 * Created on time.
 *
 * Fragment  的基类抽取
 *
 * 覆写两个方法  onCreateView  onActivityCreated
 *
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 0.全局上下文
     *
     *  可以直接拿来使用
     *  比如:  需要传代表当前页面的this时,可以直接使用mcontext代替
     * */
    protected Context mcontext;
    protected Intent intent;
    protected Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewID(),container,false);
        //1  全局context
        mcontext = getActivity();
        //2声明Intent 和 Bundle
        declarration();
        //3.初始化控件
        initView(view);
        return view;
    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    protected abstract void initView(View view);

    /**
     * 1.初始化布局文件
     *
     *   必须实现的 方法
     * */
    protected abstract int getContentViewID();



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //3 初始化数据
        initData();
        //4 处理事件
        setListener();
        //5 其他操作
        setMoreAction();

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
    public Map getMap(String key1, String value1, String key2, String value2, String key3, String value3) {
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
    protected void initData() {

    }


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
}
