package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;

public class GunRepository implements Repository<Gun>{
    private Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models;
    }

    @Override
    public void add(Gun model) {

        if (!this.models.contains(model)){
            this.models.add(model);
        }

    }

    @Override
    public boolean remove(Gun model) {

        if (this.models.contains(model)){
            this.models.remove(model);
            return true;
        }
        return false;
    }

    @Override
    public Gun find(String name) {

        for (Gun gun : this.models){

            if (gun.getName().equals(name)){
                return gun;
            }
        }

        return null;
    }
}
