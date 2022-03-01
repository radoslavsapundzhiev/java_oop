package pizzaColories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pizzaData = scanner.nextLine().split("\\s+");
        String[] doughData = scanner.nextLine().split("\\s+");
        String pizzaName = pizzaData[1];
        int numberOfToppings = Integer.parseInt(pizzaData[2]);
        String flourType = doughData[1];
        String bakingTechnique = doughData[2];
        double weightInGrams = Double.parseDouble(doughData[3]);
        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);
            Dough dough = new Dough(flourType, bakingTechnique, weightInGrams);
            pizza.setDough(dough);
            String[] toppingData = scanner.nextLine().split("\\s+");
            while (!toppingData[0].equals("END")) {
                String toppingType = toppingData[1];
                double toppingWeightInGrams = Double.parseDouble(toppingData[2]);
                Topping topping = new Topping(toppingType, toppingWeightInGrams);
                pizza.addTopping(topping);
                toppingData = scanner.nextLine().split("\\s+");
            }
            System.out.printf("%s - %.2f%n", pizza.getName(), pizza.getOverallCalories());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
