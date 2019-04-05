package br.com.notificationapp.dto;

import lombok.Data;

@Data
public class MessageToCreateDTO {

    private String origin;
    private String message;
    private String nickname;
}
