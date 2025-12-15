package com.university.finance.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.pattern.strategy.DepositStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositStrategyTest {

    @Test
    void depositShouldIncreaseBalance() {
        Account account = new Account("A1", 100.0, new User());
        DepositStrategy strategy = new DepositStrategy();

        strategy.execute(account,null, 50.0);

        assertEquals(150.0, account.getBalance());
    }

    @Test
    void depositNegativeAmountShouldFail() {
        Account account = new Account("A1", 100.0, new User());
        DepositStrategy strategy = new DepositStrategy();

        assertThrows(IllegalArgumentException.class,
                () -> strategy.execute(account,null, -10.0));
    }
}
