package com.tangv.thread.account;

import com.tangv.thread.entity.BaseTestEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * author:   tangwei
 * Date:     2021/6/27 11:36
 * Description: ABA问题
 */
@Slf4j
public class AbaQuestion extends BaseTestEntity {

    private static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    @Test
    public void test() {

        String prev = ref.getReference();
        int stamp = ref.getStamp();
        log.debug("版本号：{}", stamp);

        //其他线程操作，造成ABA问题
        new Thread(() -> log.debug("change A -> B: {}",
                ref.compareAndSet(ref.getReference(), "B", ref.getStamp(), ref.getStamp() + 1)),
                "T1").start();
        new Thread(() -> log.debug("change B -> A: {}",
                ref.compareAndSet(ref.getReference(), "A", ref.getStamp(), ref.getStamp() + 1)),
                "T2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("change A -> C: {}",
                ref.compareAndSet(prev, "C", stamp, stamp + 1));

    }

}