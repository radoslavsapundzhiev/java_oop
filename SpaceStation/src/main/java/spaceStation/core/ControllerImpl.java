package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.bags.Bag;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int exploredPlanetsCount;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.exploredPlanetsCount = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                astronaut = null;
                break;
        }
        if(astronaut == null) {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName, items);
        planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if(astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Collection<Astronaut> suitableAstronauts = astronautRepository
                .getModels()
                .stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());
        if(suitableAstronauts.size() == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        Planet planet = planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();
        mission.explore(planet, suitableAstronauts);
        Collection<Astronaut> deadAstronauts = suitableAstronauts
                .stream()
                .filter(a -> !a.canBreath())
                .collect(Collectors.toList());
        this.exploredPlanetsCount += 1;
        return String.format(PLANET_EXPLORED, planetName, deadAstronauts.size());
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanetsCount));
        builder.append(System.lineSeparator());
        builder.append(REPORT_ASTRONAUT_INFO);
        builder.append(System.lineSeparator());
        for (Astronaut astronaut: astronautRepository.getModels()) {
            builder.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName()));
            builder.append(System.lineSeparator());
            builder.append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen()));
            builder.append(System.lineSeparator());
            if(astronaut.getBag().getItems().size() > 0) {
                String itemsAsString = String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, astronaut.getBag().getItems());
                builder.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, itemsAsString));
            } else {
                builder.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none"));
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
