package com.example.administrator.contactsaver;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class show_contact extends AppCompatActivity {
    private ListView lv;
    private MyListAdapter adapter;
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    private List<PhoneInfo> list=new ArrayList<PhoneInfo>();
    private Button show_btn,save_btn,daoru_btn;
    private String JSstr=null;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result=msg.obj.toString().trim();
            if(result.equals("yes")){
                Toast.makeText(show_contact.this,"contact saved !",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(show_contact.this,"fail to save contact !",Toast.LENGTH_LONG).show();
            }
        }
    };
    Handler handler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result=msg.obj.toString().trim();
            if(result.equals("yes")){
                Toast.makeText(show_contact.this,"导入成功！",Toast.LENGTH_LONG).show();
            }else if(result.equals("nothing")){
                Toast.makeText(show_contact.this,"请先备份！",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(show_contact.this,"导入失败了...",Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        Bundle bundle=getIntent().getExtras();
        final String user=bundle.getString("loger");

        show_btn=(Button)findViewById(R.id.show_btn);
        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListView();
            }
        });

        //        lv=(ListView)findViewById(R.id.contact);
//        getPhoneNumberFromMobile=new GetPhoneNumberFromMobile();
//        list=getPhoneNumberFromMobile.getContacts(this);
//        adapter=new MyListAdapter(list,this);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String number=list.get(position).getNumber();
//                Intent intent=new Intent();
//                intent.setAction("android.intent.action.CALL");
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setData(Uri.parse("tel:" + number));
//                startActivity(intent);
//            }
//        });

//            save_btn=(Button)findViewById(R.id.save_btn);
//            save_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                  new Thread(){
//                      @Override
//                      public void run() {
//                          try {
//                              Thread.sleep(1000);
//                          }catch (InterruptedException e){
//                              e.printStackTrace();
//                          }
//                          GetPhoneNumberFromMobile get=new GetPhoneNumberFromMobile();
//                          JSstr=get.contact2Json(show_contact.this);
//                          String postStr=JSstr;
//                          String result=new httpGet(postStr,"saveContact.php").startSaveContact();
//                          Message msg=handler.obtainMessage();
//                          msg.obj=result;
//                          handler.sendMessage(msg);
//                      }
//                  }.start();
//                }
//            });

        save_btn=(Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            Log.i("err", e.getMessage());
                        }
                        GetPhoneNumberFromMobile get=new GetPhoneNumberFromMobile();
                        list=get.getContacts(show_contact.this);
                        int contactlen=list.size();
                        String post_str="flag=yes"+"&Uaccount="+user;
                        String result=null;
                        for(int i=0;i<contactlen;i++){
                            String name= list.get(i).getName();
                            String number=list.get(i).getNumber();

                            if(name==null&&number==null)
                                continue;
                            else {
                            post_str+="&contactname="+name+"&number="+number;
                                result=new httpGet(post_str,"saveContact.php").startSaveContact();
                            }
                        }
                       // Log.i("size",""+contactlen);
//                        JSstr=get.contact2Json(show_contact.this);
//                        String postStr=JSstr;
//                        String result=new httpGet(postStr,"saveContact.php").startSaveContact();
                        Message msg=handler.obtainMessage();
                        msg.obj=result;
                        handler.sendMessage(msg);
                    }
                }.start();
            }
        });

        daoru_btn=(Button)findViewById(R.id.daoru_btn);
        daoru_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            Log.i("err", e.getMessage());
                        }

                        String post_str="flag=yes"+"&Uaccount="+user;
                        String JSONstr=new httpGet(post_str,"downloadContact.php").getJSONstring();
                      //  Log.i("resturn:",JSONstr);
                        String result= write2Contact(JSONstr);
                        Message msg=handler2.obtainMessage();
                        msg.obj=result;
                        handler2.sendMessage(msg);

                    }
                }.start();
            }
        });

    }

    public void showListView(){
        lv=(ListView)findViewById(R.id.contact);
        getPhoneNumberFromMobile=new GetPhoneNumberFromMobile();
        list=getPhoneNumberFromMobile.getContacts(this);
        adapter=new MyListAdapter(list,this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String number=list.get(position).getNumber();
                Intent intent=new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
    }

    public String write2Contact(String JSstr){
        try {
            if (JSstr.equals("[]")){
                return "nothing";
            }else {
                JSONArray jsonArray = new JSONArray(JSstr);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("contactname");
                    String number = jsonObject.getString("number");
                    boolean IsExit = ContacterIsExit(name);
                    if (!IsExit) {
                        addContact(name, number);
                        Log.i("Contact", "name is " + name);
                        Log.i("Contact", "number is " + number);
                    } else {
                        continue;
                    }
                    return "yes";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void addContact(String name,String number){
        Uri uri=Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver=getApplicationContext().getContentResolver();
        ContentValues values=new ContentValues();
        long contactId= ContentUris.parseId(resolver.insert(uri,values));

        //add name
        uri=Uri.parse("content://com.android.contacts/data");
        values.put("raw_contact_id",contactId);
        values.put("mimetype", "vnd.android.cursor.item/name");
        values.put("data2", name);
        resolver.insert(uri, values);
        values.clear();

        //add number
        values.put("raw_contact_id", contactId);
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("data2","2");
        values.put("data1", number);
        resolver.insert(uri, values);
        values.clear();
    }

    public boolean ContacterIsExit(String name){
        ContentResolver resolver=getContentResolver();
        Cursor cursor=resolver.query(ContactsContract.Data.CONTENT_URI,      //uri
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},      //colum
                ContactsContract.Contacts.DISPLAY_NAME + "=?",          //where                                           //where
                new String[]{name},
                null);                                         //order
        int numOfCursor=cursor.getCount();

        if(numOfCursor>=1){
           // Log.i("number of cursor:",""+numOfCursor);
            cursor.close();
            return true;
        }else{
            cursor.close();
        return false;
        }
    }
}