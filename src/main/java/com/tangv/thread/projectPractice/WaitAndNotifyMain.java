package com.tangv.thread.projectPractice;

import lombok.extern.slf4j.Slf4j;
import netscape.security.UserTarget;

/**
 * author:   tangwei
 * Date:     2021/6/21 23:00
 * Description:
 */
@Slf4j
public class WaitAndNotifyMain {

    private static final Object lock = new Object();

    private static boolean hasPower = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("车子上路了");
                while (!hasPower) {
                    log.debug("没油，加会儿油", hasPower);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (hasPower) {
                    log.debug("车子油加满了，可以启动！[{}]", hasPower);
                }
            }
        }, "车子").start();

        new Thread(() -> {
            log.debug("其他车子启动了！");
        },"其他车子").start();

        Thread.sleep(1);
        new Thread(() -> {
            synchronized (lock) {
                hasPower = true;
                log.debug("车子加油了", hasPower);
                lock.notifyAll();
            }
        }, "加油ヾ(◍°∇°◍)ﾉﾞ").start();
    }

}