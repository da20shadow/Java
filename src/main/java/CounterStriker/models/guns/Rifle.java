package CounterStriker.models.guns;

public class Rifle extends GunImpl{
    private static final int CAN_FIRE = 10;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount() >= 10){
            return 10;
        }
        return 0;
    }
}
