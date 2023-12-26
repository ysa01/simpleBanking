package com.eteration.simplebanking.model;


import java.util.Date;

public abstract class Transaction {
    private final Date date;
    private final double amount;

    Transaction(double amount) {
        this.date = new Date();
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void apply(Account account);
}



