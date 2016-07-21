package pro.oop;

import org.junit.Test;

/**
 * Created by Near on 2015/11/25.
 */
public class TestComponent {
    @Test
    public void test() {
        Man man = new Man();
        System.out.println(man.getCar().getColor());
        man.getCar().setColor("blue");
        System.out.println(man.getCar().getColor());
    }
}

class Car {
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public Car(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                '}';
    }
}

class Man {
    private final Car car = new Car("red");

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Man{" +
                "car=" + car +
                '}';
    }
}