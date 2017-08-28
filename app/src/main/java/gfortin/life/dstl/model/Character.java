package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */
//id|name|game|type|ngHealth|ngSouls|ng1Health|ng1Souls|ng6Health|ng6Souls

public class Character {
    @DatabaseField(generatedId=true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Game game;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Type type;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Type subtype;

    public Character() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getSubtype() {
        return subtype;
    }

    public void setSubtype(Type subtype) {
        this.subtype = subtype;
    }
}


