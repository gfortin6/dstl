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
import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemProperty;
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
        itemName.setText(getResources().getIdentifier(item.getName(), "string", getActivity().getPackageName()));

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


        List<ItemProperty> itemProperties = ItemService.getItemPropertyForItem(item, dbHelper);
        for (ItemProperty property : itemProperties) {
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
}
