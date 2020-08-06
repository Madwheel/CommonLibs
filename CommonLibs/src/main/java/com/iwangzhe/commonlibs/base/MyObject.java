package com.iwangzhe.commonlibs.base;

import android.util.Log;

public class MyObject {
    public void born(){
    }

    public void create(){
    }

    public void active(){
    }

    public void deactive(){

    }

    public void destroy(){

    }

    public void terminate(){

    }

    public static void d(String msg){
        Log.d("MYWZ:",msg);
    }

    public static void d(String tag ,String msg){
        Log.d("MYWZ:"+tag,msg);
    }

    public static void e(String tag ,String msg){
        Log.e("MYWZ:"+tag,msg);
    }
}
