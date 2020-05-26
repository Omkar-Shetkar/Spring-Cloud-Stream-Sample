#### Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

#### Start Kafka
bin/kafka-server-start.sh config/server.properties

#### Start Console Consumer
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic log-in-0 --from-beginning

#### Start Console Producer
 ./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic log-in-0


