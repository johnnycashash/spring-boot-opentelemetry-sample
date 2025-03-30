package com.sample.opentelemetry.listeners;

import com.sample.opentelemetry.domain.User;
import com.sample.opentelemetry.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class UserRequestKafkaListener {
    private final UserJpaRepository userJpaRepository;
    Logger log = LoggerFactory.getLogger(UserRequestKafkaListener.class);

    public UserRequestKafkaListener(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @KafkaListener(clientIdPrefix = "create-user-topic", topics = "create-user-topic", concurrency = "1", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void createUserListener(String message) {
        log.info("Received user creation request for id {}", message);
        userJpaRepository.save(new User(Long.parseLong(message), "Created by Kafka", "temp"));
    }
}
