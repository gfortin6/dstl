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
import gfortin.life.dstl.model.Category;
import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.Location;
import gfortin.life.dstl.util.ApplicationData;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "dstl.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper helper = null;
    private static boolean loadDatabaseFromCacheFile = false;
    private Context context;
    private Dao<Item, String> itemDao = null;
    private Dao<Character, String> characterDao = null;
    private Dao<Location, String> locationDao = null;
    private Dao<Category, String> categoryDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        //super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context) {
        synchronized (DatabaseHelper.class) {
            if (DatabaseHelper.helper == null) {
                //DatabaseHelper.helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
                DatabaseHelper.helper = new DatabaseHelper(context);
            }
        }
        return DatabaseHelper.helper;
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        Log.e("DatabaseHelper", "onCreate");
        try {
            Class<?>[] modelClasses = ApplicationData.getModelclasses();

            for (int i = 0; i < modelClasses.length; i++) {
                TableUtils.createTable(connectionSource, modelClasses[i]);
            }
            PopulateDb.populateItem(this);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.e("DatabaseHelper", "onUpgrade");

        try {
            Class<?>[] modelClasses = ApplicationData.getModelclasses();

            for (int i = 0; i < modelClasses.length; i++) {
                TableUtils.dropTable(connectionSource, modelClasses[i], true);
            }
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*if (oldVersion < 8) {
            try {
                TableUtils.clearTable(getConnectionSource(), Item.class);
                PopulateDb.populateItem(this);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/
    }

    /*Item*/
    public Dao<Item, String> getItemDao() throws SQLException {
        if (itemDao == null)
            itemDao = getDao(Item.class);
        return itemDao;
    }
    /*Category*/
    public Dao<Category, String> getCategoryDao() throws SQLException {
        if (categoryDao == null)
            categoryDao = getDao(Category.class);
        return categoryDao;
    }

    /*Item*/
    public Dao<Character, String> getCharacterDao() throws SQLException {
        if (characterDao == null)
            characterDao = getDao(Character.class);
        return characterDao;
    }
    /*Category*/
    public Dao<Location, String> getLocationDao() throws SQLException {
        if (locationDao == null)
            locationDao = getDao(Location.class);
        return locationDao;
    }

    /**
     * @return the context
     */
    public Context getContext() {
        return context;
    }

}
