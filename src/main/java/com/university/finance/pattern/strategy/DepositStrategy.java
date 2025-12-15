package com.university.finance.pattern.strategy;

import com.university.finance.model.Transaction;
import com.university.finance.model.Account;

import java.util.UUID;

public class DepositStrategy implements TransactionStrategy {

    @Override
    public Transaction execute(Account from, Account to, double amount) {
        if (from == null)
            throw new IllegalArgumentException("Compte invalide");

        if (amount <= 0)
            throw new IllegalArgumentException("Montant invalide");

        from.setBalance(from.getBalance() + amount);

        return new Transaction(
                UUID.randomUUID().toString(),
                Transaction.TransactionType.DEPOSIT,
                amount,
                from,
                to,
                "Depot sur le compte " + from.getAccountNumber()
        );
    }
}
