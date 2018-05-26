package esper.sql;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName 所有EPL查询的Sql
 * @Description Sql
 * @Author liyuzhi
 * @Date 2018-05-07 16:43
 */

public class EpserEventSql {


    //查询窗口为1的数据
    //public final static String PERSON_SQL = "select name,age from esper.domain.PersonEvent.win:length(1)";

    //查询窗口为10的数据条目   测试测试窗口的功能是否有效
    //public final static String PERSON_SQL = "select count(*) as c from esper.domain.PersonEvent.win:length(10) group by name";

    //查询窗口长度为3并且年龄大于15的数据信息,窗口里存储的都是大于15的数据，窗口被大于15的装满，那么才进行进栈出栈的操作
    //public final static String PERSON_SQL = "select name,age from esper.domain.PersonEvent (age >15)#length(3)";

    //时间窗口为4秒内 每一秒发生的事件数，截止时间是4秒,例如出栈进栈规则一样 四秒的时间截止，先进先出
    // public final static String PERSON_SQL = "select count(*) as c from esper.domain.PersonEvent#time(4)";

    /**
     * 通过上下文设定的约束，来过滤或者统计四秒内时间窗口的数据 也就是分区为4秒的统计数据
     * public final static String CONTEXT_SQL="create context Batch4Seconds start @now end after 4 sec";
     * 创建上下文
     * public final static String PERSON_SQL="context Batch4Seconds select count(*) as c from esper.domain.PersonEvent";
     */

    //每四秒输出一次，统计结果会叠加。
    //public final static String PERSON_SQL="select count(*) as c from esper.domain.PersonEvent output last every 4 seconds";

    /**
     * 创建上下文：创建四秒的时间窗口，也就是每四秒为一个分区
     * public final static String CONTEXT_SQL="create context Batch4Seconds start @now end after 4 sec";
     * 根据上下文的约束来执行输出，terminated：在上下文约束的条件终止时触发输出操作 分区里的统计结果
     * public final static String PERSON_SQL = "context Batch4Seconds select count(*) as c from esper.domain.PersonEvent output last when terminated";
     */

   /* 1.创建一个命名窗口
      2.按条件约束来向窗口插入条件
      3.根据约束条件，来触发监听器的执行策略
     public final static String WINDOW_SQL = "create window PersonEventWindow#length(10) as select name,age from esper.domain.PersonEvent;" +
            "insert into PersonEventWindow select name,age from PersonEvent;" +
            "on esper.domain.PersonEvent(age=15) as query select count(*)  from PersonEventWindow as pw where pw.name=query.name";
   */


   /*
     1.创建一个10长度为10的命名窗口
     2.根据规则来向窗口里插入数据
     3.设置窗口的触发条件，并且根据where条件将窗口里的数据推送给UpdateListener
     public final static String WINDOW_SQL = "create window PersonEventWindow#length(10) as select name,age from esper.domain.PersonEvent;" +
            "insert into PersonEventWindow select name,age from esper.domain.PersonEvent;" +
            "on esper.domain.PersonEventTrigger(trigger=100) as trigger select pw.*  from PersonEventWindow as pw where trigger.name=pw.name";
   */


    public final static String WINDOW_SQL =
            "create window PersonEventWindow#length(10) as  esper.domain.PersonEvent;" +
                    "insert into PersonEventWindow select name,age from esper.domain.PersonEvent;" +
                    "on esper.domain.PersonEventTrigger(trigger=100) as trigger select pw.age  from PersonEventWindow as pw where trigger.name=pw.name;";


    public final static String NEW_WINDOW_SQL = "create window OrderEventWindow#length(10) as  esper.domain.OrderEvent;" +
            "insert into OrderEventWindow select price,name from esper.domain.OrderEvent;" +
            "on esper.domain.OrderEvent(name='wxm') select oe.* from OrderEventWindow as oe";

    public final static String Trigger_WINDOW_SQL = "create window PersonEventTriggerWindow#length(10) as  esper.domain.PersonEventTrigger;" +
            "insert into PersonEventTriggerWindow select trigger,name from esper.domain.PersonEventTrigger;" +
            "on esper.domain.PersonEventTrigger(trigger=100) select poe.* from PersonEventTriggerWindow as poe";

    //多个事件之间的关联查询
    public final static String ASSOCIATE_SQL = "select pe.name,pet.trigger from esper.domain.PersonEvent#length(10) as pe, esper.domain.PersonEventTrigger#length(5) as pet " +
            "where pe.name=pet.name";

    //匹配模式匹配，every为关键字，它表示对每个时间进行过滤，它能与逻辑运算组合使用，and表示两个匹配模式都要通过才会触发UpdateListener,or 表示只要符合一个匹配模式就能触发UpdateListener
    public final static String EVERY_SQL = "every (a = esper.domain.PersonEvent(name='liyuzhi')@consume(3) and b=esper.domain.PersonEvent(age=100)@consume(2))";

    //创建schema
    public final static String SCHEMA_SQL = "create schema PersonSchema as esper.domain.PersonEvent;" +
            "insert into PersonSchema select name , age from esper.domain.PersonEvent;" +
            "select * from PersonSchema";
    //根据用户Id定义分区context，然后每个分区只存储一个用户的数据，后边的Parttern语句表示的意思就是当分区里的用户第一次取款超过400，并且在十分钟内再次取款超过400，那么就会出发UpdateListener。
    public final static String PARTITION_SQL = "create context PersonEventPartitionByName partition by name from esper.domain.PersonEvent;" +
            "context PersonEventPartitionByName select * from pattern [" +
            "every a=esper.domain.PersonEvent(amount>100) -> b=esper.domain.PersonEvent(amount>100) where timer:within(10 seconds)" +
            "]";


    //根据用户名字定义分区context，然后每个分区之存储相同用户的数据
    //并且根据initiated关键字来根据条件来初始化分区，下边initiated关键字的后边跟着的条件的意思就是当事件流中某一个事件中的amount第一次出现大于1000的时候就会初始化分区，并且从这条时间开始计算接下来的所有事件流总数
    //as关键后边的别名就是分区字段，context.别名就是分区字段值
    public final static String INITATED_AND_PARTITION_SQL = "create context PersonEventPartitionByName partition by name from esper.domain.PersonEvent " +
            "initiated by esper.domain.PersonEvent(amount>1000) as morethen;context PersonEventPartitionByName select context.morethen,name,count(*) as c from esper.domain.PersonEvent group by name";

    //根据用户名定义分区context，每个分区存储的是相同用户的数据，
    //根据initiated关键字来设置初始化分区的条件，下边initiatee关键字后边没有设置条件，所以就是从程序启动就会初始化分区，
    //在创建context分区的时候，设置条件就是向分区里存储符合条件的事件数据
    //as关键字后边的别名就是分区字段的值
    public final static String INITATED_AND_PARTITION_SQL1 = "create context PersonEventPartitionByName partition by name from esper.domain.PersonEvent(amount>1000) " +
            "initiated by esper.domain.PersonEvent as morethen;context PersonEventPartitionByName select context.morethen,name,count(*) as c from esper.domain.PersonEvent group by name";
    ;

    public final static String MULTIPLE_PARTITION_SQL = "create context PersonEventPartitionByNameAndId partition by id and name from esper.domain.PersonEvent " +
            "initiated by esper.domain.PersonEvent;context PersonEventPartitionByNameAndId select id,name,sum(amount) from esper.domain.PersonEvent";

    public final static String GROUP_BY_PARTITION_SQL="create context PersonEventPartitionByGroup group age<10 as lowAge,group age between 10 and 20 as inAge,group age>20 as highAge from esper.domain.PersonEvent;" +
            "context PersonEventPartitionByGroup select context.name,context.id,context.label from esper.domain.PersonEvent";



    public final static String event_sql = "create window EventTriggerWindow#length(10) as  com.cnitsec.mirana.cloud.events.domain.EventsClassify;" +
            "insert into EventTriggerWindow select zhuti, describe,count(describe) as taskId from com.cnitsec.mirana.cloud.events.domain.EventsClassify group by zhuti;" +
            "on  com.cnitsec.mirana.cloud.events.domain.EventsClassify(describe like '%克隆虚拟机%') select poe.* from EventTriggerWindow as poe where poe.taskId>100 and poe.describe like '%存储%'";

}
