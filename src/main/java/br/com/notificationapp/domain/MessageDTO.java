package br.com.notificationapp.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageDTO {

    private String id;
    private String origin;
    private String message;
    private String nickName;
    private List<ConditionToSuccess> conditionToSuccess;
    private List<FieldToFind> fieldToFindList;
    private LocalDateTime creationDate;
}
