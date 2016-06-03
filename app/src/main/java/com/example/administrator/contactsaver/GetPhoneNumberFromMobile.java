package com.example.administrator.contactsaver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class GetPhoneNumberFromMobile {
    private List<PhoneInfo> list;
//    public List<PhoneInfo> getPhoneNumberFromMobile(Context context) {
//        // TODO Auto-generated constructor stub
//        list = new ArrayList<PhoneInfo>();
//        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                null, null, null, null);
//        //moveToNext方法返回的是一个boolean类型的数据
//        while (cursor.moveToNext()) {
//            //读取通讯录的姓名
//            String name = cursor.getString(cursor
//                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            //读取通讯录的号码
//            String number = cursor.getString(cursor
//                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            PhoneInfo phoneInfo = new PhoneInfo(name, number);
//            list.add(phoneInfo);
//        }
//        return list;
//    }

    public List<PhoneInfo> getContacts(Context context){
        list=new ArrayList<PhoneInfo>();
        ContentResolver resolver=context.getContentResolver();
        Uri uri=Uri.parse("content://com.android.contacts/contacts");
        Cursor idcursor=resolver.query(uri,new String[]{"_id"},null,null,null);
        while (idcursor.moveToNext()){
            int id=idcursor.getInt(0);
            uri=Uri.parse("content://com.android.contacts/contacts/" + id + "/data");
            Cursor dataCursor=resolver.query(uri,new String[]{"data1","mimetype"},null,null,null);
            String name=null,number=null;
            while (dataCursor.moveToNext()){
                String data=dataCursor.getString(0);
                String type=dataCursor.getString(1);
                if("vnd.android.cursor.item/name".equals(type))
                    name=data;
                else if("vnd.android.cursor.item/phone_v2".equals(type))
                    number=data;
            }
            dataCursor.close();
            PhoneInfo phoneInfo=new PhoneInfo(name,number);
            list.add(phoneInfo);
        }
        idcursor.close();
        return list;
    }
}
