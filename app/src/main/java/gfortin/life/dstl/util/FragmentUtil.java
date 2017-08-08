package gfortin.life.dstl.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import gfortin.life.dstl.R;
import gfortin.life.dstl.fragment.ItemFragment;
import gfortin.life.dstl.model.Item;

/**
 * Created by guillaume on 8/7/2017.
 */

public class FragmentUtil {

    public static void createNewMenuItemFragment(Fragment fragment, FragmentManager fragmentManager, String bundleName, MenuItem item, int fragmentContainer){
        Bundle bundle = new Bundle();
        bundle.putInt(bundleName,item.getItemId());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, fragment);
        fragment.setArguments(bundle);
        fragmentTransaction.commit();

    }

    public static void createNewItemFragment(Fragment fragment, FragmentManager fragmentManager, String bundleName, Item item, int fragmentContainer){
        Bundle bundle = new Bundle();
        bundle.putInt(bundleName,item.getId());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer, fragment);
        fragment.setArguments(bundle);
        fragmentTransaction.commit();

    }
}
