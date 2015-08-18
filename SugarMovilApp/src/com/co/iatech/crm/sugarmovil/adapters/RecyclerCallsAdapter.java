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
import com.co.iatech.crm.sugarmovil.activities.CallActivity;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Llamada;

public class RecyclerCallsAdapter extends RecyclerView.Adapter<RecyclerCallsAdapter.ViewHolder> {


    /**
     * Debug.
     */
    private static final String TAG = "RecyclerCallsAdapter";

    /**
     * Member Variables.
     */
    private Context mContext;
    private ArrayList<Llamada> mDataset;
    private ArrayList<Llamada> mVisibleDataset;

    public RecyclerCallsAdapter(Context context, ArrayList<Llamada> myDataset) {
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
                // Llamada Activity
                Intent intentLlamada = new Intent(mContext,
                        CallActivity.class);
                intentLlamada.putExtra(Info.ID_LLAMADA.name(), llamada.getId());
                mContext.startActivity(intentLlamada);
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
        mVisibleDataset = new ArrayList<>();
        mVisibleDataset.addAll(mDataset);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        mVisibleDataset = new ArrayList<>();
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
