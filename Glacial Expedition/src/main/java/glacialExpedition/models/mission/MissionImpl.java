package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission{
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        List<String> exhibitsToRemove = new ArrayList<>();
        for (Explorer explorer: explorers) {
            for (String exhibit: state.getExhibits().stream().filter(e -> !exhibitsToRemove.contains(e)).collect(Collectors.toList())) {
                if(!explorer.canSearch()) {
                    break;
                }
                explorer.search();
                explorer.getSuitcase().getExhibits().add(exhibit);
                exhibitsToRemove.add(exhibit);
            }
        }
    }
}
