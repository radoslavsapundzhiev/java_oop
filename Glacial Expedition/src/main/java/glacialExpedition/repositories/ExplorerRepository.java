package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer>{
    private Collection<Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }

    @Override
    public Collection getCollection() {
        return Collections.unmodifiableCollection(explorers);
    }

    @Override
    public void add(Explorer entity) {
        this.explorers.add(entity);
    }

    @Override
    public boolean remove(Explorer entity) {
        return this.explorers.remove(entity);
    }

    @Override
    public Explorer byName(String name) {
        return this.explorers.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
