package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private static final double INITIAL_OXYGEN = 70;
    private static final double NEEDED_OXYGEN = 5;
    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    protected double getNeededOxygenToBreath() {
        return NEEDED_OXYGEN;
    }
}
