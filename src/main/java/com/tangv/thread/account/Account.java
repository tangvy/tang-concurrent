package com.tangv.thread.account;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * author:   tangwei
 * Date:     2021/6/26 22:40
 * Description:
 */
public interface Account {

    Integer getBalance();

    void withdraw(Integer amount);

    static void  run(Account account) {
        List<Thread> threadList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                account.withdraw(1);
            });
            threadList.add(thread);
        }
        long start = System.currentTimeMillis();
        threadList.forEach(Thread::start);
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(account.getBalance());
        System.out.println("花费时间：" + (end - start) + "ms");
    }

}