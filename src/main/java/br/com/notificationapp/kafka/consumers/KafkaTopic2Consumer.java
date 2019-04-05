package br.com.notificationapp.kafka.consumers;

import br.com.notificationapp.kafka.KafkaTopics;
import br.com.notificationapp.kafka.entity.KafkaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopic2Consumer {

    @KafkaListener(topics = KafkaTopics.KAFKA_TEST2)
    public void consume(KafkaMessage message) {
        System.out.println("Consumindo mensagem T2" + message);
    }
}
