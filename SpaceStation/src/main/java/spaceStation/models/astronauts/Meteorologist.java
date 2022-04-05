package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{
    private static final double INITIAL_OXYGEN = 90;
    private static final double NEEDED_OXYGEN = 10;

    public Meteorologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    protected double getNeededOxygenToBreath() {
        return NEEDED_OXYGEN;
    }
}
