package esper.domain;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName PersonEventTrigger
 * @Description
 * @Author liyuzhi
 * @Date 2018-05-08 15:31
 */

public class PersonEventTrigger {
    private int trigger;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrigger() {
        return trigger;
    }

    public void setTrigger(int trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return "PersonEventTrigger{" +
                "trigger=" + trigger +
                ", name='" + name + '\'' +
                '}';
    }
}
