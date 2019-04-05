package br.com.notificationapp.repository;

import br.com.notificationapp.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, Long> {

    Message findByOrigin(String origin);
}
