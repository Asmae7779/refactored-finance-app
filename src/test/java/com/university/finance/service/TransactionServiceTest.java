package com.university.finance.service;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.model.User;
import com.university.finance.pattern.observer.TransactionObserver;
import com.university.finance.pattern.strategy.TransactionStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Test
    void historyShouldBeEmptyAtStart() {
        TransactionService service = new TransactionService();

        assertNotNull(service.getHistory());
        assertTrue(service.getHistory().isEmpty());
    }

    @Test
    void shouldAddTransactionToHistory() {
        TransactionService service = new TransactionService();

        TransactionStrategy strategy = (from, to, amount) ->
                new Transaction("1", Transaction.TransactionType.DEPOSIT,
                        amount, null, to, "test");

        Account to = new Account("A1", 100.0, new User());

        Transaction tx = service.process(strategy, null, to, 50.0);

        assertEquals(1, service.getHistory().size());
        assertEquals(tx, service.getHistory().get(0));
    }

    @Test
    void processShouldReturnTransaction() {
        TransactionService service = new TransactionService();

        TransactionStrategy strategy = (from, to, amount) ->
                new Transaction("2", Transaction.TransactionType.WITHDRAWAL,
                        amount, from, null, "test");

        Account from = new Account("A1", 200.0, new User());

        Transaction tx = service.process(strategy, from, null, 50.0);

        assertNotNull(tx);
        assertEquals(50.0, tx.getAmount());
    }

    @Test
    void observerShouldBeNotified() {
        TransactionService service = new TransactionService();

        final boolean[] notified = {false};

        TransactionObserver observer = transaction -> notified[0] = true;
        service.addObserver(observer);

        TransactionStrategy strategy = (from, to, amount) ->
                new Transaction("3", Transaction.TransactionType.DEPOSIT,
                        amount, null, to, "test");

        Account to = new Account("A1", 100.0, new User());

        service.process(strategy, null, to, 30.0);

        assertTrue(notified[0]);
    }

    @Test
    void multipleObserversShouldBeNotified() {
        TransactionService service = new TransactionService();

        final int[] counter = {0};

        TransactionObserver o1 = tx -> counter[0]++;
        TransactionObserver o2 = tx -> counter[0]++;

        service.addObserver(o1);
        service.addObserver(o2);

        TransactionStrategy strategy = (from, to, amount) ->
                new Transaction("4", Transaction.TransactionType.DEPOSIT,
                        amount, null, to, "test");

        Account to = new Account("A1", 100.0, new User());

        service.process(strategy, null, to, 20.0);

        assertEquals(2, counter[0]);
    }

    @Test
    void processShouldThrowExceptionIfStrategyFails() {
        TransactionService service = new TransactionService();

        TransactionStrategy strategy = (from, to, amount) -> {
            throw new IllegalArgumentException("Strategy error");
        };

        Account to = new Account("A1", 100.0, new User());

        assertThrows(IllegalArgumentException.class,
                () -> service.process(strategy, null, to, 10.0));
    }
}
