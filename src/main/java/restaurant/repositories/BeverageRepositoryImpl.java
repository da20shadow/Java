package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BeverageRepositoryImpl implements restaurant.repositories.interfaces.BeverageRepository<Beverages> {
    private Collection<Beverages> beverages;

    public BeverageRepositoryImpl() {
        this.beverages = new ArrayList<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {

        for (Beverages b : this.beverages){
            if (b.getName().equals(drinkName) && b.getBrand().equals(drinkBrand)){
                return b;
            }
        }
        return null;
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableCollection(this.beverages);
    }

    @Override
    public void add(Beverages beverage) {
        this.beverages.add(beverage);
    }
}
