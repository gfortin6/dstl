package gfortin.life.dstl.services;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gfortin.life.dstl.bean.CharacterItemBean;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.CharacterItemJunction;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemItemJunction;

/**
 * Created by guillaume on 20/08/17.
 */

public class ItemService {

    //src many-to-many
    //https://github.com/j256/ormlite-jdbc/blob/master/src/test/java/com/j256/ormlite/examples/manytomany/ManyToManyMain.java

    public static List<Item> getTrophyForItem(Item item, DatabaseHelper dbHelper) throws SQLException {
        QueryBuilder<ItemItemJunction, Integer> itemTrophyQb = dbHelper.getItemTrophyJunctionDao().queryBuilder();
        // just select the post-id field
        itemTrophyQb.selectColumns(ItemItemJunction.ITEM2_ID_FIELD_NAME);
        SelectArg userSelectArg = new SelectArg();
        // you could also just pass in user1 here
        itemTrophyQb.where().eq(ItemItemJunction.ITEM_ID_FIELD_NAME, userSelectArg);
        // build our outer query for Post objects
        QueryBuilder<Item, Integer> trophyQb = dbHelper.getItemDao().queryBuilder();
        // where the id matches in the post-id from the inner query
        trophyQb.where().in(Item.ID_FIELD_NAME, itemTrophyQb);
        PreparedQuery<Item> trophyForItemQuery = trophyQb.prepare();
        trophyForItemQuery.setArgumentHolderValue(0, item);
        return dbHelper.getItemDao().query(trophyForItemQuery);
    }

    public static List<Item> getItemsByTrophy(Item trophy, DatabaseHelper dbHelper) throws SQLException {
        QueryBuilder<ItemItemJunction, Integer> itemTrophyQb = dbHelper.getItemTrophyJunctionDao().queryBuilder();
        // just select the post-id field
        itemTrophyQb.selectColumns(ItemItemJunction.ITEM_ID_FIELD_NAME);
        SelectArg userSelectArg = new SelectArg();
        // you could also just pass in user1 here
        itemTrophyQb.where().eq(ItemItemJunction.ITEM2_ID_FIELD_NAME, userSelectArg);
        // build our outer query for Post objects
        QueryBuilder<Item, Integer> queryBuilder = dbHelper.getItemDao().queryBuilder();
        // where the id matches in the post-id from the inner query
        queryBuilder.where().in(Item.ID_FIELD_NAME, itemTrophyQb);
        PreparedQuery<Item> query = queryBuilder.prepare();
        query.setArgumentHolderValue(0, trophy);
        return dbHelper.getItemDao().query(query);
    }

    public static List<CharacterItemBean> getCharacterForItem(Item item, DatabaseHelper dbHelper) throws SQLException {

        QueryBuilder<Item, Integer> itemQb = dbHelper.getItemDao().queryBuilder();
        QueryBuilder<CharacterItemJunction, Integer> characterItemJunctionQueryBuilder = dbHelper.getCharacterItemDao().queryBuilder();
        itemQb.where().eq(Item.ID_FIELD_NAME, item.getId());
// join with the order query
        characterItemJunctionQueryBuilder.join(itemQb);
        List<CharacterItemJunction> characterItemJunctionList = characterItemJunctionQueryBuilder.query();

// start the order statement query
        QueryBuilder<Character, Integer> characterQb = dbHelper.getCharacterDao().queryBuilder();
// join with the order-header query
        characterQb.join(characterItemJunctionQueryBuilder);
        List<Character> characterList = characterQb.query();

        List<CharacterItemBean> characterItemBeanList = new ArrayList<CharacterItemBean>();

        if (characterItemJunctionList.size() == characterList.size()) {
            Iterator<Character> characterIterator = characterList.iterator();
            int index = 0;
            while (characterIterator.hasNext()) {
                Character character = characterIterator.next();
                CharacterItemBean characterItemBean = new CharacterItemBean(item, character, characterItemJunctionList.get(index).getCost());
                characterItemBeanList.add(characterItemBean);
                index++;
            }
        }else{
            throw new RuntimeException();
        }
        return characterItemBeanList;
    }

}
