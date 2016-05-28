package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.Producto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerProductsAdapter extends RecyclerView.Adapter<RecyclerProductsAdapter.ViewHolder> {


    /**
     * Debug.
     */
    private static final String TAG = "RecyclerProductsAdapter";

    /**
     * Member Variables.
     */
    private Context context;
    private List<Producto> mDataset;
    private List<Producto> mVisibleDataset;

    public RecyclerProductsAdapter(Context context, List<Producto> myDataset) {
        context = context;
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
        
        holder.textViewCant.setText("Saldo:  "+producto.getSaldo());
        // Eventos
        holder.mItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Account Activity
        
                ActivitiesMediator.getInstance().showActivity(context,Modules.PRODUCTS, 
                		new ActivityBeanCommunicator(producto.getId(),producto.getName()));
               
                //intentProducto.putExtra(Info.PRODUCTO_CANTIDAD.name(), producto.getSaldo());
            }
        });

        holder.itemView.setTag(producto);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVisibleDataset.size();
    }

    public void flushFilter() {
        mVisibleDataset = new ArrayList<Producto>();
        mVisibleDataset.addAll(mDataset);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        mVisibleDataset = new ArrayList<Producto>();
        for (Producto item : mDataset) {
            if (item.getName().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
            if (item.getId().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
           
        }
        notifyDataSetChanged();
    }


    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItemProduct;
        public TextView mTextViewNombre;
        public TextView textViewCant;

        public ViewHolder(View v) {
            super(v);
            mItemProduct = (LinearLayout) v.findViewById(R.id.item_product);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_producto);
            textViewCant = (TextView) v.findViewById(R.id.cant_producto);
        }
    }
}
