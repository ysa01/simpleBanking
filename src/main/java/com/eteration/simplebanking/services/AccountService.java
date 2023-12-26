package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private Map<String, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
        Account sampleAccount = new Account("Test Account", "123456");
        accounts.put(sampleAccount.getAccountNumber(), sampleAccount);
    }

    public Account findAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            try {
                throw new AccountNotFoundException("Hesap bulunamadı id: " + accountId);
            } catch (AccountNotFoundException e) {
                e.printStackTrace();
            }
        }
        return account;
    }
}
