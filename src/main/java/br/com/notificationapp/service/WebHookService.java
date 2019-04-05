package br.com.notificationapp.service;

import br.com.notificationapp.domain.Message;
import br.com.notificationapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebHookService {

    @Autowired
    private MessageRepository messageRepository;

    public String convertValuesToMessage(Message message){
        message.getFieldToFindList()
                .forEach(field -> message.setMessage(message.getMessage().replace(field.getMessageAttribute(), field.getAttributeValue())));
        return message.getMessage();
    }

    private Message getMessageFromOrigin(String origin) {
        return messageRepository.findByOrigin(origin);
    }
}
