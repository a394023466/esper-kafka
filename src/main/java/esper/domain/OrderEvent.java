package esper.domain;

/**
 * 类的注释
 *
 * @Package esper.domain
 * @ClassName OrderEvent
 * @Description
 * @Author liyuzhi
 * @Date 2018-05-08 23:31
 */

public class OrderEvent {
    private int price;
    private String name;

    @Override
    public String toString() {
        return "OrderEvent{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
