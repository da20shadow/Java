package glacialExpedition.core;

import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.List;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.EXPLORER_INVALID_TYPE;

public class ControllerImpl implements Controller {

    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.exploredStates = 0;
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

        return String.format(EXPLORER_ADDED,type,explorerName);

    }

    @Override
    public String addState(String stateName, String... exhibits) {

        State state = new StateImpl(stateName);

        for (String ex : exhibits){
            state.getExhibits().add(ex);
        }

        this.stateRepository.add(state);

        return String.format(STATE_ADDED,stateName);
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

        List<Explorer> explorerList = new ArrayList<>();

        for (Explorer ex: this.explorerRepository.getCollection()){
            if (ex.getEnergy() > 50){
                explorerList.add(ex);
            }
        }
        if (explorerList.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();

        for (State state : this.stateRepository.getCollection()){
            if (state.getName().equals(stateName)){
                mission.explore(state,explorerList);
            }
        }
        int retired = 0;
        for (Explorer e: explorerList){
            if (e.getEnergy() <= 0){
                retired++;
            }
//            for (Explorer exp: this.explorerRepository.getCollection()){
//                if (e.equals(exp)){
//                    int index = this.explorerRepository.getCollection().stream().toList().indexOf(exp);
//                    this.explorerRepository.getCollection().remove(exp);
//                    this.explorerRepository.getCollection().stream().toList().add(index,e);
//                }
//            }
        }
        this.exploredStates++;
        return String.format(STATE_EXPLORER,stateName,retired);
    }

    @Override
    public String finalResult() {
        StringBuilder str = new StringBuilder();

        str.append(String.format(FINAL_STATE_EXPLORED,this.exploredStates));
        str.append(System.lineSeparator());
        str.append(FINAL_EXPLORER_INFO);
        str.append(System.lineSeparator());

        for (Explorer explorer : this.explorerRepository.getCollection()){
            str.append("Name: ").append(explorer.getName());
            str.append(System.lineSeparator());
            str.append(String.format("Energy: %.0f",explorer.getEnergy()));
            str.append(System.lineSeparator());
            if (explorer.getSuitcase().getExhibits().isEmpty()){
                str.append("Suitcase exhibits: None");
            }else{
                str.append("Suitcase exhibits: ").append(String.join(", ",explorer.getSuitcase().getExhibits()));
            }
            str.append(System.lineSeparator());
        }
        return str.toString().trim();
    }
}
