package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String owner;
    private final String accountNumber;
    private double balance;
    private final List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Yetersiz Bakiye");
        }
        balance -= amount;
    }

    public void post(Transaction transaction) {
        transactions.add(transaction);
        transaction.apply(this);
    }
}
