package com.tangv.thread.singleton;

/**
 * author:   tangwei
 * Date:     2021/6/26 22:18
 * Description:
 */
public final class Singleton4 {

    private static Singleton4 INSTANCE = null;

    public static synchronized Singleton4 getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        INSTANCE = new Singleton4();
        return INSTANCE;
    }


}