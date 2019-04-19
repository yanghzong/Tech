package com.wd.tech.page.informationpage.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class TitleFragmentPagerAdapter extends PagerAdapter {

    private List<View> viewList;//数据源
    private String[] titles;

    public TitleFragmentPagerAdapter(List<View> viewList, String[] titles) {
        this.viewList = viewList;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }
}
