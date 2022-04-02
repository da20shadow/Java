package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{

    private static final double INITIAL_ENERGY = 60;
    private static final double ENERGY_NEEDED_FOR_NATURAL_EXPLORER = 7;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search(){

        if (super.getEnergy() >= ENERGY_NEEDED_FOR_NATURAL_EXPLORER){
            double currentEnergy = super.getEnergy();
            super.setEnergy(currentEnergy - ENERGY_NEEDED_FOR_NATURAL_EXPLORER);
        }else{
            super.setEnergy(0);
        }
    }
}
