package com.JessicaDong.GroupDemo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Notepad extends Activity {
	private EditText et;
	private String filename;
	 @Override 
	    public void onCreate(Bundle savedInstanceState) { 
	        super.onCreate(savedInstanceState); 
	        setContentView(R.layout.activity_notepad); 
	        Intent intent=getIntent(); 
	        filename =intent.getStringExtra("filename"); 
	         et=(EditText)findViewById(R.id.notepadet); 
	         try {
				editword(filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    } 
	 private void editword(String path)throws IOException
	    {
	    	  //String path  = list.get(arg2).toString(); 
	    	 // fileString=path;
	    	    	// TODO Auto-generated method stub
	    	    
	    	        File csv = new File(path); // CSV�ļ�
	    	        String line = ""; 
	    	        String shows="";
	    	        BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(csv));
						while ((line = br.readLine()) != null) { 
						    // ��һ�����ݷָ�ɶ���ֶ� 
							shows+=line;
					} }catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					et.setText(shows);
 	    
	   
	    } 
	 public void save2(View v)
	 {
		 String content = et.getText().toString()+","; 
		 //String filename = filename; 
		 if(filename==null) 
			 {Toast.makeText(Notepad.this, "no editable file", Toast.LENGTH_SHORT).show();
			 return;
			 }
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
	        	 String name = filename.substring(filename.lastIndexOf("/")+1,filename.length()); 
	        	 //���û���������ᱨ��java.lang.IllegalArgumentException: File /sdcard/vmap.rs contains a path separator
	        	 //openFileInput��openFileOutput�����ĵ�һ���������ܺ���·���ָ�����Ҳ����б�ܣ����Ե�һ���������ܴ� xxx/xxx/xx.txt ���֡� ����д���ļ�����Ҳ���� xxx.xx ���֣��ļ�������data/data/����/files����Ҫ��adb shell��ȥ������DDMS��
                 //����ֻ����file1����ȥ�͹���
	            FileOutputStream outputStream = openFileOutput(name, Activity.MODE_PRIVATE ); 
	            
	    	
	            outputStream.write(content.getBytes());  
	            outputStream.flush();  
	            outputStream.close();  
	            Toast.makeText(Notepad.this, "save successfully", Toast.LENGTH_LONG).show();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	          e.printStackTrace();  
	        }  
		
	 }
     public void returnbtn(View v)
     {
    	 Intent itIntent = new Intent(Notepad.this,EditGroup2.class);
    	 startActivity(itIntent);
    	 Notepad.this.finish();
     }
}
