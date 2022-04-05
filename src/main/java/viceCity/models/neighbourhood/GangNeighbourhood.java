package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GangNeighbourhood implements Neighbourhood{

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        if (!mainPlayer.getGunRepository().getModels().isEmpty()){

            while (!mainPlayer.getGunRepository().getModels().isEmpty()
                    && !civilPlayers.isEmpty()){

                Gun currentGun = mainPlayer.getGunRepository().getModels().iterator().next();
                Player civilPlayer = civilPlayers.iterator().next();

                while (currentGun.canFire() && civilPlayer.isAlive()){

                    civilPlayer.takeLifePoints(currentGun.fire());
                }

                if (!currentGun.canFire()){
                    mainPlayer.getGunRepository().getModels().remove(currentGun);
                }
                if (!civilPlayer.isAlive()){
                    civilPlayers.remove(civilPlayer);
                }
                }

            }
        }
}
