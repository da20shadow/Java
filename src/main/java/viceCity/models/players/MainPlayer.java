package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

public class MainPlayer extends BasePlayer{
    private final static int INITIAL_LIFE_POINTS = 100;
    private final static String MAIN_PLAYER_NAME = "Tommy Vercetti";
    private Repository<Gun> guns;

    public MainPlayer() {
        super(MAIN_PLAYER_NAME, INITIAL_LIFE_POINTS);
        guns = new GunRepository();
    }

    @Override
    public String getName() {
       return MAIN_PLAYER_NAME;
    }

    @Override
    public int getLifePoints() {
       return super.getLifePoints();
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.guns;
    }
}
