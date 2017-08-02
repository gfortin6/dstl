package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

public class ItemTrophy {
    @DatabaseField(id = true)
    private String id = UuidUtil.nextUuid();
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Item item;
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Trophy trophy;

    public ItemTrophy() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Trophy getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy trophy) {
        this.trophy = trophy;
    }

    public ItemTrophy(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


