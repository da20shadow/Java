package CounterStriker.models.guns;

public class Pistol extends GunImpl{
    private static final int FIRE_BULLETS = 1;

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        if (this.getBulletsCount() >= FIRE_BULLETS){
            return 1;
        }
        return 0;
    }
}
