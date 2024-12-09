package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
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

    private AccountService accountService;
    @Autowired
    private MessageService messageService;

  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/messages")
  public ResponseEntity getAllMessages() {
    return ResponseEntity.status(200).header("content-type", "application/json").body(messageService.getAllMessages());

  }

}
