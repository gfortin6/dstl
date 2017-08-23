package gfortin.life.dstl.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import gfortin.life.dstl.services.ItemService;
import gfortin.life.dstl.util.FragmentUtil;
import gfortin.life.dstl.util.ViewUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrophyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TrophyFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private Bundle bundle;
    private Item trophy;

    private OnFragmentInteractionListener mListener;

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

    private void populateFields(View view) throws SQLException {

       /* ImageView image = (ImageView) view.findViewById(R.id.trophy_img);
        image.setImageResource(getResources().getIdentifier(trophy.getPath(), "drawable", getActivity().getPackageName()));

        TextView name = (TextView) view.findViewById(R.id.trophy_name);
        name.setText(getResources().getIdentifier(trophy.getName(), "string", getActivity().getPackageName()));

        TextView description = (TextView) view.findViewById(R.id.trophy_desc);
        description.setText(getResources().getIdentifier(trophy.getDescription(), "string", getActivity().getPackageName()));

        setAndSaveIsAcquiredCheckbox(view);

        List<Item> itemList = ItemService.getItemsByTrophy(trophy, dbHelper);
        LinearLayout trophiesListItem = (LinearLayout) view.findViewById(R.id.trophyListItem);
        for (final Item item : itemList) {

            TextView tv = new TextView(getContext());
            tv.setText(getResources().getIdentifier(item.getName(), "string", getContext().getPackageName()));
            tv.setTextSize(25);
            tv.setPadding(0,25,0,25);

            tv.setCompoundDrawablesWithIntrinsicBounds(getResources().getIdentifier(item.getName() + "_small", "drawable", getContext().getPackageName()), 0, 0, 0);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtil.createNewFragment(new SorceryFragment(), getActivity().getSupportFragmentManager(), "itemId", item.getId(), getResources().getBoolean(R.bool.twoPaneMode));
                }
            });
            trophiesListItem.addView(tv);

            trophiesListItem.addView(ViewUtil.createDivider(getContext(),R.color.orange));
        }*/

    }

    private void setAndSaveIsAcquiredCheckbox(View view) throws SQLException {
        CheckBox isAcquiredChk = (CheckBox) view.findViewById(R.id.isAcquiredChk);
        isAcquiredChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                trophy.setAcquired(isChecked);
                try {
                    dbHelper.getItemDao().createOrUpdate(trophy);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        });

        isAcquiredChk.setChecked(trophy.isAcquired());

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
