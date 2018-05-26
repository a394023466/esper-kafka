package esper;

import com.espertech.esper.client.*;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName EsperEventSetSql
 * @Description 初始化Esper引擎
 * @Author liyuzhi
 * @Date 2018-05-08 19:06
 */

public class EsperEventSetSql {

    /**
     * 方法的注释
     *
     * @return void
     * @description 创建上下文, 或者额外的Sql，能够根据不同给的上下文来实现不同的功能
     * @methodName createContext
     * @Param: administrator
     * @Param: cts  上下文的 Sql
     * @author liyuzhi
     * @createTime 2018-05-07 19:25
     * @version v1.0
     */
    public void setSql(EPAdministrator administrator, String sql) {
        EPStatement epStatement = null;
        if (sql != null) {
            String[] sqls = sql.split(";");
            for (int i = 0; i < sqls.length; i++) {
                if (i == sqls.length - 1) {
                    epStatement = administrator.createEPL(sqls[i]);
                }
                administrator.createEPL(sqls[i]);

            }
        }
        if (epStatement != null) {
            epStatement.addListener(new EsperEventListener());
        }
    }

    public void setPatter(EPAdministrator administrator, String patternSql) {
        administrator.createPattern(patternSql).addListener(new EsperEventListener());
    }
}
