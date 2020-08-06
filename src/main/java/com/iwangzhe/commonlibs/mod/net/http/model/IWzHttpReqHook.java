package com.iwangzhe.commonlibs.mod.net.http.model;



import com.iwangzhe.commonlibs.base.CommonRes;

import java.util.Map;

import okhttp3.HttpUrl;


public interface IWzHttpReqHook<T> {
     Map<String,String> prepostParams(HttpUrl url, Map<String, String> params);
     Map<String,String> postParams(HttpUrl url, Map<String, String> params);
     Map<String,String> prepostExtraHeaders(HttpUrl url, Map<String, String> params);
     Map<String,String> postExtraHeaders(HttpUrl url, Map<String, String> params);
     <T> CommonRes<T> postRes(HttpUrl url, Map<String, String> params, CommonRes<T> cRes);
     <T> CommonRes<T> prepostRes(HttpUrl url, Map<String, String> params, CommonRes<T> cRes);
}
