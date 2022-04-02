package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

public class CivilPlayer extends BasePlayer{
    private final static int INITIAL_LIFE_POINTS = 50;

    public CivilPlayer(String name) {
        super(name, INITIAL_LIFE_POINTS);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return null;
    }
}
