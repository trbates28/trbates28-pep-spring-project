package com.example.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


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
        return messageRepository.findByPostedBy(accountId);
       // return messageRepository.getAllByAccount(accountId);
    }

    public Message getMessageById(int messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public boolean deleteById(int message_id) {

        if (messageRepository.findById(message_id).isEmpty()) {
            return false;
        }
        messageRepository.deleteById(message_id);
        return true;
    }


    public boolean patchMessageById(int message, String body) {
   
    if (messageRepository.findById(message).isEmpty() || body == "" || body.length() > 255) {
        return false; 
    }
    Message patchMessage = messageRepository.getById(message);
    patchMessage.setMessageText(body);
    messageRepository.save(patchMessage);
    return true;
}

    @Transactional
    public Message insertNewMessage(Message message) {
       if (accountRepository.findById(message.getPostedBy()).isEmpty() || message.getMessageText() == "" || message.getMessageText().length() > 255) {
            return null; 
        }
        messageRepository.save(message);

        //there was no way to retrieve the message that was just inserted to get it or it's automatically
        //generated id so this was my only working solution to produce the message requested since this new
        //message's id is lower than every other messages id in the table 
        return messageRepository.getById(messageRepository.postedBy(message.getPostedBy()));  
    }

}
