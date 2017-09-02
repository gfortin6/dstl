package gfortin.life.dstl.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.bean.CharacterItemBean;
import gfortin.life.dstl.constants.TrophyConstant;
import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Character;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.Property;
import gfortin.life.dstl.services.ItemService;
import gfortin.life.dstl.util.FragmentUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SorceryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SorceryFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private Bundle bundle;
    private Item item;
    private CheckBox isAcquiredChk;

    private OnFragmentInteractionListener mListener;

    public SorceryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dbHelper = DatabaseHelper.getInstance(getContext());
        bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_sorcery, container, false);

        try {
            int sorceryId = bundle.getInt("itemId");
            item = dbHelper.getItemDao().queryForId(sorceryId);
            populateFields(item, view);
        } catch (Exception e) {
            throw new RuntimeException();
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void populateFields(Item item, View view) throws SQLException {
        TextView itemName = (TextView) view.findViewById(R.id.sorcery_name);

        itemName.setText(getResources().getIdentifier(item.getName(), "string", getActivity().getPackageName()));/**/

        TextView itemDescription = (TextView) view.findViewById(R.id.sorcery_desc);
        itemDescription.setText(getResources().getIdentifier(item.getDescription(), "string", getActivity().getPackageName()));

        ImageView image = (ImageView) view.findViewById(R.id.sorcery_img);
        image.setImageResource(getResources().getIdentifier(item.getName(), "drawable", getActivity().getPackageName()));

        LinearLayout linearLayoutTrophies = (LinearLayout) view.findViewById(R.id.sorcery_trophy);
        List<Item> trophies = ItemService.getTrophyForItem(item, dbHelper);
        for (final Item trophy : trophies) {
            TextView tv = new TextView(getContext());
            tv.setText(getResources().getIdentifier(trophy.getName(), "string", getActivity().getPackageName()));
            tv.setTextSize(20);
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
            tv.setPadding(10, 0, 0, 0);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtil.createNewFragment(new TrophyFragment(), getActivity().getSupportFragmentManager(), "trophyId", trophy.getId(), getResources().getBoolean(R.bool.twoPaneMode));
                }
            });
            linearLayoutTrophies.addView(tv);
        }

        TextView lvlInt = (TextView) view.findViewById(R.id.sorcery_lvl_int);
        TextView spellUses = (TextView) view.findViewById(R.id.sorcery_spell_uses);
        TextView slotsUsed = (TextView) view.findViewById(R.id.sorcery_slots_used);

        List<CharacterItemBean> characterItemBeanList = ItemService.getCharacterForItem(item,dbHelper);
        showListCharacter(characterItemBeanList, view);


          /*List<Property> itemProperties = ItemService.getPropertyOfItem(item, dbHelper);*/
        for (Property property : item.getProperties()) {
            switch (property.getKey()) {
                case "nbUses":
                    spellUses.setText(property.getValue().toString());
                    break;
                case "nbSlots":
                    slotsUsed.setText(property.getValue().toString());
                    break;
                case "lvlInt":
                    lvlInt.setText(property.getValue().toString());
                    break;
            }
        }

        setAndSaveIsAcquiredCheckbox(view);

    }

    private void setAndSaveIsAcquiredCheckbox(View view) throws SQLException {
        isAcquiredChk = (CheckBox) view.findViewById(R.id.isAcquiredChk);
        isAcquiredChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setAcquired(isChecked);
                try {
                    dbHelper.getItemDao().createOrUpdate(item);
                    long nbToDiscover = dbHelper.getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Spells).and().eq("subType_id", TypeConstant.Sorceries).and().eq("is_acquired", false).countOf();
                    if(nbToDiscover == 0){
                        List<Item> trophies = ItemService.getTrophyForItem(item,dbHelper);
                        Iterator<Item> itemIterator = trophies.iterator();
                        while (itemIterator.hasNext()){
                            Item trophy = itemIterator.next();
                            if(trophy.getName().equals(TrophyConstant.WISDOM_OF_A_SAGE)){
                                trophy.setAcquired(true);
                                dbHelper.getItemDao().update(trophy);
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });

        isAcquiredChk.setChecked(item.isAcquired());

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static void showListCharacter(List<CharacterItemBean> characterItemBeanList, View view){
        if(characterItemBeanList.size()>0) {
            LinearLayout sorcery_main_layout = (LinearLayout) view.findViewById(R.id.sorcery_main_layout);
            TextView tvTitle = new TextView(view.getContext());
            tvTitle.setText(view.getResources().getString(R.string.where_to_find));
            tvTitle.setPadding(0,10,0,10);
            tvTitle.setGravity(View.TEXT_ALIGNMENT_CENTER);
            tvTitle.setTextSize(25);
            sorcery_main_layout.addView(tvTitle);

            LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2);
            LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT, 1);
            LinearLayout.LayoutParams param3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT, 1);

            LinearLayout llCharacterItemBeanTitle = new LinearLayout(view.getContext());
            llCharacterItemBeanTitle.setLayoutParams(param1);
            sorcery_main_layout.addView(llCharacterItemBeanTitle);

            TextView tvSellerNameTitle = new TextView(view.getContext());
            tvSellerNameTitle.setText(view.getResources().getString(R.string.sellerName));
            tvSellerNameTitle.setLayoutParams(param2);
            llCharacterItemBeanTitle.addView(tvSellerNameTitle);

            TextView tvCostTitle = new TextView(view.getContext());
            tvCostTitle.setText(view.getResources().getString(R.string.cost));
            tvCostTitle.setLayoutParams(param3);
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
                tvCost.setLayoutParams(param3);
                llCharacterItemBean.addView(tvCost);
            }
        }

    }
}
