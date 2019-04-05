package br.com.notificationapp.slackIntegration;

import br.com.notificationapp.slackIntegration.domain.SlackMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class SlackService {

    @Value("${slack.url}")
    private String url;

    public void sendNotificationToSlack(SlackMessage message){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<SlackMessage> httpEntity = new HttpEntity<>(message);
        restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        log.info("Notificação enviada com sucesso");
    }
}
