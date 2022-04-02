package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import static glacialExpedition.common.ExceptionMessages.EXPLORER_INVALID_TYPE;

public class ControllerImpl implements Controller {

    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {

        Explorer explorer;
        switch (type) {
            case "NaturalExplorer":

                explorer = new NaturalExplorer(explorerName);

                break;
            case "AnimalExplorer":

                explorer = new AnimalExplorer(explorerName);

                break;
            case "GlacierExplorer":

                explorer = new GlacierExplorer(explorerName);

                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        this.explorerRepository.add(explorer);

        return String.format("Added %s: %s",type,explorerName);

    }

    @Override
    public String addState(String stateName, String... exhibits) {

        State state = new StateImpl(stateName);

        for (String ex : exhibits){
            state.getExhibits().add(ex);
        }

        this.stateRepository.add(state);

        return String.format("Added state: %s",stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {

        for (Explorer ex : this.explorerRepository.getCollection()){

            if (ex.getName().equals(explorerName)){
                this.explorerRepository.remove(ex);
                return String.format("Explorer %s has retired!",explorerName);
            }
        }
        throw new IllegalArgumentException(String.format("Explorer %s doesn't exists.",explorerName));
    }

    @Override
    public String exploreState(String stateName) {

        //TODO here the actions happens.
        return null;
    }

    @Override
    public String finalResult() {

        //TODO create string builder and return string.
        return null;
    }
}
