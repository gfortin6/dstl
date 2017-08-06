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
import gfortin.life.dstl.model.CharacterItemJonction;
import gfortin.life.dstl.model.Game;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemProperty;
import gfortin.life.dstl.model.ItemPropertyJunction;
import gfortin.life.dstl.model.ItemTrophy;
import gfortin.life.dstl.model.Location;
import gfortin.life.dstl.model.Trophy;
import gfortin.life.dstl.model.Type;
import gfortin.life.dstl.util.ApplicationData;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "dstl.db";
    private static final int DATABASE_VERSION = 4;
    private static DatabaseHelper helper = null;
    private static boolean loadDatabaseFromCacheFile = false;
    private Context context;

    private Dao<Character, Integer> characterDao = null;
    private Dao<CharacterItemJonction, Integer> characterItemDao = null;
    private Dao<Game, Integer> gameDao = null;
    private Dao<Item, Integer> itemDao = null;
    private Dao<ItemProperty, Integer> itemPropertyDao = null;
    private Dao<Location, Integer> locationDao = null;
    private Dao<Trophy, Integer> trophyDao = null;
    private Dao<Type, Integer> typeDao = null;
    private Dao<ItemTrophy, Integer> itemTrophyDao = null;
    private Dao<ItemPropertyJunction, Integer> itemPropertyJunctionJDao = null;


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

            for (int i = 0; i < modelClasses.length; i++) {
                TableUtils.createTable(connectionSource, modelClasses[i]);
            }
            PopulateDb.populateType(this);
            PopulateDb.populateGame(this);
            PopulateDb.populateSorceries(this);

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

    /*CharacterItemJonction*/
    public Dao<CharacterItemJonction, Integer> getCharacterItemDao() throws SQLException {
        if (characterItemDao == null)
            characterItemDao = getDao(CharacterItemJonction.class);
        return characterItemDao;
    }

    /*Game*/
    public Dao<Game, Integer> getGamenDao() throws SQLException {
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

    /*ItemProperty*/
    public Dao<ItemProperty, Integer> getItemPropertyDao() throws SQLException {
        if (itemPropertyDao == null)
            itemPropertyDao = getDao(ItemProperty.class);
        return itemPropertyDao;
    }

    /*Location*/
    public Dao<Location, Integer> getLocationDao() throws SQLException {
        if (locationDao == null)
            locationDao = getDao(Location.class);
        return locationDao;
    }

    /*Trophy*/
    public Dao<Trophy, Integer> getTrophyDao() throws SQLException {
        if (trophyDao == null)
            trophyDao = getDao(Trophy.class);
        return trophyDao;
    }

    /*Type*/
    public Dao<Type, Integer> getTypeDao() throws SQLException {
        if (typeDao == null)
            typeDao = getDao(Type.class);
        return typeDao;
    }

    /*Type*/
    public Dao<ItemTrophy, Integer> getItemTrophyDao() throws SQLException {
        if (itemTrophyDao == null)
            itemTrophyDao = getDao(ItemTrophy.class);
        return itemTrophyDao;
    }

    /*Type*/
    public Dao<ItemPropertyJunction, Integer> getItemPropertyJonctionDao() throws SQLException {
        if (itemPropertyJunctionJDao == null)
            itemPropertyJunctionJDao = getDao(ItemPropertyJunction.class);
        return itemPropertyJunctionJDao;
    }



    /**
     * @return the context
     */
    public Context getContext() {
        return context;
    }

}
