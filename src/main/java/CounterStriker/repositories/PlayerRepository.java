package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerRepository implements Repository<Player>{

    private Map<String,Player> players;

    public PlayerRepository() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public Collection<Player> getGuns() {
        return Collections.unmodifiableCollection(this.players.values());
    }

    @Override
    public void add(Player player) {
        if (player == null){
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        this.players.put(player.getUsername(),player);
    }

    @Override
    public boolean remove(Player player) {
        return this.players.remove(player.getUsername(),player);
    }

    @Override
    public Player findByName(String name) {
        return this.players.get(name);
    }
}
