package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        Driver searchedDriver = driverRepository.getByName(driverName);
        if(searchedDriver != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driverName));
        }
        Driver driver = new DriverImpl(driverName);
        driverRepository.add(driver);
        return String.format(DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car searchedCar = carRepository.getByName(model);
        if(searchedCar != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        Car car;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                break;
            default:
                car = null;
                break;
        }
        carRepository.add(car);
        return String.format(CAR_CREATED, type + "Car", model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        if(driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        Car car  = carRepository.getByName(carModel);
        if(car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        if(race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Driver driver = driverRepository.getByName(driverName);
        if(driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if(race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        int laps = race.getLaps();
        List<Driver> fastestThreeDrivers = race
                .getDrivers()
                .stream()
                .sorted((d1, d2) -> Double.compare(d2.getCar().calculateRacePoints(laps), d1.getCar().calculateRacePoints(laps)))
                .limit(3)
                .collect(Collectors.toList());
        if(race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(DRIVER_FIRST_POSITION, fastestThreeDrivers.get(0).getName(), raceName));
        builder.append(System.lineSeparator());
        builder.append(String.format(DRIVER_SECOND_POSITION, fastestThreeDrivers.get(1).getName(), raceName));
        builder.append(System.lineSeparator());
        builder.append(String.format(DRIVER_THIRD_POSITION, fastestThreeDrivers.get(2).getName(), raceName));
        raceRepository.remove(race);
        return builder.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        Race searchedRace = raceRepository.getByName(name);
        if(searchedRace != null) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        raceRepository.add(new RaceImpl(name, laps));
        return String.format(RACE_CREATED, name);
    }
}
