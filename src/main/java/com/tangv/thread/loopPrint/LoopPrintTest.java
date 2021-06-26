package com.tangv.thread.loopPrint;

import com.tangv.thread.entity.BaseTestEntity;
import org.junit.Test;

/**
 * @author: tangwei
 * @Date: 2021/6/25 13:39
 * @Description:
 */
public class LoopPrintTest extends BaseTestEntity {

    @Test
    public void test() {
        PrintNotify printNotify = new PrintNotify(1,5);
        new Thread(() -> {printNotify.print("A", 1, 2);}).start();
        new Thread(() -> {printNotify.print("B", 2, 3);}).start();
        new Thread(() -> {printNotify.print("C", 3, 1);}).start();
    }


    public class PrintNotify{

        private int startFlag;

        private int loopTime;

        public PrintNotify(int startFlag, int loopTime) {
            this.startFlag = startFlag;
            this.loopTime = loopTime;
        }

        public void print(String printContent, int currentFlag, int nextFlag) {
            for (int i = 0; i < loopTime; i++) {
                synchronized (this) {
                    while (startFlag != currentFlag) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(printContent);
                    startFlag = nextFlag;
                    this.notifyAll();
                }
            }
        }

    }
}