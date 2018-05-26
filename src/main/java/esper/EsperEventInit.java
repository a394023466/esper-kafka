package esper;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import kafka.Consumer;

import java.util.List;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName EsperEventInit
 * @Description 初始化引擎以及Kakfa的消费者
 * @Author liyuzhi
 * @Date 2018-05-09 10:46
 */

public class EsperEventInit {
    private final static EPServiceProvider engine;
    private final static EPAdministrator administrator;

    static {
        engine = EPServiceProviderManager.getDefaultProvider();
        administrator = engine.getEPAdministrator();
    }

    public Consumer processSql(List<String> sql) {
        EsperEventSetSql eventSetSql = new EsperEventSetSql();
        setSql(eventSetSql, sql);
        try {
            return new Consumer(engine);//初始化Kafka消费者
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Consumer processPattern(List<String> pattern) {
        EsperEventSetSql eventSetSql = new EsperEventSetSql();
        setPattern(eventSetSql, pattern);
        try {
            return new Consumer(engine);//初始化Kafka消费者
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setSql(EsperEventSetSql eventSetSql, List<String> sql) {
        for (String s : sql) {
            eventSetSql.setSql(EsperEventInit.administrator, s);
        }
    }

    private void setPattern(EsperEventSetSql eventSetSql, List<String> pattern) {

        for (String s : pattern) {
            eventSetSql.setPatter(administrator, s);
        }
    }
}
