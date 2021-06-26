package com.tangv.thread.singleton;

/**
 * author:   tangwei
 * Date:     2021/6/26 22:27
 * Description:
 */
public final class Singleton5 {

    private Singleton5() {}

    private static class LazyHolder {
        static final Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return LazyHolder.INSTANCE;
    }

}