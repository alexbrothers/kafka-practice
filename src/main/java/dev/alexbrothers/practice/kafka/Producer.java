package dev.alexbrothers.practice.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private static final String BOOTSTRAP_SERVERS = "127.0.0.1:9092";
    private static final String TOPIC = "kafka-practice-topic";

    public static void main(String[] args) throws InterruptedException {
        log.info("Starting as Kafka Producer");
        final Properties producerProperties = getProducerProperties();
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(producerProperties);
        for (int i = 0; i < 1000; i++) {
            final ProducerRecord<String, String> message = new ProducerRecord<>(
                    TOPIC,
                    String.valueOf(i),
                    "Message " + i
            );
            kafkaProducer.send(message, (metadata, exception) -> {
                if (exception != null) {
                    log.error("Exception occurred while sending message: {}", exception.getMessage());
                } else {
                    log.info("Successfully send message with offset {}", metadata.offset());
                }
            });
            Thread.sleep(500L);
        }
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    private static Properties getProducerProperties() {
        final Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

}
