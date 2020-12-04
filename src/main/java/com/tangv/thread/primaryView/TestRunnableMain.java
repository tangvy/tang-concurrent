package com.tangv.thread.primaryView;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/8/24 5:58 下午
 */
@Slf4j
public class TestRunnableMain {
    public static void main(String[] args) {
        /*Runnable r1 = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };*/
        Runnable r1 = () -> log.debug("running");
        Thread t1 = new Thread(r1,"thread-tangv-test");
        t1.start();
        log.debug("running");
    }
}
