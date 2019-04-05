package br.com.notificationapp.gateway;

import br.com.notificationapp.domain.ConditionToSuccess;
import br.com.notificationapp.domain.FieldToFind;
import br.com.notificationapp.domain.Message;
import br.com.notificationapp.dto.MessageToCreateDTO;
import br.com.notificationapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.notificationapp.domain.RegexConstants.*;
import static com.google.common.base.Strings.isNullOrEmpty;

@Component
public class MessageTranslator {

    @Autowired
    MessageService messageService;

    public Message getMessageFromOrigin(String origin) {
        return messageService.getMessageFromOrigin(origin);
    }

    public void saveNewMessage(MessageToCreateDTO messageToCreateDTO) {
        List<ConditionToSuccess> conditionsToSuccess = getMessageConditions(messageToCreateDTO.getMessage());
        String messageField = findPattern((MESSAGE_FIELDS_REGEX), messageToCreateDTO.getMessage(), 1);
        List<FieldToFind> toFindList = getFieldsToFind(messageField);
        Message message = toMessage(messageToCreateDTO.getMessage(), toFindList, conditionsToSuccess);
        messageService.saveNewMessage(message);
    }

    private List<ConditionToSuccess> getMessageConditions(String message) {
        List<ConditionToSuccess> toSuccessList =  new ArrayList<>();

        Pattern successCondition = Pattern.compile(CONDITIONS_FIELDS_REGEX);
        Matcher messageMatcher = successCondition.matcher(message);
        if (messageMatcher.find()){
            List<String> conditionsList = Arrays.asList(messageMatcher.group(1).split(","));
            conditionsList.forEach(condition -> {
                String operator = findPattern(OPERATOR_REGEX, condition, 0);
                if(!isNullOrEmpty(operator)){
                    toSuccessList.add(new ConditionToSuccess(operator, condition));
                }
            });
        }
        return toSuccessList;
    }

    private Message toMessage(String messageToCreate, List<FieldToFind> toFindList, List<ConditionToSuccess> conditionsToSuccess) {
        Message message = new Message();
        message.setMessage(findPattern(MESSAGE_FIELDS_REGEX, messageToCreate, 1));
        message.setNickName(findPattern(NICKNAME_FIELDS_REGEX, messageToCreate, 1));
        message.setFieldToFindList(toFindList);
        message.setConditionToSuccesse(conditionsToSuccess);
        message.setOrigin(findPattern(ORIGIN_FIELDS_REGEX, messageToCreate, 1));
        message.setCreationDate(LocalDateTime.now());
        return message;
    }

    private List<FieldToFind> getFieldsToFind(String message) {
        List<FieldToFind> toFindList = new ArrayList<>();
        Pattern messageCondition = Pattern.compile(MESSAGE_REGEX);
        Matcher messageMatcher = messageCondition.matcher(message);
        while (messageMatcher.find()) {
            FieldToFind fieldToFind = new FieldToFind();
            setInitialValues(messageMatcher, fieldToFind);
            String condition = findPattern(VALUE_CONDITION_REGEX, messageMatcher.group(1), 1);
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
        fieldToFind.setAttributeName(findPattern(VALUE_REGEX, messageMatcher.group(), 1));
        fieldToFind.setIsPath(messageMatcher.group().contains("/"));
    }

    private void getConditions(FieldToFind fieldToFind, String condition) {
        List<String> strings = Arrays.asList(condition.split("="));
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put(strings.get(0), strings.get(1));
        fieldToFind.setConditions(stringMap);
    }

    private String findPattern(String regex, String message, int groupNum) {
        Pattern messageCondition = Pattern.compile(regex);
        Matcher messageMatcher = messageCondition.matcher(message);
        if (messageMatcher.find()) {
            return messageMatcher.group(groupNum);
        }
        return null;
    }
}
