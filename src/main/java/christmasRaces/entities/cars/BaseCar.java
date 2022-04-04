package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_MODEL;

public abstract class BaseCar implements Car{

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsepower, double cubicCentimeters) {
        setModel(model);
        this.checkHorsePower(horsepower);
        this.horsePower = horsepower;
        this.cubicCentimeters = cubicCentimeters;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()){
            throw new IllegalArgumentException(String.format(INVALID_MODEL,model,4));
        }
        this.model = model;
    }

    protected abstract void checkHorsePower(int hp);

    @Override
    public String getModel(){
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsePower * laps;
    }
}
