package br.com.notficationapp.gateway;

import br.com.notficationapp.domain.FieldToFind;
import br.com.notficationapp.domain.Message;
import br.com.notficationapp.dto.MessageDTO;
import br.com.notficationapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Strings.isNullOrEmpty;

@Component
public class MessageTranslator {

    private final String CONDITION_REGEX = "\\(([^)]+)\\)";
    private final String MESSAGE_REGEX = ":([^\\s]+[\\w ]+\\)|[^\\s|,]+)";
    private final String VALUE_REGEX = ":([^(]+)";

    @Autowired
    MessageService messageService;

    public Message getMessageFromOrigin(String origin) {
        return messageService.getMessageFromOrigin(origin);
    }

    public void saveNewMessage(MessageDTO messageDTO) {
        List<FieldToFind> toFindList = getFields(messageDTO.getMessage());
        messageService.saveNewMessage(createMessage(messageDTO, toFindList));
    }

    private Message createMessage(MessageDTO messageDTO, List<FieldToFind> toFindList) {
        Message message = new Message();
        message.setMessage(messageDTO.getMessage());
        message.setMessage(messageDTO.getMessage());
        message.setNickName(messageDTO.getNickname());
        message.setFieldToFindList(toFindList);
        message.setOrigin(messageDTO.getOrigin());
        message.setCreationDate(LocalDate.now());
        return message;
    }

    private List<FieldToFind> getFields(String message) {
        List<FieldToFind> toFindList = new ArrayList<>();
        Pattern messageCondition = Pattern.compile(MESSAGE_REGEX);
        Matcher messageMatcher = messageCondition.matcher(message);
        while (messageMatcher.find()) {
            FieldToFind fieldToFind = new FieldToFind();
            setInitialValues(messageMatcher, fieldToFind);
            String condition = findPattern(CONDITION_REGEX, messageMatcher.group(1));
            if (!isNullOrEmpty(condition)) {
                if (fieldToFind.getIsPath()){
                    fieldToFind.setAttributeName(condition);
                } else {
                    getConditions(fieldToFind, condition);
                }
            }
            toFindList.add(fieldToFind);
        }
        return toFindList;
    }

    private void setInitialValues(Matcher messageMatcher, FieldToFind fieldToFind) {
        fieldToFind.setMessageAttribute(messageMatcher.group());
        fieldToFind.setAttributeName(findPattern(VALUE_REGEX, messageMatcher.group()));
        fieldToFind.setIsPath(messageMatcher.group().contains("/"));
    }

    private void getConditions(FieldToFind fieldToFind, String condition) {
        List<String> strings = Arrays.asList(condition.split("="));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put(strings.get(0), strings.get(1));
        fieldToFind.setConditions(stringMap);
    }

    private String findPattern(String regex, String message) {
        Pattern messageCondition = Pattern.compile(regex);
        Matcher messageMatcher = messageCondition.matcher(message);
        if (messageMatcher.find()) {
            return messageMatcher.group(1);
        }
        return null;
    }
}
