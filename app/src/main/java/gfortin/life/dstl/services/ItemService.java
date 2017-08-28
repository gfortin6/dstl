package gfortin.life.dstl.services;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.sql.SQLException;
import java.util.List;

import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.Property;
import gfortin.life.dstl.model.ItemPropertyJunction;
import gfortin.life.dstl.model.ItemItemJunction;

/**
 * Created by guillaume on 20/08/17.
 */

public class ItemService {

    public static List<Property> getItemPropertyForItem(Item item, DatabaseHelper dbHelper) throws SQLException {
        QueryBuilder<ItemPropertyJunction, Integer> propertyItemQb = dbHelper.getItemPropertyJonctionDao().queryBuilder();
        // just select the post-id field
        propertyItemQb.selectColumns(ItemPropertyJunction.ITEM_PROPERTY_ID_FIELD_NAME);
        SelectArg userSelectArg = new SelectArg();
        // you could also just pass in user1 here
        propertyItemQb.where().eq(ItemPropertyJunction.ITEM_ID_FIELD_NAME, userSelectArg);
        // build our outer query for Post objects
        QueryBuilder<Property, Integer> itemPropertyQb = dbHelper.getItemPropertyDao().queryBuilder();
        // where the id matches in the post-id from the inner query
        itemPropertyQb.where().in(Item.ID_FIELD_NAME, propertyItemQb);
        PreparedQuery<Property> itemPropertyForItemQuery = itemPropertyQb.prepare();
        itemPropertyForItemQuery.setArgumentHolderValue(0, item);
        return dbHelper.getItemPropertyDao().query(itemPropertyForItemQuery);
    }

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

}
