package com.example.administrator.contactsaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MyListAdapter extends BaseAdapter {
    private List<PhoneInfo> list;
    private Context context;
    public MyListAdapter(List<PhoneInfo> list,Context context){
        this.context=context;
        this.list=list;
    }
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.colorlist,null);
            viewHolder.name=(TextView)convertView.findViewById(R.id.color_name);
            viewHolder.number=(TextView)convertView.findViewById(R.id.color_number);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.number.setText(list.get(position).getNumber());
        return convertView;
    }
    public class ViewHolder{
        TextView name;
        TextView number;
    }
}