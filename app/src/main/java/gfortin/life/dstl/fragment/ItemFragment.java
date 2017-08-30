package gfortin.life.dstl.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.adapter.ItemRecyclerViewAdapter;
import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        // Set the adapter
        Context context = view.getContext();
        RecyclerView listToDiscover = (RecyclerView) view.findViewById(R.id.listItemsToDiscover);
        RecyclerView listAcquired = (RecyclerView) view.findViewById(R.id.listAcquiredItems);
        if (mColumnCount <= 1) {
            listToDiscover.setLayoutManager(new LinearLayoutManager(context));
            listAcquired.setLayoutManager(new LinearLayoutManager(context));
        } else {
            listToDiscover.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            listAcquired.setLayoutManager(new GridLayoutManager(context, mColumnCount));

        }
        try {
            List<Item> itemsToDiscover = null;
            List<Item> itemsAcquired = null;
            switch (getArguments().getInt("itemId")) {
                case R.id.nav_sorceries:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("subType_id", TypeConstant.Sorceries).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("subType_id", TypeConstant.Sorceries).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
                case R.id.nav_miracles:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("subType_id", TypeConstant.Miracles).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("subType_id", TypeConstant.Miracles).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
                case R.id.nav_pyromancies:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("subType_id", TypeConstant.Pyromancies).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("subType_id", TypeConstant.Pyromancies).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
                case R.id.nav_trophies:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Trophy).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Trophy).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
                case R.id.nav_armors:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Armors).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Armors).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
                case R.id.nav_weapons:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Weapons).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Weapons).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
                case R.id.nav_rings:
                    itemsToDiscover = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Rings).and().eq(Item.IS_ACQUIRED_FIELD_NAME, false).query();
                    itemsAcquired = DatabaseHelper.getInstance(getContext()).getItemDao().queryBuilder().where().eq("type_id", TypeConstant.Rings).and().eq(Item.IS_ACQUIRED_FIELD_NAME, true).query();
                    break;
            }

            if (itemsToDiscover.size() > 0) {
                listToDiscover.setAdapter(new ItemRecyclerViewAdapter(itemsToDiscover, mListener, getContext(), getActivity().getSupportFragmentManager(), getResources().getBoolean(R.bool.twoPaneMode)));
                view.findViewById(R.id.tvEmptyListToDiscover).setVisibility(View.GONE);
            }
            if (itemsAcquired.size() > 0) {
                listAcquired.setAdapter(new ItemRecyclerViewAdapter(itemsAcquired, mListener, getContext(), getActivity().getSupportFragmentManager(), getResources().getBoolean(R.bool.twoPaneMode)));
                view.findViewById(R.id.tvEmptyListAcquired).setVisibility(View.GONE);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Item item);
    }
}
