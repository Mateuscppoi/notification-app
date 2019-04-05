package br.com.notificationapp.service;

import br.com.notificationapp.domain.Message;
import br.com.notificationapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message getMessageFromOrigin(String origin){
        return messageRepository.findByOrigin(origin);
    }

    public void saveNewMessage(Message message){
        messageRepository.save(message);
    }
}
