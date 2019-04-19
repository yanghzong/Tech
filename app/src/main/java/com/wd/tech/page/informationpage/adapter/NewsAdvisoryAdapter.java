package com.wd.tech.page.informationpage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.page.informationpage.bean.NewsAdvisoryBean;
import com.wd.tech.page.informationpage.bean.NewsBannerBean;

import java.util.List;

public class NewsAdvisoryAdapter extends RecyclerView.Adapter<NewsAdvisoryAdapter.ViewHolder> {

    private Context context;
    private List<NewsAdvisoryBean.ResultBean> list;

    public NewsAdvisoryAdapter(Context context, List<NewsAdvisoryBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=View.inflate(context, R.layout.item_newadvisory,null);
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        NewsAdvisoryBean.ResultBean resultBean = list.get(position);
        holder.newsAdvisorySdv.setImageURI(resultBean.getThumbnail());
        holder.newAdvisoryContent.setText(resultBean.getSummary());
        holder.newsAdvisoryTitle.setText(resultBean.getSource());
        if (resultBean.getWhetherPay()==1){
            holder.newsAdvisoryBook.setImageResource(R.mipmap.common_icon_collect_s_hdpi);
        }else{
            holder.newsAdvisoryBook.setImageResource(R.mipmap.black_star);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(position).getId();
                if (commodityClickListener!=null){
                    commodityClickListener.onCommodityClick(id);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView newsAdvisoryBook;
        private final ImageView newsAdvisoryShare;
        private final SimpleDraweeView newsAdvisorySdv;
        private final TextView newsAdvisoryBookNum;
        private final TextView newAdvisoryContent;
        private final TextView newsAdvisoryReleaseTime;
        private final TextView newsAdvisoryTitle;
        private final TextView newsAdvisoryShareNum;

        public ViewHolder(View itemView) {
            super(itemView);
            newsAdvisoryBook = itemView.findViewById(R.id.newsadvisory_iv_book);
            newsAdvisoryShare = itemView.findViewById(R.id.newsadvisory_iv_share);
            newsAdvisorySdv = itemView.findViewById(R.id.newsadvisory_sdv_icon);
            newsAdvisoryBookNum = itemView.findViewById(R.id.newsadvisory_tv_bookNum);
            newAdvisoryContent = itemView.findViewById(R.id.newsadvisory_tv_content);
            newsAdvisoryReleaseTime = itemView.findViewById(R.id.newsadvisory_tv_releaseTime);
            newsAdvisoryTitle = itemView.findViewById(R.id.newsadvisory_tv_title);
            newsAdvisoryShareNum = itemView.findViewById(R.id.newsadvisory_tv_shareNum);
        }
    }
    public interface OnCommodityClickListener {
        void onCommodityClick(int id);
    }

    private  OnCommodityClickListener commodityClickListener;

    public void setOnProductClickListener(OnCommodityClickListener listener) {
        this.commodityClickListener = listener;
    }
}
