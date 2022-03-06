package collectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection{
    private final int maxSize;
    private List<String> items;

    public Collection() {
        this.items = new ArrayList<>(100);
        this.maxSize = 100;
    }

    public List<String> getItems() {
        return items;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
