package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{

    private final static double CUBIC_CENTIMETERS = 3000;
    private final static int MINIMUM_HORSEPOWER = 250;
    private final static int MAXIMUM_HORSEPOWER = 450;

    public SportsCar(String model, int horsepower) {
        super(model, horsepower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void checkHorsePower(int hp) {
        if (hp < MINIMUM_HORSEPOWER || hp > MAXIMUM_HORSEPOWER){
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,hp));
        }
    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public int getHorsePower() {
        return 0;
    }

    @Override
    public double getCubicCentimeters() {
        return 0;
    }
}
