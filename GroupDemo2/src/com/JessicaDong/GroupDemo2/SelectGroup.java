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
        //��Ϊcheckboxע��
        SimpleAdapter ladapter = new SimpleAdapter(this,getMapData(list),R.layout.activity_item, new String[]{"ItemText"},new int[]{R.id.ItemTitle}); 
        i.setAdapter(ladapter);  
//         //��Ϊcheckbox������getdata ����
//        mycheckBoxAdapter = new MycheckBoxAdapter(this,getMapData(list));
        TextView empty = new TextView(this);  
        empty.setText("empty!");  
        i.setEmptyView(empty);  
        //�ж��Ƿ��ǿ��ļ��С�������list==null,��Ϊ���ļ���ʱ��list[0]
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
                	//�õ��ļ����ж��ٸ����ʣ�����ʾ������ע���ļ���дһ��Ҫ��try��catch
					num=readF1(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String name = path.substring(path.lastIndexOf("/")+1,path.length());  
                //����ÿһ��list��Ԫ������� ��  
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
          //��  file�Ƿ���Ŀ¼���������Ŀ¼�ͼ��뵽list�������Ŀ¼���Ͱ�Ŀ¼�����е��ļ���ʾ����
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
        File csv = new File(filePath); // CSV�ļ�

        BufferedReader br = new BufferedReader(new FileReader(csv));

        // ��ȡֱ�����һ�� 
        String line = ""; 
        while ((line = br.readLine()) != null) { 
            // ��һ�����ݷָ�ɶ���ֶ� 
            StringTokenizer st = new StringTokenizer(line, ",");
            count+=st.countTokens();
        } 
        br.close();
        return count;
    }
    
private void  readF2(String filePath)  throws IOException{
	// TODO Auto-generated method stub
    int count=0;
    File csv = new File(filePath); // CSV�ļ�

    BufferedReader br = new BufferedReader(new FileReader(csv));

    // ��ȡֱ�����һ�� 
    String line = ""; 
    while ((line = br.readLine()) != null) { 
        // ��һ�����ݷָ�ɶ���ֶ� 
        StringTokenizer st = new StringTokenizer(line, ",");
        count+=st.countTokens();
    } 
    //����.show()��ʾ������
    if(count==0) Toast.makeText(SelectGroup.this, "no words in file", Toast.LENGTH_LONG).show();
    else {
        Random rm= new Random();
        //���rm=new Random(count);
        //int t=rm.nextInt(count);
        //����������д��ÿ�η���t����count����ֵ��û�ҵ�Ϊʲô������new rm��ʱ��ָ����Χ�ͺ�
        //int t=(int)Math.random()*count;
        int t=0;
        //count-1����Ϊ��ʱ�ᱨno such element������ע��nextintÿһ��ִ�ж����ǰһ��ִ�еõ������󣬱�������t2�ʹ���t
        t=rm.nextInt(count);
        //int t2=rm.nextInt(count);
        //int count1=0;
        br.close();
        BufferedReader br1 = new BufferedReader(new FileReader(csv));

        // ��ȡֱ�����һ�� 
        String line1 = ""; 
        StringTokenizer st1;
        String viewword ;
        while ((line1 = br1.readLine()) != null) { 
            // ��һ�����ݷָ�ɶ���ֶ� 
            st1 = new StringTokenizer(line1, ",");
            //����ע��countTokens���ǽ���ͳ���ж��ٸ�����������ͳ���ж��ٸ���
            //����aaa,,,,,,, count Ҳֻ��1��aaa,bbb count��2
           int temp=st1.countTokens();
            if(t-temp<=0) 
            	{
            	t=temp-t;
            	//�������t-1Ҳ�Ǻ�count-1һ��ԭ��
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
	//��ֻ����ʱ�˵���̨�ֻ���ִ��ʱ���������oncreat�����ǵ���onStart�����������textview����
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