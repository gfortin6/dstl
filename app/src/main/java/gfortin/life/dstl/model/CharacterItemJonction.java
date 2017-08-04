package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 8/3/2017.
 */

public class CharacterItemJonction {
    @DatabaseField(id=true, generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Character character;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;

    public CharacterItemJonction(){}

}
