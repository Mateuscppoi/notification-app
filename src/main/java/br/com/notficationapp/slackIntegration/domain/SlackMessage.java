package br.com.notficationapp.slackIntegration.domain;

import lombok.Data;

import java.util.List;

@Data
public class SlackMessage {

    private List<SlackAttachments> attachments;

}
