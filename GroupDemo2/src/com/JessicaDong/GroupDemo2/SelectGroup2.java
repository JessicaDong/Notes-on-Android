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
        // ����������  
         adapter = new MycheckBoxAdapter(this,getData(list)); // ������Ŀؼ�id  
        // ��Ӳ�����ʾ  
        listView.setAdapter(adapter);  
        
        // ��listView�ļ�����
        //�Ȱ�checkbox��forcusable ��Ϊfalse���ܼ�����itemclick
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                
                // ȡ��ViewHolder����������ʡȥ��ͨ������findViewByIdȥʵ����������Ҫ��cbʵ���Ĳ���
                ViewHolder holder = (ViewHolder) arg1.getTag();
                // �ı�CheckBox��״̬
                holder.checkBox.toggle();
                // ��CheckBox��ѡ��״����¼����
                // ����ѡ����Ŀ
                //��checknum����ǣ��趨����ֻ��ѡ�������ļ�
                //ֻ������ѡ�����ĿС�ڶ�ʱ�����ܰ������Ϊtrue������ʾ��ѡ��
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
                // ��TextView��ʾ
              //  tv_show.setText("��ѡ��" + checkNum + "��");
            
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
                	//�õ��ļ����ж��ٸ����ʣ�����ʾ������ע���ļ���дһ��Ҫ��try��catch
					num=readF1(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String name = path.substring(path.lastIndexOf("/")+1,path.length());  
                //����ÿһ��list��Ԫ������� ��  
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
          //��  file�Ƿ���Ŀ¼���������Ŀ¼�ͼ��뵽list�������Ŀ¼���Ͱ�Ŀ¼�����е��ļ���ʾ����
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
 // ˢ��listview��TextView����ʾ
    private void dataChanged() {
    // ֪ͨlistViewˢ��
            adapter.notifyDataSetChanged();
    // TextView��ʾ���µ�ѡ����Ŀ
           // tv_show.setText("��ѡ��" + checkNum + "��");
        }
    
    
        public void selectwordbtn(View v) {
        	tView.setText("");
            // ����list�ĳ��ȣ�����ѡ����Ϊδѡ��δѡ����Ϊ��ѡ
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
            // ˢ��listview��TextView����ʾ
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
           // if(count==0) Toast.makeText(.this, "no words in file", Toast.LENGTH_LONG).show();
            if(count!=0) {
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
//     * listview�е�����������Ի��� 
//     */  
//    public void showInfo() {  
//        new AlertDialog.Builder(this).setTitle("�ҵ�listview")  
//                .setMessage("����...")  
//                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
//                    @Override  
//                    public void onClick(DialogInterface dialog, int which) {  
//                    }  
//                }).show();  
//    }  
}  