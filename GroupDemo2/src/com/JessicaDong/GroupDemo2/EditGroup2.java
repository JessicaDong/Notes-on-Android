package com.JessicaDong.GroupDemo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class EditGroup2 extends Activity{
	
	 ListView i ;  
	    EditText editword1;
	      
	      String fileString; 
	    //data   
	    private ArrayList<File> list ;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editgroup);
		  i = (ListView) findViewById(R.id.filelist);  
	        list = new ArrayList<File>();   
	         editword1=(EditText)findViewById(R.id.editword); 
	        getAllFiles(new File("/data/data/com.JessicaDong.GroupDemo2/files"));  
	        SimpleAdapter ladapter = new SimpleAdapter(this,getMapData(list),R.layout.activity_item, new String[]{"ItemText"},new int[]{R.id.ItemTitle}); 
	        i.setAdapter(ladapter);  
	         
//	        TextView empty = new TextView(this);  
//	        empty.setText("empty!");  
//	        i.setEmptyView(empty);  
	        //�ж��Ƿ��ǿ��ļ��С�������list==null,��Ϊ���ļ���ʱ��list[0]
	        if(list.size()==0) Toast.makeText(EditGroup2.this, "no edittable files", Toast.LENGTH_SHORT).show();
	        else {
	        i.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent it3 = new Intent();
					it3.setClass(EditGroup2.this, Notepad.class);
					it3.putExtra("filename", list.get(arg2).toString());
					EditGroup2.this.startActivity(it3);
					
//					try {
//						editword(arg2);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
				}
			});
	        }
	}
	 private ArrayList<Map<String, Object>> getMapData(ArrayList<File> list){  
	        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();  
	        HashMap<String,Object> item;  
	        int i = 0 ;  
	        for(i=0;i<list.size();i++){  
	                item = new HashMap<String,Object>();  
	                String path  = list.get(i).toString();  
	                String name = path.substring(path.lastIndexOf("/")+1,path.length());  
	                //����ÿһ��list��Ԫ������� ��  
	                item.put("ItemText",name);  
	               // item.put("ItemTitle", path);  
	                 
	                data.add(item);  
	        }  
	        return data;  
	    }  
	  private void getAllFiles(File root){  
          
	        File files[] = root.listFiles();  
	          
	        if(files != null)  
	        for(File f:files){  
	          //��  file�Ƿ���Ŀ¼���������Ŀ¼�ͼ��뵽list�������Ŀ¼���Ͱ�Ŀ¼�����е��ļ���ʾ����
	            if(f.isDirectory()){  
	                getAllFiles(f);  
	            }  
	            else{  
	            	this.list.add(f);  
	            }  
	        } 
	        else Toast.makeText(EditGroup2.this, "û�п�ѡ�ļ�", Toast.LENGTH_LONG).show();
	    }  
	  private void editword(int arg2)throws IOException
	    {
	    	  String path  = list.get(arg2).toString(); 
	    	  fileString=path;
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
					editword1.setText(shows);
    	    
	   
	    } 
	  public void creat(View v)
		 {
			 Intent itIntent=new Intent(EditGroup2.this, CreatGroup.class);
			 startActivity(itIntent);
			 EditGroup2.this.finish();
		 }
		 public void select(View v)
		 {
			 Intent itIntent=new Intent(EditGroup2.this, SelectGroup2.class);
			 startActivity(itIntent);
			 EditGroup2.this.finish();
		 }
		 public void edit(View v)
		 {
//			 Intent itIntent=new Intent(CreatGroup.this, EditGroup.class);
//			 startActivity(itIntent);
		 }
		 public void delete(View v)
		 {
			 Intent itIntent=new Intent(EditGroup2.this, DeleteGroup.class);
			 startActivity(itIntent);
			 EditGroup2.this.finish();
		 }
		 public void save2(View v)
		 {
			 String content = editword1.getText().toString()+","; 
			 String filename = fileString; 
			 if(filename==null) 
				 {Toast.makeText(EditGroup2.this, "no editable file", Toast.LENGTH_SHORT).show();
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
		            Toast.makeText(EditGroup2.this, "save successfully", Toast.LENGTH_LONG).show();  
		        } catch (FileNotFoundException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		          e.printStackTrace();  
		        }  
			
		 }
}
