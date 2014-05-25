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
		            /* �����û��ṩ���ļ������Լ��ļ���Ӧ��ģʽ����һ�������.�ļ�����ϵͳ��Ϊ�㴴��һ���ģ� 
		             * ����Ϊʲô����ط�����FileNotFoundException�׳�����Ҳ�Ƚ����ơ���Context������������� 
		             *   public abstract FileOutputStream openFileOutput(String name, int mode) 
		             *   throws FileNotFoundException; 
		             * openFileOutput(String name, int mode); 
		             * ��һ�������������ļ����ƣ�ע��������ļ����Ʋ��ܰ����κε�/����/���ַָ�����ֻ�����ļ��� 
		             *          ���ļ��ᱻ������/data/data/Ӧ������/files/GroupDemoi.txt 
		             * �ڶ��������������ļ��Ĳ���ģʽ 
		             *          MODE_PRIVATE ˽�У�ֻ�ܴ�������Ӧ�÷��ʣ� �ظ�д��ʱ���ļ����� 
		             *          MODE_APPEND  ˽��   �ظ�д��ʱ�����ļ���ĩβ����׷�ӣ������Ǹ��ǵ�ԭ�����ļ� 
		             *          MODE_WORLD_READABLE ����  �ɶ� 
		             *          MODE_WORLD_WRITEABLE ���� �ɶ�д 
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
