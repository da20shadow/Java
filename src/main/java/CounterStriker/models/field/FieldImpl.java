package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldImpl implements Field{
    @Override
    public String start(Collection<Player> players) {
        List<Player> terroristList = new ArrayList<>();
        List<Player> counterTerrorists = new ArrayList<>();

        for (Player p : players){
            if (p.getClass().getSimpleName().equals("Terrorist")){
                terroristList.add(p);
            }else {
                counterTerrorists.add(p);
            }
        }
        while (!terroristList.isEmpty() && !counterTerrorists.isEmpty()){
            for (Player t : terroristList){
                for (Player ct : counterTerrorists){
                    ct.takeDamage(t.getGun().fire());
                    if (!ct.isAlive()){
                        counterTerrorists.remove(ct);
                    }
                }
            }
            for (Player ct : counterTerrorists){
                for (Player t: terroristList){
                    t.takeDamage(ct.getGun().fire());
                    if (!t.isAlive()){
                        terroristList.remove(t);
                    }
                }
            }
        }
//
        return terroristList.isEmpty()
                ? OutputMessages.COUNTER_TERRORIST_WINS
                : OutputMessages.TERRORIST_WINS;
    }
}
