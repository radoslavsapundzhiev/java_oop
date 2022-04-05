package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.HealthFoodRepositoryImpl;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double completedBillsPrice;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.completedBillsPrice = 0.0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        //TODO:
        HealthyFood healthyFood;
        switch (type) {
            case "Salad":
                healthyFood = new Salad(name, price);
                break;
            case "VeganBiscuits":
                healthyFood = new VeganBiscuits(name, price);
                break;
            default:
                healthyFood = null;
                break;
        }
        if(healthFoodRepository.foodByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }
        healthFoodRepository.add(healthyFood);
        return String.format(OutputMessages.FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        //TODO:
        Beverages beverage;
        switch (type) {
            case "Fresh":
                beverage = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                beverage = new Smoothie(name, counter, brand);
                break;
            default:
                beverage = null;
                break;
        }
        if(beverageRepository.beverageByName(name, brand) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
        }
        beverageRepository.add(beverage);
        return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        //TODO:
        Table table;
        switch (type){
            case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
            default:
                table = null;
                break;
        }
        if(tableRepository.byNumber(tableNumber) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
        }
        tableRepository.add(table);
        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        //TODO:
        Table table = tableRepository
                .getAllEntities()
                .stream()
                .filter(t -> !t.isReservedTable())
                .filter(t -> t.getSize() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        if(table == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        table.reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        //TODO:
        Table table = tableRepository.byNumber(tableNumber);
        if(table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFood = healthFoodRepository.foodByName(healthyFoodName);
        if(healthyFood == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }
        table.orderHealthy(healthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        //TODO:
        Table table = tableRepository.byNumber(tableNumber);
        if(table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverage = beverageRepository.beverageByName(name, brand);
        if(beverage == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }
        table.orderBeverages(beverage);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        //TODO:
        Table table = tableRepository.byNumber(tableNumber);
        double bill = table.bill();
        table.clear();
        this.setCompletedBillsPrice(this.getCompletedBillsPrice() + bill);
        return String.format(OutputMessages.BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        //TODO:
        double allCompletedBillsMoney = this.getCompletedBillsPrice();
        return String.format(OutputMessages.TOTAL_MONEY, allCompletedBillsMoney);
    }

    private double getCompletedBillsPrice() {
        return completedBillsPrice;
    }

    private void setCompletedBillsPrice(double completedBillsPrice) {
        this.completedBillsPrice = completedBillsPrice;
    }
}
