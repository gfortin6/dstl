package gfortin.life.dstl.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemItemJunction;
import gfortin.life.dstl.model.Property;
import gfortin.life.dstl.model.ItemPropertyJunction;
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
            List<ItemItemJunction> lastInsertedItemItemJunction = dbHelper.getItemTrophyJunctionDao().query(dbHelper.getItemTrophyJunctionDao().queryBuilder().orderBy("id", false).limit(1L).prepare());
            int index = (lastInsertedItemItemJunction.size() != 0) ? lastInsertedItemItemJunction.get(0).getId() : 0;
            while ((line = in.readLine()) != null) {
                index++;


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
                item.setName(name);            //Assert.assertEquals();

                item.setDescription(description);
                item.setAttackType(dbHelper.getTypeDao().queryForId(Integer.parseInt(attackTypeId)));
                item.setType(dbHelper.getTypeDao().queryForId(TypeConstant.Spells));
                item.setSubtype(dbHelper.getTypeDao().queryForId(TypeConstant.Sorceries));
                dbHelper.getItemDao().create(item);


                ItemItemJunction itemItemJunction = new ItemItemJunction();
                itemItemJunction.setItem(item);
                itemItemJunction.setItem2(dbHelper.getItemDao().queryForId(Integer.parseInt(trophyId)));
                dbHelper.getItemTrophyJunctionDao().create(itemItemJunction);


                Property nbUsesProp = new Property();
                nbUsesProp.setKey("nbUses");
                nbUsesProp.setValue(nbUses);
                dbHelper.getItemPropertyDao().create(nbUsesProp);

                ItemPropertyJunction nbUsesPropJunction = new ItemPropertyJunction();
                nbUsesPropJunction.setItem(item);
                nbUsesPropJunction.setProperty(nbUsesProp);
                dbHelper.getItemPropertyJonctionDao().create(nbUsesPropJunction);


                Property nbSlotsProp = new Property();
                nbSlotsProp.setKey("nbSlots");
                nbSlotsProp.setValue(nbSlots);
                dbHelper.getItemPropertyDao().create(nbSlotsProp);

                ItemPropertyJunction nbSlotsPropJunction = new ItemPropertyJunction();
                nbSlotsPropJunction.setItem(item);
                nbSlotsPropJunction.setProperty(nbSlotsProp);
                dbHelper.getItemPropertyJonctionDao().create(nbSlotsPropJunction);


                Property lvlIntProp = new Property();
                lvlIntProp.setKey("lvlInt");
                lvlIntProp.setValue(lvlInt);
                dbHelper.getItemPropertyDao().create(lvlIntProp);

                ItemPropertyJunction lvlIntPropJunction = new ItemPropertyJunction();
                lvlIntPropJunction.setItem(item);
                lvlIntPropJunction.setProperty(lvlIntProp);
                dbHelper.getItemPropertyJonctionDao().create(lvlIntPropJunction);

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
                dbHelper.getItemDao().create(trophy);

                Property trophyLvlProp = new Property();
                trophyLvlProp.setKey("trophyLevel");
                trophyLvlProp.setValue(trophyLevel);
                dbHelper.getItemPropertyDao().create(trophyLvlProp);

                ItemPropertyJunction trophyLvlPropJunction = new ItemPropertyJunction();
                trophyLvlPropJunction.setItem(trophy);
                trophyLvlPropJunction.setProperty(trophyLvlProp);
                dbHelper.getItemPropertyJonctionDao().create(trophyLvlPropJunction);

                Property trophyValueProp = new Property();
                trophyValueProp.setKey("trophyValue");
                trophyValueProp.setValue(trophy_value);
                dbHelper.getItemPropertyDao().create(trophyValueProp);

                ItemPropertyJunction trophyValuePropJunction = new ItemPropertyJunction();
                trophyValuePropJunction.setItem(trophy);
                trophyValuePropJunction.setProperty(trophyValueProp);
                dbHelper.getItemPropertyJonctionDao().create(trophyValuePropJunction);
            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    //id|name|game|type|ngHealth|ngSouls|ng1Health|ng1Souls|ng6Health|ng6Souls
  /*  public static void populateCharacters(DatabaseHelper dbHelper){

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
                dbHelper.getCharacterDao().create(character);

                if(ngHealth.equals("null")){
                    Property property = new Property();
                    property.setKey("ngHealth");
                    property.setValue(ngHealth);
                    dbHelper.getItemPropertyDao().create(property);

                    CharacterPropertyJunction characterPropertyJunction = new CharacterPropertyJunction();
                    characterPropertyJunction.setCharacter(character);
                    characterPropertyJunction.setProperty(property);
                    dbHelper.getCharacterPropertyJunctionDao().create(characterPropertyJunction);
                }

                if(ngSouls.equals("null")){
                    Property property = new Property();
                    property.setKey("ngSouls");
                    property.setValue(ngSouls);
                    dbHelper.getItemPropertyDao().create(property);

                    CharacterPropertyJunction characterPropertyJunction = new CharacterPropertyJunction();
                    characterPropertyJunction.setCharacter(character);
                    characterPropertyJunction.setProperty(property);
                    dbHelper.getCharacterPropertyJunctionDao().create(characterPropertyJunction);
                }

                if(ng1Health.equals("null")){
                    Property property = new Property();
                    property.setKey("ng1Health");
                    property.setValue(ng1Health);
                    dbHelper.getItemPropertyDao().create(property);

                    CharacterPropertyJunction characterPropertyJunction = new CharacterPropertyJunction();
                    characterPropertyJunction.setCharacter(character);
                    characterPropertyJunction.setProperty(property);
                    dbHelper.getCharacterPropertyJunctionDao().create(characterPropertyJunction);
                }

                if(ng1Souls.equals("null")){
                    Property property = new Property();
                    property.setKey("ng1Souls");
                    property.setValue(ng1Souls);
                    dbHelper.getItemPropertyDao().create(property);

                    CharacterPropertyJunction characterPropertyJunction = new CharacterPropertyJunction();
                    characterPropertyJunction.setCharacter(character);
                    characterPropertyJunction.setProperty(property);
                    dbHelper.getCharacterPropertyJunctionDao().create(characterPropertyJunction);
                }

                if(ng6Health.equals("null")){
                    Property property = new Property();
                    property.setKey("ng6Health");
                    property.setValue(ng6Health);
                    dbHelper.getItemPropertyDao().create(property);

                    CharacterPropertyJunction characterPropertyJunction = new CharacterPropertyJunction();
                    characterPropertyJunction.setCharacter(character);
                    characterPropertyJunction.setProperty(property);
                    dbHelper.getCharacterPropertyJunctionDao().create(characterPropertyJunction);
                }

                if(ng6Souls.equals("null")){
                    Property property = new Property();
                    property.setKey("ng6Souls");
                    property.setValue(ng6Souls);
                    dbHelper.getItemPropertyDao().create(property);

                    CharacterPropertyJunction characterPropertyJunction = new CharacterPropertyJunction();
                    characterPropertyJunction.setCharacter(character);
                    characterPropertyJunction.setProperty(property);
                    dbHelper.getCharacterPropertyJunctionDao().create(characterPropertyJunction);
                }

            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }*/
}