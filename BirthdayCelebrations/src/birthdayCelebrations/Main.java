package birthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] commandTokens = scanner.nextLine().split("\\s+");
        List<Birthable> citizensAndPets = new ArrayList<>();
        while (!commandTokens[0].equals("End")) {
            if(commandTokens[0].equals("Citizen")) {
                String name = commandTokens[1];
                int age = Integer.parseInt(commandTokens[2]);
                String id = commandTokens[3];
                String birthdate = commandTokens[4];
                citizensAndPets.add(new Citizen(name, age, id, birthdate));
            } else if (commandTokens[0].equals("Pet")) {
                String name = commandTokens[1];
                String birthdate = commandTokens[2];
                citizensAndPets.add(new Pet(name, birthdate));
            }
            commandTokens = scanner.nextLine().split("\\s+");
        }
        String year = scanner.nextLine();
        citizensAndPets.stream()
                .map(e -> e.getBirthDate())
                .filter(e -> e.endsWith(year))
                .forEach(System.out::println);
    }
}
