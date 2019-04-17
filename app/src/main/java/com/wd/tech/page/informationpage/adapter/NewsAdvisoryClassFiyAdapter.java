package com.wd.tech.page.informationpage.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.page.informationpage.bean.NewsAdvisoryClassFiyBean;

import java.util.List;

public class NewsAdvisoryClassFiyAdapter extends RecyclerView.Adapter<NewsAdvisoryClassFiyAdapter.ViewHolder> {
    private Context context;
    private List<NewsAdvisoryClassFiyBean.ResultBean> list;

    public NewsAdvisoryClassFiyAdapter(Context context, List<NewsAdvisoryClassFiyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(View.inflate(context, R.layout.item_newsadvisoryclassfiy, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.sdvSortIcon.setImageURI(list.get(i).getPic());
        viewHolder.tvSortName.setText(list.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdvSortIcon;
        private final TextView tvSortName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdvSortIcon = itemView.findViewById(R.id.sort_sdv_icon);
            tvSortName = itemView.findViewById(R.id.sort_tv_name);
        }
    }
}
