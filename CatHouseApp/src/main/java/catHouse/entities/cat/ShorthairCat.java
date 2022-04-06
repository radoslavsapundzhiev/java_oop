package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    private final static int KILOGRAMS = 7;
    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        this.setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 1);
    }
}
