package com.university.finance.pattern.observer;

import com.university.finance.model.Transaction;

public class NotificationService implements TransactionObserver {

    @Override
    public void update(Transaction transaction) {
        if (transaction == null) {
            System.out.println("[NOTIFICATION] Transaction null");
            return;
        }
        System.out.println("[NOTIFICATION] Transaction "
                + transaction.getType()
                + " de " + transaction.getAmount());
    }
}
