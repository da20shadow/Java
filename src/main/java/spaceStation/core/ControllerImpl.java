package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int exploredPlanets;

    public ControllerImpl(AstronautRepository astronautRepository, PlanetRepository planetRepository) {
        this.astronautRepository = astronautRepository;
        this.planetRepository = planetRepository;
        exploredPlanets = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type){
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_ADDED,type,astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items){
            planet.getItems().add(item);
        }
        this.planetRepository.add(planet);
        return String.format(ConstantMessages.PLANET_ADDED,planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);
        if (astronaut == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST,astronautName));
        }
        this.astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED,astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Mission mission = new MissionImpl();
        List<Astronaut> astronautsForMission = new ArrayList<>();

        for (Astronaut astronaut : this.astronautRepository.getModels()){
            if (astronaut.getOxygen() > 60){
                astronautsForMission.add(astronaut);
            }
        }
        if (astronautsForMission.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int initialAstronautsNumber = astronautsForMission.size();
        mission.explore(this.planetRepository.findByName(planetName),astronautsForMission);
        this.exploredPlanets++;
        int dead = initialAstronautsNumber - astronautsForMission.size();
        return String.format(ConstantMessages.PLANET_EXPLORED,planetName,dead);
    }

    @Override
    public String report() {
        StringBuilder str = new StringBuilder();
        str.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED,this.exploredPlanets));
        str.append(System.lineSeparator());
        str.append(ConstantMessages.REPORT_ASTRONAUT_INFO);
        str.append(System.lineSeparator());

        for (Astronaut astronaut : this.astronautRepository.getModels()){
            str.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME,astronaut.getName()));
            str.append(System.lineSeparator());
            str.append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN,astronaut.getOxygen()));
            str.append(System.lineSeparator());
            if (astronaut.getBag().getItems().isEmpty()){
                str.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS,"none"));
            }else{
                String theItems =
                        String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS,
                                String.join(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER,
                                        astronaut.getBag().getItems()));
                str.append(theItems);
            }
            str.append(System.lineSeparator());
        }
        
        return str.toString().trim();
    }
}
