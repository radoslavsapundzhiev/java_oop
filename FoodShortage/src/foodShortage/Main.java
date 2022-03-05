package foodShortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        List<Citizen> citizens = new ArrayList<>();
        List<Rebel> rebels = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            if(tokens.length == 4) {
                String id = tokens[2];
                String birthdate = tokens[3];
                citizens.add(new Citizen(name, age, id, birthdate));
            } else if (tokens.length == 3) {
                String group = tokens[2];
                rebels.add(new Rebel(name, age, group));
            }
        }
        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String name = command;
            citizens.stream()
                    .filter(b -> b.getName().equals(name))
                    .findFirst()
                    .ifPresent(Buyer::buyFood);
            rebels.stream()
                    .filter(r -> r.getName().equals(name))
                    .findFirst()
                    .ifPresent(Buyer::buyFood);
            command = scanner.nextLine();
        }
        int totalFood = citizens.stream().mapToInt(Buyer::getFood).sum() +
                rebels.stream().mapToInt(Buyer::getFood).sum();
        System.out.println(totalFood);
    }
}
