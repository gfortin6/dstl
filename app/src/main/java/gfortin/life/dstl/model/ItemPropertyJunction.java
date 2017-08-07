package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guillaume on 30/07/17.
 */

@DatabaseTable(tableName = "itempropertyjunction")
public class ItemPropertyJunction {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private ItemProperty itemProperty;

    public ItemPropertyJunction() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemProperty getItemProperty() {
        return itemProperty;
    }

    public void setItemProperty(ItemProperty itemProperty) {
        this.itemProperty = itemProperty;
    }
}

