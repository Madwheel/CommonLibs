package com.iwangzhe.commonlibs.mod.net.http.model;

import java.io.IOException;

public interface IJsonHttpNetCallback{
    void onFailure(IOException e);
    void onResponse(JsonHttpNetRes response) ;
}
