package br.com.notficationapp.slackIntegration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SlackAttachments {

    private String color;
    private String pretext;
    private String title;
    private String text;

}
