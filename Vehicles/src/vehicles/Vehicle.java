package vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    public Vehicle(double fuelConsumption, double fuelQuantity, double tankCapacity) {
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public String drive(double distance) {
        double neededFuel = distance * this.getFuelConsumption();
        if(neededFuel < this.getFuelQuantity()) {
            this.setFuelQuantity(this.getFuelQuantity() - neededFuel);
            String pattern = "##.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            String format = decimalFormat.format(distance);
            return String.format("%s travelled %s km",
                    this.getClass().getSimpleName(), format);
        }
        return String.format("%s needs refueling",
                    this.getClass().getSimpleName());
    }

    public void refuel(double liters) {
        if(liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if((this.getTankCapacity() - this.getFuelQuantity()) < liters) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.setFuelQuantity(liters + this.getFuelQuantity());
    };
}
