package com.wd.tech.page.messagepage.widget;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.wd.tech.R;
import com.wd.tech.page.messagepage.activity.AddAllActivity;
import com.wd.tech.utils.storageutil.DpToPxUtil;
/*
 *添加好友和分组的popupwind
 * */


public class AddAllPopupWindow extends PopupWindow {
    public AddAllPopupWindow(final Context context) {
        super(context);
        //
        int height = DpToPxUtil.dip2px(context, 85);
        int weight = DpToPxUtil.dip2px(context, 130);
        setHeight(height);
        setWidth(weight);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final View contentView = LayoutInflater.from(context).inflate(R.layout.popup_add_all,null, false);
        setContentView(contentView);

        //popupwindow 里面的两个点击事件
        LinearLayout linearLayoutFriendPopup = contentView.findViewById(R.id.linear_layout_friend_popup);
        LinearLayout linearLayoutGroupPopup = contentView.findViewById(R.id.linear_layout_group_popup);
        linearLayoutFriendPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAllActivity.class);
                intent.putExtra("forg","friend");
                context.startActivity(intent);
            }
        });
        /*linearLayoutGroupPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddAllActivity.class);
                intent.putExtra("forg","group");
                context.startActivity(intent);
            }
        });*/
    }
}
