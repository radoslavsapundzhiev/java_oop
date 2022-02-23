package trafficLights;

public class TrafficLight {
    private Lights light;

    public TrafficLight(Lights light) {
        this.light = light;
    }

    public Lights getLight() {
        return light;
    }

    public void setLight(Lights light) {
        this.light = light;
    }

    public void changeLight() {
        if(light == Lights.GREEN) {
            this.light = Lights.YELLOW;
        } else if (light == Lights.RED) {
            this.light = Lights.GREEN;
        } else if (light == Lights.YELLOW) {
            this.light = Lights.RED;
        }
    }
}
