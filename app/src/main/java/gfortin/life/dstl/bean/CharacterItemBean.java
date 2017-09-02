package gfortin.life.dstl.bean;

import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.Item;

/**
 * Created by guillaume on 01/09/17.
 */

public class CharacterItemBean {
    private Item item;
    private Character character;
    private int cost;

    public CharacterItemBean() {
    }

    public CharacterItemBean(Item item, Character character, int cost) {
        this.item = item;
        this.character = character;
        this.cost = cost;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
