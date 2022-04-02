package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        List<String> exhibits = state.getExhibits().stream().toList();

        for (Explorer explorer : explorers){

            while (explorer.canSearch() && exhibits.size() > 0){
                explorer.search();
                String currentExhibit = exhibits.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                state.getExhibits().remove(currentExhibit);
            }
        }
    }

}
