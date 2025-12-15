package com.university.finance.service;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankingServiceTest {

    @Test
    void depositShouldWork() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);

        Account acc = new Account("A1", 100.0, new User());
        bs.deposit(acc, 50.0);

        assertEquals(150.0, acc.getBalance());
    }

    @Test
    void withdrawShouldWork() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);

        Account acc = new Account("A1", 200.0, new User());
        bs.withdraw(acc, 100.0);

        assertEquals(100.0, acc.getBalance());
    }

    @Test
    void transferShouldWork() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);

        Account a1 = new Account("A1", 300.0, new User());
        Account a2 = new Account("A2", 100.0, new User());

        bs.transfer(a1, a2, 100.0);

        assertEquals(200.0, a1.getBalance());
        assertEquals(200.0, a2.getBalance());
    }
}
