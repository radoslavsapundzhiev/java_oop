package viceCity.models.players;

public class MainPlayer extends BasePlayer{
    private final static int MAIN_PLAYER_LIFE_POINTS = 100;
    private final static String MAIN_PLAYER_NAME = "Tommy Vercetti";
    public MainPlayer() {
        super(MAIN_PLAYER_NAME, MAIN_PLAYER_LIFE_POINTS);
    }
}
