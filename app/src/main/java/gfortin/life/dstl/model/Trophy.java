package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */
public class Trophy {
    @DatabaseField(id = true)
    private String id = UuidUtil.nextUuid();
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Game game;


    public Trophy() {
    }

    public Trophy(String id, Game game) {
        this.id = id;
        this.game = game;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}


