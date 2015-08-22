package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ProductActivity;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Producto;

public class RecyclerProductsAdapter extends RecyclerView.Adapter<RecyclerProductsAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener{


    /**
     * Debug.
     */
    private static final String TAG = "RecyclerProductsAdapter";

    /**
     * Member Variables.
     */
    private Context mContext;
    private ArrayList<Producto> mDataset;
    private ArrayList<Producto> mVisibleDataset;

    public RecyclerProductsAdapter(Context context, ArrayList<Producto> myDataset) {
        mContext = context;
        mDataset = myDataset;
        mVisibleDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Producto producto = mVisibleDataset.get(position);

        // Nombre producto
        holder.mTextViewNombre.setText(producto.getName());

        // Eventos
        holder.mItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Account Activity
                Intent intentProducto = new Intent(mContext,
                        ProductActivity.class);
                intentProducto.putExtra(Info.ID_PRODUCTO.name(), producto.getId());
                mContext.startActivity(intentProducto);
            }
        });

        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);

        holder.itemView.setTag(producto);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVisibleDataset.size();
    }

    public void flushFilter() {
        mVisibleDataset = new ArrayList<>();
        mVisibleDataset.addAll(mDataset);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        mVisibleDataset = new ArrayList<>();
        for (Producto item : mDataset) {
            if (item.getName().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItemProduct;
        public TextView mTextViewNombre;

        public ViewHolder(View v) {
            super(v);
            mItemProduct = (LinearLayout) v.findViewById(R.id.item_product);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_producto);
        }
    }
}
