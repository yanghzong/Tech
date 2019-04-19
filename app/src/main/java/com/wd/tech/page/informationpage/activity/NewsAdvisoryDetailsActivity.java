package com.wd.tech.page.informationpage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;
import com.wd.tech.iview.IView;
import com.wd.tech.page.informationpage.WebViewFragment;
import com.wd.tech.page.informationpage.bean.NewsAdvisoryDetailsBean;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.utils.datarequestutil.API;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsAdvisoryDetailsActivity extends BaseActivity implements IView {

    private SmartRefreshLayout newsSrlLoad;
    private RecyclerView newsRvBody;
    private ImageView newsIvBack;
    private EditText newsEtTalk;
    private ImageView newsIvNews;
    private TextView newsTvTalkNum;
    private CheckBox newsCbZan;
    private TextView newsTvZanNum;
    private CheckBox newsIvBook;
    private ImageView newsIvShare;
    private NewsAdvisoryDetailsBean newsAdvisoryDetailsBean;
    private TextView newsadvisorydetailsTvTime;
    private TextView newsadvisorydetailsTvTitle;
    private SimpleDraweeView newsadvisorydetailsSdvIcon;
    private TextView newsadvisorydetailsTvSummary;
    private WebView newsadvisorydetailsWvContent;
    private com.example.library.AutoFlowLayout newsadvisorydetailsAflSort;
    private Type type;
    private Presenter presenter;
    private int cid1;

    @Override
    public void initViews() {
        Intent intent = getIntent();
        Bundle cid = intent.getBundleExtra("cid");
        cid1 = cid.getInt("cid");
        presenter = new Presenter();
        presenter.attach(this);
        type = new TypeToken<NewsAdvisoryDetailsBean>(){}.getType();
        presenter.doGetP(API.NewsAdvisoryDetailsUrl+"?"+"id="+cid1, type);
        newsIvBack = (ImageView) findViewById(R.id.news_iv_back);
        newsEtTalk = (EditText) findViewById(R.id.news_et_talk);
        newsIvNews = (ImageView) findViewById(R.id.news_iv_news);
        newsTvTalkNum = (TextView) findViewById(R.id.news_tv_talkNum);
        newsCbZan = (CheckBox) findViewById(R.id.news_cb_zan);
        newsTvZanNum = (TextView) findViewById(R.id.news_tv_zanNum);
        newsIvBook = (CheckBox) findViewById(R.id.news_iv_book);
        newsIvShare = (ImageView) findViewById(R.id.news_iv_share);
        newsadvisorydetailsTvTime = findViewById(R.id.newsadvisorydetails_tv_time);
        newsadvisorydetailsTvTitle = findViewById(R.id.newsadvisorydetails_tv_title);
        newsadvisorydetailsSdvIcon = findViewById(R.id.newsadvisorydetails_sdv_icon);
        newsadvisorydetailsTvSummary = findViewById(R.id.newsadvisorydetails_tv_summary);
        newsadvisorydetailsWvContent = findViewById(R.id.newsadvisorydetails_wv_content);
        newsadvisorydetailsAflSort = findViewById(R.id.newsadvisorydetails_afl_sort);

    }



    @Override
    protected int getContentViewID() {
        return R.layout.activity_newsadvisorydetails;
    }


    @Override
    public void onSuccessIV(Object o) {

        if (o instanceof  NewsAdvisoryDetailsBean){
            newsAdvisoryDetailsBean= (NewsAdvisoryDetailsBean)o;
            NewsAdvisoryDetailsBean.ResultBean result = newsAdvisoryDetailsBean.getResult();
            if (result!=null){
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(result.getReleaseTime()));
                newsadvisorydetailsTvTime.setText(time);//发布时间
                newsadvisorydetailsTvTitle.setText(result.getTitle());//标题
                newsadvisorydetailsTvSummary.setText(result.getSummary());//标题
                newsadvisorydetailsSdvIcon.setImageURI(result.getThumbnail());
                WebSettings webSettings =newsadvisorydetailsWvContent.getSettings();
               webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
               webSettings.setJavaScriptEnabled(true);//支持js
                newsadvisorydetailsWvContent.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // 得到 URL 可以传给应用中的某个 WebView 页面加载显示
                    return true;
                }
            });
            final String content = result.getContent();
            if (content != null) {
                newsadvisorydetailsWvContent.loadDataWithBaseURL(null, WebViewFragment.getNewContent(content), "text/html", "UTF-8", null);
            }
            }
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
