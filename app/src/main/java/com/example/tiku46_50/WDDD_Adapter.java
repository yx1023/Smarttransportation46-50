package com.example.tiku46_50;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WDDD_Adapter extends BaseExpandableListAdapter {

    List<DD>list=new ArrayList<>();
    Context context;
    public WDDD_Adapter(Context context,List<DD>list){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getData().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getData().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        G g;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.wddd_item_g,parent,false);
            g=new G();
            g.iv=convertView.findViewById(R.id.iv);
            g.t1=convertView.findViewById(R.id.tv1);
            g.t2=convertView.findViewById(R.id.tv2);
            g.t3=convertView.findViewById(R.id.tv3);
            convertView.setTag(g);
        }else {
            g= (G) convertView.getTag();
        }
        if(isExpanded){
            g.iv.setImageResource(R.drawable.xiajiantou);
        }else {
            g.iv.setImageResource(R.drawable.youjiantou);
        }
        g.t1.setText(list.get(groupPosition).getLine());
        g.t2.setText("票价：￥"+list.get(groupPosition).getPrice());
        g.t3.setText("订单编号"+list.get(groupPosition).getNum());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        C c;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.wddd_item_c,parent,false);
            c=new C();

            c.t1=convertView.findViewById(R.id.tv1);
            c.t2=convertView.findViewById(R.id.tv2);

            convertView.setTag(c);
        }else {
            c= (C) convertView.getTag();
        }
        if(childPosition==0){
            c.t1.setVisibility(View.VISIBLE);
        }else {
            c.t1.setVisibility(View.INVISIBLE);
        }
        c.t2.setText(list.get(groupPosition).getData().get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class G{
        TextView t1;
        TextView t2;
        TextView t3;
        ImageView iv;
    }
    static class C{
        TextView t1;
        TextView t2;

    }
}
