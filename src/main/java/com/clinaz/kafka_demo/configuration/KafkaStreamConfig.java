package com.clinaz.kafka_demo.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@EnableKafkaStreams
@Configuration
public class KafkaStreamConfig {

  @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
  public KafkaStreamsConfiguration kStreamsConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "activity-stream-app");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
    props.put(StreamsConfig.consumerPrefix("auto.offset.reset"), "earliest");
    return new KafkaStreamsConfiguration(props);
  }

  @Bean
  public StreamsBuilderFactoryBeanConfigurer configurer() {
    return factoryBean -> factoryBean.setStateListener((newState, oldState) -> {
      System.out.printf("Kafka Streams state changed from %s to %s%n", oldState, newState);
    });
  }

  @Bean
  public KStream<String, String> kStream(StreamsBuilder builder) {
    ObjectMapper mapper = new ObjectMapper();

    KStream<String, String> stream = builder.stream("user-activity");

    stream
        .peek((key, value) -> System.out.printf("Received message: %s%n", value))
        .filter((key, value) -> {
          try {
            JsonNode node = mapper.readTree(value);
            return "LIKE".equalsIgnoreCase(node.get("type").asText());
          } catch (Exception e) {
            System.err.printf("Failed to parse event: %s%n", e.getMessage());
            return false;
          }
        })
        .peek((key, value) -> System.out.printf("Filtered LIKE event: %s%n", value))
        .to("likes-only");

    return stream;
  }
}
