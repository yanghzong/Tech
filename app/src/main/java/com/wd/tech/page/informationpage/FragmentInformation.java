package com.wd.tech.page.informationpage;

import android.app.Application;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.R;
import com.wd.tech.application.MyApplication;
import com.wd.tech.baseclass.BaseFragment;
import com.wd.tech.iview.IView;
import com.wd.tech.page.informationpage.activity.ClassfiyActivity;
import com.wd.tech.page.informationpage.activity.NewsAdvisoryListActivity;
import com.wd.tech.page.informationpage.adapter.NewsAdvisoryAdapter;
import com.wd.tech.page.informationpage.bean.NewsAdvisoryBean;
import com.wd.tech.page.informationpage.bean.NewsBannerBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 *
 * 1. 资讯页
 *
 */

public class FragmentInformation extends BaseFragment implements IView, View.OnClickListener {


    private RecyclerView rvNewsAdvisory;
    private List<NewsBannerBean.ResultBean> bannerlist;
    private List<NewsAdvisoryBean.ResultBean> newsAdvisorylist;
    private NewsAdvisoryAdapter newsAdvisoryAdapter;
    private Presenter presenter;
    private XBanner xbannerAdvisory;
    private ImageView newsAdvisorySort;
    private ImageView newsAdvisorySearch;


    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    protected void initView(View view) {

        xbannerAdvisory = view.findViewById(R.id.xbanner_advisory);
        rvNewsAdvisory = view.findViewById(R.id.info_rv_list);
        newsAdvisorySort = view.findViewById(R.id.newsadvisory_sort);
        newsAdvisorySearch = view.findViewById(R.id.newadvisory_search);

       /* presenter = new Presenter();
        Type type=new TypeToken<NewsBannerBean>(){}.getType();
        presenter.doGetP(API.NewsAdvisoryBannerUrl,type);*/


    }

    @Override
    protected void setListener() {
        super.setListener();
        newsAdvisorySearch.setOnClickListener(this);
        newsAdvisorySort.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

        bannerlist = new ArrayList<>();
        newsAdvisorylist = new ArrayList<>();
        newsAdvisoryAdapter = new NewsAdvisoryAdapter(MyApplication.applicationContext,newsAdvisorylist);
        rvNewsAdvisory.setAdapter(newsAdvisoryAdapter);
        rvNewsAdvisory.setLayoutManager(new LinearLayoutManager(MyApplication.applicationContext));
        newsAdvisoryAdapter.setOnProductClickListener(new NewsAdvisoryAdapter.OnCommodityClickListener() {
            @Override
            public void onCommodityClick(int id) {
                Intent intent=new Intent(MyApplication.applicationContext,NewsAdvisoryListActivity.class);
                String s = String.valueOf(id);
                intent.putExtra("cid",s);
                startActivity(intent);
            }
        });

    }

    /**
     * 3.初始化布局文件
     *
     *   必须实现的 方法
     * */
    @Override
    protected int getContentViewID() {
        return R. layout.fragmentinformation;
    }

    @Override
    public void onSuccessIV(Object T) {
        if(T instanceof NewsBannerBean)
        {
            NewsBannerBean banner1= (NewsBannerBean) T;
            xbannerAdvisory.setData(banner1.getResult(),null);
            xbannerAdvisory.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    NewsBannerBean.ResultBean bean= (NewsBannerBean.ResultBean) model;
                    SimpleDraweeView simpleDraweeView=new SimpleDraweeView(MyApplication.applicationContext);
                    simpleDraweeView.setImageURI(bean.getImageUrl());
                    banner.setPageChangeDuration(1000);
                }
            });

        }

    }

    @Override
    public void onFailed(String message) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newadvisory_search:

                break;
            case R.id.newsadvisory_sort:
                Intent intent=new Intent(getContext(),ClassfiyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
