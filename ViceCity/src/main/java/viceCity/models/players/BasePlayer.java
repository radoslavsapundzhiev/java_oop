package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player{
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.trim().equals("")) {
            this.name = name;
        } else {
            throw new NullPointerException("Player's name cannot be null or a whitespace!");
        }
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        if(lifePoints >= 0) {
            this.lifePoints = lifePoints;
        } else {
            throw new IllegalArgumentException("Player life points cannot be below zero!");
        }
    }

    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    public boolean isAlive() {
        return this.getLifePoints() > 0;
    }

    public void takeLifePoints(int points) {
        this.setLifePoints(this.getLifePoints() - points);
    }
}
