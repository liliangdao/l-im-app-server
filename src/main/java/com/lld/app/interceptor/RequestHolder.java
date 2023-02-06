package com.lld.app.interceptor;

/**
 * @author: Chackylee
 * @description:
 * @create: 2022-08-04 11:14
 **/
public class RequestHolder {
    private final static ThreadLocal<Integer> requestHolder = new ThreadLocal<>();

    public static void set(Integer user) {
        requestHolder.set(user);
    }

    public static Integer get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
