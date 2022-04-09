package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelperRepository implements Repository<Helper>{
    private Map<String,Helper> helpers;

    public HelperRepository() {
        this.helpers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(this.helpers.values());
    }

    @Override
    public void add(Helper helper) {
        this.helpers.put(helper.getName(),helper);
    }

    @Override
    public boolean remove(Helper model) {
        return this.helpers.remove(model.getName(),model);
    }

    @Override
    public Helper findByName(String name) {
        return this.helpers.get(name);
    }
}
