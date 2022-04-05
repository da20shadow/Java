package restaurant.repositories;

import restaurant.entities.healthyFoods.Food;

import java.util.Collection;
import java.util.Collections;

public class HealthFoodRepositoryImpl implements restaurant.repositories.interfaces.HealthFoodRepository<Food> {
    private Collection<Food> foods;

    @Override
    public Food foodByName(String name) {
        for (Food f : this.foods){
            if (f.getName().equals(name)){
                return f;
            }
        }
        return null;
    }

    @Override
    public Collection<Food> getAllEntities() {
        return Collections.unmodifiableCollection(this.foods);
    }

    @Override
    public void add(Food food) {
        this.foods.add(food);
    }
}
