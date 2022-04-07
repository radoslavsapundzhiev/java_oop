package christmasRaces.repositories;

import christmasRaces.entities.cars.Car;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CarRepository implements Repository<Car> {
    private Collection<Car> models;
    public CarRepository() {
        this.models = new ArrayList<>();
    }
    @Override
    public Car getByName(String name) {
        return this.models
                .stream()
                .filter(c -> c.getModel().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Car> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Car car) {
        this.models.add(car);
    }

    @Override
    public boolean remove(Car car) {
        return this.models.remove(car);
    }
}
