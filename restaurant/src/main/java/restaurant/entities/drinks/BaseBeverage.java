package restaurant.entities.drinks;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;

public abstract class BaseBeverage implements Beverages {
    private String name;
    private int counter;
    private double price;
    private String brand;

    public BaseBeverage(String name, int counter, double price, String brand) {
        this.setName(name);
        this.setCounter(counter);
        this.setPrice(price);
        this.setBrand(brand);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    private void setCounter(int counter) {
        if(counter <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COUNTER);
        }
        this.counter = counter;
    }

    @Override
    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if(price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        if(brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }
}
