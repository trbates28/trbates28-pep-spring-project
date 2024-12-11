package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(int accountId) {
        return accountRepository.findById(accountId).orElse(null);
    } 

    @Transactional
    public boolean insertNewAccount(Account account) {

    if (accountRepository.findByUsername(account.getUsername()) != null) {
            return false; 
    }
    accountRepository.save(account);
    return true;  
}

@Transactional
public Account login(Account account) {

Account dbAccount = accountRepository.findByUsername(account.getUsername());

if (dbAccount == null  || !dbAccount.getUsername().equals(account.getUsername()) || 
    !dbAccount.getPassword().equals(account.getPassword())) {
    return null;
}
return dbAccount;  
}

}
