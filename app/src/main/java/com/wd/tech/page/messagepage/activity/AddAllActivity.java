package com.wd.tech.page.messagepage.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.utils.storageutil.DpToPxUtil;

public class AddAllActivity extends BaseActivity implements View.OnClickListener{


    private int winWidth;
    private ImageView iconUnderlineAddAll;
    boolean flagRight = true,flagLeft = true;
    @Override
    public void initViews() {
        Intent intent = getIntent();
        String forg = intent.getStringExtra("forg");
        showLongToast(forg+"");
        //获取屏幕宽度
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        winWidth = wm.getDefaultDisplay().getWidth();

        //控件及点击监听
        initControl();

    }

    private void initControl() {
        iconUnderlineAddAll = findViewById(R.id.icon_underline_add_all);
        TextView textFindFriendAddAll = findViewById(R.id.text_find_friend_add_all);
        TextView textFindGroupAddAll = findViewById(R.id.text_find_group_add_all);

        textFindFriendAddAll.setOnClickListener(this);
        textFindGroupAddAll.setOnClickListener(this);
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_add_all;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //找人
            case R.id.text_find_friend_add_all:
                flagRight = true;
                underlineSlideAnimationLeft();
                break;
            //找群
            case R.id.text_find_group_add_all:
                flagLeft = true;
                underlineSlideAnimationRight();
                break;
        }
    }

    //下划线移动到找人下面  -> 向左平移
    private void underlineSlideAnimationLeft(){
        if (flagLeft){
            int moveDistance = DpToPxUtil.dip2px(this, 60);
            ObjectAnimator.ofFloat(iconUnderlineAddAll, "translationX",  winWidth-moveDistance, 0).setDuration(500).start();
            flagLeft = false;
        }else{
            //no thing to do
        }
    }

    //下划线移动到找群下面  -> 向右平移
    private void underlineSlideAnimationRight(){
        if (flagRight){
            int moveDistance = DpToPxUtil.dip2px(this, 60);
            int thirtySixDp = DpToPxUtil.dip2px(this, 36);
            ObjectAnimator.ofFloat(iconUnderlineAddAll, "translationX", 0, winWidth-moveDistance*2-thirtySixDp).setDuration(500).start();
            flagRight = false;
        }else{
            //no thing to do
        }
    }
}
