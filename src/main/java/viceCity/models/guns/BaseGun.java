package viceCity.models.guns;

import static viceCity.common.ExceptionMessages.*;

public abstract class BaseGun implements Gun {
    private String name;
    private  int bulletsPerBarrel;
    private  int totalBullets;
    private int bulletsInBarrel; //capacity = bulletsPerBarrel
    private boolean canFire;

    public BaseGun(String name, int bulletsPerBarrel, int totalBullets) {

        if (name == null || name.trim().equals("")){
            throw new NullPointerException(NAME_NULL);
        }
        if (bulletsPerBarrel < 0){
            throw new IllegalArgumentException(BULLETS_LESS_THAN_ZERO);
        }
        if (totalBullets < 0){
            throw new IllegalArgumentException(TOTAL_BULLETS_LESS_THAN_ZERO);
        }
        this.name = name;
        this.bulletsPerBarrel = bulletsPerBarrel;
        this.totalBullets = totalBullets;
    }

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public int getBulletsPerBarrel(){
        return this.bulletsPerBarrel;
    }

    @Override
    public int getTotalBullets(){
        return this.totalBullets;
    }

    @Override
    public boolean canFire() {
        return this.canFire;
    }

    @Override
    public abstract int fire();
//    {
//
//        int remain;
//
//        if (this.bulletsInBarrel < shoot){
//
//            int canAdd = this.bulletsPerBarrel - this.bulletsInBarrel;
//
//            if (this.totalBullets >= canAdd){
//
//                this.totalBullets -= canAdd;
//                this.bulletsInBarrel = this.bulletsPerBarrel;
//
//            }else if (this.totalBullets > shoot){
//                remain = this.bulletsInBarrel + this.totalBullets;
//                this.bulletsInBarrel = remain;
//            }
//
//        }
//
//        if (this.bulletsInBarrel >= shoot){
//            remain = this.bulletsInBarrel - shoot;
//            this.bulletsInBarrel = remain;
//            return shoot;
//        }
//
//        return 0;
//    }

    public int getBulletsInBarrel() {
        return bulletsInBarrel;
    }

    public void setBulletsInBarrel(int bulletsInBarrel) {
        this.bulletsInBarrel = bulletsInBarrel;
    }

    public void setTotalBullets(int bullets){
        this.totalBullets = bullets;
    }
}
