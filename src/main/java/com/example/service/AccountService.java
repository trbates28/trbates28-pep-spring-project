package com.example.service;

import java.util.List;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountById(int accountId) {
        return accountRepository.findById(accountId);
    } 

    @Transactional
    public boolean insertNewAccount(Account account) {

    if (accountRepository.findByUser(account.getUsername()) != null) {
            return false; 
    }
    accountRepository.insertNewAccount(account.getUsername(), account.getPassword());
    return true;  
}

}
