package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements restaurant.repositories.interfaces.HealthFoodRepository<HealthyFood> {
    private Collection<HealthyFood> foods;

    public HealthFoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        for (HealthyFood f : this.foods){
            if (f.getName().equals(name)){
                return f;
            }
        }
        return null;
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableCollection(this.foods);
    }

    @Override
    public void add(HealthyFood food) {
        this.foods.add(food);
    }
}
