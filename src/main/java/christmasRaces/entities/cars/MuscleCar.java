package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar{

    private static final double CUBIC_CENTIMETERS = 5000;
    private static final int MINIMUM_HORSEPOWER = 400;
    private static final int MAXIMUM_HORSEPOWER = 600;

    public MuscleCar(String model, int horsepower) {
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
