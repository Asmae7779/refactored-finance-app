package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;

/**
 * Interface du pattern STRATEGY pour les différentes stratégies de traitement des transactions.
 * Les classes concrètes implémenteront la logique spécifique (dépôt, retrait, transfert).
 */
public interface TransactionStrategy {
    Transaction execute(Account from, Account to, double amount);
}