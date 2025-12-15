package com.university.finance.model;

import java.util.Date;

public class Transaction {
    private String id;
    private TransactionType type;
    private double amount;
    private Date date;
    private Account accountFrom;
    private Account accountTo;
    private String description;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    public Transaction() {
        this.date = new Date(); //date actuelle
    }

    public Transaction(String id, TransactionType type, double amount,
                       Account accountFrom, Account accountTo, String description) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = new Date();
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.description = description;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Account getAccountFrom() { return accountFrom; }
    public void setAccountFrom(Account accountFrom) { this.accountFrom = accountFrom; }

    public Account getAccountTo() { return accountTo; }
    public void setAccountTo(Account accountTo) { this.accountTo = accountTo; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                ", accountFrom=" + (accountFrom != null ? accountFrom.getAccountNumber() : "null") +
                ", accountTo=" + (accountTo != null ? accountTo.getAccountNumber() : "null") +
                ", description='" + description + '\'' +
                '}';
    }
}