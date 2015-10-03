package com.co.iatech.crm.sugarmovil.activities.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;

/**
 * Representa una tarea asincrona de obtencion de usuarios.
 */
public class UsersTask extends AsyncTask<Void, Void, Boolean> {
   
    private List<User> usersArray;
    
    public UsersTask(){
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
            usersArray = new ArrayList<User>();
            resultado  = ControlConnection.getInfo(TypeInfoServer.getUsers);
            
            Log.d("UsersTask", "Obteniendo Usuarios");
            JSONObject jObj = new JSONObject(resultado);

            JSONArray jArr = jObj.getJSONArray("results");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject obj = jArr.getJSONObject(i);
   
                usersArray.add(new User(obj));
            }
            
            return true;
        } catch (Exception e) {
            Log.d("UserTaks", "Buscar Usuarios Error: "
                    + e.getClass().getName() + ":" + e.getMessage());
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            if (usersArray.size() > 0) {
            	
            	ListsHolder.saveList(ListsHolderType.USERS, usersArray);
            	Log.d("UsersTask", "Usuarios Obtenidos OK");
            } else {
                Log.d("UsersTask",
                        "No hay Usuarios Recibidos ");
            }
        }
    }
}