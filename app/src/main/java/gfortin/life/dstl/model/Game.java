package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

public class Game {
    @DatabaseField(id = true)
    private String id = UuidUtil.nextUuid();
    @DatabaseField
    private String name;
    @DatabaseField
    private String front_cover_path;
    @DatabaseField
    private String back_cover_path;


    public Game() {
    }



    public Game(String name, String front_cover_path, String back_cover_path) {
        this.name = name;
        this.front_cover_path = front_cover_path;
        this.back_cover_path = back_cover_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFront_cover_path() {
        return front_cover_path;
    }

    public void setFront_cover_path(String front_cover_path) {
        this.front_cover_path = front_cover_path;
    }

    public String getBack_cover_path() {
        return back_cover_path;
    }

    public void setBack_cover_path(String back_cover_path) {
        this.back_cover_path = back_cover_path;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", front_cover_path='" + front_cover_path + '\'' +
                ", back_cover_path='" + back_cover_path + '\'' +
                '}';
    }
}


