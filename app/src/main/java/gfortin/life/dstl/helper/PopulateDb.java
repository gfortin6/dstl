package gfortin.life.dstl.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemProperty;
import gfortin.life.dstl.model.ItemPropertyJunction;
import gfortin.life.dstl.model.ItemTrophy;
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

    public static void populateGame(DatabaseHelper dbHelper) {
        try {
            Game game = new Game();
            game.setName("dark_souls");
            //  Game game = new Game("Dark souls", null, null);
            dbHelper.getGamenDao().create(game);
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
                String type = stTok.nextToken();
                String subType = stTok.nextToken();
                String trophyId = stTok.nextToken();

                Item item = new Item();
                item.setId(id);
                item.setName(name);
                item.setDescription(description);
                item.setAttackType(dbHelper.getTypeDao().queryForId(Integer.parseInt(attackTypeId)));
                item.setType(dbHelper.getTypeDao().queryForId(Integer.parseInt(type)));
                item.setSubtype(dbHelper.getTypeDao().queryForId(Integer.parseInt(subType)));
                dbHelper.getItemDao().create(item);


                ItemTrophy itemTrophy = new ItemTrophy();
                itemTrophy.setItem(item);
                itemTrophy.setTrophy(dbHelper.getTrophyDao().queryForId(Integer.parseInt(trophyId)));

                ItemProperty nbUsesProp = new ItemProperty();
                nbUsesProp.setKey("nbUses");
                nbUsesProp.setValue(nbUses);
                dbHelper.getItemPropertiesDao().create(nbUsesProp);

                ItemPropertyJunction nbUsesPropJunction = new ItemPropertyJunction();
                nbUsesPropJunction.setItem(item);
                nbUsesPropJunction.setItemProperty(nbUsesProp);
                dbHelper.getItemPropertyJonctionDao().create(nbUsesPropJunction);


                ItemProperty nbSlotsProp = new ItemProperty();
                nbSlotsProp.setKey("nbSlots");
                nbSlotsProp.setValue(nbSlots);
                dbHelper.getItemPropertiesDao().create(nbSlotsProp);

                ItemPropertyJunction nbSlotsPropJunction = new ItemPropertyJunction();
                nbUsesPropJunction.setItem(item);
                nbUsesPropJunction.setItemProperty(nbSlotsProp);
                dbHelper.getItemPropertyJonctionDao().create(nbSlotsPropJunction);



                ItemProperty lvlIntProp = new ItemProperty();
                lvlIntProp.setKey("lvlInt");
                lvlIntProp.setValue(lvlInt);
                dbHelper.getItemPropertiesDao().create(lvlIntProp);

                ItemPropertyJunction lvlIntPropJunction = new ItemPropertyJunction();
                nbUsesPropJunction.setItem(item);
                nbUsesPropJunction.setItemProperty(lvlIntProp);
                dbHelper.getItemPropertyJonctionDao().create(lvlIntPropJunction);

            }
            in.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }


}