package collectionHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> str = new ArrayList<>();
        String[] itemsToAdd = scanner.nextLine().split("\\s+");
        int amountOfRemoveOperations = Integer.parseInt(scanner.nextLine());

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myLIst = new MyListImpl();

        printAddResult(addCollection, itemsToAdd);
        printAddResult(addRemoveCollection, itemsToAdd);
        printAddResult(myLIst, itemsToAdd);

        printRemoveResult(addRemoveCollection, itemsToAdd, amountOfRemoveOperations);
        printRemoveResult(myLIst, itemsToAdd, amountOfRemoveOperations);
    }
    public static void printAddResult(Addable collection, String[] itemsToAdd) {
        for (int i = 0; i < Math.min(100, itemsToAdd.length); i++) {
            System.out.print(collection.add(itemsToAdd[i]) + " ");
        }
        System.out.println();
    }
    public static void printRemoveResult(AddRemovable collection, String[] itemsToAdd, int amountOfRemoveOperations) {
        for (int i = 0; i < Math.min(amountOfRemoveOperations, itemsToAdd.length); i++) {
            System.out.print(collection.remove() + " ");
        }
        System.out.println();
    }
}
