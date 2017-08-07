package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 8/3/2017.
 */

@DatabaseTable(tableName = "characteritemjunction")
public class CharacterItemJunction {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Character character;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;

    public CharacterItemJunction(){}

}
