package com.example.administrator.contactsaver;

/**
 * Created by Administrator on 2016/5/30.
 */
public class PhoneInfo {
    private String name;
    private String number;
    public PhoneInfo(String name,String number){
        this.name=name;
        this.number=number;
    }
    public String getName(){
        return name;
    }

    public String getNumber() {
        return number;
    }
}
