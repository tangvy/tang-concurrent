package com.tangv.thread.projectPractice;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;

/**
 * Description: 卖票
 * Author:      TangWei
 * Date:        2020/9/1 2:09 下午
 */
@Slf4j
public class SellTicketMain {

    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(1000);
        //所有线程集合
        List<Thread> threadList = new ArrayList<>();
        //卖出的票数统计
        List<Integer> amountList = new Vector<>();
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(() -> {
                int amount = ticketWindow.sell(randomAmount());
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();
        }
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //统计卖出的票数和剩余票数
        int sold = amountList.stream().mapToInt(Integer::intValue).sum();
        int remain = ticketWindow.getCount();
        log.debug("卖出票数:{},剩余票数:{}",sold,remain);
        List<Integer> list = Arrays.asList(1,3,5,7,9);
        log.debug("{}",list.stream().reduce((acc,item) -> acc+=item).get());
    }

    //Random为线程安全的
    static Random random = new Random();

    //随机1-5
    public static int randomAmount(){return  random.nextInt(5)+1;}

}

@Slf4j
class TicketWindow{
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public synchronized int sell(int amount) {
        if (count >= amount) {
            this.count -= amount;
            return amount;
        }else {return 0;}
    }
}