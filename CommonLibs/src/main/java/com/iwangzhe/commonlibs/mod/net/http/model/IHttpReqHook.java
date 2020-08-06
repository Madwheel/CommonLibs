package com.iwangzhe.commonlibs.mod.net.http.model;

import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Response;


public interface IHttpReqHook {
    Map<String,String> postRequest(HttpUrl url, Map<String, String> params);
    void postResponse(HttpUrl url, Response response);
}
