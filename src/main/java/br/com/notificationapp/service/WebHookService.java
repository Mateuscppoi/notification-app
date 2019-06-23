package br.com.notificationapp.service;

import br.com.notificationapp.domain.ConditionToSuccess;
import br.com.notificationapp.domain.FieldToFind;
import br.com.notificationapp.domain.Message;
import br.com.notificationapp.domain.MessageDTO;
import br.com.notificationapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.notificationapp.domain.RegexConstants.BRACKET_REGEX;

@Service
public class WebHookService {

    @Autowired
    private MessageRepository messageRepository;

    public String convertValuesToMessage(MessageDTO message){
        int sucessConditions = 0;
        message.getFieldToFindList()
                .forEach(field -> message.setMessage(message.getMessage().replace(field.getMessageAttribute(), field.getAttributeValue())));
        for (FieldToFind field : message.getFieldToFindList()) {
            for (ConditionToSuccess condition : message.getConditionToSuccess()) {
                if (field.getAttributeName().equals(formatKey(condition))) {
                    condition.wasSucessful("sasas");
                }
            }
        }
        return message.getMessage();
    }

    private String formatKey(ConditionToSuccess condition) {
        return String.valueOf(condition.getCondition().keySet()).replaceAll(BRACKET_REGEX, "");
    }

    private Message getMessageFromOrigin(String origin) {
        return messageRepository.findByOrigin(origin);
    }
}
