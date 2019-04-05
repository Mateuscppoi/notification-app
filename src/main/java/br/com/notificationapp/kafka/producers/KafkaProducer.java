package br.com.notificationapp.kafka.producers;

import br.com.notificationapp.kafka.entity.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @Autowired
    private KafkaProducer(final KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void addMessage(KafkaMessage message , String topics) {
        kafkaTemplate.send(topics, message);
    }
}
