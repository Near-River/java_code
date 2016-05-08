package pro.commons;

/**
 * Created by Near on 2015/12/1.
 */
class Goods {
    private String name;
    private double price;
    private boolean isDiscount;

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isDiscount=" + isDiscount +
                '}';
    }

    public Goods() {
    }

    public Goods(String name, double price, boolean isDiscount) {
        this.name = name;
        this.price = price;
        this.isDiscount = isDiscount;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(boolean isDiscount) {
        this.isDiscount = isDiscount;
    }
}
