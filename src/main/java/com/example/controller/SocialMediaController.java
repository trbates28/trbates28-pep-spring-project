package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("social")
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody List<Message> getMessages() {

    return null;

  }

}
