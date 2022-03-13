package vehicles;

public class Truck extends Vehicle{
    private final static double SUMMER_FUEL_CONSUMPTION_ADDITION = 1.6;
    private final static double REFUEL_TANK_CORRECTION = 5.0;
    public Truck(double fuelConsumption, double fuelQuantity, double tankCapacity) {
        super(fuelConsumption + SUMMER_FUEL_CONSUMPTION_ADDITION,
                fuelQuantity, tankCapacity);
    }

    @Override
    public void refuel(double liters) {
        double decreasedFuel = liters * (100 - REFUEL_TANK_CORRECTION) / 100;
        super.refuel(decreasedFuel);
    }
}
