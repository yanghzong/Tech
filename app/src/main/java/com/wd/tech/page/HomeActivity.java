package com.wd.tech.page;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.page.communitypage.FragmentCommunity;
import com.wd.tech.page.informationpage.FragmentInformation;
import com.wd.tech.page.loginandregistpage.LoginActivity;
import com.wd.tech.page.messagepage.FragmentMessage;
import com.wd.tech.utils.storageutil.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Home 页
 *
 *   存放三个Fragment
 *   实现侧拉抽屉
 *
 * */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 标识符定义区
     *
     * */
    public boolean ISLOGIN = false;//记录是否已经登录标记
    public boolean DAYANDNIGHT = true;//记录白天黑夜是否切换  默认白天


    /**
     * 控件初始化声明区
     *
     * */
    private SlidingMenu slidingMenu;
    private List<Fragment> fragmentList;
    private ViewPager vpFragment;
    private LinearLayout llInformationButtom;
    private LinearLayout llMessageButtom;
    private LinearLayout llCommunityButtom;
    private ImageView imgInformationButtom;
    private TextView txtInformationButtom;
    private ImageView imgMessageButtom;
    private TextView txtMessageButtom;
    private ImageView imgCommunityButtom;
    private TextView txtCommunityButtom;
    private ImageView imgHeadPhotoSlidingMenu;
    private TextView txtLoginAndRegistSlidingMenu;
    private Button btnDayAndNight;
    private boolean islogin;

    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    public void initViews() {
        //初始化侧滑菜单栏
        slidingMenu = new SlidingMenu(mcontext);
        //初始化listfragment  和  fragment
        fragmentList = new ArrayList<>();
        FragmentInformation fragmentInformation = new FragmentInformation();
        FragmentMessage fragmentMessage = new FragmentMessage();
        FragmentCommunity fragmentCommunity = new FragmentCommunity();
        fragmentList.add(fragmentInformation);
        fragmentList.add(fragmentMessage);
        fragmentList.add(fragmentCommunity);
        //初始化ViewPage
        vpFragment = findViewById(R.id.vp_fragment);

        //底部按钮
        imgInformationButtom = findViewById(R.id.img_information_buttom);
        txtInformationButtom = findViewById(R.id.txt_information_buttom);
        imgMessageButtom = findViewById(R.id.img_message_buttom);
        txtMessageButtom = findViewById(R.id.txt_message_buttom);
        imgCommunityButtom = findViewById(R.id.img_community_buttom);
        txtCommunityButtom = findViewById(R.id.txt_community_buttom);
        //按钮默认设置
        imgInformationButtom.setImageResource(R.mipmap.common_tab_information_n_hdpi);
        imgMessageButtom.setImageResource(R.mipmap.common_tab_message_s_hdpi);
        imgCommunityButtom.setImageResource(R.mipmap.common_tab_community_s_hdpi);
        txtInformationButtom.setTextColor(Color.WHITE);
        txtMessageButtom.setTextColor(Color.BLACK);
        txtCommunityButtom.setTextColor(Color.BLACK);

        //按钮容器  为它设置点击事件即可
        llInformationButtom = findViewById(R.id.ll_information_buttom);
        llMessageButtom = findViewById(R.id.ll_message_buttom);
        llCommunityButtom = findViewById(R.id.ll_community_buttom);
        llInformationButtom.setOnClickListener(this);
        llMessageButtom.setOnClickListener(this);
        llCommunityButtom.setOnClickListener(this);

    }


    /**
     * 点击事件
     *
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_information_buttom://资讯容器
                vpFragment.setCurrentItem(0);
                onBtnClickListener(0);
                break;

            case R.id.ll_message_buttom://消息容器
                vpFragment.setCurrentItem(1);
                onBtnClickListener(1);
                break;

            case R.id.ll_community_buttom://社区容器
                vpFragment.setCurrentItem(2);
                onBtnClickListener(2);
                break;

            case R.id.txt_loginandregist_slidingmenu://未登录前  用户点击登录 跳转登录页面
                startAvtivity(LoginActivity.class);
                break;

            case R.id.btn_dayandnight://白天夜间模式切换
                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if(mode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);//白天
                    DAYANDNIGHT = true;//切换之后改变标识 并存储
                    SPUtil.putBoolean(mcontext,"DAYANDNIGHT",DAYANDNIGHT);
                    showShortToast("白天模式开启");
                } else if(mode == Configuration.UI_MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);//黑夜模式
                    DAYANDNIGHT = false;;//切换之后改变标识 并存储
                    SPUtil.putBoolean(mcontext,"DAYANDNIGHT",DAYANDNIGHT);
                    showShortToast("夜间模式开启");
                }
                recreate();

                break;
        }
    }

    /**
     * 6.处理事件
     *
     * */
    @Override
    protected void setListener() {
        super.setListener();
        //6.0 SP拿值
        getSPUtils();
        //6.1. 设置侧滑菜单栏
        setSlidingMenu();
        //6.2 ViewPager设置
        setViewPager();

    }

    /**
     * 6.2 ViewPager设置
     *
     * **/
    private void setViewPager() {
        vpFragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        vpFragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //6.2.1 滑动监听
                onBtnClickListener(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 6.2.1 滑动监听
     *
     * */
    private void onBtnClickListener(int position) {
        imgInformationButtom.setImageResource(position==0? R.mipmap.common_tab_information_n_hdpi:R.mipmap.common_tab_informatiion_s_hdpi);
        imgMessageButtom.setImageResource(position==1? R.mipmap.common_tab_message_n_hdpi:R.mipmap.common_tab_message_s_hdpi);
        imgCommunityButtom.setImageResource(position==2? R.mipmap.common_tab_community_n_hdpi:R.mipmap.common_tab_community_s_hdpi);

        txtInformationButtom.setTextColor(position==0?Color.WHITE:Color.BLACK);
        txtMessageButtom.setTextColor(position==1?Color.WHITE:Color.BLACK);
        txtCommunityButtom.setTextColor(position==2?Color.WHITE:Color.BLACK);

    }

    /**
     * 6.1. 设置侧滑菜单栏
     *
     * */
    private void setSlidingMenu() {
        //设置侧滑方法
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置触摸屏幕的模式 --滑动边缘有效
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.dp_100);
        //设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);

        //把菜单添加进所有的Activity中 可选值SLIDING_CONTENT 会导致Activity界面混乱 ,SLIDING_WINDOW
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);
        //设置滑动后剩余部分
        slidingMenu.setBehindOffset(150);
        //为侧滑菜单设置布局
        SlidingMenuLayout();
    }

    /**
     * 为侧滑菜单设置布局
     *
     * */
    private void SlidingMenuLayout() {
        //首先判断
        if(!islogin){//如果没有登录,就展示提示登录布局
            //初始化布局
            slidingMenu.setMenu(R.layout.slidingmenu);
            //初始化控件
            imgHeadPhotoSlidingMenu = slidingMenu.findViewById(R.id.img_headphoto_slidingmenu);//用户头像 没登录前默认头像
            imgHeadPhotoSlidingMenu.setImageResource(R.mipmap.login_icon_n_hdpi);
            txtLoginAndRegistSlidingMenu = slidingMenu.findViewById(R.id.txt_loginandregist_slidingmenu);//登录注册按钮
            txtLoginAndRegistSlidingMenu.setOnClickListener(this);

        }else{//否则就进行展示登录后的布局,展示用户的基本信息
            //初始化布局
            slidingMenu.setMenu(R.layout.slidingmenu_logined);
            //初始化控件
            btnDayAndNight = findViewById(R.id.btn_dayandnight);//白天夜间模式切换按钮
            btnDayAndNight.setOnClickListener(this);
        }
    }

    /**
     * 6.0 SP拿值
     *
     * */
    private void getSPUtils() {
        //登录成功的标识，用来改变侧滑布局
        islogin = SPUtil.getBoolean(mcontext, "ISLOGIN", false);
    }

    /**
     * 5.初始化数据
     *
     *   非必须实现的 方法
     * */
    @Override
    protected void initData() {
        super.initData();
        //获取登录的标识值  sp

    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    protected int getContentViewID() {
        return R.layout.activity_home;
    }


}
