package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;

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
import com.co.iatech.crm.sugarmovil.model.Contacto;

public class RecyclerContactsAdapter extends RecyclerView.Adapter<RecyclerContactsAdapter.ViewHolder> {


    /**
     * Debug.
     */
    private static final String TAG = "RecyclerContactsAdapter";

    /**
     * Member Variables.
     */
    private Context mContext;
    private String mUrl;
    private ArrayList<Contacto> mDataset;
    private ArrayList<Contacto> mVisibleDataset;

    public RecyclerContactsAdapter(Context context, ArrayList<Contacto> myDataset) {
        mContext = context;
        mDataset = myDataset;
        mVisibleDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contacts_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Contacto contacto = mVisibleDataset.get(position);

        // Nombre cuenta
        holder.mTextViewNombre.setText(contacto.getNombre());

        // Eventos
        holder.mItemAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Contact Activity
            	ActivitiesMediator.getInstance().setActualID( contacto.getId());
            	ActivitiesMediator.getInstance().showActivity(mContext,Modules.CONTACTS);
 
            }
        });

        holder.itemView.setTag(contacto);
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
        for (Contacto item : mDataset) {
            if (item.getNombre().toLowerCase().contains(queryText))
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

        public ViewHolder(View v) {
            super(v);
            mItemAccount = (LinearLayout) v.findViewById(R.id.item_contacts);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_contacto);
        }
    }
}
