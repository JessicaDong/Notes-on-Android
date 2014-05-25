package com.JessicaDong.GroupDemo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Random;
import java.util.StringTokenizer;
  
import android.R.integer;
import android.app.Activity;  
import android.app.AlertDialog;  
import android.content.DialogInterface;  
import android.content.Intent;
import android.os.Bundle;  
import android.util.Log;
import android.view.ContextMenu;  
import android.view.ContextMenu.ContextMenuInfo;  
import android.view.MenuItem;  
import android.view.View;  
import android.view.View.OnCreateContextMenuListener;  
import android.widget.AdapterView;  
import android.widget.AdapterView.AdapterContextMenuInfo;  
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.CheckBox;
import android.widget.ListView;  
import android.widget.TextView;
import android.widget.Toast;  
import com.JessicaDong.GroupDemo2.MycheckBoxAdapter.ViewHolder;

public class SelectGroup2 extends Activity {  
    TextView tView ;  
    private ListView listView;  
    private ArrayList<File> list ; 
    private int checkNum;
    ArrayList<Map<String, Object>> data;
    private MycheckBoxAdapter adapter;
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_selectgroup2);  
        tView = (TextView)findViewById(R.id.word);   
        tView.setText("");
        listView = (ListView) findViewById(R.id.filelist);  
        list = new ArrayList<File>();  
        getAllFiles(new File("/data/data/com.JessicaDong.GroupDemo2/files"));  
        // 配置适配器  
         adapter = new MycheckBoxAdapter(this,getData(list)); // 布局里的控件id  
        // 添加并且显示  
        listView.setAdapter(adapter);  
        
        // 绑定listView的监听器
        //先把checkbox的forcusable 设为false才能监听到itemclick
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                ViewHolder holder = (ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.checkBox.toggle();
                // 将CheckBox的选中状况记录下来
                // 调整选定条目
                //用checknum做标记，设定做多只能选择两个文件
                //只有在已选择的数目小于二时，才能把他标记为true，及显示被选中
                if (checkNum<2&&holder.checkBox.isChecked() == true) {
                    data.get(arg2).put("flag", "true");
                    checkNum++;}
//                } else {
//                    data.get(arg2).put("flag", "false");
//                    checkNum--;
//                }
                    if(holder.checkBox.isChecked() == false)
                    {
                    	data.get(arg2).put("flag", "false");
                        checkNum--;
                    }
                
                if(checkNum==2) 
                	{
                		//holder.checkBox.setEnabled(false);
                		Toast.makeText(SelectGroup2.this, "you have chosen two files", Toast.LENGTH_SHORT).show();
                		
                	}
               //else holder.checkBox.setEnabled(false);
                dataChanged();
                Log.i("datachange", ""+checkNum);
                // 用TextView显示
              //  tv_show.setText("已选中" + checkNum + "项");
            
        }});
    }  
    private ArrayList<Map<String, Object>> getData(ArrayList<File> list){  
         data = new ArrayList<Map<String, Object>>();  
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
                item.put("flag", "false");
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
 // 刷新listview和TextView的显示
    private void dataChanged() {
    // 通知listView刷新
            adapter.notifyDataSetChanged();
    // TextView显示最新的选中数目
           // tv_show.setText("已选中" + checkNum + "项");
        }
    
    
        public void selectwordbtn(View v) {
        	tView.setText("");
            // 遍历list的长度，将已选的设为未选，未选的设为已选
            for (int i = 0; i < list.size(); i++) {
                if (data.get(i).get("flag").equals("true")) {
                	//data.get(i).put("flag", "false");
                   // checkNum--;
                	selectword(i);
                	
                } else {
                	//data.get(i).put("flag", "true");
                  //  checkNum++;
                }
            }
            // 刷新listview和TextView的显示
            dataChanged();
        }
 
        private void selectword(int arg2)
        {
        	  String path  = list.get(arg2).toString(); 
        	 try {
    			readF2(path);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	        

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
           // if(count==0) Toast.makeText(.this, "no words in file", Toast.LENGTH_LONG).show();
            if(count!=0) {
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
        
        public void creat(View v)
        {
        	 Intent itIntent=new Intent(SelectGroup2.this, CreatGroup.class);
        	 startActivity(itIntent);
        	 SelectGroup2.this.finish();
        }
        public void select(View v)
        {
//        	 Intent itIntent=new Intent(CreatGroup.this, SelectGroup.class);
//        	 startActivity(itIntent);
//        	 //CreatGroup.this.finish();
        }
        public void edit(View v)
        {
        	 Intent itIntent=new Intent(SelectGroup2.this, EditGroup.class);
        	 startActivity(itIntent); SelectGroup2.this.finish();
        	 SelectGroup2.this.finish();
        }
        public void delete(View v)
        {
        	 Intent itIntent=new Intent(SelectGroup2.this, DeleteGroup.class);
        	 startActivity(itIntent);
        	 SelectGroup2.this.finish();
        }
  
//    /** 
//     * listview中点击按键弹出对话框 
//     */  
//    public void showInfo() {  
//        new AlertDialog.Builder(this).setTitle("我的listview")  
//                .setMessage("介绍...")  
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
//                    @Override  
//                    public void onClick(DialogInterface dialog, int which) {  
//                    }  
//                }).show();  
//    }  
}  