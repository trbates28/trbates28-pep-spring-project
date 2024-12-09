package com.example.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

   // private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;

   /* public MessageService(MessageService messageService) {
        this.messageService = messageService;
    }*/ 

    public List<Message> getAllMessages() {

        return messageRepository.findAll();
    }

}
