package com.example.administrator.contactsaver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class show_contact extends AppCompatActivity {
    private ListView lv;
    private MyListAdapter adapter;
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    private List<PhoneInfo> list=new ArrayList<PhoneInfo>();
    private Button show_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
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
}
