package com.iwangzhe.commonlibs.base;

public class CommonRes<T> extends MyObject {
    protected T mResObj;
    protected String mResult;
    protected int mErrorCode;
    protected boolean mFinBool;

    public CommonRes(boolean finBool) {
        mFinBool = finBool;
        mErrorCode = 0;
        mResObj = null;
        mResult = "";
    }

    public CommonRes(boolean finBool, int errorCode) {
        mFinBool = finBool;
        mErrorCode = errorCode;
        mResObj = null;
        mResult = "";
    }

    public CommonRes(boolean finBool, int errorCode, T resObj) {
        mFinBool = finBool;
        mErrorCode = errorCode;
        mResObj = resObj;
        mResult = "";
    }

    public CommonRes(T mResObj, String mResult, int mErrorCode, boolean mFinBool) {
        this.mResObj = mResObj;
        this.mResult = mResult;
        this.mErrorCode = mErrorCode;
        this.mFinBool = mFinBool;
    }

    public CommonRes(boolean finBool, String response) {
        mFinBool = finBool;
        mErrorCode = 0;
        mResObj = null;
        mResult = response;
    }

    public CommonRes(boolean finBool, int errorCode, String response) {
        mFinBool = finBool;
        mErrorCode = errorCode;
        mResObj = null;
        mResult = response;
    }

    public CommonRes(boolean finBool, int errorCode, T resObj, String response) {
        mFinBool = finBool;
        mErrorCode = errorCode;
        mResObj = resObj;
        mResult = response;
    }

    public CommonRes(T mResObj, String mResult, int mErrorCode, boolean mFinBool, String response) {
        this.mResObj = mResObj;
        this.mResult = mResult;
        this.mErrorCode = mErrorCode;
        this.mResult = response;
    }

    public String getmResult() {
        return mResult == null ? "" : mResult;
    }

    public void setmResult(String mResult) {
        this.mResult = mResult;
    }

    public void setResObj(T resObj) {
        mResObj = resObj;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public void setFinBool(boolean finBool) {
        mFinBool = finBool;
    }

    public T getResObj() {
        return mResObj;
    }

    public boolean getFinBool() {
        return mFinBool;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public boolean isFin() {
        return mFinBool;
    }

    public boolean isNoError() {
        return mErrorCode == 0;
    }

    public boolean isOk() {
        return mFinBool && mErrorCode == 0;
    }
}


