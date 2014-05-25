package com.JessicaDong.GroupDemo2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatGroup extends Activity{

	private EditText creatEditText;
	private EditText creatfilename;
	//private List<Map<Integer,String>> filenameslist; 
	 public int i=0;
	 private Map<Integer, String> map ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creatgroup);
		 creatEditText = (EditText)findViewById(R.id.editText1);
		 creatfilename = (EditText)findViewById(R.id.editText2);
		// filenameslist = new ArrayList<Map<Integer,String>>();
		// map = new HashMap<Integer, String>();
	}
	 public void save(View v)
		{
		
			 String content = creatEditText.getText().toString()+","; 
			 String filename = creatfilename.getText().toString(); 
				Log.i("creatgroup", "save");
		        try {  
		            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的， 
		             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的 
		             *   public abstract FileOutputStream openFileOutput(String name, int mode) 
		             *   throws FileNotFoundException; 
		             * openFileOutput(String name, int mode); 
		             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名 
		             *          该文件会被保存在/data/data/应用名称/files/GroupDemoi.txt 
		             * 第二个参数，代表文件的操作模式 
		             *          MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖 
		             *          MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件 
		             *          MODE_WORLD_READABLE 公用  可读 
		             *          MODE_WORLD_WRITEABLE 公用 可读写 
		             *  */  
		        	//String filename="GroupDemo"+i+".txt";
		        	Log.i("creatgroup", filename);
		        	//fileNames[i]="GroupDemo"+i+".txt";
		            FileOutputStream outputStream = openFileOutput(filename, Activity.MODE_APPEND ); 
		            
		    		//map.put(i, filename);
		    		//filenameslist.add(map);
		            outputStream.write(content.getBytes());  
		            outputStream.flush();  
		            outputStream.close();  
		            Toast.makeText(CreatGroup.this, "save successfully", Toast.LENGTH_LONG).show();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		          e.printStackTrace();  
		        }  
		        creatEditText.setText("");
			i++;
		}
	 public void creat(View v)
	 {
		 Intent itIntent=new Intent(CreatGroup.this, CreatGroup.class);
		 startActivity(itIntent);
		 CreatGroup.this.finish();
	 }
	 public void select(View v)
	 {
		 Intent itIntent=new Intent(CreatGroup.this, SelectGroup2.class);
		 startActivity(itIntent);
		 CreatGroup.this.finish();
	 }
	 public void edit(View v)
	 {
		 Intent itIntent=new Intent(CreatGroup.this, EditGroup2.class);
		 startActivity(itIntent);
		 CreatGroup.this.finish();
	 }
	 public void delete(View v)
	 {
		 Intent itIntent=new Intent(CreatGroup.this, DeleteGroup.class);
		 startActivity(itIntent);
		 CreatGroup.this.finish();
	 }
}
