package onlineShop.models.products.computers;

public class Laptop extends BaseComputer{
    private final static double OVERALL_PERFORMANCE = 10;
    public Laptop(int id, String manufacturer, String model, double price) {
        super(id, manufacturer, model, price, OVERALL_PERFORMANCE);
    }
}
