package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * Created by guillaume on 30/07/17.
 */

public class Game {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField (columnName = "front_cover_path")
    private String frontCoverPath;
    @DatabaseField (columnName = "back_cover_path")
    private String backCoverPath;
    @DatabaseField (columnName = "release_date")
    private Date releaseDate;


    public Game() {
    }

    public Game(int id, String name, String frontCoverPath, String back_cover_path, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.frontCoverPath = frontCoverPath;
        this.backCoverPath = back_cover_path;
        this.releaseDate = releaseDate;
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

    public String getFrontCoverPath() {
        return frontCoverPath;
    }

    public void setFrontCoverPath(String frontCoverPath) {
        this.frontCoverPath = frontCoverPath;
    }

    public String getBackCoverPath() {
        return backCoverPath;
    }

    public void setBackCoverPath(String backCoverPath) {
        this.backCoverPath = backCoverPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frontCoverPath='" + frontCoverPath + '\'' +
                ", backCoverPath='" + backCoverPath + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}


