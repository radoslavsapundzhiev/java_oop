package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static final double INITIAL_OXYGEN = 50;
    private static final double NEEDED_OXYGEN = 10;

    public Geodesist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    protected double getNeededOxygenToBreath() {
        return NEEDED_OXYGEN;
    }
}
