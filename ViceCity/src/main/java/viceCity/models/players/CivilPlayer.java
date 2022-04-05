package viceCity.models.players;

public class CivilPlayer extends BasePlayer{
    private final static int INITIAL_LIFE_POINTS = 50;
    public CivilPlayer(String name) {
        super(name, INITIAL_LIFE_POINTS);
    }
}
