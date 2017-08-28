package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by guillaume on 8/3/2017.
 */

@DatabaseTable(tableName = "characterlocationjunction")
public class CharacterLocationJunction {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Character character;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Location location;

    public CharacterLocationJunction(){}

}
