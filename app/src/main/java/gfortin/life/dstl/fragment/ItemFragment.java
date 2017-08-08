package gfortin.life.dstl.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.util.ApplicationData;

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
    private DatabaseHelper dbHelper;
    private Dao<Item, Integer> itemDao;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(getContext());
        try {
            itemDao = dbHelper.getItemDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            try {
                List<Item> items = null;
                switch (getArguments().getInt("itemId")){
                    case R.id.nav_sorceries:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("subType_id", TypeConstant.Sorceries);
                        break;
                    case R.id.nav_miracles:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("subType_id", TypeConstant.Miracles);
                        break;
                    case R.id.nav_pyromancies:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("subType_id", TypeConstant.Pyromancies);
                        break;
                    case R.id.nav_trophies:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("type_id", TypeConstant.Trophy);
                        break;
                    case R.id.nav_armors:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("type_id", TypeConstant.Armors);
                        break;
                    case R.id.nav_weapons:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("type_id", TypeConstant.Weapons);
                        break;
                    case R.id.nav_rings:
                        items = DatabaseHelper.getInstance(getContext()).getItemDao().queryForEq("type_id", TypeConstant.Rings);
                        break;
                }

                recyclerView.setAdapter(new ItemRecyclerViewAdapter(items, mListener, getContext(), getActivity().getSupportFragmentManager()));
            }catch(Exception e){
                throw new RuntimeException();
            }
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
