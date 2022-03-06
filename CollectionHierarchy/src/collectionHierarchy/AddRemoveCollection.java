package collectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable {
    @Override
    public int add(String item) {
        this.getItems().add(0, item);
        return 0;
    }

    @Override
    public String remove() {
        return this.getItems().remove(this.getItems().size() - 1);
    }
}
