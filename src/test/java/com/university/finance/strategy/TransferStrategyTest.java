package com.university.finance.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.pattern.strategy.TransferStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferStrategyTest {

    @Test
    void transferShouldMoveMoney() {
        Account from = new Account("A1", 200.0, new User());
        Account to = new Account("A2", 100.0, new User());

        TransferStrategy strategy = new TransferStrategy();
        strategy.execute(from, to, 50.0);

        assertEquals(150.0, from.getBalance());
        assertEquals(150.0, to.getBalance());
    }

    @Test
    void transferWithInsufficientFundsShouldFail() {
        Account from = new Account("A1", 20.0, new User());
        Account to = new Account("A2", 100.0, new User());

        TransferStrategy strategy = new TransferStrategy();

        assertThrows(IllegalStateException.class,
                () -> strategy.execute(from, to, 50.0));
    }
}
