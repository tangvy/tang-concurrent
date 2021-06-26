package com.tangv.thread.account;

import com.tangv.thread.entity.BaseTestEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * author:   tangwei
 * Date:     2021/6/26 22:42
 * Description:
 */
@Slf4j
public class AccountUnsafe extends BaseTestEntity implements Account {

    private Integer balance;

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public synchronized void withdraw(Integer amount) {
        this.balance -= amount;
    }

    @Test
    public void test() {
        AccountUnsafe account = new AccountUnsafe();
        account.setBalance(1000);
        Account.run(account);
    }
}