package com.wd.tech.page.informationpage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.page.informationpage.bean.NewsAdvisorySearchBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdvisorySerchListAdapter extends RecyclerView.Adapter<NewsAdvisorySerchListAdapter.ViewHolder> {
    private Context context;
    private List<NewsAdvisorySearchBean.ResultBean> list;


    public NewsAdvisorySerchListAdapter(Context context, List<NewsAdvisorySearchBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(context, R.layout.item_newsadvisorysearchlist, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.titleTvName.setText(list.get(i).getTitle());
        viewHolder.titleTvSource.setText(list.get(i).getSource());
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(list.get(i).getReleaseTime()));
        viewHolder.titleTvTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTvName;
        private TextView titleTvSource;
        private TextView titleTvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTvName =itemView.findViewById(R.id.title_tv_name);
            titleTvSource =itemView. findViewById(R.id.title_tv_source);
            titleTvTime = itemView.findViewById(R.id.title_tv_time);
        }
    }
}
