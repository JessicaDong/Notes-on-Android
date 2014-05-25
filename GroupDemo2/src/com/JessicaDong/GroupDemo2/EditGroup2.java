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
	        //判断是否是空文件夹。不能用list==null,因为空文件夹时有list[0]
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
	                //保存每一格list单元格的数据 ，  
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
	          //看  file是否是目录，如果不是目录就加入到list，如果是目录，就把目录下所有的文件显示出来
	            if(f.isDirectory()){  
	                getAllFiles(f);  
	            }  
	            else{  
	            	this.list.add(f);  
	            }  
	        } 
	        else Toast.makeText(EditGroup2.this, "没有可选文件", Toast.LENGTH_LONG).show();
	    }  
	  private void editword(int arg2)throws IOException
	    {
	    	  String path  = list.get(arg2).toString(); 
	    	  fileString=path;
	    	    	// TODO Auto-generated method stub
	    	    
	    	        File csv = new File(path); // CSV文件
	    	        String line = ""; 
	    	        String shows="";
	    	        BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(csv));
						while ((line = br.readLine()) != null) { 
						    // 把一行数据分割成多个字段 
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
		        	 String name = filename.substring(filename.lastIndexOf("/")+1,filename.length()); 
		        	 //如果没有上面这句会报错java.lang.IllegalArgumentException: File /sdcard/vmap.rs contains a path separator
		        	 //openFileInput和openFileOutput函数的第一个参数不能含有路径分隔符，也就是斜杠，所以第一个参数不能传 xxx/xxx/xx.txt 这种。 可以写净文件名，也就是 xxx.xx 这种，文件保存在data/data/包名/files，需要用adb shell进去看或者DDMS。
                     //所以只传“file1”进去就够了
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
