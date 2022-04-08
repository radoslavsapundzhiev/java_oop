package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;
    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(System.lineSeparator());
        builder.append(String.format(" " + COMPUTER_COMPONENTS_TO_STRING, this.getComponents().size()));
        builder.append(System.lineSeparator());
        for (Component component: this.getComponents()) {
            builder.append(component);
            builder.append(System.lineSeparator());
        }
        double totalOverallPerformance = this.getPeripherals()
                .stream()
                .mapToDouble(Product::getOverallPerformance)
                .sum();
        double averageOverallPerformance = 0.0;
        if(this.getPeripherals().size() != 0) {
            averageOverallPerformance = totalOverallPerformance / this.getPeripherals().size();
        }
        builder.append(String.format(" " + COMPUTER_PERIPHERALS_TO_STRING, this.getPeripherals().size(), averageOverallPerformance));
        builder.append(System.lineSeparator());
        for (Peripheral peripheral: this.getPeripherals()) {
            builder.append(peripheral);
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    @Override
    public double getOverallPerformance() {
        double totalOverallPerformanceComponents = this.getComponents()
                .stream()
                .mapToDouble(Product::getOverallPerformance)
                .sum();
        double averageOverallPerformanceComponents = 0.0;
        if(this.getComponents().size() != 0) {
            averageOverallPerformanceComponents = totalOverallPerformanceComponents / this.getComponents().size();
        }
        return super.getOverallPerformance() + averageOverallPerformanceComponents;
    }

    @Override
    public double getPrice() {
        double componentsPrice = this.getComponents()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
        double peripheralPrice = this.getPeripherals()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
        return super.getPrice() + componentsPrice + peripheralPrice;
    }

    @Override
    public void addComponent(Component component) {
        Component searchedComponent = this.getComponents()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals(component.getClass().getSimpleName()))
                .findAny()
                .orElse(null);
        if(searchedComponent != null) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT, component.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.getComponents().add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component searchedComponent = this.getComponents()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findAny()
                .orElse(null);
        if(this.getComponents().size() == 0 || searchedComponent == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT, componentType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.getComponents().remove(searchedComponent);
        return searchedComponent;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        Peripheral searchedPeripheral = this.getPeripherals()
                .stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName()))
                .findAny()
                .orElse(null);
        if(searchedPeripheral != null) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(),
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.getPeripherals().add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral searchedPeripheral = this.getPeripherals()
                .stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findAny()
                .orElse(null);
        if(this.getPeripherals().size() == 0 || searchedPeripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL, peripheralType,
                    this.getClass().getSimpleName(), this.getId()));
        }
        this.getPeripherals().remove(searchedPeripheral);
        return searchedPeripheral;
    }
}
