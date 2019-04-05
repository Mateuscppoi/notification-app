package br.com.notificationapp.slackIntegration.domain;

import lombok.Data;

@Data
public class SlackAttachments {

    private String color;
    private String pretext;
    private String title;
    private String text;

}
