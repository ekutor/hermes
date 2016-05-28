package com.co.iatech.crm.sugarmovil.activities.tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activities.ListOpportunityActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Representa una tarea asincrona para obtener oportunidades.
 */
public class GetOpportunitiesTask extends AsyncTask<String, Void, Boolean> {

	  private static final String TAG = "GetOpportunitiesTask";
	  
	private ProgressDialog progressDialog;
	
	private ListOpportunityActivity activity;
	private RecyclerView recyclerView;
	 private ArrayList<Oportunidad> oportunitiesXAccount = new ArrayList<Oportunidad>();
	
	public GetOpportunitiesTask(ListOpportunityActivity listOpportunityActivity, RecyclerView recyclerView) {
		activity = listOpportunityActivity;
		this.recyclerView = recyclerView;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(activity,
				ProgressDialog.THEME_HOLO_DARK);
		progressDialog.setMessage("Cargando Oportunidades...");
		progressDialog.setIndeterminate(true);
		progressDialog.show();
	}

	@Override
	        protected Boolean doInBackground(String... params) {
	            try {
	            	
	            	String  key = "idAccount";
	            	String id = ActivitiesMediator.getInstance().getActualID(Modules.ACCOUNTS);
	                // Resultado
	                String resultado = null;

	                // Intento de obtener datos
	                ControlConnection.addHeader(key, id);
	                resultado  = ControlConnection.getInfo(TypeInfoServer.getAccountOpportunities, activity);
	                oportunitiesXAccount.clear();

	                JSONObject jObj = new JSONObject(resultado);

	                JSONArray jArr = jObj.getJSONArray("results");
	                for (int i = 0; i < jArr.length(); i++) {
	                    JSONObject obj = jArr.getJSONObject(i);
	                    oportunitiesXAccount.add(new Oportunidad(obj));
	                }

	                return true;
	            } catch (Exception e) {
	            	e.printStackTrace();
	                Log.d(TAG, "Buscar Error: "
	                        + e.getClass().getName() + ":" + e.getMessage());
	                return false;
	            }
	        }

	@Override
	protected void onPostExecute(final Boolean success) {
		RecyclerView.Adapter recyclerViewAdapter;
		if (success) {
			// ListsHolder.saveList(ListsHolderType.CAMPAIGNS, campsArray);
			if (oportunitiesXAccount.size() > 0) {
				recyclerViewAdapter = new RecyclerGenericAdapter( activity, AdapterSearchUtil.transform(oportunitiesXAccount) , Modules.OPPORTUNITIES);
				recyclerView.setAdapter(recyclerViewAdapter);
			} else {
				progressDialog
						.setMessage("Esta cuenta no tiene oportunidades asociadas.");
				Log.d(TAG, "No hay valores: " + oportunitiesXAccount.size());
			}
		}
//		try {
//			Thread.sleep(500);
			progressDialog.dismiss();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}

	@Override
	protected void onCancelled() {
		Log.d(TAG, "Cancelado ");
	}

}