package pro.gof.create.prototype_pattern;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by near on 2015/12/11.
 */
public class Sheep implements Cloneable, Serializable {
    private String name;
    private Date birtyday;

    public Sheep() {
        System.out.println("sheep constructor...");
    }

    public Sheep(String name, Date birtyday) {
        this();
        this.name = name;
        this.birtyday = birtyday;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", birtyday=" + birtyday +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirtyday() {
        return birtyday;
    }

    public void setBirtyday(Date birtyday) {
        this.birtyday = birtyday;
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        // 优化，支持深复制
        Sheep sheep = (Sheep) super.clone();
        sheep.birtyday = (Date) sheep.birtyday.clone();
        return sheep;
    }*/

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 支持浅复制
        return super.clone();
    }
}
