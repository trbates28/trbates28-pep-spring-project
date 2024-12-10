package com.example.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Service
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getAllMessagesUser(int accountId) {
        return messageRepository.getAllByAccount(accountId);
    }

    public Message getMessageById(int messageId) {
        return messageRepository.findById(messageId);
    }

    public boolean deleteById(int message_id) {

        if (messageRepository.findById(message_id) == null) {
            return false;
        }
        messageRepository.deleteById(message_id);
        return true;

    }

    public boolean patchMessageById(int message, String body) {
   
    if (messageRepository.findById(message) == null || body == "" || body.length() > 255) {
        return false; 
    }
    messageRepository.patchMessageById(body, message);
    return true;
}

    @Transactional
    public Message insertNewMessage(Message message) {
       if (accountRepository.findById(message.getPostedBy()) == null || message.getMessageText() == "" || message.getMessageText().length() > 255) {
            return null; 
        }
        messageRepository.insertNewMessage(message.getPostedBy(), message.getMessageText(), message.getTimePostedEpoch());
        //there was no way to retrieve the message that was just inserted to get it or it's automatically
        //generated id so this was my only working solution to produce the message requested
        message.setMessageId(messageRepository.postedBy(message.getPostedBy()));
        return message;  
    }

}
