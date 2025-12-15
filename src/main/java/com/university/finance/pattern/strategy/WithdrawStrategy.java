package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

import java.util.UUID;

public class WithdrawStrategy implements TransactionStrategy {

    @Override
    public Transaction execute(Account from, Account to, double amount) {

        if (from == null)
            throw new IllegalArgumentException("Compte invalide");

        if (amount <= 0)
            throw new IllegalArgumentException("Montant invalide");

        if (from.getBalance() < amount)
            throw new IllegalStateException("Solde insuffisant");

        from.setBalance(from.getBalance() - amount);

        return new Transaction(
                UUID.randomUUID().toString(),
                Transaction.TransactionType.WITHDRAWAL,
                amount,
                from,
                to,
                "Retrait du compte " + from.getAccountNumber()
        );
    }
}
