package com.co.iatech.crm.sugarmovil.activities.tasks;

import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Representa una tarea asincrona de obtencion de informacion.
 */
public class GenericTask extends AsyncTask<String, Void, Boolean> {
    private ProgressDialog progressDialog;

    private Activity view;
    private TypeInfoServer typeRequest;
    private IMovilModuleActions movilModule;
    private String response;
    private String message;
    
    public GenericTask(Activity view, TypeInfoServer typeRequest,String userMessage){
    	this.view = view;
    	this.typeRequest = typeRequest;
    	this.movilModule =  (IMovilModuleActions)view;
    	message = userMessage;
    	if(userMessage == null ){
    		message = "Cargando "+movilModule.getModule().name().toLowerCase()+" ...";
    	}
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try{
	        progressDialog = new ProgressDialog(view, ProgressDialog.THEME_HOLO_DARK);
	        progressDialog.setMessage(message);
	        progressDialog.setIndeterminate(true);
	        progressDialog.show();
        }catch(Exception e){
        	Message.showShortExt(Utils.errorToString(e), view);
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
        	
        	//Params
        	 if(params != null && params.length > 1){
        		 boolean cleanCache = true;
	        	 for(int i = 0; i < params.length ; i++ ){
	        		 String key = params[i];
	            	 String id = params[++i];
	            	 ControlConnection.addHeader(key, id, cleanCache);
	            	 cleanCache = false;
	    		 }
        	 }
            // Resultado
            response= "";

            // Intento de obtener datos
            
            response  = ControlConnection.getInfo(typeRequest,view);
         
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean success) {
    	movilModule.addInfo(response);
    	progressDialog.dismiss();        
    }
 
}
