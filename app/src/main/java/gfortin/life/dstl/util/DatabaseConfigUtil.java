package gfortin.life.dstl.util;


import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

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

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    private static Class<?>[] modelClasses = new Class[]{
            Game.class,
            Type.class,
            Item.class,
            Character.class,
            Location.class,
            ItemItemJunction.class,
            CharacterItemJunction.class,
            Property.class,
            ItemPropertyJunction.class,
            CharacterPropertyJunction.class,


    };

    public static void main(String[] args) throws SQLException, IOException {
        //writeConfigFile("ormlite_config.txt", ApplicationData.getModelclasses());
        writeConfigFile("ormlite_config.txt", modelClasses);
    }

}
