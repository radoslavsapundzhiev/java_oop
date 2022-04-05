package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class MissionImpl implements Mission{
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Deque<String> items = new ArrayDeque<>(planet.getItems());
        for (Astronaut astronaut: astronauts) {
            String item = items.poll();
            while (astronaut.canBreath() && item != null) {
                astronaut.breath();
                astronaut.getBag().getItems().add(item);
                item = items.poll();
            }
        }
    }
}
