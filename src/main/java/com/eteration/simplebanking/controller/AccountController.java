package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        Account account = accountService.findAccount(accountId);
        return ResponseEntity.ok(account);
    }

    public ResponseEntity<TransactionStatus> credit(String accountId, DepositTransaction depositTransaction) {
        try {
            Account account = accountService.findAccount(accountId);
            account.post(depositTransaction);
            return ResponseEntity.ok(new TransactionStatus("OK"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TransactionStatus(e.getClass().getSimpleName()));
        }
    }


    @PostMapping("/debit/{accountId}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountId, @RequestBody WithdrawalTransaction withdrawalTransaction) {
        try {
            Account account = accountService.findAccount(accountId);

            // Hesap bulunamazsa
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TransactionStatus("Account not found"));
            }

            // Yeterli bakiye kontrolü yapılıyor
            if (account.getBalance() < withdrawalTransaction.getAmount()) {
                throw new InsufficientBalanceException("Yetersiz bakiye");
            }

            account.post(withdrawalTransaction);
            return ResponseEntity.ok(new TransactionStatus("OK"));
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionStatus("Yetersiz Bakiye"));
        }
    }

}
