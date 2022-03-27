package viceCity.models.guns;

public class Pistol extends BaseGun{
    private final static int BULLETS_PER_BARREL = 10;
    private final static int TOTAL_BULLETS = 100;
    private final static int BULLETS_CAN_SHOOT = 1;
    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        if(!this.canFire()) {
            this.setTotalBullets(this.getTotalBullets() - BULLETS_PER_BARREL);
        }
        this.setTotalBullets(this.getTotalBullets() - BULLETS_CAN_SHOOT);
        return 1;
    }
}
