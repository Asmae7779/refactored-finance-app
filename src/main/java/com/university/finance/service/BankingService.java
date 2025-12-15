package com.university.finance.service;

import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.model.User;
import com.university.finance.pattern.strategy.*;

public class BankingService {

    private final TransactionService transactionService;
    public static final Account CASH_ACCOUNT= new Account("SYS-CASH-001",100000,new User("12","SYS-CASH-001"));
    public static final Account EXTERNAL_ACCOUNT= new Account("SYS-EXT-001",100000,new User("144","SYS-EXT-001"));

    public BankingService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Transaction deposit(Account account, double amount) {
        return transactionService.process(
                new DepositStrategy(), account, CASH_ACCOUNT, amount);
    }

    public Transaction withdraw(Account account, double amount) {
        return transactionService.process(
                new WithdrawStrategy(), account, EXTERNAL_ACCOUNT, amount);
    }

    public Transaction transfer(Account from, Account to, double amount) {
        return transactionService.process(
                new TransferStrategy(), from, to, amount);
    }
}
