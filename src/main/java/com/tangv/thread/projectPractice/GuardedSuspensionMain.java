package com.tangv.thread.projectPractice;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * author:   tangwei
 * Date:     2021/6/22 20:03
 * Description:
 */
@Slf4j
public class GuardedSuspensionMain {

    public static void main(String[] args) {

        GuardedObject guardedObject = new GuardedObject();

        new Thread(() -> {
           //子线程执行下载任务
            try {
                log.debug("开始下载=====");
                Thread.sleep(1500);
                List<String> netPage = download();
                log.debug("下载完成=====");
                guardedObject.complete(netPage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        log.debug("下载中=====等待下载完成=====");
        //主线程阻塞等待
        List<String> netPage = (List<String>) guardedObject.get(2000);
        log.debug("下载内容==================");
        log.debug(netPage.toString());
    }

    static class GuardedObject {

        private Object response;

        private final Object lock = new Object();

        public Object get(long millis) {
            synchronized (lock) {
                //1) 记录最初时间
                long begin = System.currentTimeMillis();
                //2) 已经经历的时间
                long timePassed = 0;
                //条件不满足则等待
                while (response == null) {
                    long waitTime = millis - timePassed;
                    log.debug("还需等待时间：{}", waitTime);
                    if (waitTime <= 0) {
                        log.debug("超时了=====");
                        break;
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timePassed = System.currentTimeMillis() - begin;
                    log.debug("过去的时间：{}，Object is NULL {}", timePassed, response == null);
                }
                return response;
            }
        }

        public void complete(Object response) {
            synchronized (lock) {
                this.response = response;
                //条件满足则通知等待线程
                lock.notifyAll();
            }
        }
    }


    public static List<String> download() throws Exception {
        URL url = new URL("https://www.baidu.com/");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        List<String> netPage = new ArrayList<>();
        while (bufferedReader.readLine() != null) {
            netPage.add(bufferedReader.readLine());
        }
        return netPage;
    }
}