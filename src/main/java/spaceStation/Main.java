package spaceStation;

import spaceStation.core.Controller;
import spaceStation.core.ControllerImpl;
import spaceStation.core.Engine;
import spaceStation.core.EngineImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

public class Main {
    public static void main(String[] args) {

        AstronautRepository astronautRepository = new AstronautRepository();
        PlanetRepository planetRepository = new PlanetRepository();
        Controller controller = new ControllerImpl(astronautRepository,planetRepository);
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
