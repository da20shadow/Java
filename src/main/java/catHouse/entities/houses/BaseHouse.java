package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.Collection;

public abstract class BaseHouse implements House{

    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
    }

    @Override
    public int sumSoftness() {
        int sum = 0;
        for (Toy toy : this.toys){
            sum += toy.getSoftness();
        }
        return sum;
    }

    @Override
    public void addCat(Cat cat) {
        if (this.cats.size() >= this.capacity){
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        boolean shortHouseCat = this.getClass().getSimpleName().startsWith("Short")
                && cat.getClass().getSimpleName().startsWith("Short");
        boolean longHouseCat = this.getClass().getSimpleName().startsWith("Long")
                && cat.getClass().getSimpleName().startsWith("Long");
        if (shortHouseCat || longHouseCat) {
            this.cats.add(cat);
        }else {
            throw new IllegalStateException(ConstantMessages.UNSUITABLE_HOUSE);
        }
    }

    @Override
    public void removeCat(Cat cat) {
        this.cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.toys.add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat : this.cats){
            cat.eating();
        }
    }

    @Override
    public String getStatistics(){
        StringBuilder str = new StringBuilder();

        str.append(String.format("%s %s:", this.getName(),this.getClass().getSimpleName()));
        str.append(System.lineSeparator());
        str.append("Cats:");

        if (this.getCats().isEmpty()){
            str.append(" none");
            return str.toString();
        }

        for (Cat cat : this.getCats()){
            str.append(" ").append(cat.getName());
        }
        str.append(System.lineSeparator());
        int sumSoftness = sumSoftness();
        str.append("Toys: ").append(this.getToys().size()).append(sumSoftness);
        return str.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }
}
