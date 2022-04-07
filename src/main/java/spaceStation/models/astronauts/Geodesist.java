package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut{
    private static final int INITIAL_UNIT_OXYGEN = 50;

    public Geodesist(String name) {
        super(name, INITIAL_UNIT_OXYGEN);
    }
}
