package com.iwangzhe.commonlibs.base;

import com.alibaba.fastjson.annotation.JSONField;

public class JBase {
    private int errorCode;

    public JBase(){
        errorCode = 0;
    }

    @JSONField(name="error_code")
    public int getErrorCode(){
        return errorCode;
    }

    @JSONField(name="error_code")
    public void setErrorCode(int value){
        errorCode = value;
    }

}
