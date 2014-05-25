package com.JessicaDong.GroupDemo2;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class DeleteGroup extends Activity{

	private ListView deletelist;
	  private ArrayList<File> list ;  
	  private SimpleAdapter ladapter;
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_deletegroup);
        deletelist=(ListView)findViewById(R.id.filelist);
        list=new ArrayList<File>();
        getAllFiles(new File("/data/data/com.JessicaDong.GroupDemo2/files"));  
        ladapter = new SimpleAdapter(this,getMapData(list),R.layout.activity_item, new String[]{"ItemText"},new int[]{R.id.ItemTitle}); 
        deletelist.setAdapter(ladapter);  
        
        TextView empty = new TextView(this);  
        empty.setText("no deletable files");  
        deletelist.setEmptyView(empty); 
        //判断是否是空文件夹。不能用list==null,因为空文件夹时有list[0]
        if(list.size()==0) Toast.makeText(DeleteGroup.this, "no deletable files", Toast.LENGTH_SHORT ).show();
        else{
        deletelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				deleteFile(arg2);
			}
		});
        }
	}
	//注意这里要加权限：<!-- 在SDCard中创建与删除文件权限 -->
//    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
//    <!-- 往SDCard写入数据权限 -->
//    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	private void deleteFile(int arg2)
	{
		File filed=list.get(arg2);
		if(filed.exists())
		filed.delete();
		 Intent itIntent=new Intent(DeleteGroup.this, DeleteGroup.class);
		 startActivity(itIntent);
		 DeleteGroup.this.finish();
//		ladapter.notifyDataSetChanged();  
//		Toast.makeText(DeleteGroup.this, "delete successfully", Toast.LENGTH_LONG).show();
	}
	private void getAllFiles(File root)
	{
		File files[]=root.listFiles();
		if (files!=null) {
			for(File f:files)
			{
				if(f.isDirectory())
					getAllFiles(f);
				else list.add(f);
			}
			
		}
		else Toast.makeText(DeleteGroup.this, "no deletable files", Toast.LENGTH_LONG).show();
	}
	
	private ArrayList<Map<String, Object>> getMapData(ArrayList<File> list){  
		 if(list.size()==0) Toast.makeText(DeleteGroup.this, "no deletable files", Toast.LENGTH_LONG).show();
		 
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();  
        HashMap<String,Object> item;  
        int i = 0 ;  
        for(i=0;i<list.size();i++){  
        	
                item = new HashMap<String,Object>();  
                String path  = list.get(i).toString();  
                
                String name = path.substring(path.lastIndexOf("/")+1,path.length());  
                //保存每一格list单元格的数据 ，  
                item.put("ItemText",name);  
               // item.put("ItemTitle", path);  
                 
                data.add(item);  
        }  
		 
        return data;  
		 
    }  
	 public void creat(View v)
	 {
		 Intent itIntent=new Intent(DeleteGroup.this, CreatGroup.class);
		 startActivity(itIntent);
		 DeleteGroup.this.finish();
	 }
	 public void select(View v)
	 {
		 Intent itIntent=new Intent(DeleteGroup.this, SelectGroup2.class);
		 startActivity(itIntent);
		 DeleteGroup.this.finish();
	 }
	 public void edit(View v)
	 {
		 Intent itIntent=new Intent(DeleteGroup.this, EditGroup2.class);
		 startActivity(itIntent);
		 DeleteGroup.this.finish();
	 }
	 public void delete(View v)
	 {
//		 Intent itIntent=new Intent(DeleteGroup.this, DeleteGroup.class);
//		 startActivity(itIntent);
//		 DeleteGroup.this.finish();
	 }
}
