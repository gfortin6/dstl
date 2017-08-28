package gfortin.life.dstl.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import gfortin.life.dstl.R;
import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.CharacterItemJunction;
import gfortin.life.dstl.model.CharacterPropertyJunction;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemItemJunction;
import gfortin.life.dstl.model.Property;
import gfortin.life.dstl.model.ItemPropertyJunction;
import gfortin.life.dstl.model.Location;
import gfortin.life.dstl.model.Type;
import gfortin.life.dstl.util.ApplicationData;
import gfortin.life.dstl.util.PopulateDb;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "dstl.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper helper = null;
    private static boolean loadDatabaseFromCacheFile = false;
    private Context context;

    private Dao<Character, Integer> characterDao = null;
    private Dao<CharacterItemJunction, Integer> characterItemDao = null;
    private Dao<Game, Integer> gameDao = null;
    private Dao<Item, Integer> itemDao = null;
    private Dao<Property, Integer> itemPropertyDao = null;
    private Dao<Location, Integer> locationDao = null;
    private Dao<Type, Integer> typeDao = null;
    private Dao<ItemItemJunction, Integer> itemTrophyDao = null;
    private Dao<ItemPropertyJunction, Integer> itemPropertyJunctionJDao = null;
    private Dao<CharacterPropertyJunction, Integer> characterPropertyJunctionDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        //super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context) {
        synchronized (DatabaseHelper.class) {
            if (DatabaseHelper.helper == null) {
                DatabaseHelper.helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            }
        }
        return DatabaseHelper.helper;
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            Class<?>[] modelClasses = ApplicationData.getModelclasses();

            for (Class modelClass: modelClasses) {
                TableUtils.createTable(connectionSource, modelClass);
            }
            PopulateDb.populateType(this);
            PopulateDb.populateGames(this);
            PopulateDb.populateTrophies(this);
            PopulateDb.populateSorceries(this);
            PopulateDb.populateCharacters(this);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.d("onUpgrade", "onUpgrade");
        try {
            Class<?>[] modelClasses = ApplicationData.getModelclasses();

            for (int i = 0; i < modelClasses.length; i++) {
                TableUtils.dropTable(connectionSource, modelClasses[i], true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*Character*/
    public Dao<Character, Integer> getCharacterDao() throws SQLException {
        if (characterDao == null)
            characterDao = getDao(Character.class);
        return characterDao;
    }

    /*CharacterItemJunction*/
    public Dao<CharacterItemJunction, Integer> getCharacterItemDao() throws SQLException {
        if (characterItemDao == null)
            characterItemDao = getDao(CharacterItemJunction.class);
        return characterItemDao;
    }

    /*Game*/
    public Dao<Game, Integer> getGameDao() throws SQLException {
        if (gameDao == null)
            gameDao = getDao(Game.class);
        return gameDao;
    }

    /*Item*/
    public Dao<Item, Integer> getItemDao() throws SQLException {
        if (itemDao == null)
            itemDao = getDao(Item.class);
        return itemDao;
    }

    /*Property*/
    public Dao<Property, Integer> getItemPropertyDao() throws SQLException {
        if (itemPropertyDao == null)
            itemPropertyDao = getDao(Property.class);
        return itemPropertyDao;
    }

    /*Location*/
    public Dao<Location, Integer> getLocationDao() throws SQLException {
        if (locationDao == null)
            locationDao = getDao(Location.class);
        return locationDao;
    }

    /*Type*/
    public Dao<Type, Integer> getTypeDao() throws SQLException {
        if (typeDao == null)
            typeDao = getDao(Type.class);
        return typeDao;
    }

    /*Type*/
    public Dao<ItemItemJunction, Integer> getItemTrophyJunctionDao() throws SQLException {
        if (itemTrophyDao == null)
            itemTrophyDao = getDao(ItemItemJunction.class);
        return itemTrophyDao;
    }

    /*Type*/
    public Dao<ItemPropertyJunction, Integer> getItemPropertyJonctionDao() throws SQLException {
        if (itemPropertyJunctionJDao == null)
            itemPropertyJunctionJDao = getDao(ItemPropertyJunction.class);
        return itemPropertyJunctionJDao;
    }

    public Dao<CharacterPropertyJunction, Integer> getCharacterPropertyJunctionDao() throws SQLException{
        if(characterPropertyJunctionDao == null){
            characterPropertyJunctionDao = getDao(CharacterPropertyJunction.class);
        }
        return characterPropertyJunctionDao;
    }


    /**
     * @return the context
     */
    public Context getContext() {
        return context;
    }


/*
    public Item selectItemWithProperties(Item item){
        try{
            QueryBuilder<Item, Integer> itemQb = getItemDao().queryBuilder();
            QueryBuilder<ItemPropertyJunction, Integer> itemPropertyJunctionQb = getItemPropertyJonctionDao().queryBuilder();
            QueryBuilder<Property, Integer> itemPropertyQb = getItemPropertyDao().queryBuilder();

            itemQb.join(itemPropertyJunctionQb).where().e


        }catch (Exception e){
            throw new RuntimeException();
        }
    }*/

}
