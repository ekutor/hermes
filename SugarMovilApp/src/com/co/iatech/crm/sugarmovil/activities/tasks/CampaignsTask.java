package com.co.iatech.crm.sugarmovil.activities.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Campana;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;

/**
 * Representa una tarea asincrona de obtencion de usuarios.
 */
public class CampaignsTask extends AsyncTask<Void, Void, Boolean> {
   
    private List<Campana> campsArray;
	private Activity context;
    
    public CampaignsTask(Activity context){
    	this.context = context;
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            // Parametros
            String resultado = null;

            // Intento de obtener cuentas
            campsArray = new ArrayList<Campana>();
            resultado  = ControlConnection.getInfo(TypeInfoServer.getCampaigns, context);
            
            Log.d("CampaignsTask", "Obteniendo Campanhas");
            JSONObject jObj = new JSONObject(resultado);

            JSONArray jArr = jObj.getJSONArray("results");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject obj = jArr.getJSONObject(i);
   
                campsArray.add(new Campana(obj));
            }
            
            return true;
        } catch (Exception e) {
            Log.d("CampaignsTask", "Buscar CAmapnhas Error: "
                    + e.getClass().getName() + ":" + e.getMessage());
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            if (campsArray.size() > 0) {
            	
            	ListsHolder.saveList(ListsHolderType.CAMPAIGNS, campsArray);
            	Log.d("CampaignsTask", "CAmpañas Obtenidas OK");
            } else {
                Log.d("CampaignsTask",
                        "No hay CAmpañas Recibidos ");
            }
        }
    }
}