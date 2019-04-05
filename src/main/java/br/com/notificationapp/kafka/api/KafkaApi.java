package br.com.notificationapp.kafka.api;


import br.com.notificationapp.kafka.KafkaTopics;
import br.com.notificationapp.kafka.entity.KafkaMessage;
import br.com.notificationapp.kafka.producers.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KafkaApi {

    private final KafkaProducer producer;

    public KafkaApi(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/t1")
    public void createT1Message(@RequestParam("name") String name) {
        producer.addMessage(new KafkaMessage(name), KafkaTopics.KAFKA_TEST);

    }

    @PostMapping("/t2")
    public void createT2Message(@RequestParam("name") String name) {
        producer.addMessage(new KafkaMessage(name), KafkaTopics.KAFKA_TEST2);

    }
}
