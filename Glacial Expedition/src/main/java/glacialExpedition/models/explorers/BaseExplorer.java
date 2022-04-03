package glacialExpedition.models.explorers;

import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

public abstract class BaseExplorer implements Explorer{
    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, Double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase = new Carton();
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if(name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new NullPointerException(ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY);
        }
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    protected void setEnergy(Double energy) {
        if(energy >= 0) {
            this.energy = energy;
        } else {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        double decreasedEnergy = this.getEnergy() - 15;
        if(decreasedEnergy < 0) {
            setEnergy(0.0);
        } else {
            setEnergy(decreasedEnergy);
        }
    }

    @Override
    public boolean canSearch() {
        return this.getEnergy() > 0;
    }
}
