package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

@DatabaseTable(tableName = "itemitemjunction")
public class ItemItemJunction {
    public final static String ITEM2_ID_FIELD_NAME = "item2_id";
    public final static String ITEM_ID_FIELD_NAME = "item_id";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item2;

    public ItemItemJunction() {
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

    public Item getItem2() {
        return item2;
    }

    public void setItem2(Item item2) {
        this.item2 = item2;
    }
}


