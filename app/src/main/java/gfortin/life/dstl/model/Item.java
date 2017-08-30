package gfortin.life.dstl.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.Collection;

/**
 * Created by guillaume on 30/07/17.
 */

public class Item {
    public final static String ID_FIELD_NAME = "id";
    public final static String IS_ACQUIRED_FIELD_NAME = "is_acquired";


    @DatabaseField(id = true)
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
    @DatabaseField(columnName = "is_acquired")
    private boolean isAcquired;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Game game;
    @ForeignCollectionField
    Collection<Property> properties;


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

    public Collection<Property> getProperties() {
        return properties;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
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


