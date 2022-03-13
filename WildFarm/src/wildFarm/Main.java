package wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int count = 0;
        Mammal mammal = null;
        Food food = null;
        List<Mammal> mammals = new ArrayList<>();
        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");
            if(count % 2 == 0) {
                String animalType = tokens[0];
                String animalName = tokens[1];
                Double animalWeight = Double.parseDouble(tokens[2]);
                String animalLivingRegion = tokens[3];
                if(animalType.equals("Mouse")) {
                    mammal = new Mouse(animalName, animalType, animalWeight, animalLivingRegion);
                } else if(animalType.equals("Zebra")) {
                   mammal = new Zebra(animalName, animalType, animalWeight, animalLivingRegion);
                } else if(animalType.equals("Tiger")) {
                    mammal = new Tiger(animalName, animalType, animalWeight, animalLivingRegion);
                }
                if(tokens.length == 5) {
                    String catBreed = tokens[4];
                    mammal = new Cat(animalName, animalType, animalWeight, animalLivingRegion, catBreed);
                }
            } else {
                String foodType = tokens[0];
                Integer quantity = Integer.parseInt(tokens[1]);
                if(foodType.equals("Vegetable")) {
                    food = new Vegetable(quantity);
                } else if(foodType.equals("Meat")) {
                    food = new Meat(quantity);
                }
                if(mammal != null && food != null) {
                    try {
                        mammal.makeSound();
                        mammals.add(mammal);
                        mammal.eat(food);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            line = scanner.nextLine();
            count++;
        }
        mammals.forEach(System.out::println);
    }
}
