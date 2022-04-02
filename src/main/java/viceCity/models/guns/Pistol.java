package viceCity.models.guns;

public class Pistol extends BaseGun{
    private final static int BULLETS_PER_BARREL = 10;
    private final static int TOTAL_BULLETS = 10;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire(){
        int totalRemain = super.getTotalBullets();
        int current = super.getBulletsInBarrel();
        int capacity = super.getBulletsPerBarrel();
        int remain;

        if (current < 1){

            if (totalRemain >= capacity){

                totalRemain -= capacity;
                super.setTotalBullets(totalRemain);
                super.setBulletsInBarrel(capacity -1);
                return 1;

            }else if (totalRemain > 0){
                super.setBulletsInBarrel(totalRemain -1);
                return 1;
            }

        }else {
            remain = current - 1;
            super.setBulletsInBarrel(remain);
            return 1;
        }

        return 0;
    }
}
