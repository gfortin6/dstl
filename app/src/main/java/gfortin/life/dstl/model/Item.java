package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

public class Item {
    @DatabaseField(id = true)
    private String id = UuidUtil.nextUuid();
    @DatabaseField
    private Character character;
    @DatabaseField
    private Location location;
    @DatabaseField
    private Category category;


    public Item() {
    }

    public Item(String id, Character character, Location location, Category catgory) {
        this.id = id;
        this.character = character;
        this.location = location;
        this.category = catgory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


