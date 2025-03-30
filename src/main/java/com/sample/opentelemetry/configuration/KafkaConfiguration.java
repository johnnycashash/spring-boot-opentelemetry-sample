package com.sample.opentelemetry.configuration;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.micrometer.KafkaTemplateObservation;

@Configuration
public class KafkaConfiguration {
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory, KafkaProperties kafkaProperties) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(kafkaProperties.getTemplate().getDefaultTopic());
        kafkaTemplate.setTransactionIdPrefix(kafkaProperties.getTemplate().getTransactionIdPrefix());
        kafkaTemplate.setObservationEnabled(true);
        kafkaTemplate.setObservationConvention(new KafkaTemplateObservation.DefaultKafkaTemplateObservationConvention());
        return kafkaTemplate;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        concurrentKafkaListenerContainerFactory.setAutoStartup(true);
        return concurrentKafkaListenerContainerFactory;
    }
}
