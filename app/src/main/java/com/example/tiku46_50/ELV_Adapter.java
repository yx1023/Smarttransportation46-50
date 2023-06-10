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

public class ELV_Adapter extends BaseExpandableListAdapter {
    private List<Car>list=new ArrayList<>();
    private Context context;
    public ELV_Adapter(Context context,List<Car>list){
        this.list=list;
        this.context=context;
    }



    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getBusline().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getBusline().get(childPosition);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.g_item,parent,false);
            g=new G();
            g.number=convertView.findViewById(R.id.number);
            g.tv1=convertView.findViewById(R.id.tv1);
            g.tv2=convertView.findViewById(R.id.tv2);
            g.time1=convertView.findViewById(R.id.time1);
            g.time2=convertView.findViewById(R.id.time2);
            g.jiantou=convertView.findViewById(R.id.jiantou);
            convertView.setTag(g);
        }else {
            g= (G) convertView.getTag();
        }

        if(isExpanded){
            g.jiantou.setImageResource(R.drawable.xiajiantou);
        }else {
            g.jiantou.setImageResource(R.drawable.youjiantou);
        }
        g.number.setText(list.get(groupPosition).getId()+"号线");
        g.tv1.setText(list.get(groupPosition).getBusline().get(0)+"——"+list.get(groupPosition).getBusline().get(list.get(groupPosition).getBusline().size()-1));
        g.tv2.setText("票价：￥"+list.get(groupPosition).getFares()+"   里程："+list.get(groupPosition).getMileage()+"km");
        g.time1.setText(list.get(groupPosition).getTime().replace("~","-"));
        g.time2.setText(list.get(groupPosition).getTime().split("~")[0]+"-"+list.get(groupPosition).getTime().split("~")[1]);



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        C c;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.c_item,parent,false);
            c=new C();
            c.tv1=convertView.findViewById(R.id.tv1);
            c.tv2=convertView.findViewById(R.id.tv2);
            convertView.setTag(c);
        }else {
            c= (C) convertView.getTag();
        }
        if(childPosition==0){
            c.tv1.setText("起点：");
            c.tv1.setVisibility(View.VISIBLE);
            c.tv2.setText(list.get(groupPosition).getBusline().get(childPosition));
        } else if (childPosition==list.get(groupPosition).getBusline().size()-1) {
            c.tv1.setText("终点：");
            c.tv1.setVisibility(View.VISIBLE);
            c.tv2.setText(list.get(groupPosition).getBusline().get(childPosition));
        }else {
            c.tv1.setVisibility(View.INVISIBLE);
            c.tv2.setText(list.get(groupPosition).getBusline().get(childPosition));
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    static class G{
        TextView number;
        TextView tv1;
        TextView tv2;
        TextView time1;
        TextView time2;
        ImageView jiantou;
    }
    static class C{

        TextView tv1;
        TextView tv2;

    }
}
