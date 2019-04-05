package br.com.notificationapp.slackIntegration.domain;

import lombok.Data;

import java.util.List;

@Data
public class SlackMessage {

    private List<SlackAttachments> attachments;

}
