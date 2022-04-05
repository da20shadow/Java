package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;

import java.util.Collection;
import java.util.Collections;

public class TableRepositoryImpl implements restaurant.repositories.interfaces.TableRepository<Table> {
    private Collection<Table> tables;

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(this.tables);
    }

    @Override
    public void add(Table table) {
        this.tables.add(table);
    }

    @Override
    public Table byNumber(int number) {
        for (Table t: this.tables){
            if (t.getTableNumber() == number){
                return t;
            }
        }
        return null;
    }
}
