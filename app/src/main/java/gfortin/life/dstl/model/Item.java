package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by guillaume on 30/07/17.
 */

public class Item {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Character character;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Location location;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Type type;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Type subtype;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "attack_type_id")
    private Type attackType;
    @DatabaseField
    private boolean enchantable;
    @DatabaseField
    private boolean special;


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


    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public boolean isEnchantable() {
        return enchantable;
    }

    public void setEnchantable(boolean enchantable) {
        this.enchantable = enchantable;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", character=" + character +
                ", location=" + location +
                ", type=" + type +
                ", subtype=" + subtype +
                ", attackType=" + attackType +
                ", enchantable=" + enchantable +
                ", special=" + special +
                '}';
    }
}


