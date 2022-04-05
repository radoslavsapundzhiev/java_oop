package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReversedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.setNumber(number);
        this.setSize(size);
        this.setPricePerPerson(pricePerPerson);
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.setReversedTable(false);
    }

    private void setNumber(int number) {
        this.number = number;
    }

    private void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    private void setSize(int size) {
        if(size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.setReversedTable(true);
        this.setNumberOfPeople(numberOfPeople);
        this.setAllPeople(pricePerPerson() * numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double healthyFoodPrice = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double beveragesPrice = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        return healthyFoodPrice + beveragesPrice + this.allPeople();
    }

    @Override
    public void clear() {
        this.setAllPeople(0);
        this.healthyFood.clear();
        this.beverages.clear();
        this.setReversedTable(false);
        this.setNumberOfPeople(0);
    }

    @Override
    public String tableInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Table - %d", this.getTableNumber()));
        builder.append(System.lineSeparator());
        builder.append(String.format("Size - %d", this.getSize()));
        builder.append(System.lineSeparator());
        builder.append(String.format("Type - %s", this.getClass().getSimpleName()));
        builder.append(System.lineSeparator());
        builder.append(String.format("All price - %.2f", this.pricePerPerson()));
        return builder.toString();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReversedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    private void setReversedTable(boolean reversedTable) {
        isReversedTable = reversedTable;
    }

    private void setAllPeople(double allPeople) {
        this.allPeople = allPeople;
    }
}
