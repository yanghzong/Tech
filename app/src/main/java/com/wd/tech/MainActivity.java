package com.wd.tech;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.page.HomeActivity;
import com.wd.tech.utils.storageutil.SPUtil;

/**
 * 程序入口类
 *
 * */

public class MainActivity extends BaseActivity {
    /**
     * 标识区
     *
     * */
    private boolean STARTANIMATIONTRUE = false;//是否是第一次使用,展示启动页

    /**
     * 控件区
     * */
    private ImageView imgTitleAnimation;
    private LinearLayout llTitleAnimation;

    /**
     * 5.初始化数据
     *
     *   非必须实现的 方法
     * */
    @Override
    protected void initData() {
        //获取sp中的标识
        boolean startanimationtrue = SPUtil.getBoolean(mcontext, "STARTANIMATIONTRUE", false);
        STARTANIMATIONTRUE = startanimationtrue;

    }

    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    public void initViews() {
        imgTitleAnimation = findViewById(R.id.img_title_animation);//旋转头像
        llTitleAnimation = findViewById(R.id.ll_title_animation);//旋转标题
    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }

    /**
     * 6.处理事件
     *   比如点击事件等等
     *   非必须
     * */
    @Override
    protected void setListener() {
        super.setListener();

        //1.如果是第一次使用 就进入页面就旋转动画
        if(!STARTANIMATIONTRUE){
            ObjectAnimator imgObjectAnimator = ObjectAnimator.ofFloat(imgTitleAnimation, "rotation", 0f, 360f, 0f);
            //文字动画
            ObjectAnimator llObjectAnimator = ObjectAnimator.ofFloat(llTitleAnimation, "rotation", 0f, 360f, 0f);

            //动画管理
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(imgObjectAnimator).with(llObjectAnimator);
            animatorSet.setDuration(10000);
            animatorSet.start();

            //动画监听
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {//结束后跳转 并改变状态
                    super.onAnimationEnd(animation);
                    //跳转之前 进行标识存储 代表启动页已启动过一次
                    SPUtil.putBoolean(mcontext,"STARTANIMATIONTRUE",true);
                    //2. 就跳转页面
                    startAvtivity(HomeActivity.class);
                    finish();
                }
            });
        }else{//如果不是第一次使用
            //2. 就跳转页面
            startAvtivity(HomeActivity.class);
            finish();
        }
    }

    /**
     * 销毁方法
     *
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
