package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GangNeighbourhood implements Neighbourhood{
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        //TODO this method doesn't work maybe need to rewrite it from scratch

        if (mainPlayer.getGunRepository().getModels().size() > 0){

            List<String> mainPlayerGunsNames = new ArrayList<>();

            for (Gun gun : mainPlayer.getGunRepository().getModels()){
                mainPlayerGunsNames.add(gun.getName());
            }

            Gun currentGun;
            while (mainPlayerGunsNames.size() > 0 && civilPlayers.size() > 0){

                for(Player civil : civilPlayers){

                    GetCurrentGun(mainPlayer, mainPlayerGunsNames, civil);

                    if (!civil.isAlive()){
                        civilPlayers.remove(civil);
                    }

                }

            }

            if (civilPlayers.size() > 0){

                for (Player civil : civilPlayers){

                    List<String> civilGuns = new ArrayList<>();

                    for (Gun gun : civil.getGunRepository().getModels()){
                        civilGuns.add(gun.getName());
                    }

                    Gun currentCivilGun;

                    while (mainPlayer.isAlive() && civilGuns.size() > 0){

                        GetCurrentGun(civil, civilGuns, mainPlayer);

                    }

                }


            }
        }

    }

    private void GetCurrentGun(Player mainPlayer, List<String> mainPlayerGunsNames, Player civil) {
        Gun currentGun;
        currentGun = mainPlayer.getGunRepository().find(mainPlayerGunsNames.get(0));

        while (civil.isAlive()){

            int takenPoints = currentGun.fire();

            if (takenPoints <= 0){
                mainPlayerGunsNames.remove(0);
                mainPlayer.getGunRepository().remove(currentGun);
                break;
            }else{
                civil.takeLifePoints(takenPoints);
            }

        }
    }
}
