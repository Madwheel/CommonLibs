package com.iwangzhe.commonlibs.mod.io.file;



import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.io.file.serv.IoFileServApi;

import java.io.InputStream;

public class IoFileMain extends ModMain {
    public String getModName() {
        return "IoFileMain";
    }

    private static IoFileMain instance = new IoFileMain();

    private IoFileMain() {
    }

    public static IoFileMain getInstance() {
        return instance;
    }

    public IoFileServApi pServApi;

    public void born() {
        super.born();
        pServApi = IoFileServApi.getInstance(this);
    }


    public String readFileStr(String filePath) {
        return pServApi.readFileStr(filePath);
    }

    public String readFileStr(InputStream is) {
        return pServApi.readFileStr(is);
    }
}
