package com.wd.tech.page.communitypage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.page.communitypage.bean.CommunityListBean;

import java.util.List;

/**
 * Author : 张自力
 * Created on time.
 *
 * 社区列表 适配器
 *
 */
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private Context mcontext;
    private List<CommunityListBean.ResultBean> list;

    public CommunityAdapter(Context mcontext, List<CommunityListBean.ResultBean> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mcontext,R.layout.community_itemone,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(list!=null){
            CommunityListBean.ResultBean resultBean = list.get(i);
            if(resultBean!=null){
                int id = resultBean.getId();//社区id
                int userId = resultBean.getUserId();//发布人id
                String headPic = resultBean.getHeadPic();//发布人头像
                String nickName = resultBean.getNickName();//发布人昵称
                String signature = resultBean.getSignature();//发布人签名
                int whetherVip = resultBean.getWhetherVip();//发布人是否为vip 1为是，2为否
                String content = resultBean.getContent();//内容
                String file = resultBean.getFile();//图片地址
                long publishTime = resultBean.getPublishTime();//发布时间
                int comment = resultBean.getComment();//评论数
                int praise = resultBean.getPraise();//点赞数
                int whetherFollow = resultBean.getWhetherFollow();//登录用户是否关注发布人，1=是2=否
                int whetherGreat = resultBean.getWhetherGreat();//登录用户是否点过赞，1=是2=否
                int power = resultBean.getPower();//登录用户是否的权限（用于判断是否为自己的帖子去删除），1=是2=否

                //赋值
                viewHolder.commuItemOneImg.setImageURI(headPic);
                viewHolder.commuItemOneName.setText(nickName);
                viewHolder.coummItemOneTime.setText(publishTime+"");
                viewHolder.coummItemOneBq.setText(signature);
                viewHolder.coummItemOneTitle.setText(signature);
                viewHolder.coummItemOneContent.setText(content);
                //viewHolder.coummItemOneExpandOrFold//全文点击事件
                viewHolder.coummItemOneComment.setText(comment+"");//评论数量
                viewHolder.coummItemOneShareNum.setText(praise+"");//点赞数量

                //评论没有写


            }

        }
    }

    @Override
    public int getItemCount() {
        if(list.size()>0){
            return list.size();
        }else{
            return 0;
        }
    }

    static class ViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView commuItemOneImg;
        private final TextView commuItemOneName;
        private final TextView coummItemOneTime;
        private final TextView coummItemOneBq;//个人标签
        private final TextView coummItemOneTitle;
        private final TextView coummItemOneContent;
        private final TextView coummItemOneExpandOrFold;//全文点击
        private final TextView coummItemOneComment;//评论数量
        private final TextView coummItemOneShareNum;//点赞数量
        private final TextView coummItemName;
        private final TextView coummItemTime;
        private final TextView coummItemName2;
        private final TextView coummItemTime2;
        private final TextView coummItemName3;
        private final TextView coummItemTime3;
        private final Button btnClickSeekPL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commuItemOneImg = itemView.findViewById(R.id.coumm_item_one_img);
            commuItemOneName = itemView.findViewById(R.id.coumm_item_one_name);
            coummItemOneTime = itemView.findViewById(R.id.coumm_item_one_time);
            coummItemOneBq = itemView.findViewById(R.id.coumm_item_one_bq);
            coummItemOneTitle = itemView.findViewById(R.id.coumm_item_one_title);
            coummItemOneContent = itemView.findViewById(R.id.coumm_item_one_content);
            coummItemOneExpandOrFold = itemView.findViewById(R.id.coumm_item_one_expand_or_fold);//全文
            coummItemOneComment = itemView.findViewById(R.id.coumm_item_one_comment);//评论数量
            coummItemOneShareNum = itemView.findViewById(R.id.coumm_item_one_share_num);  //点赞数量

            //评论区 最多展示三条评论
            coummItemName = itemView.findViewById(R.id.coumm_item_name);
            coummItemTime = itemView.findViewById(R.id.coumm_item_time);
            coummItemName2 = itemView.findViewById(R.id.coumm_item_name2);
            coummItemTime2 = itemView.findViewById(R.id.coumm_item_time2);
            coummItemName3 = itemView.findViewById(R.id.coumm_item_name3);
            coummItemTime3 = itemView.findViewById(R.id.coumm_item_time3);
            btnClickSeekPL = itemView.findViewById(R.id.btn_click_seekPL);//点击查看更多

        }
    }

}
