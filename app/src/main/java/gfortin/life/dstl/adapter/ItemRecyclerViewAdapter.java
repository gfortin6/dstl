package gfortin.life.dstl.adapter;

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
import gfortin.life.dstl.fragment.SorceryFragment;
import gfortin.life.dstl.fragment.TrophyFragment;
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
    private boolean isTwoPannels;


    public ItemRecyclerViewAdapter(List<Item> items, OnListFragmentInteractionListener listener, Context context, FragmentManager fragmentManager, boolean isTwoPannels) {
        mValues = items;
        mListener = listener;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.isTwoPannels = isTwoPannels;
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
        Integer imgResource = context.getResources().getIdentifier(mValues.get(position).getName()+"_small","drawable",context.getPackageName());
        holder.mItemImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgResource));
                //setText(mValues.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Bundle bundle = new Bundle();
                bundle.putInt("itemId", holder.mItem.getId());
                Fragment detailFragment = null;*/
               if(holder.mItem.getSubtype() != null){
                   if (holder.mItem.getSubtype().getId() == TypeConstant.Sorceries){
                       FragmentUtil.createNewFragment(new SorceryFragment(), fragmentManager, "itemId", holder.mItem.getId(), isTwoPannels);
                       //    detailFragment = new SorceryFragment();
                   }else if (holder.mItem.getSubtype().getId() == TypeConstant.Miracles){

                   }
               }else{
                   if (holder.mItem.getType().getId() == TypeConstant.Trophy){
                       FragmentUtil.createNewFragment(new TrophyFragment(), fragmentManager, "trophyId", holder.mItem.getId(), isTwoPannels);
                   }
               }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
