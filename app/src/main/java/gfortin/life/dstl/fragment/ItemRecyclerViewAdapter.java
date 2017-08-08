package gfortin.life.dstl.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import gfortin.life.dstl.R;
import gfortin.life.dstl.constants.TypeConstant;
import gfortin.life.dstl.fragment.ItemFragment.OnListFragmentInteractionListener;
import gfortin.life.dstl.holder.ViewHolder;
import gfortin.life.dstl.model.Item;
import gfortin.life.dstl.util.FragmentUtil;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Item} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<Item> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;
    private FragmentManager fragmentManager;


    public ItemRecyclerViewAdapter(List<Item> items, OnListFragmentInteractionListener listener, Context context, FragmentManager fragmentManager) {
        mValues = items;
        mListener = listener;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mItemNameView.setText( context.getResources().getIdentifier(mValues.get(position).getName(),"string",context.getPackageName()));
        Integer imgResource = context.getResources().getIdentifier(mValues.get(position).getName(),"drawable",context.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgResource);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 125, 125, true);
        holder.mItemImageView.setImageBitmap(resized);
                //setText(mValues.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Bundle bundle = new Bundle();
                bundle.putInt("itemId", holder.mItem.getId());
                Fragment detailFragment = null;*/
                if (holder.mItem.getSubtype().getId() == TypeConstant.Sorceries){
                    FragmentUtil.createNewItemFragment(new SorceriesFragment(), fragmentManager, "itemId", holder.mItem, R.id.main_fragment_container);
                //    detailFragment = new SorceriesFragment();
                }else if (holder.mItem.getSubtype().getId() == TypeConstant.Miracles){

                }
               // Log.d("ITEM", holder.mItem.toString());
            }
        });
       /* holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                @Override
                public void onClick(View v) {

                }

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
