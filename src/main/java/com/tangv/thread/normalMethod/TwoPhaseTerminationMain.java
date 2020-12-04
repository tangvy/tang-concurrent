package com.tangv.thread.normalMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 两阶段终止
 * Author:      TangWei
 * Date:        2020/8/31 2:51 下午
 */
@Slf4j
public class TwoPhaseTerminationMain {
    public static void main(String[] args) throws InterruptedException {
     TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
     twoPhaseTermination.start();
     Thread.sleep(3500);
     twoPhaseTermination.stop();
    }
}

@Slf4j
class TwoPhaseTermination{
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("处理打断事宜");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }


}
