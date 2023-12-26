package com.eteration.simplebanking.model;


public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) {
        account.deposit(getAmount());
    }
}
