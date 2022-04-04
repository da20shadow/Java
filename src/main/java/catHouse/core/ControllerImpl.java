package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShortHairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.Repository;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private ToyRepository toyRepository;
    private Collection<House> houses;

    public ControllerImpl() {
        toyRepository = new ToyRepository();
        houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type){
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        this.houses.add(house);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type){
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalStateException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toyRepository.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        if (this.toyRepository.findFirst(toyType) == null){
            throw new IllegalStateException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));
        }
        Toy toy = this.toyRepository.findFirst(toyType);
        for (House house : this.houses){
            if (house.getName().equals(houseName)){
                house.buyToy(toy);
                this.toyRepository.removeToy(toy);
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
            }
        }
        return null;
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {

        Cat cat;
        switch (catType){
            case "ShortHair":
                cat = new ShortHairCat(catName,catBreed,price);
                break;
            case "LongHair":
                cat = new LonghairCat(catName,catBreed,price);
                break;
            default:
                throw new IllegalStateException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        for (House h : this.houses){
            if (h.getName().equals(houseName)){
                h.addCat(cat);
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);
            }
        }

        return null;
    }

    @Override
    public String feedingCat(String houseName) {
        int fedCount = 0;
        for (House h : this.houses){
            if (h.getName().equals(houseName)){
                h.feeding();
                fedCount = h.getCats().size();
            }
        }
        return String.format(ConstantMessages.FEEDING_CAT,fedCount);
    }

    @Override
    public String sumOfAll(String houseName) {
        double sum = 0;
        for (House h : this.houses){
            if (h.getName().equals(houseName)){
                for (Cat cat : h.getCats()){
                    sum += cat.getPrice();
                }
                for(Toy t : h.getToys()){
                    sum += t.getPrice();
                }
            }
        }
        return String.format(ConstantMessages.VALUE_HOUSE,houseName,sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder str = new StringBuilder();
        for (House h : this.houses){
            str.append(h.getStatistics());
        }
        return str.toString();
    }
}
