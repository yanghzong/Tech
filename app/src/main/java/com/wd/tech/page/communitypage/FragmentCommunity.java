package com.wd.tech.page.communitypage;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseFragment;
import com.wd.tech.iview.IView;
import com.wd.tech.page.communitypage.adapter.CommunityAdapter;
import com.wd.tech.page.communitypage.bean.CommunityListBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 *
 * 1. 社区页
 *
 */

public class FragmentCommunity extends BaseFragment implements IView {

    //社区列表 页数  和  数量
    private int page = 10;
    private int count = 10;

    private XRecyclerView xlvCommunityList;
    private Presenter presenter;
    private List<CommunityListBean.ResultBean> listCommunity;
    private CommunityAdapter communityAdapter;

    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    protected void initView(View view) {
        // 初始化Presenter对象
        presenter = new Presenter();
        presenter.attach(this);

        //社区列表
        xlvCommunityList = view.findViewById(R.id.xlv_communitylist);
        //社区列表 设置
        setCommunityList(presenter);


    }

    /**
     * 社区列表 设置
     *
     * */
    private void setCommunityList(Presenter presenter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        xlvCommunityList.setLayoutManager(linearLayoutManager);
        listCommunity = new ArrayList<>();
        communityAdapter = new CommunityAdapter(mcontext, listCommunity);
        xlvCommunityList.setAdapter(communityAdapter);
        Type type = new TypeToken<CommunityListBean>(){}.getType();//社区列表泛型
        presenter.doGetP(API.APICommunityListUrl+"?page="+page+"&count="+count,type);//社区列表请求
    }

    /**
     * 6.处理事件
     *   比如点击事件等等
     *   非必须
     * */
    @Override
    protected void setListener() {
        super.setListener();



    }

    /**
     * 成功方法
     *
     * */
    @Override
    public void onSuccessIV(Object o) {
       //社区列表
       if(o instanceof CommunityListBean){//如果是社区列表
           CommunityListBean communityListBean = (CommunityListBean) o;
           if(communityListBean!=null){
               String status = communityListBean.getStatus();
               String message = communityListBean.getMessage();
               if(status.equals("0000")){
                   //查询成功
                   List<CommunityListBean.ResultBean> result = communityListBean.getResult();
                   if(result!=null){
                      listCommunity.clear();
                      listCommunity.addAll(result);
                      communityAdapter.notifyDataSetChanged();
                   }
               }
               showShortToast(message);
           }

       }
    }

    /**
     * 失败方法
     *
     * */
    @Override
    public void onFailed(String message) {
        showShortToast(message);
    }

    /**
     * 销毁
     *
     * */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.datach();
        }
    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    protected int getContentViewID() {
        return R. layout.fragmentcommunity;
    }

}
