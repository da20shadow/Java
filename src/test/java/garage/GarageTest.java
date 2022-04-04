package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTest {

    private Garage garage;

    @Before
    public void setup(){
        garage = new Garage();
    }

    @Test
    public void testGetCars() {
        List<Car> cars;
        cars = createThreeCars();
        for (Car car: cars){
            this.garage.addCar(car);
        }
        Assert.assertEquals(3,garage.getCount());
        Assert.assertEquals(cars,garage.getCars());
    }

    private List<Car> createThreeCars() {
        Car porsche = new Car("Porsche",300,95000);
        Car bmw = new Car("BMW",330,65000);
        Car audi = new Car("Audi",230,15000);

        List<Car> cars = new ArrayList<>();
        cars.add(porsche);
        cars.add(bmw);
        cars.add(audi);
        return cars;
    }

    @Test
    public void testGetCount() {
        Car car = new Car("Name",100,1000);
        Assert.assertEquals(0,garage.getCount());
        garage.addCar(car);
        Assert.assertEquals(1,garage.getCount());
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        List<Car> cars = createThreeCars();
        List<Car> carsMaxSpeed = new ArrayList<>();

        for (Car car : cars){
            garage.addCar(car);
            if (car.getMaxSpeed() > 320){
                carsMaxSpeed.add(car);
            }
        }
        Assert.assertEquals(carsMaxSpeed,garage.findAllCarsWithMaxSpeedAbove(320));
    }

    @Test
    public void testAddCarSuccess() {
        Assert.assertEquals(0,garage.getCount());
        Car car = new Car("Porsche", 220, 99000);
        this.garage.addCar(car);
        Assert.assertEquals(car,garage.getCars().get(0));
        Assert.assertEquals(1,garage.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddingCarThrowException(){
        this.garage.addCar(null);
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        List<Car> cars = createThreeCars();
        Car mostExpensiveCar = new Car("Lai",10,100);
        double greatestPrice = 0;

        for (Car car : cars){
            garage.addCar(car);
            if (greatestPrice < car.getPrice()){
                mostExpensiveCar = car;
                greatestPrice = car.getPrice();
            }
        }
        Car carFromGarage = garage.getTheMostExpensiveCar();

        Assert.assertEquals(mostExpensiveCar,carFromGarage);
    }

    @Test
    public void testFindAllCarsByBrand() {
        Car audi = new Car("Audi",100,900);

        List<Car>cars = createThreeCars();
        List<Car> audiFromList = new ArrayList();

        for (Car car : cars){
            garage.addCar(car);
            if (car.getBrand().equals("Audi")){
                audiFromList.add(car);
            }
        }
        garage.addCar(audi);
        audiFromList.add(audi);
        List<Car> audiGarage = garage.findAllCarsByBrand("Audi");

        Assert.assertEquals(audiFromList,audiGarage);

    }
}