package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final int INITIAL_KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        super.kilograms = INITIAL_KILOGRAMS;
        //TODO change the super.kilograms to be in the super constructor
    }

    @Override
    public void eating() {
        super.kilograms += 3;
    }
}
