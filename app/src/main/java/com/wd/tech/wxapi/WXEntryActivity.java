package com.wd.tech.wxapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.HomeActivity;
import com.wd.tech.page.loginandregistpage.bean.WXLoginBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;
import com.wd.tech.utils.networkutil.NetUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * date：2019/1/27
 * desc：
 * implements IWXAPIEventHandler
 *
 * 使用微信接口  进行微信登录
 *
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler,IView {


    private Presenter presenter;
    private SharedPreferences.Editor edit;

    /**
     * 布局文件
     *
     * */
    @Override
    protected int getContentViewID() {
        return R.layout.wxactivity_login;
    }

    /**
     * 初始化控件
     *
     * */
    @Override
    public void initViews() {
        //初始化Presenter
        initPresenter();
    }



    //初始化Presenter
    private void initPresenter() {

        presenter = new Presenter();
        //关联
        presenter.attach(this);

    }


    /**
     * 成功的方法
     *
     * */
    @Override
    public void onSuccessIV(Object o) {
        if (o instanceof WXLoginBean){
           WXLoginBean wxLoginBean = (WXLoginBean) o;
            if (wxLoginBean.getStatus().equals("0000")) {
                SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                edit = sp.edit();

                edit.putString("key",wxLoginBean.getResult().getUserId()+"")
                         .putString("keyone",wxLoginBean.getResult().getSessionId());
                boolean commit = edit.commit();
                if(commit){
                    showShortToast(wxLoginBean.getMessage());
                    //登录成功,跳转到首页界面
                    startAvtivity(HomeActivity.class);
                    finish();
                    overridePendingTransition(R.anim.start_fullscreen, R.anim.quit_fullscreen);
                }
            }

        }
    }

    /**
     * 失败的方法
     * */
    @Override
    public void onFailed(String message) {
        showShortToast(message);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                Log.i("qwe", "onResp: code = "+code);
                Map<String,String> WXLoginmap=new HashMap<>();
                WXLoginmap.put("code",code);

                //进行网络判断
                if(NetUtil.checkNet(this)) {
                    //定义一个泛型
                    Type type = new TypeToken<WXLoginBean>() {
                    }.getType();
                    //调用方法 进行 详情请求
                    presenter.doPostMapP(API.APIWXLoginUrl,WXLoginmap,type);

                }else{
                    showShortToast("请链接网络");
                    //调用网络工具类中的方法，跳转到设置网络的界面
                    NetUtil.setNetworkMethod(WXEntryActivity.this);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                showShortToast("取消");
                break;
            default:
                showShortToast("未知错误");
                break;
        }

    }


    /**
     * 清除微信memory leak
     */
    public static void cleanWXLeak() {
        try {
            Class clazz = com.tencent.a.a.a.a.g.class;
            Field field = clazz.getDeclaredField("V");
            field.setAccessible(true);
            Object obj = field.get(clazz);
            if (obj != null) {
                com.tencent.a.a.a.a.g g = (com.tencent.a.a.a.a.g) obj;
                Field mapField = clazz.getDeclaredField("U");
                mapField.setAccessible(true);
                Map map = (Map) mapField.get(g);
                map.clear();
            }
            field.set(clazz, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.datach();
        }

        //清除微信
        cleanWXLeak();
    }


}
