package com.JessicaDong.GroupDemo2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import android.content.Context;  
import android.util.Log;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.CheckBox;  
import android.widget.ImageView;  
import android.widget.TextView;  
import android.widget.Toast;  
  
public class MycheckBoxAdapter extends BaseAdapter {  
  
    private LayoutInflater mInflater;  
  
    private ArrayList<Map<String, Object>> listData;  
 // ��������CheckBox��ѡ��״��
    private static HashMap<Integer,Boolean> isSelected;
  
    public Map<Integer,Map<String, Object>> selectMap = new HashMap<Integer, Map<String, Object>>();  
    //private Map<Integer, Integer> selectMap = new HashMap<Integer, Integer>();  
    public class ViewHolder {  
         
        public TextView title;  
        public CheckBox checkBox;  
    }  
  
    public MycheckBoxAdapter(Context context, ArrayList<Map<String, Object>> listData) {  
        this.mInflater = LayoutInflater.from(context);  
        this.listData = listData;  
        isSelected = new HashMap<Integer, Boolean>();
     // ��ʼ������
             initDate();
    }  
 // ��ʼ��isSelected������
    private void initDate(){
    for(int i=0; i<listData.size();i++) {
                getIsSelected().put(i,false);
            }
    }
    @Override  
    public int getCount() {  
        return listData.size();  
    }  
  
    @Override  
    public Object getItem(int position) {  
        return listData.get(position);  
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }

//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public View getView(int arg0, View arg1, ViewGroup arg2) {
//		// TODO Auto-generated method stub
//		return null;
//	}  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        ViewHolder holder = null;  
            if (convertView == null) {  
            // ���ViewHolder����  
            holder = new ViewHolder();  
                // ���벼�ֲ���ֵ��convertview  
            convertView = mInflater.inflate(R.layout.activity_item2, null);    
                holder.title = (TextView) convertView.findViewById(R.id.ItemTitle2);  
              holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);  
            // Ϊview���ñ�ǩ  
            convertView.setTag(holder);  
        } else {  
            // ȡ��holder  
            holder = (ViewHolder) convertView.getTag();  
            }  
  
  
        // ����list��TextView����ʾ  
            holder.title.setText((CharSequence) listData.get(position).get("ItemText"));
        // ����isSelected������checkbox��ѡ��״��  
       // holder.checkBox.setChecked(getIsSelected().get(position));  
            //��仰�ǳ���Ҫ������checkbox�Ƿ�ѡ�У�
            holder.checkBox.setChecked(listData.get(position).get("flag").equals("true"));
        return convertView;  
        
    }  
  
    public static HashMap<Integer,Boolean> getIsSelected() {  
        return isSelected;  
       // int n=isSelected.size();
       // Log.i("isselected", ""+n);
    }  
  
    public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {  
    	MycheckBoxAdapter.isSelected = isSelected;  
    }  
  
//    @Override  
//    public View getView(final int position, View convertView, ViewGroup parent) {  
//        ViewHolder holder = null;  
//        if (convertView == null) {  
//            holder = new ViewHolder();  
//            convertView = mInflater.inflate(R.layout.activity_item2, null);  
//            final View view = convertView;  
//           
//            holder.title = (TextView) convertView.findViewById(R.id.ItemTitle2);  
//            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);  
//            holder.checkBox.setOnClickListener(new OnClickListener() {  
//            	
//                @Override  
//                public void onClick(View v) {  
//                	if(selectMap.size()<2){
//                    if (selectMap.get(position) != null) {  
//                        selectMap.remove(position);  
//                    } else {  
//                    	//ArrayList<Map<String, Object>> arrayList =listData.get(position);
//                        selectMap.put(position, listData.get(position));  
//                    }  
//                    Toast.makeText(view.getContext(),  
//                            "��ѡ����:" + selectMap.size() + "��.",  
//                            Toast.LENGTH_SHORT).show();  
//                }  
//                	//else {Toast.makeText(view.getContext(),  "You have chosen two files",   Toast.LENGTH_SHORT).show();  }
//                
//            }}
//           
//      
//           
//            	);  
//            int n = selectMap.size();
//            Log.i("selectmap2", ""+n);
//            
//            convertView.setTag(holder);  
//        } else {  
//            holder = (ViewHolder) convertView.getTag();  
//        }  
//  
//      
//        holder.title.setText((CharSequence) listData.get(position).get("ItemText"));  
//  
//        if (selectMap.get(position) != null) {  
//            holder.checkBox.setChecked(true);  
//        } else {  
//            holder.checkBox.setChecked(false);  
//        }  
//  
//        return convertView;  
//        
//    }  
    

    
}  