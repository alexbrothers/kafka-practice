# Kafka Practice

## Purpose
This repo is to practice/learn Kafka topics, including writing producers, consumers, consumer groups, etc.

## Local Development
1. Run `docker-compose up` to bring up the dependencies.
   1. Zookeeper
   2. Kafka Broker
   3. [Kowl](https://github.com/redpanda-data/kowl) - Kafka UI useful for debugging
2. Start up the Kafka consumer by running
```zsh
./gradlew -PmainClass=dev.alexbrothers.practice.kafka.Consumer run
```
3. Start up the Kafka producer by running
```
zsh./gradlew -PmainClass=dev.alexbrothers.practice.kafka.Producer run
```

Watch as messages are produced/consumed!

## Notes
This demo uses the `StringSerializer` and `StringDeserializer`. A more practical and real-world approach would be to use the `KafkaAvroSerializer` along with Schema Registry.