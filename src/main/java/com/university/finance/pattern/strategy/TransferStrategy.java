package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

import java.util.UUID;

public class TransferStrategy implements TransactionStrategy {

    @Override
    public Transaction execute(Account from, Account to, double amount) {

        if (from == null || to == null)
            throw new IllegalArgumentException("Comptes invalides");

        if (amount <= 0)
            throw new IllegalArgumentException("Montant invalide");

        if (from.getBalance() < amount)
            throw new IllegalStateException("Solde insuffisant");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        return new Transaction(
                UUID.randomUUID().toString(),
                Transaction.TransactionType.TRANSFER,
                amount,
                from,
                to,
                "Transfert de " + from.getAccountNumber() +
                        " vers " + to.getAccountNumber()
        );
    }
}
