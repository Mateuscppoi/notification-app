package br.com.notificationapp.api;

import br.com.notificationapp.domain.Message;
import br.com.notificationapp.dto.MessageToCreateDTO;
import br.com.notificationapp.gateway.JsonTranslator;
import br.com.notificationapp.gateway.MessageTranslator;
import br.com.notificationapp.service.WebHookService;
import br.com.notificationapp.slackIntegration.SlackService;
import br.com.notificationapp.slackIntegration.domain.SlackMessage;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static java.util.Objects.isNull;

@Log4j2
@RestController
@RequestMapping(value = "/api")
public class WebhookApi {

    @Autowired
    private WebHookService webhookService;

    @Autowired
    private JsonTranslator jsonTranslator;

    @Autowired
    private MessageTranslator messageTranslator;

    @Autowired
    private SlackService slackService;

    @PostMapping("/createMessage")
    public void receiveMessage(@RequestBody MessageToCreateDTO message) {
        Message existingMessage = messageTranslator.getMessageFromOrigin(message.getOrigin());
        if (isNull(existingMessage)) {
            messageTranslator.saveNewMessage(message);
        } else {
            throw new RuntimeException("Já existe uma mensagem cadatrada para esse endpoint");
        }
    }

    @PostMapping(value = "/slack/{origin}")
    @ResponseStatus(HttpStatus.CREATED)
    public String WebhookFromOriginSlack(@PathVariable("origin") String origin, @RequestBody Object object) throws IOException {
        JsonNode jsonNode = jsonTranslator.translateToJsonNode(object);
        Message messageFromOrigin = messageTranslator.getMessageFromOrigin(origin);

        jsonTranslator.getDataFromNode(jsonNode, messageFromOrigin.getFieldToFindList());
        return webhookService.convertValuesToMessage(messageFromOrigin);
    }

    @PostMapping("/realDeal/{origin}")
    public String real(@PathVariable("origin") String origin, @RequestBody Object json) throws IOException {
        log.info("Recebido um json de origem /{}", origin);
        JsonNode jsonNode = jsonTranslator.translateToJsonNode(json);
        Message messageFromOrigin = messageTranslator.getMessageFromOrigin(origin);

        jsonTranslator.getDataFromNode(jsonNode, messageFromOrigin.getFieldToFindList());
        String message = webhookService.convertValuesToMessage(messageFromOrigin);
        log.info("Enviando notificação para o slack");
        System.out.println(message);
        return message;
    }

    @GetMapping("/slack")
    public void testSlackAttatchment(){
        SlackMessage message = new SlackMessage();
//        SlackAttachments slackAttachments = new SlackAttachments("#36a64f", "Test Pretext", "Test Title", "Test Body");
//        message.setAttachments(Lists.newArrayList(slackAttachments));
        slackService.sendNotificationToSlack(message);
    }
}
