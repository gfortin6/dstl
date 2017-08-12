package gfortin.life.dstl.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import java.util.List;

import gfortin.life.dstl.R;
import gfortin.life.dstl.helper.DatabaseHelper;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.model.ItemProperty;
import gfortin.life.dstl.model.ItemPropertyJunction;
import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SorceriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SorceriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseHelper dbHelper;
    private Dao<Item, Integer> itemDao;
    private Dao<ItemProperty, Integer> itemPropertyDao;
    private Bundle bundle;
    private Item item;
    private PreparedQuery<ItemProperty> itemPropertyForItemQuery = null;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SorceriesFragment() {
        // Required empty public constructor
    }

/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dbHelper = DatabaseHelper.getInstance(getContext());
        bundle = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_sorceries, container, false);

        try{
            int sorceryId = bundle.getInt("itemId");
            Item item = dbHelper.getItemDao().queryForId(sorceryId);
            List<ItemProperty> itemProperties = getItemAndProperties(item);
            populateFields(item, itemProperties, view);
        }catch (Exception e){
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

    private void populateFields(Item item, List<ItemProperty> listProperties, View view){
        TextView itemName = (TextView) view.findViewById(R.id.sorcery_name);
        itemName.setText( getResources().getIdentifier(item.getName(),"string",getActivity().getPackageName()));

        TextView itemDescription = (TextView) view.findViewById(R.id.sorcery_desc);
        itemDescription.setText(getResources().getIdentifier(item.getDescription(),"string",getActivity().getPackageName()));

        ImageView image = (ImageView) view.findViewById(R.id.sorcery_img);
        image.setImageResource(getResources().getIdentifier(item.getName(),"drawable",getActivity().getPackageName()));

        TextView lvl_int = (TextView) view.findViewById(R.id.sorceries_lvl_int);
        //lvl_int.setText(item.get);

        TextView spellUses = (TextView) view.findViewById(R.id.sorceries_lvl_int);

        TextView slotsUsed = (TextView) view.findViewById(R.id.sorceries_lvl_int);


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



   private List<ItemProperty> getItemAndProperties(Item item) throws SQLException {
        if (itemPropertyForItemQuery == null) {
            itemPropertyForItemQuery = makeItemPropertyForItemQuery();
        }
        itemPropertyForItemQuery.setArgumentHolderValue(0, item);
        return dbHelper.getItemPropertyDao().query(itemPropertyForItemQuery);
    }

    private PreparedQuery<ItemProperty> makeItemPropertyForItemQuery() throws SQLException {
        // build our inner query for UserPost objects
        QueryBuilder<ItemPropertyJunction, Integer> propertyItemQb = dbHelper.getItemPropertyJonctionDao().queryBuilder();
        // just select the post-id field
        propertyItemQb.selectColumns(ItemPropertyJunction.ITEM_PROPERTY_ID_FIELD_NAME);
        SelectArg userSelectArg = new SelectArg();
        // you could also just pass in user1 here
        propertyItemQb.where().eq(ItemPropertyJunction.ITEM_PROPERTY_ID_FIELD_NAME, userSelectArg);

        // build our outer query for Post objects
        QueryBuilder<ItemProperty, Integer> postQb = dbHelper.getItemPropertyDao().queryBuilder();
        // where the id matches in the post-id from the inner query
        postQb.where().in(ItemProperty.ID_FIELD_NAME, propertyItemQb);
        return postQb.prepare();
    }
}
