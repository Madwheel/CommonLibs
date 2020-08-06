package com.iwangzhe.commonlibs.mod.net.http.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHttpNetRes extends HttpNetRes {
    protected JSONObject mJsonObject;
    public JsonHttpNetRes(String jsonStr){
        try {
            JSONTokener tokener = new JSONTokener(jsonStr);
            mJsonObject = new JSONObject(tokener);
        }
        catch(JSONException e){
            d(e.toString());
        }
    }

    public JSONObject getJsonObject() {
        return mJsonObject;
    }
}
