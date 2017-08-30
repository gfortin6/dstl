package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guillaume on 8/3/2017.
 */

@DatabaseTable(tableName = "characteritemjunction")
public class CharacterItemJunction {
    public final static String CHARACTER_ID_FIELD_NAME = "character_id";
    public final static String ITEM_ID_FIELD_NAME = "item_id";


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Character character;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Item item;

    public CharacterItemJunction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
