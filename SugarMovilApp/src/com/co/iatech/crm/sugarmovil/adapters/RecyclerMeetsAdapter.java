package com.co.iatech.crm.sugarmovil.adapters;

import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.Meeting;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCallStatusConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerMeetsAdapter extends RecyclerView.Adapter<RecyclerMeetsAdapter.ViewHolder> {

    /**
     * Member Variables.
     */
    private Context context;

    private List<Meeting> mDataset;
    private List<Meeting> mVisibleDataset;

    public RecyclerMeetsAdapter(Context context,List<Meeting> mAccountsArray) {
        this.context = context;
        mDataset = mAccountsArray;
        mVisibleDataset = mDataset;
    }

	@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meet_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting mt = mVisibleDataset.get(position);

        holder.name.setText(mt.getName());
        holder.dateS.setText(Utils.transformTimeBakendToUI(mt.getDateStart(), 5 ));
        holder.location.setText(mt.getLocation());
        holder.status.setText(ListCallStatusConverter.getInstance().convert(mt.getStatus(), DataToGet.VALUE));
        // Eventos
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Account Activity     
                ActivitiesMediator.getInstance().showActivity(context,Modules.CALENDAR, new ActivityBeanCommunicator(mt.id, mt.getName()));
            }
        });

        holder.itemView.setTag(mt);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVisibleDataset.size();
    }

    

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
    	
    	public LinearLayout itemLayout;
        public TextView name;
        public TextView dateS;
        public TextView location;
        public TextView duration;
        public TextView status;

        public ViewHolder(View v) {
            super(v);
            itemLayout = (LinearLayout) v.findViewById(R.id.item_meet);
            name = (TextView) v.findViewById(R.id.mtName);
            dateS = (TextView) v.findViewById(R.id.mtStartDate);
            location = (TextView) v.findViewById(R.id.location);
            duration = (TextView) v.findViewById(R.id.duration);
            status = (TextView) v.findViewById(R.id.status);
        }
    }
}
