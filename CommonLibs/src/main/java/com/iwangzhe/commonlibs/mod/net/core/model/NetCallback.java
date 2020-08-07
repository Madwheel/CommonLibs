package com.iwangzhe.commonlibs.mod.net.core.model;


import com.iwangzhe.commonlibs.base.IResCallback;

abstract public class NetCallback {
    protected IResCallback mCallback;
    public NetCallback(IResCallback callback){
        mCallback = callback;
    }

}
