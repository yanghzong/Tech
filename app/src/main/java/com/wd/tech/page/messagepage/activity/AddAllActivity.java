package com.wd.tech.page.messagepage.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.loginandregistpage.bean.UserLoginBean;
import com.wd.tech.page.messagepage.bean.FindMegAllBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;
import com.wd.tech.utils.networkutil.NetUtil;
import com.wd.tech.utils.storageutil.DpToPxUtil;
import com.wd.tech.utils.storageutil.SPUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AddAllActivity<T> extends BaseActivity implements View.OnClickListener,IView<T>{


    private int winWidth;
    private ImageView iconUnderlineAddAll;
    boolean flagRight = true,flagLeft = true;
    private EditText editSearchActivityAddAll;
    private ImageView imgSearchActivityAddAll;
    private ImageView imgPeopleHomepage;
    private ImageView imgFinshActivityAddAll;
    private SimpleDraweeView sdvFindPeople;
    private TextView textFindPeople;
    private ConstraintLayout conFindPeopleMsg;

    private boolean findType = true;
    private Map<String, String> map;
    private Presenter presenter;
    private int userId;
    private String sessionId;


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
        //初始化p层
        presenter = new Presenter();
        presenter.attach(this);
        //获取userid sessionid
        SPUtil spUtil = new SPUtil();
        userId = spUtil.getInt(this, "userId", 0);
        sessionId = spUtil.getString(this, "sessionId", "");
        Log.e("三十岁",userId+"     "+sessionId);
        //设置一个Map 传参用
        map = new HashMap<>();

    }

    //控件及点击监听
    private void initControl() {
        iconUnderlineAddAll = findViewById(R.id.icon_underline_add_all);
        TextView textFindFriendAddAll = findViewById(R.id.text_find_friend_add_all);
        TextView textFindGroupAddAll = findViewById(R.id.text_find_group_add_all);
        imgSearchActivityAddAll = findViewById(R.id.img_search_activity_add_all);
        imgFinshActivityAddAll = findViewById(R.id.img_finsh_activity_add_all);
        editSearchActivityAddAll = findViewById(R.id.edit_search_activity_add_all);
        sdvFindPeople = findViewById(R.id.sdv_find_people);
        textFindPeople = findViewById(R.id.text_find_people);
        imgPeopleHomepage = findViewById(R.id.img_people_homepage);
        conFindPeopleMsg = findViewById(R.id.con_find_people_msg);


        textFindFriendAddAll.setOnClickListener(this);
        textFindGroupAddAll.setOnClickListener(this);
        imgSearchActivityAddAll.setOnClickListener(this);
        imgFinshActivityAddAll.setOnClickListener(this);
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
                editSearchActivityAddAll.setHint("请输入好友手机号");
                break;
            //找群
            case R.id.text_find_group_add_all:
                flagLeft = true;
                underlineSlideAnimationRight();
                editSearchActivityAddAll.setHint("请输入群号");
                break;
            //搜索
            case R.id.img_search_activity_add_all:
                //2 用户注册泛型
                Type typeFindMegAllBean = new TypeToken<FindMegAllBean>() {
                }.getType();
                String searchMsgStr = editSearchActivityAddAll.getText().toString().trim();
                if (findType){
                    //判断是否有网络链接
                    if (NetUtil.checkNet(this)){
                        map.put("phone",searchMsgStr);
                        presenter.doGetNetSomeThingP(API.FindUserByPhoneUrl,map,userId+"",sessionId,typeFindMegAllBean);
                    }else{
                        showShortToast("没网啦 亲 请检查网络");
                    }
                }
                break;
            //返回上一层
            case R.id.img_finsh_activity_add_all:
                finish();
                break;
        }
    }

    //下划线移动到找人下面  -> 向左平移
    private void underlineSlideAnimationLeft(){
        if (flagLeft){
            //下划线移动的距离 = 下划线相对Y轴的距离*2 + 下划线的长度
            int moveDistance = DpToPxUtil.dip2px(this, 60);
            int thirtySixDp = DpToPxUtil.dip2px(this, 36);
            ObjectAnimator.ofFloat(iconUnderlineAddAll, "translationX",  winWidth-moveDistance*2-thirtySixDp, 0).setDuration(500).start();
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

    @Override
    public void onSuccessIV(T T) {
        if (T instanceof FindMegAllBean){
            FindMegAllBean findMegAllBean = (FindMegAllBean) T;
            if (findMegAllBean!=null){
                sdvFindPeople.setImageURI(findMegAllBean.getResult().getHeadPic());
                textFindPeople.setText(findMegAllBean.getResult().getNickName());
                conFindPeopleMsg.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
