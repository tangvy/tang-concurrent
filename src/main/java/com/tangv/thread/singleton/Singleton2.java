package com.tangv.thread.singleton;

/**
 * author:   tangwei
 * Date:     2021/6/26 22:09
 * Description:
 */
public final class Singleton2 {

    private static final Singleton2 INSTANCE = new Singleton2();

    public Singleton2() {

    }

    public Singleton2 getInstance() {
        return INSTANCE;
    }

}