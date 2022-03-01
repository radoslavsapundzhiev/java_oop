package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimestions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimestions[0];
        int cols = dimestions[1];
        int[][] matrix = fillMatrix(rows, cols);
        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] playerCoordinates = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            moveEvil(matrix, evilCoordinates);
            sum = movePlayer(matrix, sum, playerCoordinates);
            command = scanner.nextLine();
        }
        System.out.println(sum);
    }

    private static long movePlayer(int[][] matrix, long sum, int[] playerCoordinates) {
        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];
        while (playerRow >= 0 && playerCol < matrix[1].length) {
            if (playerRow >= 0 && playerRow < matrix.length && playerCol >= 0 && playerCol < matrix[0].length) {
                sum += matrix[playerRow][playerCol];
            }
            playerCol++;
            playerRow--;
        }
        return sum;
    }

    private static void moveEvil(int[][] matrix, int[] evilCoordinates) {
        int evilRow = evilCoordinates[0];
        int evilCol = evilCoordinates[1];
        while (evilRow >= 0 && evilCol >= 0) {
            if (evilRow >= 0 && evilRow < matrix.length && evilCol >= 0 && evilCol < matrix[0].length) {
                matrix[evilRow][evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }
    }

    private static int[][] fillMatrix(int x, int y) {
        int[][] matrix = new int[x][y];
        int value = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }
}
