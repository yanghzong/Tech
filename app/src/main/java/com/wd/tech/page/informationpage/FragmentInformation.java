package com.wd.tech.page.informationpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
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

public class FragmentInformation extends BaseFragment implements IView, View.OnClickListener, OnRefreshLoadMoreListener {


    private RecyclerView rvNewsAdvisory;
    private List<NewsBannerBean.ResultBean> bannerlist;
    private List<NewsAdvisoryBean.ResultBean> newsAdvisorylist;
    private NewsAdvisoryAdapter newsAdvisoryAdapter;
    private Presenter presenter;
    private XBanner newsadvisoryBanner;
    private ImageView newsAdvisorySort;
    private ImageView newsAdvisorySearch;
    private int page=1;
    private int count=5;
    private SmartRefreshLayout infoSrlLoad;
    private Type type;
    private Type type1;


    /**
     * 4.初始化控件
     *
     *   必须实现的 方法
     * */
    @Override
    protected void initView(View view) {

         presenter=new Presenter();
         presenter.attach(this);
        type = new TypeToken<NewsBannerBean>(){}.getType();
         presenter.doGetP(API.NewsAdvisoryBannerUrl, type);
        type1 = new TypeToken<NewsAdvisoryBean>(){}.getType();
         presenter.doGetP(API.NewsAdvisoryListUrl+"?page="+page+"&count="+count, type1);
        newsadvisoryBanner = view.findViewById(R.id.newsadvisory_banner);
        rvNewsAdvisory = view.findViewById(R.id.info_rv_list);
        newsAdvisorySort = view.findViewById(R.id.newsadvisory_sort);
        newsAdvisorySearch = view.findViewById(R.id.newadvisory_search);
        infoSrlLoad = view.findViewById(R.id.info_srl_load);




    }

    @Override
    protected void setListener() {
        super.setListener();
        newsAdvisorySearch.setOnClickListener(this);
        newsAdvisorySort.setOnClickListener(this);
        infoSrlLoad.setOnRefreshLoadMoreListener(this);

    }

    @Override
    protected void initData() {
        super.initData();

        bannerlist = new ArrayList<>();
        newsAdvisorylist = new ArrayList<>();
        rvNewsAdvisory.addItemDecoration(new DividerItemDecoration(mcontext, DividerItemDecoration.VERTICAL));
        newsAdvisoryAdapter = new NewsAdvisoryAdapter(mcontext,newsAdvisorylist);
        rvNewsAdvisory.setAdapter(newsAdvisoryAdapter);
        rvNewsAdvisory.setLayoutManager(new LinearLayoutManager(mcontext,LinearLayoutManager.VERTICAL,false));
        newsAdvisoryAdapter.setOnProductClickListener(new NewsAdvisoryAdapter.OnCommodityClickListener() {
            @Override
            public void onCommodityClick(int id) {
               /* Intent intent=new Intent(mcontext,NewsAdvisoryListActivity.class);
                String s = String.valueOf(id);
                intent.putExtra("cid",s);
                startActivity(intent);*/
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
        //存放图片集合
        if (T instanceof NewsBannerBean) {
            final NewsBannerBean newsAdvisoryBannerList = (NewsBannerBean) T;
            final List<String> images = new ArrayList<>();
            for (int i = 0; i < newsAdvisoryBannerList.getResult().size(); i++) {
                images.add(newsAdvisoryBannerList.getResult().get(i).getImageUrl());
            }
            //设值展示图片
            newsadvisoryBanner.setData(R.layout.item_advisory_banner, images, null);
            newsadvisoryBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    SimpleDraweeView draweeView = view.findViewById(R.id.my_image_view);
                    draweeView.setImageURI(images.get(position));
                }
            });
            //设置Item点击监听
            newsadvisoryBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, View view, int position) {

                }
            });
        }
        if (T instanceof NewsAdvisoryBean) {
            NewsAdvisoryBean newsAdvisoryBean = (NewsAdvisoryBean) T;
            List<NewsAdvisoryBean.ResultBean> result = newsAdvisoryBean.getResult();
            if (!(result.size() > 0)) {
                Toast.makeText(getActivity(), "暂无更多数据", Toast.LENGTH_SHORT).show();
            } else {
                newsAdvisorylist.addAll(result);
                newsAdvisoryAdapter.notifyDataSetChanged();
            }
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        count+=5;
        presenter.doGetP(API.NewsAdvisoryListUrl+page+count,type1);
        infoSrlLoad.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        newsAdvisorylist.clear();
        page = 1;
        presenter.doGetP(API.NewsAdvisoryListUrl+page+count,type1);
        infoSrlLoad.finishRefresh();
    }
}
