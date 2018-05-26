package esper;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import esper.domain.PersonEvent;
import esper.sql.EpserEventSql;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类的注释
 *
 * @Package PACKAGE_NAME
 * @ClassName EsperTest
 * @Description 这是一个Esper测试类
 * @Author liyuzhi
 * @Date 2018-05-07 10:25
 */

public class EsperTest {

    @Test
    public void esperTest() {
        List<String> sqls = new ArrayList<String>();
        sqls.add(EpserEventSql.NEW_WINDOW_SQL);
        sqls.add(EpserEventSql.Trigger_WINDOW_SQL);
        sqls.add(EpserEventSql.WINDOW_SQL);


        new EsperEventInit().processSql(sqls).start();

    }


}
