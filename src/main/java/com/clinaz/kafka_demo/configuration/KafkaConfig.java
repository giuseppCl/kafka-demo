package com.clinaz.kafka_demo.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
  @Bean
  public NewTopic userActivityTopic() {
    return TopicBuilder.name("user-activity")
        .partitions(2)
        .build();
  }
}
