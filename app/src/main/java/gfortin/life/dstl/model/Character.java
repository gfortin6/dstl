package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

public class Character {
    @DatabaseField(generatedId=true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Game game;

    public Character() {
    }
}


