package com.tangv.thread.projectPractice;

import com.tangv.thread.primaryView.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 多线程模拟泡茶流程
 * Author:      TangWei
 * Date:        2020/8/31 5:35 下午
 */

@Slf4j
public class MakeTeaMain {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            Sleeper.sleep(1);
            log.debug("烧开水");
            Sleeper.sleep(15);
        },"老王");
        Thread t2 = new Thread(() -> {
           log.debug("洗茶壶，洗茶杯，拿茶叶");
           Sleeper.sleep(4);
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("泡茶");
        },"小王");
        t1.start();
        t2.start();
    }

}
