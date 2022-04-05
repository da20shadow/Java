package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoneyEarned;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.totalMoneyEarned = 0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {

        Food food;
        switch (type){
            case "Salad":
                food = new Salad(name,price);
                break;
            case "VeganBiscuits":
                food = new VeganBiscuits(name,price);
                break;
            default:
                throw new IllegalArgumentException("Can't Add UnHealthy Food!");
        }

        if (this.healthFoodRepository.foodByName(name) == null){
            this.healthFoodRepository.add(food);
        }else{
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST,name));
        }

        return String.format(OutputMessages.FOOD_ADDED,name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage;
        switch (type){
            case "Fresh":
                beverage = new Fresh(name,counter,brand);
                break;
            case "Smoothie":
                beverage = new Smoothie(name,counter,brand);
                break;
            default:
                throw new IllegalArgumentException("Can't Add Unhealthy Beverage!");
        }

        if (this.beverageRepository.beverageByName(name,brand) == null){
            this.beverageRepository.add(beverage);
        }else{
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST,name));
        }

        return String.format(OutputMessages.BEVERAGE_ADDED,type,brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        switch (type){
            case "InGarden":
                table = new InGarden(tableNumber,capacity);
                break;
            case "Indoors":
                table = new Indoors(tableNumber,capacity);
                break;
            default:
                throw new IllegalArgumentException("Invalid table!");
        }
        if (this.tableRepository.byNumber(tableNumber) == null){
            this.tableRepository.add(table);
        }else{
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED,tableNumber));
        }
        return String.format(OutputMessages.TABLE_ADDED,tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        boolean foundTable = false;
        int tableNumber = -9999;
        for (Table table : this.tableRepository.getAllEntities()){
            if (!table.isReservedTable() && table.getSize() >= numberOfPeople){
                tableNumber = table.getTableNumber();
                this.tableRepository.byNumber(tableNumber).reserve(numberOfPeople);
                foundTable = true;
            }
        }
        if (!foundTable){
            throw new IllegalArgumentException(String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople));
        }
        return String.format(OutputMessages.TABLE_RESERVED,tableNumber,numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {

        if (this.tableRepository.byNumber(tableNumber) == null){
            throw new IllegalArgumentException(String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber));
        }
        if (this.healthFoodRepository.foodByName(healthyFoodName) == null){
            throw new IllegalArgumentException(String.format(OutputMessages.NONE_EXISTENT_FOOD,healthyFoodName));
        }
        this.tableRepository.byNumber(tableNumber).orderHealthy(this.healthFoodRepository.foodByName(healthyFoodName));
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL,healthyFoodName,tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        if (this.tableRepository.byNumber(tableNumber) == null){
            throw new IllegalArgumentException(String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber));
        }
        if (this.beverageRepository.beverageByName(name,brand) == null){
            throw new IllegalArgumentException(String.format(OutputMessages.NON_EXISTENT_DRINK,name,brand));
        }
        this.tableRepository.byNumber(tableNumber).orderBeverages(this.beverageRepository.beverageByName(name,brand));
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL,name,tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        double bill = this.tableRepository.byNumber(tableNumber).bill();
        this.totalMoneyEarned += bill;
        this.tableRepository.byNumber(tableNumber).clear();
        return String.format(OutputMessages.BILL,tableNumber,bill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY,this.totalMoneyEarned);
    }
}
