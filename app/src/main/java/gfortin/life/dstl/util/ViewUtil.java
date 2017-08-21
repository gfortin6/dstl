package gfortin.life.dstl.util;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import gfortin.life.dstl.R;

/**
 * Created by guillaume on 20/08/17.
 */

public class ViewUtil {

    public static View createDivider(Context context, int color){
        View divider = new View(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,2);
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(context.getResources().getColor(color));
        return divider;
    }
}
