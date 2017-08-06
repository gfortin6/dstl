package gfortin.life.dstl.model;

import com.j256.ormlite.field.DatabaseField;

import gfortin.life.dstl.util.UuidUtil;

/**
 * Created by guillaume on 30/07/17.
 */

public class ItemProperty {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String key;
    @DatabaseField
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

/*

    @DatabaseField
    private int cost;

    /*ARMORS*
    @DatabaseField
    private int durability;
    @DatabaseField
    private int weight;
    @DatabaseField
    private int physical_protection;
    @DatabaseField
    private int strike_protection;
    @DatabaseField
    private int slash_protection;
    @DatabaseField
    private int thrust_protection;
    @DatabaseField
    private int magical_protection;
    @DatabaseField
    private int fire_protection;
    @DatabaseField
    private int lightning_protection;
    @DatabaseField
    private int poise_protection;
    @DatabaseField
    private int bleed_protection;
    @DatabaseField
    private int poison_protection;
    @DatabaseField
    private int curse_protection;

    /*SPELLS*
    @DatabaseField
    private int nb_uses;
    @DatabaseField
    private int nb_slots;
    @DatabaseField
    private int lvl_int;
    @DatabaseField
    private int lvl_faith;

    /*REQUIREMENTS & BONUS*
    @DatabaseField
    private int strength_requirement;
    @DatabaseField
    private int strength_bonus;
    @DatabaseField
    private int dexterity_requirement;
    @DatabaseField
    private int dexterity_bonus;
    @DatabaseField
    private int intelligence_requirement;
    @DatabaseField
    private int intelligence_bonus;
    @DatabaseField
    private int faith_requirement;
    @DatabaseField
    private int faith_bonus;

    /*Damages*
    @DatabaseField
    private String physical_dmg;
    @DatabaseField
    private String magical_dmg;
    @DatabaseField
    private String fire_dmg;
    @DatabaseField
    private String lightning_dmg;


    /*Shields*
    @DatabaseField
    private int stability;


    /*RINGS*
    @DatabaseField
    private String effect_lvl1;
    @DatabaseField
    private String effect_lvl2;
    @DatabaseField
    private String effect_lvl3;





    public ItemProperty() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPhysical_protection() {
        return physical_protection;
    }

    public void setPhysical_protection(int physical_protection) {
        this.physical_protection = physical_protection;
    }

    public int getStrike_protection() {
        return strike_protection;
    }

    public void setStrike_protection(int strike_protection) {
        this.strike_protection = strike_protection;
    }

    public int getSlash_protection() {
        return slash_protection;
    }

    public void setSlash_protection(int slash_protection) {
        this.slash_protection = slash_protection;
    }

    public int getThrust_protection() {
        return thrust_protection;
    }

    public void setThrust_protection(int thrust_protection) {
        this.thrust_protection = thrust_protection;
    }

    public int getMagical_protection() {
        return magical_protection;
    }

    public void setMagical_protection(int magical_protection) {
        this.magical_protection = magical_protection;
    }

    public int getFire_protection() {
        return fire_protection;
    }

    public void setFire_protection(int fire_protection) {
        this.fire_protection = fire_protection;
    }

    public int getLightning_protection() {
        return lightning_protection;
    }

    public void setLightning_protection(int lightning_protection) {
        this.lightning_protection = lightning_protection;
    }

    public int getPoise_protection() {
        return poise_protection;
    }

    public void setPoise_protection(int poise_protection) {
        this.poise_protection = poise_protection;
    }

    public int getBleed_protection() {
        return bleed_protection;
    }

    public void setBleed_protection(int bleed_protection) {
        this.bleed_protection = bleed_protection;
    }

    public int getPoison_protection() {
        return poison_protection;
    }

    public void setPoison_protection(int poison_protection) {
        this.poison_protection = poison_protection;
    }

    public int getCurse_protection() {
        return curse_protection;
    }

    public void setCurse_protection(int curse_protection) {
        this.curse_protection = curse_protection;
    }

    public int getNb_uses() {
        return nb_uses;
    }

    public void setNb_uses(int nb_uses) {
        this.nb_uses = nb_uses;
    }

    public int getNb_slots() {
        return nb_slots;
    }

    public void setNb_slots(int nb_slots) {
        this.nb_slots = nb_slots;
    }

    public int getLvl_int() {
        return lvl_int;
    }

    public void setLvl_int(int lvl_int) {
        this.lvl_int = lvl_int;
    }

    public int getLvl_faith() {
        return lvl_faith;
    }

    public void setLvl_faith(int lvl_faith) {
        this.lvl_faith = lvl_faith;
    }

    public int getStrength_requirement() {
        return strength_requirement;
    }

    public void setStrength_requirement(int strength_requirement) {
        this.strength_requirement = strength_requirement;
    }

    public int getStrength_bonus() {
        return strength_bonus;
    }

    public void setStrength_bonus(int strength_bonus) {
        this.strength_bonus = strength_bonus;
    }

    public int getDexterity_requirement() {
        return dexterity_requirement;
    }

    public void setDexterity_requirement(int dexterity_requirement) {
        this.dexterity_requirement = dexterity_requirement;
    }

    public int getDexterity_bonus() {
        return dexterity_bonus;
    }

    public void setDexterity_bonus(int dexterity_bonus) {
        this.dexterity_bonus = dexterity_bonus;
    }

    public int getIntelligence_requirement() {
        return intelligence_requirement;
    }

    public void setIntelligence_requirement(int intelligence_requirement) {
        this.intelligence_requirement = intelligence_requirement;
    }

    public int getIntelligence_bonus() {
        return intelligence_bonus;
    }

    public void setIntelligence_bonus(int intelligence_bonus) {
        this.intelligence_bonus = intelligence_bonus;
    }

    public int getFaith_requirement() {
        return faith_requirement;
    }

    public void setFaith_requirement(int faith_requirement) {
        this.faith_requirement = faith_requirement;
    }

    public int getFaith_bonus() {
        return faith_bonus;
    }

    public void setFaith_bonus(int faith_bonus) {
        this.faith_bonus = faith_bonus;
    }

    public String getPhysical_dmg() {
        return physical_dmg;
    }

    public void setPhysical_dmg(String physical_dmg) {
        this.physical_dmg = physical_dmg;
    }

    public String getMagical_dmg() {
        return magical_dmg;
    }

    public void setMagical_dmg(String magical_dmg) {
        this.magical_dmg = magical_dmg;
    }

    public String getFire_dmg() {
        return fire_dmg;
    }

    public void setFire_dmg(String fire_dmg) {
        this.fire_dmg = fire_dmg;
    }

    public String getLightning_dmg() {
        return lightning_dmg;
    }

    public void setLightning_dmg(String lightning_dmg) {
        this.lightning_dmg = lightning_dmg;
    }

    public int getStability() {
        return stability;
    }

    public void setStability(int stability) {
        this.stability = stability;
    }

    public String getEffect_lvl1() {
        return effect_lvl1;
    }

    public void setEffect_lvl1(String effect_lvl1) {
        this.effect_lvl1 = effect_lvl1;
    }

    public String getEffect_lvl2() {
        return effect_lvl2;
    }

    public void setEffect_lvl2(String effect_lvl2) {
        this.effect_lvl2 = effect_lvl2;
    }

    public String getEffect_lvl3() {
        return effect_lvl3;
    }

    public void setEffect_lvl3(String effect_lvl3) {
        this.effect_lvl3 = effect_lvl3;
    }
*/

}


