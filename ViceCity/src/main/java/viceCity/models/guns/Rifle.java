package viceCity.models.guns;

public class Rifle extends BaseGun{
    private final static int BULLETS_PER_BARREL = 50;
    private final static int TOTAL_BULLETS = 500;
    private final static int BULLETS_SHOT = 5;
    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
        super.setCanFire(this.getBulletsPerBarrel() >= BULLETS_SHOT || this.getTotalBullets() >= BULLETS_PER_BARREL);
    }

    @Override
    public int fire() {
        if(this.getBulletsPerBarrel() >= BULLETS_SHOT) {
            this.setBulletsPerBarrel(this.getBulletsPerBarrel() - BULLETS_SHOT);
        } else {
            if(this.getTotalBullets() >= BULLETS_PER_BARREL) {
                this.setBulletsPerBarrel(BULLETS_PER_BARREL - BULLETS_SHOT);
                this.setTotalBullets(this.getTotalBullets() - BULLETS_PER_BARREL);
            }
        }
        return BULLETS_SHOT;
    }
}
