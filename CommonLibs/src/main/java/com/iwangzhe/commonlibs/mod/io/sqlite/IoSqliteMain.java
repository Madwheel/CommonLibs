package com.iwangzhe.commonlibs.mod.io.sqlite;


import com.iwangzhe.commonlibs.base.ModMain;

public class IoSqliteMain extends ModMain {
    public String getModName(){
        return "IoSqliteMain";
    }
    private static IoSqliteMain instance = new IoSqliteMain();
    private IoSqliteMain(){}
    public static IoSqliteMain getInstance(){
        return instance;
    }
    public void create() {
        super.create();
    }
}
