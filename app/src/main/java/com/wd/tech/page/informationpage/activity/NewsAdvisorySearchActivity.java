package com.wd.tech.page.informationpage.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.AutoFlowLayout;
import com.example.library.FlowAdapter;
import com.wd.tech.R;
import com.wd.tech.baseclass.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsAdvisorySearchActivity extends BaseActivity {


    private android.widget.ImageView imgSearch;
    private android.widget.EditText searchEtContent;
    private android.widget.TextView searchTvCancal;
    private android.widget.LinearLayout searchLlFront;
    private com.example.library.AutoFlowLayout searchAflHot;
    private com.example.library.AutoFlowLayout searchAflLater;

    @Override
    public void initViews() {
        imgSearch = (ImageView) findViewById(R.id.img_search);
        searchEtContent = (EditText) findViewById(R.id.search_et_content);
        searchTvCancal = (TextView) findViewById(R.id.search_tv_cancal);
        searchLlFront = (LinearLayout) findViewById(R.id.search_ll_front);
        searchAflHot = (AutoFlowLayout) findViewById(R.id.search_afl_hot);
        searchAflLater = (AutoFlowLayout) findViewById(R.id.search_afl_later);
    }

    @Override
    protected void initData() {
        super.initData();
        setHotShow();
    }
    //设置最热搜索
    private void setHotShow() {
        final List<String> hotList = new ArrayList<>();
        hotListAddData(hotList);
        searchAflHot.setAdapter(new FlowAdapter(hotList) {
            @Override
            public View getView(final int i) {
                View view = View.inflate(mcontext, R.layout.item_streaming, null);
                TextView tvSo = view.findViewById(R.id.search_tv_so);
                tvSo.setText(hotList.get(i));

                tvSo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        searchEtContent.setText(hotList.get(i));
                        searchEtContent.setSelection(hotList.get(i).length());
                        searchMethod();
                    }
                });

                return view;
            }
        });
    }
    //填充最热搜索数据
    private void hotListAddData(List<String> hotList) {
        hotList.add("小米");
        hotList.add("雷军");
        hotList.add("华为科技");
        hotList.add("金钱");
        hotList.add("微信");
        hotList.add("区块链");
        hotList.add("子弹短信");
        hotList.add("民营企业");
        hotList.add("资本市场");
        hotList.add("锤子科技");
    }
    /**
     * 进行搜索方法
     */
    private void searchMethod() {
        String content = searchEtContent.getText().toString();
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this, "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("title",content);
            startAvtivity(NewsAdvisorySearchTwoActivity.class,"title",bundle);
        }
    }

    @Override
    protected void setListener() {
        super.setListener();
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMethod();
            }
        });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_news_advisory_search;
    }


}
