package viceCity.models.guns;

public abstract class BaseGun implements Gun{
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private boolean canFire;
    private int bulletsInBarrel;

    public BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.setTotalBullets(totalBullets);
        this.bulletsInBarrel = bulletsPerBarrel;
    }

    public int getBulletsInBarrel() {
        return bulletsInBarrel;
    }

    public void setBulletsInBarrel(int bulletsInBarrel) {
        this.bulletsInBarrel = bulletsInBarrel;
    }

    public boolean canFire() {
        return bulletsInBarrel > 0;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public int getTotalBullets() {
        return totalBullets;
    }

    public void setTotalBullets(int totalBullets) {
        if(totalBullets >= 0) {
            this.totalBullets = totalBullets;
        } else {
            throw new IllegalArgumentException("Total bullets cannot be below zero!");
        }
    }

    public int getBulletsPerBarrel() {
        return bulletsPerBarrel;
    }

    public void setBulletsPerBarrel(int bulletsPerBarrel) {
        if(bulletsPerBarrel >= 0) {
            this.bulletsPerBarrel = bulletsPerBarrel;
        } else {
            throw new IllegalArgumentException("Bullets cannot be below zero!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.trim().equals("")) {
            this.name = name;
        } else {
            throw new NullPointerException("Name cannot be null or whitespace!");
        }
    }
}
