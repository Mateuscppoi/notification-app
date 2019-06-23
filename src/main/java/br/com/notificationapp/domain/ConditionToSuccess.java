package br.com.notificationapp.domain;

import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.notificationapp.domain.RegexConstants.OPERATOR_REGEX;

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

    public Boolean wasSucessful(String value){
        boolean wasSucessfull = false;
        for (String condition : condition.values()) {
            if (isNumber(condition)) {
                Integer convertedValue = Integer.valueOf(value);
                Integer convertedCondition = Integer.valueOf(condition);
                switch (getOperation()) {
                    case "=":
                        wasSucessfull = convertedValue == convertedCondition ;
                        break;
                    case ">":
                        wasSucessfull = convertedValue > convertedCondition;
                        break;
                    case "<":
                        wasSucessfull = convertedValue < convertedCondition;
                        break;

                }
            } else {
                wasSucessfull = value.equals(condition);
            }
        }
        return wasSucessfull;
    }

    private boolean isNumber(String condition) {
        return NumberUtils.isParsable(condition);
    }

}
