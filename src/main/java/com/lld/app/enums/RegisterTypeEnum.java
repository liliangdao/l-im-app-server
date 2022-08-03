package com.lld.app.enums;

public enum RegisterTypeEnum {

    /**
     * 1 username；2 验证码。
     */
    USERNAME_PASSWORD(1),

    MOBILE(2),
    ;

    private int code;

    RegisterTypeEnum(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
