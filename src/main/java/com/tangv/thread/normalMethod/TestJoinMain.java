package com.tangv.thread.normalMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/8/25 2:42 下午
 */
@Slf4j
public class TestJoinMain {
    static int r1 = 0;
    static int r2 = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
                public void run() {
                try {
                    Thread.sleep(1000);
                    r1 = 10;
                    long s1 = System.currentTimeMillis();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        );
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                r2 = 20;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.debug("join begin");
        t1.join();
        log.debug("t1 join end");
        t2.join();
        log.debug("t2 join end");
        long end = System.currentTimeMillis();
        log.debug("r1:{},r2:{},cost:{}",r1,r2,end-start);
    }
}
