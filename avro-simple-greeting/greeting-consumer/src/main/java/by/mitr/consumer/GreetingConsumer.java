package by.mitr.consumer;

import by.mitr.avrolearn.Greeting;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class GreetingConsumer {
    private static final String GREETING_TOPIC = "greeting-topic";

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "greeting.consumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());

        KafkaConsumer<String,byte[]> consumer = new KafkaConsumer<String, byte[]>(props);

        consumer.subscribe(Collections.singletonList(GREETING_TOPIC));
        System.out.println("Simple-Greeting-Consumer starting work");
        while (true){
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String,byte[]> record: records){
                byte[] value = record.value();
                Greeting greeting = Greeting.fromByteBuffer(ByteBuffer.wrap(value));
                System.out.println( "Simple-Greeting-Consumer ger greeting with message - "+greeting.getGreeting()+" from - "+greeting.getFrom());
            }
        }
    }
}
