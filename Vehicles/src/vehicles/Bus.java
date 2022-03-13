package vehicles;

public class Bus extends Vehicle{
    private final static double SUMMER_FUEL_CONSUMPTION_ADDITION = 1.4;
    public Bus(double fuelConsumption, double fuelQuantity, double tankCapacity) {
        super(fuelConsumption + SUMMER_FUEL_CONSUMPTION_ADDITION,
                fuelQuantity,
                tankCapacity);
    }

    public String driveEmpty(double distance) {
        super.setFuelConsumption(this.getFuelConsumption() - SUMMER_FUEL_CONSUMPTION_ADDITION);
        String result = super.drive(distance);
        super.setFuelConsumption(this.getFuelConsumption() + SUMMER_FUEL_CONSUMPTION_ADDITION);
        return result;
    }
}
