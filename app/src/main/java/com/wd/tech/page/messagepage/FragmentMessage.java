package com.wd.tech.page.messagepage;

import android.view.View;

import com.wd.tech.R;
import com.wd.tech.baseclass.BaseFragment;

/**
 * Author : 张自力
 * Created on time.
 *
 * 2 消息界面
 *
 */

public class FragmentMessage extends BaseFragment {

    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    protected void initView(View view) {

    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    protected int getContentViewID() {
        return R. layout.fragmentmessage;
    }
}
