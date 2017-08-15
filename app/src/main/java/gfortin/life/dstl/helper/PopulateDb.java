package gfortin.life.dstl.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemProperty;
import gfortin.life.dstl.model.ItemPropertyJunction;
import gfortin.life.dstl.model.ItemTrophyJunction;
import gfortin.life.dstl.model.Trophy;
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
                //public Trophy(int id, Game game, String name, String description, String value, String path, boolean acquired) {

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
            List<ItemTrophyJunction> lastInsertedItemTrophyJunction = dbHelper.getItemTrophyJunctionDao().query(dbHelper.getItemTrophyJunctionDao().queryBuilder().orderBy("id", false).limit(1L).prepare());
            int index = (lastInsertedItemTrophyJunction.size() != 0) ? lastInsertedItemTrophyJunction.get(0).getId():0;
            while ((line = in.readLine()) != null) {
                index ++;


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
                dbHelper.getItemDao().create(item);


                ItemTrophyJunction itemTrophyJunction = new ItemTrophyJunction();
                itemTrophyJunction.setItem(item);
                itemTrophyJunction.setTrophy(dbHelper.getTrophyDao().queryForId(Integer.parseInt(trophyId)));
                dbHelper.getItemTrophyJunctionDao().create(itemTrophyJunction);


                ItemProperty nbUsesProp = new ItemProperty();
                nbUsesProp.setKey("nbUses");
                nbUsesProp.setValue(nbUses);
                dbHelper.getItemPropertyDao().create(nbUsesProp);

                ItemPropertyJunction nbUsesPropJunction = new ItemPropertyJunction();
                nbUsesPropJunction.setItem(item);
                nbUsesPropJunction.setItemProperty(nbUsesProp);
                dbHelper.getItemPropertyJonctionDao().create(nbUsesPropJunction);


                ItemProperty nbSlotsProp = new ItemProperty();
                nbSlotsProp.setKey("nbSlots");
                nbSlotsProp.setValue(nbSlots);
                dbHelper.getItemPropertyDao().create(nbSlotsProp);

                ItemPropertyJunction nbSlotsPropJunction = new ItemPropertyJunction();
                nbSlotsPropJunction.setItem(item);
                nbSlotsPropJunction.setItemProperty(nbSlotsProp);
                dbHelper.getItemPropertyJonctionDao().create(nbSlotsPropJunction);


                ItemProperty lvlIntProp = new ItemProperty();
                lvlIntProp.setKey("lvlInt");
                lvlIntProp.setValue(lvlInt);
                dbHelper.getItemPropertyDao().create(lvlIntProp);

                ItemPropertyJunction lvlIntPropJunction = new ItemPropertyJunction();
                lvlIntPropJunction.setItem(item);
                lvlIntPropJunction.setItemProperty(lvlIntProp);
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
                int trophy_value = Integer.parseInt(stTok.nextToken());


                Trophy trophy = new Trophy();
                trophy.setId(id);
                trophy.setGame(dbHelper.getGameDao().queryForId(gameId));
                trophy.setName(name);
                trophy.setDescription(description);
                trophy.setAcquired(false);
                trophy.setPath(trophyLevel);
                trophy.setValue(trophy_value);

                dbHelper.getTrophyDao().create(trophy);

            }
            in.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

}