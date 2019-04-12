package com.wd.tech.baseclass;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewID(),container,false);
        //2.初始化控件
        initView(view);
        return view;
    }

    /**
     * 2.初始化布局文件
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
}
