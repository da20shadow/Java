package viceCity.models.guns;

public class Rifle extends BaseGun{
    private final static int BULLETS_PER_BARREL = 50;
    private final static int TOTAL_BULLETS = 500;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }
    @Override
    public int fire(){
        int totalRemain = super.getTotalBullets();
        int current = super.getBulletsInBarrel();
        int capacity = super.getBulletsPerBarrel();
        int remain;

        if (current < 5){

            int canAdd = capacity - current;

            if (totalRemain >= canAdd){

                totalRemain -= canAdd;
                super.setTotalBullets(totalRemain);
                super.setBulletsInBarrel(capacity - 5);
                return 5;
            }else if (totalRemain > 0){
                remain = current + totalRemain;

                if (remain >= 5){
                    super.setBulletsInBarrel(remain -5);
                    super.setTotalBullets(0);
                    return 5;
                }
            }

        }else {
            remain = current - 5;
            super.setBulletsInBarrel(remain);
            return 5;
        }

        return 0;
    }
}
