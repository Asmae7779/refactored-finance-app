package com.university.finance.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.pattern.strategy.WithdrawStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawStrategyTest {

    @Test
    void withdrawShouldDecreaseBalance() {
        Account account = new Account("A1", 200.0, new User());
        WithdrawStrategy strategy = new WithdrawStrategy();

        strategy.execute(account,null, 50.0);

        assertEquals(150.0, account.getBalance());
    }

    @Test
    void withdrawMoreThanBalanceShouldFail() {
        Account account = new Account("A1", 50.0, new User());
        WithdrawStrategy strategy = new WithdrawStrategy();

        assertThrows(IllegalStateException.class,
                () -> strategy.execute(account, null,100.0));
    }
}
