package vehicles;

import java.awt.event.ItemListener;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        try {
            String[] carInfo = scanner.nextLine().split("\\s+");
            double carFuelQuantity = Double.parseDouble(carInfo[1]);
            double carFuelConsumption = Double.parseDouble(carInfo[2]);
            double carTankCapacity = Double.parseDouble(carInfo[3]);
            Vehicle car = new Car(carFuelConsumption, carFuelQuantity, carTankCapacity);
            vehicles.put("Car", car);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            String[] truckInfo = scanner.nextLine().split("\\s+");
            double truckFuelQuantity = Double.parseDouble(truckInfo[1]);
            double truckFuelConsumption = Double.parseDouble(truckInfo[2]);
            double truckTankCapacity = Double.parseDouble(truckInfo[3]);
            Vehicle truck = new Truck(truckFuelConsumption, truckFuelQuantity, truckTankCapacity);
            vehicles.put("Truck", truck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            String[] busInfo = scanner.nextLine().split("\\s+");
            double busFuelQuantity = Double.parseDouble(busInfo[1]);
            double busFuelConsumption = Double.parseDouble(busInfo[2]);
            double busTankCapacity = Double.parseDouble(busInfo[3]);
            Vehicle bus= new Bus(busFuelConsumption, busFuelQuantity, busTankCapacity);
            vehicles.put("Bus", bus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfCommands; i++) {
            String[] commandTokens = scanner.nextLine().split("\\s+");
            String command = commandTokens[0];
            String vehicleType = commandTokens[1];
            if(vehicles.containsKey(vehicleType)) {
                Vehicle vehicle = vehicles.get(vehicleType);
                switch (command) {
                    case "Drive":
                        double distance = Double.parseDouble(commandTokens[2]);
                        String result = vehicle.drive(distance);
                        System.out.println(result);
                        break;
                    case "Refuel":
                        double liters = Double.parseDouble(commandTokens[2]);
                        try {
                            vehicle.refuel(liters);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "DriveEmpty":
                        double emptyBusDistance = Double.parseDouble(commandTokens[2]);
                        if(vehicles.get(vehicleType) instanceof Bus) {
                            String emptyBusResult = ((Bus) vehicle).driveEmpty(emptyBusDistance);
                            System.out.println(emptyBusResult);
                        }
                        break;
                }
            }
        }
        for (Map.Entry<String, Vehicle> entry: vehicles.entrySet()) {
            System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue().getFuelQuantity());
        }
    }
}
