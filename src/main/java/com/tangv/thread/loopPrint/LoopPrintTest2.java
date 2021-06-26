package com.tangv.thread.loopPrint;

import com.tangv.thread.entity.BaseTestEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: tangwei
 * @Date: 2021/6/26 13:57
 * @Description:
 */
@Slf4j
public class LoopPrintTest2 extends BaseTestEntity {

    @Test
    public void test() {
        AwaitSignal awaitSignal = new AwaitSignal(10);
        Condition aCondition = awaitSignal.newCondition();
        Condition bCondition = awaitSignal.newCondition();
        Condition cCondition = awaitSignal.newCondition();
        new Thread(() -> awaitSignal.print("a", aCondition, bCondition)).start();
        new Thread(() -> awaitSignal.print("b", bCondition, cCondition)).start();
        new Thread(() -> awaitSignal.print("c", cCondition, aCondition)).start();
        awaitSignal.start(aCondition);

    }

    public class AwaitSignal extends ReentrantLock {

        private int loopTimes;

        public AwaitSignal(int loopTimes) {
            this.loopTimes = loopTimes;
        }

        public void print(String content, Condition current, Condition next) {
            for (int i = 0;i < loopTimes;i++) {
                this.lock();
                try {
                    current.await();
                    System.out.println(content);
                    next.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    this.unlock();
                }
            }
        }

        public void start(Condition first) {
            lock();
            try {
                log.debug("START");
                first.signal();
            } finally {
                unlock();
            }
        }
    }

}