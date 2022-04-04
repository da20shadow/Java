package catHouse.entities.cat;

public class ShortHairCat extends BaseCat{
    private static final int INITIAL_KILOGRAMS = 7;

    public ShortHairCat(String name, String breed, double price) {
        super(name, breed, price,INITIAL_KILOGRAMS);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 1);
    }
}
