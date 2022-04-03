package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;
import christmasRaces.core.interfaces.Controller;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {
        Driver driver = new DriverImpl(driverName);
        if (this.driverRepository.getAll().contains(driver)){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS,driverName));
        }
        this.driverRepository.add(driver);
        return String.format("Driver %s is created",driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        Car car = null;
        switch (type){
            case "Muscle":
                car = new MuscleCar(model,horsePower);
                break;
            case "Sports":
                car = new SportsCar(model,horsePower);
                break;
        }
        if (car != null){
            if (this.carRepository.getAll().contains(car)){
                throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS,model));
            }
            this.carRepository.add(car);
            return String.format("%s %s is created.",type,model);
        }
        return null;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        if (this.driverRepository.getByName(driverName) != null){
            if (this.carRepository.getByName(carModel) != null){
                this.driverRepository.getByName(driverName).addCar(this.carRepository.getByName(carModel));
            }else {
                throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND,carModel));
            }
        }else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));
        }
        return String.format("Driver %s received car %s",driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        if (this.raceRepository.getByName(raceName) != null){

            if (this.driverRepository.getByName(driverName) != null){

                this.raceRepository.getByName(raceName).addDriver(this.driverRepository.getByName(driverName));

            }else {
                throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,driverName));
            }

        }else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        return String.format("Driver %s added in %s race.",
                driverName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        List<Driver> winners;

        if (this.raceRepository.getByName(raceName) != null){
            winners = this.raceRepository.getByName(raceName).getDrivers()
                    .stream()
                    .sorted((d1,d2)->{
                        int laps = this.raceRepository.getByName(raceName).getLaps();
                        return Double.compare(d2.getCar().calculateRacePoints(laps), d1.getCar().calculateRacePoints(laps));
                            })
                    .limit(3)
                    .collect(Collectors.toList());
        }else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,raceName));
        }
        if (winners.size() < 3){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,3));
        }

        this.raceRepository.remove(this.raceRepository.getByName(raceName));
        return String.format("Driver %s wins %s race.", winners.get(0).getName(), raceName) +
                System.lineSeparator() +
                String.format("Driver %s wins %s race.", winners.get(1).getName(), raceName) +
                System.lineSeparator() +
                String.format("Driver %s is third in %s race.", winners.get(2).getName(), raceName);
    }

    @Override
    public String createRace(String name, int laps) {

        Race race = new RaceImpl(name,laps);
        if (this.raceRepository.getByName(name) != null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,name));
        }
        this.raceRepository.add(race);
        return String.format("Race %s is created.",name);
    }
}
