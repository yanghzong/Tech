package com.wd.tech.page.informationpage.activity;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.informationpage.adapter.NewsAdvisoryClassFiyAdapter;
import com.wd.tech.page.informationpage.bean.NewsAdvisoryClassFiyBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClassfiyActivity extends BaseActivity implements IView {


    private RecyclerView rvNewsAdvisorySort;
    private List<NewsAdvisoryClassFiyBean.ResultBean> classfiybean;
    private Type type;
    private NewsAdvisoryClassFiyAdapter newsAdvisoryClassFiyAdapter;
    private Presenter presenter;

    @Override
    public void initViews() {
        //初始化p层
        presenter = new Presenter();
        presenter.attach(this);
        type = new TypeToken<NewsAdvisoryClassFiyBean>(){}.getType();
        presenter.doGetP(API.NewsAdvisoryClassFiyUrl,type);
        //初始化控件
        rvNewsAdvisorySort = findViewById(R.id.rv_newsadvisory_sort);


    }

    @Override
    protected void initData() {
        super.initData();
        classfiybean = new ArrayList<>();
        //初始化adapter
        newsAdvisoryClassFiyAdapter = new NewsAdvisoryClassFiyAdapter(mcontext,classfiybean);
        rvNewsAdvisorySort.setAdapter(newsAdvisoryClassFiyAdapter);
        //设置布局管理器
        rvNewsAdvisorySort.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected void setListener() {
        super.setListener();
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_classfiy;
    }

    @Override
    public void onSuccessIV(Object o) {
        if (o instanceof NewsAdvisoryClassFiyBean){
            NewsAdvisoryClassFiyBean newsAdvisoryClassFiyBean=(NewsAdvisoryClassFiyBean) o;
            List<NewsAdvisoryClassFiyBean.ResultBean> result = newsAdvisoryClassFiyBean.getResult();
            if (result!=null){
                classfiybean.clear();
                classfiybean.addAll(result);
            }
            newsAdvisoryClassFiyAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.datach();

    }
}
