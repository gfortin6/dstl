package gfortin.life.dstl.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gfortin.life.dstl.R;
import gfortin.life.dstl.model.Item;

/**
 * Created by guillaume on 8/6/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final ImageView mItemImageView;
    public final TextView mItemNameView;
    public Item mItem;

    public ViewHolder(View view) {
        super(view);
        mView = view;
        mItemImageView = (ImageView) view.findViewById(R.id.list_item_image);
        mItemNameView = (TextView) view.findViewById(R.id.list_item_name);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mItemNameView.getText() + "'";
    }
}