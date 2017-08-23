package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by guillaume on 30/07/17.
 */

public class Item {
    public final static String ID_FIELD_NAME = "id";


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Type type;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Type subtype;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "attack_type_id")
    private Type attackType;
    @DatabaseField
    private boolean isAcquired;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Game game;



    public Item() {
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

    public Type getAttackType() {
        return attackType;
    }

    public void setAttackType(Type attackType) {
        this.attackType = attackType;
    }

    public boolean isAcquired() {
        return isAcquired;
    }

    public void setAcquired(boolean acquired) {
        isAcquired = acquired;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", subtype=" + subtype +
                ", attackType=" + attackType +
                '}';
    }
}


