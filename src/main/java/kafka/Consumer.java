package kafka;

import com.espertech.esper.client.EPServiceProvider;
import common.TopicMappingModelClass;
import esper.EsperEventSend;
import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

/**
 * 类的注释
 *
 * @Package PACKAGE_NAME
 * @ClassName Consumer
 * @Description Kafka消费者
 * @Author liyuzhi
 * @Date 2018-05-07 11:46
 */

public class Consumer extends ShutdownableThread {


    private KafkaConsumer<Integer, String> consumer = null;
    private EsperEventSend eventSend;
    private EPServiceProvider engine;

    public Consumer(EPServiceProvider engine) {
        super("KafkaConsumerExample", false);
        this.eventSend = new EsperEventSend();
        this.engine = engine;

        initKafkaConsumer();
    }

    private void initKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ConsumerProperties.BROKER_LIST);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, ConsumerProperties.GROUP_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, ConsumerProperties.INTERVAL);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, ConsumerProperties.TIMEOUT);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<Integer, String>(props);
    }

    @Override
    public void doWork() {
        consumer.subscribe(getTopic());
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        for (ConsumerRecord<Integer, String> record : records) {
            System.out.println(record.value());
            try {
                eventSend.send(record.value(), engine, TopicMappingModelClass.getModelClass(record.topic()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private List<String> getTopic() {
        List<String> topics = new ArrayList<>();
        for (String topic : ConsumerProperties.TOPIC_LIST.split(",")) {
            Collections.addAll(topics, topic);
        }
        return topics;
    }
}
