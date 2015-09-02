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
import com.co.iatech.crm.sugarmovil.activities.TaskActivity;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Tarea;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;

public class RecyclerTasksDetailAdapter extends RecyclerView.Adapter<RecyclerTasksDetailAdapter.ViewHolder> {



    /**
     * Debug.
     */
    private static final String TAG = "RecyclerTasksDetailAdapter";

    /**
     * Member Variables.
     */
    private Context mContext;
    private ArrayList<TareaDetalle> mDataset;
    private ArrayList<TareaDetalle> mVisibleDataset;

    public RecyclerTasksDetailAdapter(Context context, ArrayList<TareaDetalle> myDataset) {
        mContext = context;
        mDataset = myDataset;
        mVisibleDataset = mDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tasks_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TareaDetalle tarea = mVisibleDataset.get(position);

        // Nombre tarea
        holder.mTextViewNombre.setText(tarea.getName());

        // Eventos
        holder.mItemTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tarea Activity
                Intent intentTarea = new Intent(mContext,
                        TaskActivity.class);
                intentTarea.putExtra(Info.ID_TAREA.name(), tarea);
                mContext.startActivity(intentTarea);
            }
        });

        holder.itemView.setTag(tarea);
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
        for (TareaDetalle item : mDataset) {
            if (item.getName().toLowerCase().contains(queryText))
                mVisibleDataset.add(item);
        }
        notifyDataSetChanged();
    }

    /**
     * Clase para ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItemTarea;
        public TextView mTextViewNombre;

        public ViewHolder(View v) {
            super(v);
            mItemTarea = (LinearLayout) v.findViewById(R.id.item_tasks);
            mTextViewNombre = (TextView) v.findViewById(R.id.nombre_tarea);
        }
    }
}
