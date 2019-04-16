package com.wd.tech.page.messagepage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wd.tech.MainActivity;
import com.wd.tech.R;

public class ContactListAdapter extends BaseExpandableListAdapter {
    public String[] groups;
    public String[][] children;
    public Context mContext;

    LayoutInflater mInflater;
    public ContactListAdapter(Context mContext,String[] groups, String[][] children) {
        this.groups = groups;
        this.children = children;
        this.mContext = mContext;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //获取的群体数量，得到groups里元素的个数
    @Override
    public int getGroupCount() {
        return groups.length;
    }
    //取得指定组中的children个数，就是groups中每一个条目中的个数
    @Override
    public int getChildrenCount(int i) {
        return children[i].length;
    }
    //获取与给定的组相关的数据，得到数组groups中元素的数据
    @Override
    public Object getGroup(int i) {
        return groups[i];
    }
    //获取与孩子在给定的组相关的数据,得到数组children中元素的数据
    @Override
    public Object getChild(int i, int i1) {
        return children[i][i1];
    }
    //获取组在给定的位置编号，即groups中元素的ID
    @Override
    public long getGroupId(int i) {
        return i;
    }
    //获取在给定的组的children的ID，也就是children中元素的ID
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    //表示孩子是否和组ID是跨基础数据的更改稳定
    @Override
    public boolean hasStableIds() {
        return true;
    }
    //获取一个视图显示给定组，存放groups
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
//        TextView textView = getGenericView(24);
//        textView.setText(getGroup(i).toString());
//        return textView;
        if(view==null){
            view = mInflater.inflate(R.layout.item_contact_team,null);
        }
        GroupViewHolder gvh = new GroupViewHolder();
        gvh.tv_team_teamname = view.findViewById(R.id.tv_team_teamname);
        gvh.tv_team_teamname.setText(getGroup(i).toString());
        return view;
    }
    //获取一个视图显示在给定的组 的儿童的数据，就是存放children
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
//        TextView textView = getGenericView(18);
//        textView.setText(getChild(i, i1).toString());
        if(view==null){
            view = mInflater.inflate(R.layout.item_contact_member,null);
        }
        ChildViewHolder cvh = new ChildViewHolder();
        cvh.tv_member_name = view.findViewById(R.id.tv_member_name);
        cvh.tv_member_signature = view.findViewById(R.id.tv_member_signature);
        cvh.tv_member_name.setText(getChild(i,i1).toString());

        return view;

    }
    //孩子在指定的位置是可选的，即：children中的元素是可点击的
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    private class GroupViewHolder{
       TextView tv_team_teamname;

    }
    private class ChildViewHolder{
       TextView tv_member_signature;
       TextView tv_member_name;
    }

}
