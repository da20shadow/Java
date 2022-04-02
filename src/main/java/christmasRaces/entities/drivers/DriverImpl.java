package christmasRaces.entities.drivers;

import christmasRaces.entities.cars.Car;

import static christmasRaces.common.ExceptionMessages.CAR_INVALID;

public class DriverImpl implements Driver{

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate = false;

    public DriverImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {
        if (car == null){
            throw new IllegalArgumentException(CAR_INVALID);
        }
        this.car = car;
        this.canParticipate = true;
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
