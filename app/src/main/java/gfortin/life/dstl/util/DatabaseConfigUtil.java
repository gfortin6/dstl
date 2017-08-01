package gfortin.life.dstl.util;


import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import gfortin.life.dstl.model.Category;
import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.Location;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] modelClasses = new Class[] {
            Category.class,
            Item.class,
            Character.class,
            Location.class
    };

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile("ormlite_config.txt", modelClasses);
    }

}
