package com.tangv.thread.primaryView;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/8/24 5:26 下午
 */
@Slf4j
public class TestThreadMain {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> log.debug("running"));
        t1.setName("thread-tangv-test");
        t1.start();
        log.debug("running");
    }
}
