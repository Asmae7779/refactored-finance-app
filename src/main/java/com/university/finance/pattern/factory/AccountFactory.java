package com.university.finance.pattern.factory;
import com.university.finance.model.Account;
import com.university.finance.model.User;

import java.util.UUID;

public class AccountFactory {

    public static Account createAccount(User owner, double initialBalance) {

        if (owner == null)
            throw new IllegalArgumentException("Proprietaire obligatoire");

        if (initialBalance < 0)
            throw new IllegalArgumentException("Solde negatif interdit");

        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setOwner(owner);
        account.setBalance(initialBalance);

        return account;
    }
}
