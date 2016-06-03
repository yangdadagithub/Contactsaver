package com.example.administrator.contactsaver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button show_btn;
    private Button login_btn;
    private Button regist_btn;
    private EditText ed_ac,ed_pwd;
   Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           String result=msg.obj.toString().trim();
           if(result.equals("yes")){
               Toast.makeText(MainActivity.this,"log in successful !",Toast.LENGTH_LONG).show();
           }else {
               Toast.makeText(MainActivity.this,"fail to log in !",Toast.LENGTH_LONG).show();
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_ac=(EditText)findViewById(R.id.edit_account);
        ed_pwd=(EditText)findViewById(R.id.edit_pwd);

        login_btn=(Button)findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        String post_str="Uaccount="+ed_ac.getText().toString().trim()+"&Upassword="+ed_pwd.getText().toString().trim();
                        post_str=post_str+"&flag=yes";
                        String result=new httpGet(post_str,"contackusercheck.php").startLink();
                        Message msg=handler.obtainMessage();
                        msg.obj=result;
                        handler.sendMessage(msg);
                    }
                }.start();
            }
        });

        regist_btn=(Button)findViewById(R.id.regist_btn);
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Regist.class);
                startActivity(intent);
            }
        });


    }
}
