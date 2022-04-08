package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Collection<Computer> computers;
    private Collection<Component> components;
    private Collection<Peripheral> peripherals;
    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }
    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer searchedComputer = this.computers
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if(searchedComputer != null) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        Computer computer;
        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;
            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;
            default:
                computer = null;
                break;
        }
        if(computer == null) {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        this.computers.add(computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst()
                .orElse(null);
        if(computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Peripheral searchedPeripheral = this.peripherals
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(searchedPeripheral != null) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                peripheral = null;
                break;
        }
        if(peripheral == null) {
            throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }
        computer.addPeripheral(peripheral);
        this.peripherals.add(peripheral);
        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst()
                .orElse(null);
        if(computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Peripheral peripheral = computer.removePeripheral(peripheralType);
        this.peripherals.remove(peripheral);
        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst()
                .orElse(null);
        if(computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Component searchedComponent = this.components
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if(searchedComponent != null) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
        Component component;
        switch (componentType){
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                component = null;
                break;
        }
        if(component == null) {
            throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }
        computer.addComponent(component);
        this.components.add(component);
        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst()
                .orElse(null);
        if(computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Component component = computer.removeComponent(componentType);
        this.components.remove(component);
        return String.format(REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if(computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        this.computers.remove(computer);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> possibleComputersToBuy = this.computers
                .stream()
                .sorted((c1, c2) -> Double.compare(c2.getOverallPerformance(), c1.getOverallPerformance()))
                .filter(c -> c.getPrice() <= budget)
                .collect(Collectors.toList());
        if(this.computers.size() == 0 || possibleComputersToBuy.size() == 0) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }
        Computer bestComputer = possibleComputersToBuy.get(0);
        this.computers.remove(bestComputer);
        return bestComputer.toString();
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if(computer == null) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        return computer.toString();
    }
}
