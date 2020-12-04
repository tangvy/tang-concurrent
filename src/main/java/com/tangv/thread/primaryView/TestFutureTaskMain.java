package com.tangv.thread.primaryView;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/8/24 6:45 下午
 */
@Slf4j
public class TestFutureTaskMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                Thread.sleep(2000);
                return 100;
            }
        });
        Thread t1 = new Thread(task,"thread-task");
        t1.start();
        log.debug("线程返回值:{}",task.get());
        log.debug("running");
    }
}
