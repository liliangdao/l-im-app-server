package com.lld.app.enums;


import com.lld.app.exception.ApplicationExceptionEnum;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-05-11 14:33
 **/
public enum ErrorCode implements ApplicationExceptionEnum {

    USER_NOT_EXIST(10000,"用户不存在"),
    USERNAME_OR_PASSWORD_ERROR(10001,"用户名或密码错误"),
    MOBILE_IS_REGISTER(10002,"该手机号已注册了用户"),
            ;

    private int code;
    private String error;

    ErrorCode(int code, String error){
        this.code = code;
        this.error = error;
    }
    public int getCode() {
        return this.code;
    }

    public String getError() {
        return this.error;
    }

}
