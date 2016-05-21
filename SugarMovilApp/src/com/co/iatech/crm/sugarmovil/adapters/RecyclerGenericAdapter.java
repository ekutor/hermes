package com.co.iatech.crm.sugarmovil.adapters;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.search.IAdapterItems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;;

public class RecyclerGenericAdapter extends RecyclerView.Adapter<RecyclerGenericAdapter.ViewHolder> {

    /**
     * Member Variables.
     */
    private Context context;
    private List<IAdapterItems> dataHolder;
    private List<IAdapterItems> visibleData;
    private Modules recyclerModule;

    public RecyclerGenericAdapter(Context context, List<IAdapterItems> dataSet, Modules recyclerModule) {
        this.context = context;
        dataHolder = dataSet;
        visibleData = dataSet;
        this.recyclerModule = recyclerModule;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final IAdapterItems searchAdapter = visibleData.get(position);

        // Nombre tarea
        holder.txtItem.setText(searchAdapter.getSearchName());

        // Eventos
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	ActivitiesMediator.getInstance().showActivity(context, recyclerModule, searchAdapter.getSearchId());
            }
        });

        holder.itemView.setTag(searchAdapter);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return visibleData.size();
    }

    public void flushFilter() {
        visibleData = new ArrayList<IAdapterItems>();
        visibleData.addAll(dataHolder);
        notifyDataSetChanged();
    }

    public void setFilter(String queryText) {

    	visibleData = new ArrayList<IAdapterItems>();
        for (IAdapterItems item : dataHolder) {
            if (item.getSearchName().toLowerCase().contains(queryText))
            	visibleData.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout itemLayout;
        public TextView txtItem;

        public ViewHolder(View v) {
            super(v);
            itemLayout = (LinearLayout) v.findViewById(R.id.item_recycler_linear_layout);
            txtItem = (TextView) v.findViewById(R.id.name_item);
        }
    }
}
