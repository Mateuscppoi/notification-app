package br.com.notificationapp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "MESSAGE")
public class Message {

    @Id
    private String id;
    private String origin;
    private String message;
    private String nickName;
    private List<ConditionToSuccess> conditionToSuccesse;
    private List<FieldToFind> fieldToFindList;
    private LocalDateTime creationDate;
}
