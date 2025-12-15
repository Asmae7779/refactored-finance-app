package com.university.finance.pattern.observer;

import com.university.finance.model.Transaction;

public class AuditLogger implements TransactionObserver {

    @Override
    public void update(Transaction transaction) {
        System.out.println("[AUDIT] " + transaction);
    }
}
