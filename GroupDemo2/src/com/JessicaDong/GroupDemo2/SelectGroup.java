package com.JessicaDong.GroupDemo2;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;  
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Random;
import java.util.StringTokenizer;

import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.widget.AdapterView;
import android.widget.Button;  
import android.widget.ListView;  
import android.widget.SimpleAdapter;  
import android.widget.TextView;  
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class SelectGroup extends Activity {  
      
    //view   
    TextView tView ;  
    ListView i ;  
    Button btn ;   
    private MycheckBoxAdapter mycheckBoxAdapter; 
      
    //data   
    private ArrayList<File> list ;  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_selectgroup);  
          
        tView = (TextView)findViewById(R.id.word);   
          tView.setText("");
//        //btn = (Button) findViewById(R.id.go);  
//        tView = new TextView(this);  
//        tView.setText("get all files in your sd card");  
          
        //init data  
        i = (ListView) findViewById(R.id.filelist);  
        list = new ArrayList<File>();   
          
        getAllFiles(new File("/data/data/com.JessicaDong.GroupDemo2/files"));  
        //改为checkbox注释
        SimpleAdapter ladapter = new SimpleAdapter(this,getMapData(list),R.layout.activity_item, new String[]{"ItemText"},new int[]{R.id.ItemTitle}); 
        i.setAdapter(ladapter);  
//         //改为checkbox，加入getdata 方法
//        mycheckBoxAdapter = new MycheckBoxAdapter(this,getMapData(list));
        TextView empty = new TextView(this);  
        empty.setText("empty!");  
        i.setEmptyView(empty);  
        //判断是否是空文件夹。不能用list==null,因为空文件夹时有list[0]
        if(list.size()==0) Toast.makeText(SelectGroup.this, "no selectable files", Toast.LENGTH_SHORT).show();
        else {
        i.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				selectword(arg2);
			}
		});
        }
    }  
     
   
    private ArrayList<Map<String, Object>> getMapData(ArrayList<File> list){  
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();  
        HashMap<String,Object> item;  
        int i = 0 ;  
        for(i=0;i<list.size();i++){  
        	int num=0;
                item = new HashMap<String,Object>();  
                String path  = list.get(i).toString();  
                try {
                	//得到文件中有多少个单词，并显示出来。注意文件读写一定要有try，catch
					num=readF1(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String name = path.substring(path.lastIndexOf("/")+1,path.length());  
                //保存每一格list单元格的数据 ，  
                item.put("ItemText",name+"   Words:"+num);  
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
    }  
    private void selectword(int arg2)
    {
    	tView.setText("");
    	  String path  = list.get(arg2).toString(); 
    	 try {
			readF2(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	        

    } 
    
    private int  readF1(String filePath)  throws IOException{
    	// TODO Auto-generated method stub
        int count=0;
        File csv = new File(filePath); // CSV文件

        BufferedReader br = new BufferedReader(new FileReader(csv));

        // 读取直到最后一行 
        String line = ""; 
        while ((line = br.readLine()) != null) { 
            // 把一行数据分割成多个字段 
            StringTokenizer st = new StringTokenizer(line, ",");
            count+=st.countTokens();
        } 
        br.close();
        return count;
    }
    
private void  readF2(String filePath)  throws IOException{
	// TODO Auto-generated method stub
    int count=0;
    File csv = new File(filePath); // CSV文件

    BufferedReader br = new BufferedReader(new FileReader(csv));

    // 读取直到最后一行 
    String line = ""; 
    while ((line = br.readLine()) != null) { 
        // 把一行数据分割成多个字段 
        StringTokenizer st = new StringTokenizer(line, ",");
        count+=st.countTokens();
    } 
    //不加.show()显示不出来
    if(count==0) Toast.makeText(SelectGroup.this, "no words in file", Toast.LENGTH_LONG).show();
    else {
        Random rm= new Random();
        //如果rm=new Random(count);
        //int t=rm.nextInt(count);
        //上两行连着写，每次返回t都是count的中值，没找到为什么，但是new rm的时候不指定范围就好
        //int t=(int)Math.random()*count;
        int t=0;
        //count-1是因为有时会报no such element，而且注意nextint每一次执行都会比前一次执行得到的数大，比如下行t2就大于t
        t=rm.nextInt(count);
        //int t2=rm.nextInt(count);
        //int count1=0;
        br.close();
        BufferedReader br1 = new BufferedReader(new FileReader(csv));

        // 读取直到最后一行 
        String line1 = ""; 
        StringTokenizer st1;
        String viewword ;
        while ((line1 = br1.readLine()) != null) { 
            // 把一行数据分割成多个字段 
            st1 = new StringTokenizer(line1, ",");
            //这里注意countTokens不是仅仅统计有多少个“，”而是统计有多少个词
            //比如aaa,,,,,,, count 也只是1，aaa,bbb count是2
           int temp=st1.countTokens();
            if(t-temp<=0) 
            	{
            	t=temp-t;
            	//这里最大到t-1也是和count-1一个原因
            	 for(int j=0;j<t-1;++j)
            	    {
            	    	String s=st1.nextToken();
            	    	 //String viewword = line1.substring(count1+1,line1.length()); 
            	    }
            	 viewword=st1.nextToken();
            	 tView.setText(tView.getText()+" "+viewword);
            		break;
            	}
            	
            else t-=temp;
        } 
       // tView.setText(viewword);
       br1.close();
	}

    //String viewword = line1.substring(count1+1,line1.length()); 
  // return count; 
}
@Override
protected void onStart()
{
	//在只是暂时退到后台又回来执行时，不会调用oncreat，而是调用onStart，所以这里把textview清零
	super.onStart();
	tView.setText("");
	
}

public void creat(View v)
{
	 Intent itIntent=new Intent(SelectGroup.this, CreatGroup.class);
	 startActivity(itIntent);
	 SelectGroup.this.finish();
}
public void select(View v)
{
//	 Intent itIntent=new Intent(CreatGroup.this, SelectGroup.class);
//	 startActivity(itIntent);
//	 //CreatGroup.this.finish();
}
public void edit(View v)
{
	 Intent itIntent=new Intent(SelectGroup.this, EditGroup.class);
	 startActivity(itIntent); SelectGroup.this.finish();
}
public void delete(View v)
{
	 Intent itIntent=new Intent(SelectGroup.this, DeleteGroup.class);
	 startActivity(itIntent);
	 SelectGroup.this.finish();
}
}  