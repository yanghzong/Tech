package com.wd.tech.page.messagepage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.nfc.cardemulation.CardEmulation;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.baseclass.BaseFragment;
import com.wd.tech.page.messagepage.fragment.FragmentContactList;
import com.wd.tech.page.messagepage.fragment.FragmentMessageList;
import com.wd.tech.page.messagepage.widget.AddAllPopupWindow;
import com.wd.tech.utils.storageutil.DpToPxUtil;

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
    private ImageView imgAddAllFragmentmessage;
    private ConstraintLayout conTitleLayout;
    boolean flagRight = true,flagLeft = true;
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
        initControl(view);

        //默认展示消息fragment
        defaultShowFragment();

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
                flagRight = true;
                transaction.hide(fragmentContactList).show(fragmentMessageList).commit();
                titleSlideAnimationLeft();
                break;
            case R.id.text_contacts_fragmentmessage:
                flagLeft = true;
                transaction.hide(fragmentMessageList).show(fragmentContactList).commit();
                titleSlideAnimationRight();
                break;
            case R.id.img_add_all_fragmentmessage:
                //点击弹出popupwind
                showPopup();
                break;

        }
    }


    //初始化控件及监听
    private void initControl(View view) {
        textMessageFragmentmessage = view.findViewById(R.id.text_message_fragmentmessage);
        textContactsFragmentmessage = view.findViewById(R.id.text_contacts_fragmentmessage);
        textSelectedStateStyle = view.findViewById(R.id.text_selected_state_style);
        imgAddAllFragmentmessage = view.findViewById(R.id.img_add_all_fragmentmessage);
        conTitleLayout = view.findViewById(R.id.con_title_layout);

        //点击监听
        textMessageFragmentmessage.setOnClickListener(this);
        textContactsFragmentmessage.setOnClickListener(this);
        imgAddAllFragmentmessage.setOnClickListener(this);


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
        textMessageFragmentmessage.setTextColor(Color.parseColor("#ffffff"));
        textContactsFragmentmessage.setTextColor(Color.parseColor("#333333"));
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
        if (flagRight){
            int moveDistance = DpToPxUtil.dip2px(getContext(), 94);
            ObjectAnimator.ofFloat(textSelectedStateStyle, "translationX", 0, moveDistance).setDuration(250).start();
            setSelectedStyle();
            flagRight = false;
        }else{
            //no thing to do

        }
    }

    //被选中的title的样式  -> 向右平移
    private void titleSlideAnimationLeft(){
        if (flagLeft){
            int moveDistance = DpToPxUtil.dip2px(getContext(), 94);
            ObjectAnimator.ofFloat(textSelectedStateStyle, "translationX", moveDistance, 0).setDuration(250).start();
            setSelectedStyle();
            flagLeft = false;
        }else{
            //no thing to do
        }

    }

    //点击弹出popupwind
    private void showPopup() {
        AddAllPopupWindow addAllPopupWindow = new AddAllPopupWindow(getContext());
        PopupWindowCompat.showAsDropDown(addAllPopupWindow, conTitleLayout, 0, -8, Gravity.END);
    }
}
