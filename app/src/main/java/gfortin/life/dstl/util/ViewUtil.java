package gfortin.life.dstl.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.bean.CharacterItemBean;
import gfortin.life.dstl.model.Location;

/**
 * Created by guillaume on 20/08/17.
 */

public class ViewUtil {

    public static View createHorzontalDivider(Context context, int color){
        View divider = new View(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,2);
        lp.setMargins(0,20,0,20);
        divider.setLayoutParams(lp);
        divider.setBackgroundColor(context.getResources().getColor(color));
        return divider;
    }

    public static void showListCharacter(List<CharacterItemBean> characterItemBeanList, View view, List<Location> locations){
        LinearLayout sorcery_main_layout = (LinearLayout) view.findViewById(R.id.sorcery_main_layout);


        if(characterItemBeanList.size()>0 || locations.size()>0) {

            sorcery_main_layout.addView(ViewUtil.createHorzontalDivider(view.getContext(), R.color.white));

            TextView tvTitle = new TextView(view.getContext());
            tvTitle.setText(view.getResources().getString(R.string.availability));
            tvTitle.setPadding(0, 10, 0, 10);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setTextSize(20);
            sorcery_main_layout.addView(tvTitle);

        }

        if(characterItemBeanList.size()>0){
            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2);
            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT, 1);

            LinearLayout llCharacterItemBeanTitle = new LinearLayout(view.getContext());
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2);
            param.setMargins(0,10,0,50);

            llCharacterItemBeanTitle.setLayoutParams(param);
            sorcery_main_layout.addView(llCharacterItemBeanTitle);

            TextView tvSellerNameTitle = new TextView(view.getContext());
            tvSellerNameTitle.setText(view.getResources().getString(R.string.sellerName));
            tvSellerNameTitle.setLayoutParams(param2);
            llCharacterItemBeanTitle.addView(tvSellerNameTitle);

            TextView tvCostTitle = new TextView(view.getContext());
            tvCostTitle.setText(view.getResources().getString(R.string.cost));
            tvCostTitle.setLayoutParams(param2);
            llCharacterItemBeanTitle.addView(tvCostTitle);

            for (CharacterItemBean characterItemBean : characterItemBeanList) {
                LinearLayout llCharacterItemBean = new LinearLayout(view.getContext());
                llCharacterItemBean.setLayoutParams(param1);
                sorcery_main_layout.addView(llCharacterItemBean);

                TextView tvSellerName = new TextView(view.getContext());
                tvSellerName.setText(view.getResources().getIdentifier(characterItemBean.getCharacter().getName(),"string", view.getContext().getPackageName()));
                tvSellerName.setLayoutParams(param2);
                llCharacterItemBean.addView(tvSellerName);

                TextView tvCost = new TextView(view.getContext());
                tvCost.setText(""+characterItemBean.getCost());
                tvCost.setLayoutParams(param2);
                llCharacterItemBean.addView(tvCost);
            }

            showListLocation(locations, characterItemBeanList, view, sorcery_main_layout);

            sorcery_main_layout.addView(ViewUtil.createHorzontalDivider(view.getContext(),R.color.white));
        }

    }

    public static void showListLocation(List<Location> locations, List<CharacterItemBean> characterItemBeanList, View view, LinearLayout sorcery_main_layout){
        if(characterItemBeanList.size()>0 && locations.size()>0){
            TextView tvTitle = new TextView(view.getContext());
            tvTitle.setText(view.getResources().getString(R.string.or));
            tvTitle.setAllCaps(true);
            tvTitle.setPadding(0, 10, 0, 10);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setTextSize(15);
            sorcery_main_layout.addView(tvTitle);
        }

        if(locations.size()>0){
            for (Location location: locations) {
                TextView tvLocation = new TextView(view.getContext());
                tvLocation.setText(view.getResources().getIdentifier(location.getName(), "string",view.getContext().getPackageName()));
            }
        }

    }
}
