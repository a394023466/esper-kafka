package esper;

import com.espertech.esper.client.*;
import utils.JsonUtil;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName EsperEventSend
 * @Description 数据源的输入类
 * @Author liyuzhi
 * @Date 2018-05-07 13:17
 */

public class EsperEventSend {

    /**
     * 方法的注释
     *
     * @return void
     * @description 从Kakfa接收到数据以后，利用Esper发送数据
     * @methodName inputData
     * @Param: s 从Kakfa接收到的数据
     * @Param: engine Esper引擎
     * @author liyuzhi
     * @createTime 2018-05-07 16:36
     * @version v1.0
     */
    public void send(String s, EPServiceProvider engine, Class clazz) {
        try {
            Object o = JsonUtil.toObject(s, clazz);
            engine.getEPRuntime().sendEvent(o);
        } catch (EPException | EPServiceDestroyedException e) {
            e.printStackTrace();
        }
    }


}
