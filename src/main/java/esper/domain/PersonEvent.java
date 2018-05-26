package esper.domain;

/**
 * 类的注释
 *
 * @Package esper
 * @ClassName PersonEvent
 * @Description Esper时间类型实体
 * @Author liyuzhi
 * @Date 2018-05-07 10:30
 */

public class PersonEvent {
    private int id;
    private String name;
    private int age;
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", amount=" + amount +
                '}';
    }
}
