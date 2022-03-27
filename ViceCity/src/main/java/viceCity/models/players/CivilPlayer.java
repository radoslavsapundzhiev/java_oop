package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    private final static int BASE_PLAYER_LIFE_POINTS = 50;
    public CivilPlayer(String name) {
        super(name, BASE_PLAYER_LIFE_POINTS);
    }
}
