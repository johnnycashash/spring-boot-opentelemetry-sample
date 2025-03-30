package com.sample.opentelemetry.controller;

import com.sample.opentelemetry.domain.User;
import com.sample.opentelemetry.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/users")
public class SpringRestController {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private UserJpaRepository repository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return repository.findById(id).orElseGet(() -> {
            kafkaTemplate.send("create-user-topic", id);
            return repository.findById(id).get();
        });
    }

}
