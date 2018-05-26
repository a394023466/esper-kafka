package esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName EsperEventListener
 * @Description 事件监听器
 * @Author liyuzhi
 * @Date 2018-05-07 13:12
 */

public class EsperEventListener implements UpdateListener {
    /**
     * 方法的注释
     *
     * @return void
     * @description Esper监控数据流的功能
     * @methodName update
     * @Param: newData 新数据流
     * @Param: oldData 过时的数据流
     * @author liyuzhi
     * @createTime 2018-05-07 16:39
     * @version v1.0
     */
    @Override
    public void update(EventBean[] newData, EventBean[] oldData) {
        for (EventBean newDatum : newData) {
            System.err.println(newDatum.getUnderlying());
//            System.err.println("a:" + newDatum.get("a"));
//            System.err.println("b:" + newDatum.get("b"));
//            System.err.println("b:" + newDatum.get("name"));
//            System.err.println("b:" + newDatum.get("age"));
//            System.err.println("b:" + newDatum.get("c"));
        }
        // System.err.println(bean.get("name") +"   "+bean.get("age"));
        //System.err.println(bean.get("c"));
    }

}
