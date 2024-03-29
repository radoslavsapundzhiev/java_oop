package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{
    private final static int INITIAL_SIZE = 3;
    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + 3);
    }
}
