package trafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] trafficLightSignals = scanner.nextLine().split("\\s+");
        List<TrafficLight> trafficLights = new ArrayList<>();
        for (String light: trafficLightSignals) {
            Lights currentLight = Lights.valueOf(light);
            TrafficLight trafficLight = new TrafficLight(currentLight);
            trafficLights.add(trafficLight);
        }
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            for (TrafficLight trafficLight: trafficLights) {
                trafficLight.changeLight();
                System.out.print(trafficLight.getLight() + " ");
            }
            System.out.println();
        }

    }
}
