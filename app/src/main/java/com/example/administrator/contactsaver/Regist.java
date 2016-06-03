package com.example.administrator.contactsaver;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Regist extends AppCompatActivity {
private EditText ed_ac,ed_pwd;
    private Button mks_btn;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String result="";
            if(msg.obj.toString().trim().equals("yes"))
                result="regist successful !";
            else
                result="fail to regist !";
            Toast.makeText(Regist.this,result,Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ed_ac=(EditText)findViewById(R.id.ed_account);
        ed_pwd=(EditText)findViewById(R.id.ed_pwd);
        mks_btn=(Button)findViewById(R.id.mks_btn);
        mks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        String postStr="Uaccount="+ed_ac.getText().toString().trim()+"&Upassword="+ed_pwd.getText().toString().trim();
                        postStr=postStr+"&flag=yes";
                        Log.i("postStr",postStr);
                        String result=new httpGet(postStr,"contackregist.php").startLink();
                        Log.i("result",result);
                        Message msg=handler.obtainMessage();
                        msg.obj=result;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }
}
