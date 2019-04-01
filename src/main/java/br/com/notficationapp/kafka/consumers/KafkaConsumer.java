package br.com.notficationapp.kafka.consumers;

import br.com.notficationapp.kafka.KafkaTopics;
import br.com.notficationapp.kafka.entity.KafkaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = KafkaTopics.KAFKA_TEST)
    public void consume(KafkaMessage message) {
        System.out.println("Consumindo mensagem T1" + message);
    }
}
