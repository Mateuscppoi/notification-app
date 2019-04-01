package br.com.notficationapp.repository;

import br.com.notficationapp.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, Long> {

    Message findByOrigin(String origin);
}
