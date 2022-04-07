package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private static final double INITIAL_UNIT_OXYGEN = 70;
    private static final int BIOLOGIST_OXYGEN_DECREASE = 5;
    public Biologist(String name) {
        super(name, INITIAL_UNIT_OXYGEN);
    }

    @Override
    public void breath(){
        if (this.getOxygen() - BIOLOGIST_OXYGEN_DECREASE >= 0){
            this.setOxygen(this.getOxygen() - BIOLOGIST_OXYGEN_DECREASE);
        }else {
            this.setOxygen(0);
        }
    }
}
