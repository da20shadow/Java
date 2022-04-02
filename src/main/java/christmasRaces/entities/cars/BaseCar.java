package christmasRaces.entities.cars;

public abstract class BaseCar implements Car{

    private String model;
    private int horsepower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsepower, double cubicCentimeters) {
        this.model = model;
        this.checkHorsePower(horsepower);
        this.horsepower = horsepower;
        this.cubicCentimeters = cubicCentimeters;
    }

    protected abstract void checkHorsePower(int hp);

    @Override
    public abstract String getModel();

    @Override
    public abstract int getHorsePower();

    @Override
    public abstract double getCubicCentimeters();

    @Override
    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsepower * laps;
    }
}
