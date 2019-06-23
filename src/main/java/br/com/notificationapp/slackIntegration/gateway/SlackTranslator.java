package br.com.notificationapp.slackIntegration.gateway;

import br.com.notificationapp.domain.Message;
import br.com.notificationapp.slackIntegration.SlackService;
import br.com.notificationapp.slackIntegration.domain.SlackAttachments;
import br.com.notificationapp.slackIntegration.domain.SlackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlackTranslator {

    @Autowired
    private SlackService slackService;

    public SlackMessage translateToSlackMessage(String formatedMessage, Message messageToConvert){
        SlackMessage slackMessage = new SlackMessage();
        SlackAttachments slackAttachments = new SlackAttachments();
        messageToConvert.getConditionToSuccess()
                .forEach(conditionToSuccess -> messageToConvert.getFieldToFindList()
                        .forEach(fieldToFind -> {
                            if (true){}
                        }));
        return slackMessage;
    }
}
