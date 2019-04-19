package com.wd.tech.page.informationpage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.informationpage.adapter.NewsAdvisorySerchListAdapter;
import com.wd.tech.page.informationpage.adapter.TitleFragmentPagerAdapter;
import com.wd.tech.page.informationpage.bean.NewsAdvisoryBean;
import com.wd.tech.page.informationpage.bean.NewsAdvisorySearchBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewsAdvisorySearchTwoActivity extends BaseActivity implements IView, View.OnClickListener, OnRefreshLoadMoreListener {


    private android.widget.ImageView searchIvSo;
    private android.widget.EditText searchEtContent;
    private android.widget.TextView searchTvCancal;
    private android.widget.LinearLayout searchLlBack;
    private android.support.v7.widget.RecyclerView rvSearchlist;
    private int page = 1;
    private int count = 8;
    private Type type;
    private List<NewsAdvisorySearchBean.ResultBean> searchlist;
    private NewsAdvisorySerchListAdapter newsAdvisorySerchListAdapter;
    private Presenter presenter;
    private android.support.design.widget.TabLayout searchTlToggle;
    private android.support.v4.view.ViewPager searchVpContent;
    private RecyclerView titleRv;
    private SmartRefreshLayout titleSrl;
    private String title1;


    @Override
    public void initViews() {
        Intent intent = getIntent();
        Bundle title = intent.getBundleExtra("title");
        title1 = title.getString("title");
        //初始化p层
        presenter = new Presenter();
        presenter.attach(this);
        type = new TypeToken<NewsAdvisorySearchBean>() {
        }.getType();
        presenter.doGetP(API.NewsAdvisorySearchUrl+"?"+"title="+title1+"&"+"page="+page+"&"+"count="+count, type);
        searchIvSo = (ImageView) findViewById(R.id.search_iv_so);
        searchEtContent = (EditText) findViewById(R.id.search_et_content);
        searchTvCancal = (TextView) findViewById(R.id.search_tv_cancal);
        searchLlBack = (LinearLayout) findViewById(R.id.search_ll_back);
        searchTlToggle =(TabLayout) findViewById(R.id.search_tl_toggle);
        searchVpContent =(ViewPager) findViewById(R.id.search_vp_content);
        searchEtContent.setText(title1);

        //填充viewpager的视图
        View titleView = View.inflate(this,R.layout.fragment_title,null);
        View authorView = View.inflate(this,R.layout.fragment_author,null);
        List<View> views = new ArrayList<>();
        views.add(titleView);
        views.add(authorView);
        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(views, new String[]{"标题", "作者"});

        //设置viewpager+tablayout
        searchVpContent.setAdapter(adapter);
        searchTlToggle.setupWithViewPager(searchVpContent);

        //寻找视图的控件
        titleRv = titleView.findViewById(R.id.title_rv_result);
        titleSrl = titleView.findViewById(R.id.title_srl_load);



    }

    @Override
    protected void initData() {
        super.initData();
        searchlist = new ArrayList<>();

        newsAdvisorySerchListAdapter = new NewsAdvisorySerchListAdapter(mcontext, searchlist);
        titleRv.setAdapter(newsAdvisorySerchListAdapter);
        titleRv.setLayoutManager(new LinearLayoutManager(mcontext));


    }

    @Override
    protected void setListener() {
        super.setListener();
        searchIvSo.setOnClickListener(this);
        searchTvCancal.setOnClickListener(this);
        titleSrl.setOnRefreshLoadMoreListener(this);
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_news_advisory_search_two;
    }

    @Override
    public void onSuccessIV(Object o) {
        if (o instanceof NewsAdvisorySearchBean) {
            NewsAdvisorySearchBean newsAdvisorySearchBean = (NewsAdvisorySearchBean) o;
            List<NewsAdvisorySearchBean.ResultBean> result = newsAdvisorySearchBean.getResult();
            if (!(result.size() > 0)) {
                Toast.makeText(mcontext, "暂无更多数据", Toast.LENGTH_SHORT).show();
            } else {
                searchlist.addAll(result);
                newsAdvisorySerchListAdapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    public void onFailed(String message) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_iv_so:
                searchMethod();
                break;
            case R.id.search_tv_cancal:
                finish();
                break;
        }
    }

    private void searchMethod() {
        String content = searchEtContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(mcontext, "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
        } else {
            title1=content;
            searchlist.clear();
            presenter.doGetP(API.NewsAdvisorySearchUrl+"?"+"title="+title1+"&"+"page="+page+"&"+"count="+count, type);

        }
    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        presenter.doGetP(API.NewsAdvisorySearchUrl+"?"+"title="+title1+"&"+"page="+page+"&"+"count="+count, type);
        titleSrl.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        searchlist.clear();
        page = 1;
        presenter.doGetP(API.NewsAdvisorySearchUrl+"?"+"title="+title1+"&"+"page="+page+"&"+"count="+count, type);
        titleSrl.finishRefresh();
    }
}
