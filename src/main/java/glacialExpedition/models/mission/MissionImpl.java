package glacialExpedition.models.mission;

import glacialExpedition.core.ControllerImpl;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        List<String> exhibits = new ArrayList<>(state.getExhibits());

        for (Explorer explorer : explorers){

            while (explorer.canSearch() && exhibits.size() > 0){
                explorer.search();
                explorer.getSuitcase().getExhibits().add(exhibits.iterator().next());
                exhibits.remove(0);
            }
        }

    }

}
