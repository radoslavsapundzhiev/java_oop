package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.*;

public class AstronautRepository implements Repository<Astronaut> {
    private Collection<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new ArrayList<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts);
    }

    @Override
    public void add(Astronaut astronaut) {
        this.astronauts.add(astronaut);
    }

    @Override
    public boolean remove(Astronaut astronaut) {
        return this.astronauts.remove(astronaut);
    }

    @Override
    public Astronaut findByName(String name) {
        return this.astronauts
                .stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
