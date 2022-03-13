package vehicles;

public class Car extends Vehicle{
    private final static double SUMMER_FUEL_CONSUMPTION_ADDITION = 0.9;

    public Car(double fuelConsumption, double fuelQuantity, double tankCapacity) {
        super(fuelConsumption + SUMMER_FUEL_CONSUMPTION_ADDITION,
                fuelQuantity, tankCapacity);
    }
}
