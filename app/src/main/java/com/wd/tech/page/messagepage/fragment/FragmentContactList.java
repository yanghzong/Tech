package com.wd.tech.page.messagepage.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.baseclass.BaseFragment;
import com.wd.tech.page.messagepage.adapter.ContactListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContactList extends BaseFragment {
    public String[] groups = { "魏", "蜀", "吴" };
    ContactListAdapter contactListAdapter;
    public String[][] children = {
            { "曹操", "荀彧", "郭嘉", "夏侯惇", "许褚"},
            { "刘备", "诸葛亮", "关羽", "赵云", "庞统", "魏延", "马超" },
            { "孙权", "周瑜", "鲁肃", "黄盖", "吕蒙"},
    };


    @Override
    protected void initView(View view) {
        contactListAdapter = new ContactListAdapter(getContext(),groups,children);
        ExpandableListView expandableListView = view.findViewById(R.id.expan_listview);
       expandableListView.setAdapter(contactListAdapter);
       expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
           @Override
           public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
               Toast.makeText(getContext(), "当前点击的是：："+groups[i]+"国的"+children[i][i1], Toast.LENGTH_SHORT).show();
               return false;
           }
       });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.fragment_contact_list;
    }
}
