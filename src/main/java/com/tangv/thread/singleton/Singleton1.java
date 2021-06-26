package com.tangv.thread.singleton;

/**
 * author:   tangwei
 * Date:     2021/6/26 21:03
 * Description:
 */
public final class Singleton1 {

    private static volatile Singleton1 INSTANCE = null;

    public static Singleton1 getInstance() {
        if (INSTANCE == null) {
            synchronized(Singleton1.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton1();
                }
            }
        }
        return INSTANCE;
    }

}