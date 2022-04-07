package christmasRaces.repositories;

import christmasRaces.entities.races.Race;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> models;
    public RaceRepository() {
        this.models = new ArrayList<>();
    }
    @Override
    public Race getByName(String name) {
        return this.models
                .stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Race race) {
        this.models.add(race);
    }

    @Override
    public boolean remove(Race race) {
        return this.models.remove(race);
    }
}
