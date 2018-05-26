package common;

import esper.domain.OrderEvent;
import esper.domain.PersonEvent;
import esper.domain.PersonEventTrigger;

import java.util.HashMap;
import java.util.Map;

/**
 * 类的注释
 *
 * @Package common
 * @ClassName TopicMappingModelClass
 * @Description 主题与实体映射
 * @Author liyuzhi
 * @Date 2018-05-08 18:50
 */

public class TopicMappingModelClass {
    private final static Map<String, Class> map = new HashMap<>();

    static {
        map.put("esper_test", PersonEvent.class);
        map.put("test", PersonEventTrigger.class);
        map.put("order_test", OrderEvent.class);
    }

    public static Class getModelClass(String topic) {
        return map.get(topic);
    }
}
