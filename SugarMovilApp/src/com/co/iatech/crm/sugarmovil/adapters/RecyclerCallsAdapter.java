package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.Llamada;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerCallsAdapter extends RecyclerView.Adapter<RecyclerCallsAdapter.ViewHolder> {


    /**
     * Debug.
     */
    private static final String TAG = "RecyclerCallsAdapter";

    /**
     * Member Variables.
     */
    private Context mContext;
    private List<Llamada> mDataset;
    private List<Llamada> mVisibleDataset;

    public RecyclerCallsAdapter(Context context, List<Llamada> myDataset) {
        mContext = context;
        mDataset = myDataset;
        mVisibleDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calls_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Llamada llamada = mVisibleDataset.get(position);

        // Nombre cuenta
        holder.mTextViewNombre.setText(llamada.getName());

        // Eventos
        holder.mItemLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	ActivitiesMediator.getInstance().setActualID(llamada.getId());
            	ActivitiesMediator.getInstance().showActivity(mContext,Modules.CALLS);
            }
        });

        holder.itemView.setTag(llamada);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVisibleDataset.size();
    }

    public void flushFilter() {
        mVisibleDataset = new ArrayList<Llamada>();
        mVisibleDataset.addAll(mDataset);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        mVisibleDataset = new ArrayList<Llamada>();
        for (Llamada item : mDataset) {
            if (item.getName().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItemLlamada;
        public TextView mTextViewNombre;

        public ViewHolder(View v) {
            super(v);
            mItemLlamada = (LinearLayout) v.findViewById(R.id.item_calls);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_llamada);
        }
    }
}
