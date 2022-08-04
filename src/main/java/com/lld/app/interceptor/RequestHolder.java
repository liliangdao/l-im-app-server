package com.lld.app.interceptor;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-04 11:14
 **/
public class RequestHolder {
    private final static ThreadLocal<String> requestHolder = new ThreadLocal<>();

    public static void set(String user) {
        requestHolder.set(user);
    }

    public static String get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
