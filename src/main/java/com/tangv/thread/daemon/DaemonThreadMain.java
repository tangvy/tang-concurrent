package com.tangv.thread.daemon;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 守护线程
 * Author:      TangWei
 * Date:        2020/8/31 3:44 下午
 */
@Slf4j
public class DaemonThreadMain {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("结束");
        },"t1");
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(1000);
        log.debug("结束");
    }

}
