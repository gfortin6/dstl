package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guillaume on 30/07/17.
 */

@DatabaseTable(tableName = "itempropertyjunction")
public class ItemPropertyJunction {
    public final static String ITEM_PROPERTY_ID_FIELD_NAME = "item_property_id";
    public final static String ITEM_ID_FIELD_NAME = "item_id";



    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;
    @DatabaseField(foreign=true, foreignAutoRefresh=true, columnName = "item_property_id")
    private Property property;

    public ItemPropertyJunction() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}


