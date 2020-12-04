package com.tangv.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 同步锁
 * Author:      TangWei
 * Date:        2020/8/31 9:20 下午
 */

@Slf4j
public class SynchronizedDemo {
    static int counter = 0;
    static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0;i<5000;i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0;i<5000;i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        },"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}",counter);
    }
}
