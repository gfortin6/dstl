package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guillaume on 30/07/17.
 */

@DatabaseTable(tableName = "characterpropertyjunction")
public class CharacterPropertyJunction {
   public final static String ITEM_PROPERTY_ID_FIELD_NAME = "property_id";
    public final static String ITEM_ID_FIELD_NAME = "character_id";



    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Character character;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private ItemProperty property;

    public CharacterPropertyJunction() {
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public ItemProperty getProperty() {
        return property;
    }

    public void setProperty(ItemProperty property) {
        this.property = property;
    }
}


