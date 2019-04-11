package com.wd.tech.page.messagepage;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.nfc.cardemulation.CardEmulation;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.baseclass.BaseFragment;
import com.wd.tech.page.messagepage.fragment.FragmentContactList;
import com.wd.tech.page.messagepage.fragment.FragmentMessageList;

/**
 * Author : 张自力
 * Created on time.
 * <p>
 * 2 消息界面
 */

public class FragmentMessage extends BaseFragment implements View.OnClickListener {

    //先获取fragment
    private FragmentMessageList fragmentMessageList;
    private FragmentContactList fragmentContactList;
    private TextView textMessageFragmentmessage;
    private TextView textContactsFragmentmessage;
    private TextView textSelectedStateStyle;


    /**
     * 4.初始化控件
     * <p>
     * 必须实现的 方法
     */
    @Override
    protected void initView(View view) {
        //每个fragment对象的实例
        fragmentMessageList = new FragmentMessageList();
        fragmentContactList = new FragmentContactList();
        textMessageFragmentmessage = view.findViewById(R.id.text_message_fragmentmessage);
        textContactsFragmentmessage = view.findViewById(R.id.text_contacts_fragmentmessage);
        textSelectedStateStyle = view.findViewById(R.id.text_selected_state_style);
        //默认展示消息fragment
        defaultShowFragment();
        setSelectedStyle();
        textContactsFragmentmessage.setTextColor(Color.parseColor("#333333"));
        //点击监听
        textMessageFragmentmessage.setOnClickListener(this);
        textContactsFragmentmessage.setOnClickListener(this);



    }



    /**
     * 3.初始化布局文件
     * <p>
     * 必须实现的 方法
     */
    @Override
    protected int getContentViewID() {

        return R.layout.fragmentmessage;

    }

    @Override
    public void onClick(View view) {
        // 获取FragmentTransaction对象
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.text_message_fragmentmessage:
                transaction.hide(fragmentContactList).show(fragmentMessageList).commit();
                titleSlideAnimationLeft();
                break;
            case R.id.text_contacts_fragmentmessage:
                transaction.hide(fragmentMessageList).show(fragmentContactList).commit();
                titleSlideAnimationRight();
                break;
        }
    }

    //默认展示消息fragment的方法
    private void defaultShowFragment() {
        //创建fragment管理类 使用add,hide,show方式
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先把每个fragment添加 把联系人fragment隐藏
        transaction.add(R.id.frame_layout_fragmentmessage, fragmentMessageList);
        transaction.add(R.id.frame_layout_fragmentmessage, fragmentContactList);
        transaction.show(fragmentMessageList).hide(fragmentContactList).commit();
    }

    //被选中的title的样式
    private void setSelectedStyle(){
        //判断fragment是否是可见
        if (fragmentMessageList.isVisible()){
            textMessageFragmentmessage.setTextColor(Color.parseColor("#333333"));
        }else{
            textMessageFragmentmessage.setTextColor(Color.parseColor("#ffffff"));
        }
        if (fragmentContactList.isVisible()){
            textContactsFragmentmessage.setTextColor(Color.parseColor("#333333"));
        }else{
            textContactsFragmentmessage.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    //被选中的title的样式  -> 向右平移
    private void titleSlideAnimationRight(){
        ObjectAnimator.ofFloat(textSelectedStateStyle, "translationX", 0, 141).setDuration(250).start();
        setSelectedStyle();
    }

    //被选中的title的样式  -> 向右平移
    private void titleSlideAnimationLeft(){
        ObjectAnimator.ofFloat(textSelectedStateStyle, "translationX", 141, 0).setDuration(250).start();
        setSelectedStyle();
    }
}
