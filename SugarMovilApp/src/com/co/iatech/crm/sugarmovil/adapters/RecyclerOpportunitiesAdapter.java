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
import com.co.iatech.crm.sugarmovil.activities.OpportunityActivity;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;

public class RecyclerOpportunitiesAdapter extends RecyclerView.Adapter<RecyclerOpportunitiesAdapter.ViewHolder> {

    /**
     * Debug.
     */
    private static final String TAG = "RecyclerOpportunitiesAdapter";

    /**
     * Member Variables.
     */
    private Context mContext;
    private String idCuentaAsociada;
    private ArrayList<Oportunidad> mDataset;
    private ArrayList<Oportunidad> mVisibleDataset;

    public RecyclerOpportunitiesAdapter(Context context, ArrayList<Oportunidad> myDataset, String idCuentaAsociada) {
        mContext = context;
        mDataset = myDataset;
        this.idCuentaAsociada = idCuentaAsociada;
        mVisibleDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_opportunities_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Oportunidad oportunidad = mVisibleDataset.get(position);

        // Nombre cuenta
        holder.mTextViewNombre.setText(oportunidad.getName());

        // Eventos
        holder.mItemOportunidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oportunidad Activity
                Intent intentOportunidad = new Intent(mContext,
                        OpportunityActivity.class);
                intentOportunidad.putExtra(Info.CUENTA_ACTUAL.name(), idCuentaAsociada);
                intentOportunidad.putExtra(Info.OPORTUNIDAD_SELECCIONADA.name(), oportunidad.getId());
                mContext.startActivity(intentOportunidad);
            }
        });

        holder.itemView.setTag(oportunidad);
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
        for (Oportunidad item : mDataset) {
            if (item.getName().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItemOportunidad;
        public TextView mTextViewNombre;

        public ViewHolder(View v) {
            super(v);
            mItemOportunidad = (LinearLayout) v.findViewById(R.id.item_opportunity);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_oportunidad);
        }
    }
}
