package gfortin.life.dstl.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.adapter.ItemRecyclerViewAdapter;
import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.Property;
import gfortin.life.dstl.services.ItemService;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrophyFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TrophyFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private Bundle bundle;
    private Item trophy;
    private List<Item> itemList;
    private int mColumnCount = 1;
    private ItemFragment.OnListFragmentInteractionListener mListener;

    public TrophyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dbHelper = DatabaseHelper.getInstance(getContext());
        bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_trophy, container, false);


        try {
            int trophyId = bundle.getInt("trophyId");
            trophy = dbHelper.getItemDao().queryForId(trophyId);
            populateFields(view);
        } catch (Exception e) {
            throw new RuntimeException();
        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemFragment.OnListFragmentInteractionListener) {
            mListener = (ItemFragment.OnListFragmentInteractionListener) context;
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

    private void populateFields(View view) throws SQLException {

        for (Property property : trophy.getProperties()) {
            if (property.getKey().equals(TypeConstant.TROPHY_LVL)) {
                ImageView image = (ImageView) view.findViewById(R.id.trophy_img);
                image.setImageResource(getResources().getIdentifier(property.getValue().toString(), "drawable", getActivity().getPackageName()));
            }
        }


        TextView name = (TextView) view.findViewById(R.id.trophy_name);
        name.setText(getResources().getIdentifier(trophy.getName(), "string", getActivity().getPackageName()));

        TextView description = (TextView) view.findViewById(R.id.trophy_desc);
        description.setText(getResources().getIdentifier(trophy.getDescription(), "string", getActivity().getPackageName()));

        setAndSaveIsAcquiredCheckbox(view);

        itemList = ItemService.getItemsByTrophy(trophy, dbHelper);
        //  LinearLayout trophiesListItem = (LinearLayout) view.findViewById(R.id.trophyListItem);

        List<Item> itemsToDiscover = new ArrayList();
        List<Item> itemsAcquired = new ArrayList();

        for (final Item item : itemList) {
            if (item.isAcquired()) {
                itemsAcquired.add(item);
            } else {
                itemsToDiscover.add(item);
            }
        }
        RecyclerView listToDiscover = (RecyclerView) view.findViewById(R.id.listItemsToDiscover);
        RecyclerView listAcquired = (RecyclerView) view.findViewById(R.id.listAcquiredItems);
        Context context = view.getContext();
        if (mColumnCount <= 1) {
            listToDiscover.setLayoutManager(new LinearLayoutManager(context));
            listAcquired.setLayoutManager(new LinearLayoutManager(context));
        } else {
            listToDiscover.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            listAcquired.setLayoutManager(new GridLayoutManager(context, mColumnCount));

        }
        if (itemsToDiscover.size() > 0) {
            listToDiscover.setAdapter(new ItemRecyclerViewAdapter(itemsToDiscover, mListener, getContext(), getActivity().getSupportFragmentManager(), getResources().getBoolean(R.bool.twoPaneMode)));
        } else {
            view.findViewById(R.id.tvToDiscover).setVisibility(View.GONE);
            view.findViewById(R.id.viewToDiscover).setVisibility(View.GONE);
            listToDiscover.setVisibility(View.GONE);
        }
        if (itemsAcquired.size() > 0) {
            listAcquired.setAdapter(new ItemRecyclerViewAdapter(itemsAcquired, mListener, getContext(), getActivity().getSupportFragmentManager(), getResources().getBoolean(R.bool.twoPaneMode)));
        } else {
            view.findViewById(R.id.tvAcquired).setVisibility(View.GONE);
            view.findViewById(R.id.viewAcquired).setVisibility(View.GONE);
            listToDiscover.setVisibility(View.GONE);
        }


       /* TextView tv = new TextView(getContext());
        tv.setText(getResources().getIdentifier(item.getName(), "string", getContext().getPackageName()));
        tv.setTextSize(25);
        tv.setPadding(0, 25, 0, 25);

        tv.setCompoundDrawablesWithIntrinsicBounds(getResources().getIdentifier(item.getName() + "_small", "drawable", getContext().getPackageName()), 0, 0, 0);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtil.createNewFragment(new SorceryFragment(), getActivity().getSupportFragmentManager(), "itemId", item.getId(), getResources().getBoolean(R.bool.twoPaneMode));
            }
        });
        trophiesListItem.addView(tv);

        trophiesListItem.addView(ViewUtil.createDivider(getContext(), R.color.orange));*/

    }

    private void setAndSaveIsAcquiredCheckbox(View view) throws SQLException {
        CheckBox isAcquiredChk = (CheckBox) view.findViewById(R.id.isAcquiredChk);
        isAcquiredChk.setChecked(trophy.isAcquired());
        isAcquiredChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setNegativeButton(android.R.string.no, null);
                builder.setCancelable(false);

                if (isChecked) {
                    builder.setMessage(String.format(getResources().getString(R.string.confirmAcquiredTrophy), getResources().getString(getResources().getIdentifier(trophy.getName(), "string", getContext().getPackageName()))));
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            try {
                                trophy.setAcquired(isChecked);
                                dbHelper.getItemDao().createOrUpdate(trophy);
                                for (Item item : itemList) {
                                    if (!item.isAcquired()) {
                                        item.setAcquired(true);
                                        dbHelper.getItemDao().createOrUpdate(item);
                                    }
                                }
                            } catch (Exception e) {
                                throw new RuntimeException();
                            }
                        }
                    });

                    builder.show();

                } else {
                    builder.setMessage(String.format(getResources().getString(R.string.confirmUncheckTrophy), getResources().getString(getResources().getIdentifier(trophy.getName(), "string", getContext().getPackageName())), getResources().getString(R.string.sorceries)));
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            try {
                                trophy.setAcquired(isChecked);
                                dbHelper.getItemDao().createOrUpdate(trophy);

                                for (Item item : itemList) {
                                    item.setAcquired(false);
                                    dbHelper.getItemDao().createOrUpdate(item);
                                }
                            } catch (Exception e) {
                                throw new RuntimeException();
                            }

                        }
                    });
                    builder.show();
                }

            }
        });

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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Item item);
    }
}