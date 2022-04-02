package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import static viceCity.common.ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO;
import static viceCity.common.ExceptionMessages.PLAYER_NULL_USERNAME;

public abstract class BasePlayer implements Player{
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    protected BasePlayer(String name, int lifePoints) {
        if (name == null || name.trim().equals("")){
            throw new NullPointerException(PLAYER_NULL_USERNAME);
        }
        if (lifePoints < 0){
            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.name = name;
        this.lifePoints = lifePoints;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public int getLifePoints(){
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public void takeLifePoints(int points) {
        int newPoints = this.lifePoints - points;
        this.lifePoints = Math.max(newPoints, 0);
    }
}
