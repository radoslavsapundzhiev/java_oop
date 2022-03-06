package collectionHierarchy;

public class MyListImpl extends Collection implements MyList{
    private int used;

    public MyListImpl() {
        this.used = this.getItems().size();
    }

    @Override
    public int add(String item) {
        this.getItems().add(0, item);
        return 0;
    }

    @Override
    public int getUsed() {
        return this.getItems().size();
    }

    @Override
    public String remove() {
        return this.getItems().remove(0);
    }
}
