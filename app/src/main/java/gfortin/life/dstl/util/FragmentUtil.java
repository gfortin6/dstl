package gfortin.life.dstl.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import gfortin.life.dstl.R;

/**
 * Created by guillaume on 8/7/2017.
 */

public class FragmentUtil {
    public static void createNewFragment(Fragment fragment, FragmentManager fragmentManager, String bundleName, Integer id, boolean isTwoPannels) {
        Bundle bundle = new Bundle();
        bundle.putInt(bundleName, id);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(getViewId(isTwoPannels), fragment);
        fragmentTransaction.addToBackStack(null);
        fragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    public static void refreshFragment(boolean isTwoPannels, FragmentManager fragmentManager) {
        int fragmentId = getViewId(isTwoPannels);
        Fragment currentFragment = fragmentManager.findFragmentById(fragmentId);
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commit();
    }


    private static int getViewId(boolean isTwoPanels) {
        int viewId = 0;
        if (isTwoPanels) {
            viewId = R.id.main_fragment_container;
        } else {
            viewId = R.id.main_fragment_container;
        }
        return viewId;
    }
}
