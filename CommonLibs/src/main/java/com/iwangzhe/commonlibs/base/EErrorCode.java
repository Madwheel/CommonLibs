package com.iwangzhe.commonlibs.base;

public enum EErrorCode {
    OK(0),
    NO_OBJECT(10001),
    INNER_ERROR(10007);

    private final int value;

    EErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
