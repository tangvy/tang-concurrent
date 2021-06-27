package com.tangv.thread.account;

import com.tangv.thread.entity.BaseTestEntity;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author:   tangwei
 * Date:     2021/6/26 23:26
 * Description:
 */
public class AccountCas extends BaseTestEntity implements Account {

    private AtomicInteger balance;

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    public void setBalance(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public void withdraw(Integer amount) {
        /*
        //原始的方式：
        while (true) {
            int prev = balance.get();
            int next = prev - amount;
            //比较并设置值
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }*/
        //balance.getAndAdd(-1*amount);
        balance.addAndGet(-1*amount);
    }

    @Test
    public void test() {
        AccountCas accountCas = new AccountCas();
        accountCas.setBalance(1000);
        Account.run(accountCas);
    }
}