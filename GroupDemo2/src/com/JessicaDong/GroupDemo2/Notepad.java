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
