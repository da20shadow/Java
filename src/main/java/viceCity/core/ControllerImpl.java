package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private MainPlayer mainPlayer;
    private Collection<Player> civilPlayerList;
    private List<Gun> guns;
    private int startNumOfPlayers;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayerList = new ArrayList<>();
        this.guns = new ArrayList<>();
    }

    @Override
    public String addPlayer(String name) {

        CivilPlayer civilPlayer = new CivilPlayer(name);
        this.civilPlayerList.add(civilPlayer);

        return String.format("Successfully added civil player: %s!",name);
    }

    @Override
    public String addGun(String type, String name) {

        if (type.equals("Pistol")){

            Gun pistol = new Pistol(name);
            this.guns.add(pistol);

        }else if(type.equals("Rifle")){

            Gun rifle = new Rifle(name);
            this.guns.add(rifle);

        }else{

            return "Invalid gun type!";

        }

        return String.format("Successfully added %s of type: %s",name,type);
    }

    @Override
    public String addGunToPlayer(String name) {

        if (this.guns.size() == 0){
            return "There are no guns in the queue!";
        }

        Gun first = this.guns.get(0);

        if (name.equals("Vercetti")){

            this.mainPlayer.getGunRepository().getModels().add(first);
            this.guns.remove(0);
            return String.format("Successfully added %s to the Main Player: Tommy Vercetti",first.getName());

        }else {

            boolean exist = false;
            CivilPlayer theCivilPlayer = null;
            for (Player civilPlayer: this.civilPlayerList){

                if (civilPlayer.getName().equals(name)){
                    theCivilPlayer = (CivilPlayer) civilPlayer;
                    civilPlayer.getGunRepository().add(first);
                    this.guns.remove(0);
                    exist = true;

                }
            }

            if (!exist){
                return "Civil player with that name doesn't exists!";
            }else{
                return String.format("Successfully added %s to the Civil Player: %s"
                        ,first.getName(),theCivilPlayer.getName());
            }
        }
    }

    @Override
    public String fight() {

        this.startNumOfPlayers = this.civilPlayerList.size();

        GangNeighbourhood gangNeighbourhood = new GangNeighbourhood();

        gangNeighbourhood.action(this.mainPlayer,this.civilPlayerList);

        boolean isAnyoneHarmed = false;
        for (Player player : this.civilPlayerList){

            if (player.getLifePoints() < 50){
                isAnyoneHarmed = true;
            }
        }

        if (this.mainPlayer.getLifePoints() == 100
                && this.startNumOfPlayers == this.civilPlayerList.size()
                && !isAnyoneHarmed){

            return "Everything is okay!";
        }else {
            StringBuilder message = new StringBuilder();
            message.append("A fight happened:");
            message.append(System.lineSeparator());
            message.append(String.format("Tommy live points: %d!",this.mainPlayer.getLifePoints()));
            message.append(System.lineSeparator());
            int totalKilled = this.startNumOfPlayers - this.civilPlayerList.size();
            message.append(String.format("Tommy has killed: %d players!",totalKilled));
            message.append(System.lineSeparator());
            message.append(String.format("Left Civil Players: %d!",this.civilPlayerList.size()));

            return message.toString();
        }
    }
}
