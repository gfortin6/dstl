package gfortin.life.dstl.helper;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by guillaume on 8/2/2017.
 */

public class LanguageHelper {
    public static void changeLocale(Resources res, String locale){
        Configuration config;
        config = new Configuration(res.getConfiguration());

        switch (locale){
            case "fr":
                config.setLocale(Locale.FRENCH);
                break;
            default:
                config.setLocale(Locale.ENGLISH);
        }
    }
}
