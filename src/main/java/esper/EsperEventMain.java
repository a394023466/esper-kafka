package esper;

import esper.sql.EpserEventSql;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName EsperEventMain
 * @Description
 * @Author liyuzhi
 * @Date 2018-05-09 13:50
 */

public class EsperEventMain {
    public static void main(String[] args) {
        List<String> sqls = new ArrayList<String>();
        sqls.add(EpserEventSql.event_sql);


        //new EsperEventInit().processPattern(sqls).start();
       new EsperEventInit().processSql(sqls).start();
    }
}
