package br.com.notficationapp.service;

import br.com.notficationapp.domain.Message;
import br.com.notficationapp.repository.MessageRepository;
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
