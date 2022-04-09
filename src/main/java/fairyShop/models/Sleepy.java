package fairyShop.models;

public class Sleepy extends BaseHelper{
    private static final int INITIAL_ENERGY = 50;
    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }
    @Override
    public void work() {
        this.setEnergy(Math.max(this.getEnergy() - 15, 0));
    }
}
