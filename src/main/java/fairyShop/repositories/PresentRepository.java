package fairyShop.repositories;

import fairyShop.models.Present;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentRepository implements Repository<Present>{
    private Map<String,Present> presents;

    public PresentRepository() {
        this.presents = new LinkedHashMap<>();
    }

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(this.presents.values());
    }

    @Override
    public void add(Present present) {
        this.presents.put(present.getName(),present);
    }

    @Override
    public boolean remove(Present present) {
        return this.presents.remove(present.getName(),present);
    }

    @Override
    public Present findByName(String name) {
        return this.presents.get(name);
    }
}
