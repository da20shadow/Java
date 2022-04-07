package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        while (astronauts.iterator().hasNext()){

            Astronaut astronaut = astronauts.iterator().next();

            if (astronaut.canBreath() && planet.getItems().size() > 0){
                while (planet.getItems().iterator().hasNext()){
                    astronaut.breath();
                    String item = planet.getItems().iterator().next();
                    astronaut.getBag().getItems().add(item);
                    planet.getItems().remove(item);
                    if (!astronaut.canBreath()){
                        astronauts.remove(astronaut);
                        break;
                    }
                }
            }else if (planet.getItems().isEmpty()){
                break;
            }
        }
    }
}
