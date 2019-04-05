package br.com.notificationapp.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.notificationapp.domain.RegexConstants.*;

@Getter
public class ConditionToSuccess {

    private Map<String, String> condition;
    private String operation;

    public ConditionToSuccess(String operator, String message) {
        List<String> conditions = Arrays.asList(message.split(OPERATOR_REGEX));
        if (!conditions.isEmpty()){
            Map<String, String> condition =  new HashMap<>();
            condition.put(conditions.get(0), conditions.get(1));
            this.condition = condition;
        }
        this.operation = operator;
    }

}
