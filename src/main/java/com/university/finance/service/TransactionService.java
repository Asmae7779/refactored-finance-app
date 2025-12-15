package com.university.finance.service;
import com.university.finance.model.Transaction;
import com.university.finance.pattern.observer.TransactionObserver;
import com.university.finance.pattern.strategy.TransactionStrategy;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private final List<TransactionObserver> observers = new ArrayList<>();

    private final List<Transaction> history = new ArrayList<>();

    public List<Transaction> getHistory() {
        return history;
    }
    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public Transaction process(TransactionStrategy strategy,
                               com.university.finance.model.Account from,
                               com.university.finance.model.Account to,
                               double amount) {

        Transaction transaction = strategy.execute(from, to, amount);
        observers.forEach(o -> o.update(transaction));
        history.add(transaction);
        return transaction;
    }
}
