package com.tangv.thread.normalMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 打断
 * Author:      TangWei
 * Date:        2020/8/31 2:25 下午
 */

@Slf4j
public class TestInterruptMain {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleeping...");
            try {
                Thread.sleep(5000);
                log.debug("sleeping end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"interrupt-thread");
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标记：{}",t1.isInterrupted());
    }

}
