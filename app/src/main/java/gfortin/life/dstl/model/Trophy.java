package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */
public class Trophy {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Game game;
    @DatabaseField
    private String name;
    @DatabaseField
    private String description;
    @DatabaseField
    private int value;
    @DatabaseField
    private String path;
    @DatabaseField
    private boolean acquired;

    public Trophy() {
    }

    public Trophy(int id, Game game, String name, String description, int value, String path, boolean acquired) {
        this.id = id;
        this.game = game;
        this.name = name;
        this.description = description;
        this.value = value;
        this.path = path;
        this.acquired = acquired;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAcquired() {
        return acquired;
    }

    public void setAcquired(boolean acquired) {
        this.acquired = acquired;
    }

    @Override
    public String toString() {
        return "Trophy{" +
                "id=" + id +
                ", game=" + game +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                ", path='" + path + '\'' +
                ", acquired=" + acquired +
                '}';
    }
}


