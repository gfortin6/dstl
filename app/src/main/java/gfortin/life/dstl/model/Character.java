package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

public class Character {
    @DatabaseField(id=true)
    private String id = UuidUtil.nextUuid();

    public Character() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


