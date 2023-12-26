package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.InsufficientBalanceException;

public class WithdrawalTransaction extends Transaction{
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void apply(Account account) {
        try {
            account.withdraw(getAmount());
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
    }
}


