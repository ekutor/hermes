package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter.SearchType;
import com.co.iatech.crm.sugarmovil.adapters.search.IAdapterItems;
import com.co.iatech.crm.sugarmovil.model.Account;
import com.co.iatech.crm.sugarmovil.model.Lead;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerLeadsAdapter extends RecyclerView.Adapter<RecyclerLeadsAdapter.ViewHolder> {

    /**
     * Member Variables.
     */
    private Context context;

    private List<Lead> mDataset;
    private List<Lead> mVisibleDataset;

    public RecyclerLeadsAdapter(Context context,List<Lead> mAccountsArray) {
        this.context = context;
        mDataset = mAccountsArray;
        mVisibleDataset = mDataset;
    }

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leads_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Lead lead = mVisibleDataset.get(position);

        holder.txtName.setText(lead.getFirst_name());
        holder.txtCompany.setText(lead.getRazonsocial_c());
        holder.txtPhonework.setText(lead.getPhone_work());
        holder.txtPhoneMobile.setText(lead.getPhone_mobile());
        // Eventos
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Account Activity     
                ActivitiesMediator.getInstance().showActivity(context,Modules.LEADS, new ActivityBeanCommunicator(lead.getId(), lead.getFirst_name()));
            }
        });

        holder.itemView.setTag(lead);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVisibleDataset.size();
    }

    public void flushFilter() {
        mVisibleDataset = new ArrayList<Lead>();
        mVisibleDataset.addAll(mDataset);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

        mVisibleDataset = new ArrayList<Lead>();
        for (Lead item : mDataset) {
            if (item.getFirst_name().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
            if (item.getRazonsocial_c().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
    	
    	public LinearLayout itemLayout;
        public TextView txtName;
        public TextView txtCompany;
        public TextView txtPhonework;
        public TextView txtPhoneMobile;

        public ViewHolder(View v) {
            super(v);
            itemLayout = (LinearLayout) v.findViewById(R.id.item_leads);
            txtName = (TextView) v.findViewById(R.id.leadName);
            txtCompany = (TextView) v.findViewById(R.id.leadCompany);
            txtPhonework = (TextView) v.findViewById(R.id.leadPhoneW);
            txtPhoneMobile = (TextView) v.findViewById(R.id.leadPhoneM);
        }
    }
}
