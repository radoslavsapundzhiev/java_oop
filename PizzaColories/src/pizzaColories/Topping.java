package pizzaColories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if(toppingType.equals("Meat") || toppingType.equals("Veggies") ||
            toppingType.equals("Cheese") || toppingType.equals("Sauce")) {
            this.toppingType = toppingType;
        } else {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
    }

    private void setWeight(double weight) {
        if(weight >= 1 && weight <= 50) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
    }

    public double calculateCalories() {
        double toppingTypeCoefficient = 0;
        switch (this.toppingType) {
            case "Meat":
                toppingTypeCoefficient = 1.2;
                break;
            case "Veggies":
                toppingTypeCoefficient = 0.8;
                break;
            case "Cheese":
                toppingTypeCoefficient = 1.1;
                break;
            case "Sauce":
                toppingTypeCoefficient = 0.9;
                break;
        }
        return 2 * this.weight * toppingTypeCoefficient;
    }
}
