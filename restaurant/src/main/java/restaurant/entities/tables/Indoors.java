package restaurant.entities.tables;

public class Indoors extends BaseTable{
    private static final double pricePerPerson = 3.5;
    public Indoors(int number, int size) {
        super(number, size, pricePerPerson);
    }
}
