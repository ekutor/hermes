package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.Cuenta;

public class RecyclerAccountsAdapter extends RecyclerView.Adapter<RecyclerAccountsAdapter.ViewHolder> {


    /**
     * Debug.
     */
    private static final String TAG = "RecyclerAccountsAdapter";

    /**
     * Member Variables.
     */
    private Context context;

    private List<Cuenta> mDataset;
    private List<Cuenta> mVisibleDataset;

    public RecyclerAccountsAdapter(Context context,List<Cuenta> mAccountsArray) {
        this.context = context;
        mDataset = mAccountsArray;
        mVisibleDataset = mDataset;
    }

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_accounts_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Cuenta cuenta = mVisibleDataset.get(position);

        // Nombre cuenta
        holder.mTextViewNombre.setText(cuenta.getName());
        holder.textViewUen.setText(cuenta.getUen());
        holder.textViewNit.setText(cuenta.getNit());
        // Eventos
        holder.mItemAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Account Activity     
                ActivitiesMediator.getInstance().showActivity(context,Modules.ACCOUNTS, cuenta.getId());
            }
        });

        holder.itemView.setTag(cuenta);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVisibleDataset.size();
    }

    public void flushFilter() {
        mVisibleDataset = new ArrayList<Cuenta>();
        mVisibleDataset.addAll(mDataset);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        mVisibleDataset = new ArrayList<Cuenta>();
        for (Cuenta item : mDataset) {
            if (item.getName().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
            if (item.getNit().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItemAccount;
        public TextView mTextViewNombre;
        public TextView textViewUen;
        public TextView textViewNit;

        public ViewHolder(View v) {
            super(v);
            mItemAccount = (LinearLayout) v.findViewById(R.id.item_accounts);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_cuenta);
            textViewUen = (TextView) v.findViewById(R.id.txtUen);
            textViewNit = (TextView) v.findViewById(R.id.txtNit);
        }
    }
}
