package kafka;

/**
 * 类的注释
 *
 * @Package kafka
 * @ClassName ConsumerProperties
 * @Description Kafka的配置
 * @Author liyuzhi
 * @Date 2018-05-07 12:27
 */

public class ConsumerProperties {

    /**
     * 以下是Kafka消费者需要的参数
     */
    public final static String ZK = "192.168.101.188:2181";
    public final static String GROUP_ID = "1";
    public final static String BROKER_LIST = "192.168.101.188:9092";
    public final static int BUFFER_SIZE = 64 * 1024;
    public final static int TIMEOUT = 20000;
    public final static int INTERVAL = 10000;

    /**
     * 一下是Kafka消费者Consumer,消费的主题,
     */
    public final static String TOPIC_LIST = "esper_test,test,order_test";

}
