package gfortin.life.dstl.util;

import com.j256.ormlite.dao.ForeignCollection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemItemJunction;
import gfortin.life.dstl.model.Property;
import gfortin.life.dstl.model.Type;

public class PopulateDb {

    public static void populateType(DatabaseHelper dbHelper) {
        try {
            InputStream input = dbHelper.getContext().getApplicationContext()
                    .getAssets().open("types.txt");


            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer stTok = new StringTokenizer(line, "|");

                int id = Integer.parseInt(stTok.nextToken());
                String name = stTok.nextToken();

                Type type = new Type(id, name, null);
                dbHelper.getTypeDao().create(type);

            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public static void populateGames(DatabaseHelper dbHelper) {
        try {
            InputStream input = dbHelper.getContext().getApplicationContext()
                    .getAssets().open("games.txt");


            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer stTok = new StringTokenizer(line, "|");

                int id = Integer.parseInt(stTok.nextToken());
                String name = stTok.nextToken();
                String release = stTok.nextToken();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(release);


                Game game = new Game();
                game.setId(id);
                game.setName(name);
                game.setFrontCoverPath(name + "_fc");
                game.setBackCoverPath(name + "_bc");
                game.setReleaseDate(date);

                dbHelper.getGameDao().create(game);

            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


    public static void populateSorceries(DatabaseHelper dbHelper) {
        try {
            InputStream input = dbHelper.getContext().getApplicationContext()
                    .getAssets().open("sorceries.txt");

            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer stTok = new StringTokenizer(line, "|");
                int id = Integer.parseInt(stTok.nextToken());
                String name = stTok.nextToken();
                String nbUses = stTok.nextToken();
                String nbSlots = stTok.nextToken();
                String lvlInt = stTok.nextToken();
                String description = stTok.nextToken();
                String attackTypeId = stTok.nextToken();
                String trophyId = stTok.nextToken();

                Item item = new Item();
                item.setId(id);
                item.setName(name);

                item.setDescription(description);
                item.setAttackType(dbHelper.getTypeDao().queryForId(Integer.parseInt(attackTypeId)));
                item.setType(dbHelper.getTypeDao().queryForId(TypeConstant.Spells));
                item.setSubtype(dbHelper.getTypeDao().queryForId(TypeConstant.Sorceries));


                ItemItemJunction itemItemJunction = new ItemItemJunction();
                itemItemJunction.setItem(item);
                itemItemJunction.setItem2(dbHelper.getItemDao().queryForId(Integer.parseInt(trophyId)));
                dbHelper.getItemTrophyJunctionDao().create(itemItemJunction);

                Collection<Property> properties = new ArrayList<>();

                Property nbUsesProp = new Property();
                nbUsesProp.setKey("nbUses");
                nbUsesProp.setValue(nbUses);
                nbUsesProp.setItem(item);
                properties.add(nbUsesProp);
                dbHelper.getItemPropertyDao().create(nbUsesProp);

                Property nbSlotsProp = new Property();
                nbSlotsProp.setKey("nbSlots");
                nbSlotsProp.setValue(nbSlots);
                nbSlotsProp.setItem(item);
                properties.add(nbSlotsProp);
                dbHelper.getItemPropertyDao().create(nbSlotsProp);

                Property lvlIntProp = new Property();
                lvlIntProp.setKey("lvlInt");
                lvlIntProp.setValue(lvlInt);
                lvlIntProp.setItem(item);
                properties.add(lvlIntProp);
                dbHelper.getItemPropertyDao().create(lvlIntProp);


                item.setProperties(properties);
                dbHelper.getItemDao().create(item);


            }
            in.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public static void populateTrophies(DatabaseHelper dbHelper) {
        try {
            InputStream input = dbHelper.getContext().getApplicationContext()
                    .getAssets().open("trophies.txt");


            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer stTok = new StringTokenizer(line, "|");

                int id = Integer.parseInt(stTok.nextToken());
                String name = stTok.nextToken();
                String description = stTok.nextToken();
                int gameId = Integer.parseInt(stTok.nextToken());
                String trophyLevel = stTok.nextToken();
                String trophy_value = stTok.nextToken();


                Item trophy = new Item();
                trophy.setId(id);
                trophy.setName(name);
                trophy.setDescription(description);
                trophy.setAcquired(false);
                trophy.setGame(dbHelper.getGameDao().queryForId(gameId));
                trophy.setType(dbHelper.getTypeDao().queryForId(TypeConstant.Trophy));

                Collection<Property> properties = new ArrayList<>();

                Property trophyLvlProp = new Property();
                trophyLvlProp.setKey(TypeConstant.TROPHY_LVL);
                trophyLvlProp.setValue(trophyLevel);
                trophyLvlProp.setItem(trophy);
                dbHelper.getItemPropertyDao().create(trophyLvlProp);
                properties.add(trophyLvlProp);


                Property trophyValueProp = new Property();
                trophyValueProp.setKey(TypeConstant.TROPHY_VALUE);
                trophyValueProp.setValue(trophy_value);
                trophyValueProp.setItem(trophy);
                properties.add(trophyValueProp);
                dbHelper.getItemPropertyDao().create(trophyValueProp);


                trophy.setProperties(properties);
                dbHelper.getItemDao().create(trophy);


            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    //id|name|game|type|ngHealth|ngSouls|ng1Health|ng1Souls|ng6Health|ng6Souls
    public static void populateCharacters(DatabaseHelper dbHelper) {

        try {
            InputStream input = dbHelper.getContext().getApplicationContext()
                    .getAssets().open("characters.txt");

            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = in.readLine()) != null) {
                StringTokenizer stTok = new StringTokenizer(line, "|");

                int id = Integer.parseInt(stTok.nextToken());
                String name = stTok.nextToken();
                int gameId = Integer.parseInt(stTok.nextToken());
                int typeId = Integer.parseInt(stTok.nextToken());
                String ngHealth = stTok.nextToken();
                String ngSouls = stTok.nextToken();
                String ng1Health = stTok.nextToken();
                String ng1Souls = stTok.nextToken();
                String ng6Health = stTok.nextToken();
                String ng6Souls = stTok.nextToken();

                Character character = new Character();
                character.setId(id);
                character.setName(name);
                character.setGame(dbHelper.getGameDao().queryForId(gameId));
                character.setType(dbHelper.getTypeDao().queryForId(typeId));

                Collection<Property> properties = new ArrayList<>();


                if (!ngHealth.equals("null")) {
                    Property ngHealthProp = new Property();
                    ngHealthProp.setKey("ngHealth");
                    ngHealthProp.setValue(ngHealth);
                    ngHealthProp.setCharacter(character);
                    properties.add(ngHealthProp);
                    dbHelper.getItemPropertyDao().create(ngHealthProp);
                }

                if (!ngSouls.equals("null")) {
                    Property ngSoulsProp = new Property();
                    ngSoulsProp.setKey("ngSouls");
                    ngSoulsProp.setValue(ngSouls);
                    ngSoulsProp.setCharacter(character);
                    properties.add(ngSoulsProp);
                    dbHelper.getItemPropertyDao().create(ngSoulsProp);
                }

                if (!ng1Health.equals("null")) {
                    Property ng1HealthProp = new Property();
                    ng1HealthProp.setKey("ng1Health");
                    ng1HealthProp.setValue(ng1Health);
                    ng1HealthProp.setCharacter(character);
                    properties.add(ng1HealthProp);
                    dbHelper.getItemPropertyDao().create(ng1HealthProp);
                }

                if (!ng1Souls.equals("null")) {
                    Property ng1SoulsProp = new Property();
                    ng1SoulsProp.setKey("ng1Souls");
                    ng1SoulsProp.setValue(ng1Souls);
                    ng1SoulsProp.setCharacter(character);
                    properties.add(ng1SoulsProp);
                    dbHelper.getItemPropertyDao().create(ng1SoulsProp);
                }

                if (!ng6Health.equals("null")) {
                    Property ng6HealthProp = new Property();
                    ng6HealthProp.setKey("ng6Health");
                    ng6HealthProp.setValue(ng6Health);
                    ng6HealthProp.setCharacter(character);
                    properties.add(ng6HealthProp);
                    dbHelper.getItemPropertyDao().create(ng6HealthProp);
                }

                if (!ng6Souls.equals("null")) {
                    Property ng6SoulsProp = new Property();
                    ng6SoulsProp.setKey("ng6Souls");
                    ng6SoulsProp.setValue(ng6Souls);
                    ng6SoulsProp.setCharacter(character);
                    properties.add(ng6SoulsProp);
                    dbHelper.getItemPropertyDao().create(ng6SoulsProp);
                }

                character.setProperties(properties);
                dbHelper.getCharacterDao().create(character);


            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }
}