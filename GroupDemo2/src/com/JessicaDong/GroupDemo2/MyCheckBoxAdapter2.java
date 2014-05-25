package com.JessicaDong.GroupDemo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
public class MyCheckBoxAdapter2 extends BaseAdapter {
Context context;
ArrayList<Map<String, Object>> listData;
//记录checkbox的状态
HashMap<Integer, Boolean> state = new HashMap<Integer, Boolean>();
//构造函数
public MyCheckBoxAdapter2(Context context, ArrayList<Map<String, Object>> listData) {
this.context = context;
this.listData = listData;
}
@Override
public int getCount() {
// TODO Auto-generated method stub
return listData.size();
}
@Override
public Object getItem(int position) {
// TODO Auto-generated method stub
return listData.get(position);
}
@Override
public long getItemId(int position) {
// TODO Auto-generated method stub
return position;
}
// 重写View
@Override
public View getView(final int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
LayoutInflater mInflater = LayoutInflater.from(context);
convertView = mInflater.inflate(R.layout.activity_item2, null);
//ImageView image = (ImageView) convertView.findViewById(R.id.friend_image);
//image.setBackgroundResource((Integer) listData.get(position).get("friend_image"));
TextView username = (TextView) convertView.findViewById(R.id.ItemTitle2);
username.setText((String) listData.get(position).get("ItemText"));
//TextView id = (TextView) convertView.findViewById(R.id.friend_id);
//id.setText((String) listData.get(position).get("friend_id"));
CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox1);
check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
@Override
public void onCheckedChanged(CompoundButton buttonView,
boolean isChecked) {
// TODO Auto-generated method stub
if (isChecked) {
state.put(position, isChecked);
} else {
state.remove(position);
}
}
});
check.setChecked((state.get(position) == null ? false : true));
return convertView;
}
}