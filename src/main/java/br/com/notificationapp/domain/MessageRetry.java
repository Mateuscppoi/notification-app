package br.com.notificationapp.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class MessageRetry {

    private Long id;
    private String origin;
    private String message;
    private Integer retryTimes;
    private LocalDate lastRetry;
    private Boolean isDelivered;

    public boolean canRetry(){
        return this.retryTimes <= 3 ;
    }
}
