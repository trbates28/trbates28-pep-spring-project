package com.example.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.*;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping("/register")
  public ResponseEntity submitAccount(@RequestBody Account account){ 
    
    if (account.getUsername() == "" || account.getPassword().length() < 4) {
      return ResponseEntity.status(400).header("content-type", "application/json").body(account);
    }                           
    boolean newAccount = accountService.insertNewAccount(account);
    if (newAccount) {
      return ResponseEntity.status(200).header("content-type", "application/json").body(account);
    }
    else {
      return ResponseEntity.status(409).header("content-type", "application/json").body(account);
    }
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Account account) {
    Account dataBaseAccount = accountService.login(account);
    if (dataBaseAccount == null) {
      return ResponseEntity.status(401).header("content-type", "application/json").body(account);
    }
    return ResponseEntity.status(200).header("content-type", "application/json").body(dataBaseAccount);
  }
  
  @PostMapping("/messages")
  public ResponseEntity submitInfo(@RequestBody Message message){

    if (messageService.insertNewMessage(message) == null) {
      return ResponseEntity.status(400).body(message);
    }                                      

    return ResponseEntity.status(200).header("content-type", "application/json").body(message);
  }

  @DeleteMapping("/messages/{messageId}")
  public ResponseEntity deleteMessage(@PathVariable int messageId) {
    if (messageService.deleteById(messageId)) {
      return ResponseEntity.status(200).header("content-type", "application/json").body(1);
    }
    return ResponseEntity.status(200).header("content-type", "application/json").body(null);
  }

  @GetMapping("/messages")
  public ResponseEntity getAllMessages() {
    return ResponseEntity.status(200).header("content-type", "application/json").body(messageService.getAllMessages());
  }
  @GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity getMessagesByUser(@PathVariable int accountId) {
    return ResponseEntity.status(200).header("content-type", "application/json").body(messageService.getAllMessagesUser(accountId));
  }

  @GetMapping("/messages/{message_id}")
  public ResponseEntity getMessageById(@PathVariable int message_id) {
    return ResponseEntity.status(200).header("content-type", "application/json").body(messageService.getMessageById(message_id));
  }

  @PatchMapping("/messages/{messageId}")
  public ResponseEntity patchMessageById(@PathVariable int messageId, @RequestBody Map<String, Object> requestBody) {
    if (messageService.patchMessageById(messageId, (String) requestBody.get("messageText"))) {
      return ResponseEntity.status(200).header("content-type", "application/json").body(1);
    }
    return ResponseEntity.status(400).header("content-type", "application/json").body(null);
  }

}
