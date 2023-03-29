package by.mitr.provider;

import by.mitr.avrolearn.Greeting;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GreetingProvider {
    private static final String GREETING_TOPIC = "greeting-topic";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        KafkaProducer<String,byte[]> producer =new KafkaProducer<String, byte[]>(props);

        Greeting greeting = Greeting.newBuilder().setGreeting("Hi Avro learn").setFrom("Simple-Greeting-Provider").build();

        byte[] value = greeting.toByteBuffer().array();
        ProducerRecord<String, byte[]> record = new ProducerRecord<>(GREETING_TOPIC,value);
        RecordMetadata recordMetadata = producer.send(record).get();

        System.out.println("Simple-Greeting-Producer ger recordMetadata - "+recordMetadata.toString());



    }
}
