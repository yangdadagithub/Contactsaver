package com.example.administrator.contactsaver;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/6/1.
 */
public class httpGet {
    private static String PATH="http://192.168.191.1/mywebdb/";
    private static URL url;
    private String postStr;
    public httpGet(String post_str,String address){
        String adrurl;
        adrurl=PATH+address;
        postStr=post_str;
        try {
            url=new URL(adrurl);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    public String startLink(){
        try {
           // System.out.println(PATH);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            //System.out.println("OPEN LINK");
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] data=postStr.getBytes();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));

            OutputStream out=connection.getOutputStream();
            out.write(data, 0, data.length);
            out.close();
            InputStream in=connection.getInputStream();

            if(connection.getResponseCode()==200){
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                byte[] datain=new byte[1024];
                int len=0;
                String result="";

                if(in!=null){
                    while((len=in.read(datain))!=-1){
                        outputStream.write(datain,0,len);
                    }
                    result=new String(outputStream.toByteArray(),"utf-8");
//                    System.out.println(result);
                  //  Log.i("return",result);
                    out.close();
                    in.close();

                    return result;

                }

            }
//            out.close();
            in.close();
        }catch (IOException e){
            e.printStackTrace();
            Log.i("err", e.getMessage());
        }
        return null;
    }

    public String startSaveContact(){
        try {
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] data=postStr.getBytes();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "application/Json");
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));

            OutputStream out=connection.getOutputStream();
            out.write(data, 0, data.length);
            out.close();

            InputStream in=connection.getInputStream();

            if(connection.getResponseCode()==200){
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                byte[] datain=new byte[1024];
                int len=0;
                String result="";

                if(in!=null){
                    while((len=in.read(datain))!=-1){
                        outputStream.write(datain,0,len);
                    }
                    result=new String(outputStream.toByteArray(),"utf-8");
//                    System.out.println(result);
                  //  Log.i("return",result);
                    out.close();
                    in.close();

                    return result;

                }

            }
//            out.close();
            in.close();

        }catch (IOException e){
            e.printStackTrace();
            Log.i("err", e.getMessage());
        }
        return null;
    }

    public String getJSONstring(){
        try {
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            byte[] data=postStr.getBytes();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "application/Json");
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));

            OutputStream out=connection.getOutputStream();
            out.write(data, 0, data.length);
            out.close();

            InputStream in=connection.getInputStream();

            if(connection.getResponseCode()==200){
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                byte[] datain=new byte[1024];
                int len=0;
                String result="";

                if(in!=null){
                    while((len=in.read(datain))!=-1){
                        outputStream.write(datain,0,len);
                    }
                    result=new String(outputStream.toByteArray(),"utf-8");
//                    System.out.println(result);
                  //  Log.i("return",result);
                    out.close();
                    in.close();

                    return result;

                }

            }
//            out.close();
            in.close();

        }catch (IOException e){
            e.printStackTrace();
            Log.i("err", e.getMessage());
        }
        return null;
    }

}
