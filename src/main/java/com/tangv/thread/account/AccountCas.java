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
        while (true) {
            int prev = balance.get();
            int next = prev - amount;
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }

    @Test
    public void test() {
        AccountCas accountCas = new AccountCas();
        accountCas.setBalance(1000);
        Account.run(accountCas);
    }
}