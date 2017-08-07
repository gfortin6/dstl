package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

@DatabaseTable(tableName = "itemtrophyjunction")
public class ItemTrophyJunction {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Trophy trophy;

    public ItemTrophyJunction() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Trophy getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy trophy) {
        this.trophy = trophy;
    }
}


