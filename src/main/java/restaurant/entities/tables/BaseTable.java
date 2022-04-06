package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood;
    private Collection<HealthyFood> orderedHealthyFood;
    private Collection<Beverages> beverages;
    private Collection<Beverages> orderedBeverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.orderedHealthyFood = new ArrayList<>();
        this.orderedBeverages = new ArrayList<>();
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
    }

    public void setSize(int size) {
        //TODO Възможен проблем: пише, че трябва да е size < 0
        // а в ексепшана казва, че трябва да е по голямо от нула!!!
        if (size <= 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
        this.allPeople = numberOfPeople * pricePerPerson;
    }

    public Collection<HealthyFood> getOrderedHealthyFood() {
        return orderedHealthyFood;
    }

    public Collection<Beverages> getOrderedBeverages() {
        return orderedBeverages;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.orderedHealthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.orderedBeverages.add(beverages);
    }

    @Override
    public double bill() {

        double bill = 0;
        for (HealthyFood food: this.getOrderedHealthyFood()){
            bill += food.getPrice();
        }
        for (Beverages beverage: this.getOrderedBeverages()){
            bill += beverage.getPrice();
        }
        bill += this.allPeople;
        return bill;
    }

    @Override
    public void clear() {
        this.getOrderedBeverages().clear();
        this.getOrderedHealthyFood().clear();
        this.numberOfPeople = 0;
        this.isReservedTable = false;
        this.allPeople = 0;
    }

    @Override
    public String tableInformation() {

        StringBuilder str = new StringBuilder();
        str.append("Table - ").append(this.number);
        str.append(System.lineSeparator());
        str.append("Size - ").append(this.size);
        str.append(System.lineSeparator());
        str.append("Type - ").append(this.getClass().getSimpleName());
        str.append("All price - ").append(this.allPeople);

        return null;
    }
}
