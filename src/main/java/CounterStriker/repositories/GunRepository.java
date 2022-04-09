package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun>{

    private Map<String,Gun> guns;

    public GunRepository() {
        this.guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getGuns() {
        //TODO if not get 150/150 maybe should return directly the collection
        return Collections.unmodifiableCollection(this.guns.values());
    }

    @Override
    public void add(Gun gun) {
        if (gun == null){
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }
        this.guns.put(gun.getName(),gun);
    }

    @Override
    public boolean remove(Gun gun) {
        return this.guns.remove(gun.getName(),gun);
    }

    @Override
    public Gun findByName(String name) {
        return this.guns.get(name);
    }
}
