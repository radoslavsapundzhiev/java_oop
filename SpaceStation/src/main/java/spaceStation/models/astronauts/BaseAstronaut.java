package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import static spaceStation.common.ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY;
import static spaceStation.common.ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseAstronaut implements Astronaut{
    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if(oxygen < 0) {
            throw new IllegalArgumentException(ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void breath() {
        this.setOxygen(Math.max(0, this.getOxygen() - getNeededOxygenToBreath()));
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    protected abstract double getNeededOxygenToBreath();
}
