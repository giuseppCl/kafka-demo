package com.clinaz.kafka_demo.service;

import com.clinaz.kafka_demo.model.ActivityEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ActivityConsumer1 {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @KafkaListener(topics = "user-activity", groupId = "activity-consumer")
  public void listen(ConsumerRecord<String, String> record) {
    try {
      String json = record.value();
      ActivityEvent event = objectMapper.readValue(json, ActivityEvent.class);
      System.out.printf("Consumer1 - Partition %d: %s - %s%n",
          record.partition(), event.getUserId(), event.getType());
    } catch (Exception e) {
      System.err.println("Consumer1 - Failed to parse event: " + e.getMessage());
    }
  }
}