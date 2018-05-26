package utils;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

/**
 * 类的注释
 *
 * @Package utils
 * @ClassName EsperEventUtils
 * @Description Esper工具类
 * @Author liyuzhi
 * @Date 2018-05-07 13:19
 */

public class EsperEventUtils {

    public static void get(String message) {
        EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();//1.初始化Esper引擎

        engine.getEPAdministrator().getConfiguration().addEventType(String.class); //2.告诉Esper处理事件的类policy型

//        EPStatement statement = engine.getEPAdministrator().createEPL(getSql());//3.创建EPL查询
//        engine.getEPRuntime().sendEvent(new PersonEvent("liyuzhi", 10));
    }
}
