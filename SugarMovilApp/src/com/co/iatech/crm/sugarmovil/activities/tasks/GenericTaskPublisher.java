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
public class GenericTaskPublisher extends AsyncTask<String, Void, Boolean> {
    private ProgressDialog progressDialog;

    private Activity view;
    private TypeInfoServer typeRequest;
    private String response;
    private String message;
    private Modules actualModule;
    
   
    public GenericTaskPublisher(Activity view, Modules movilModule , TypeInfoServer typeRequest,String userMessage){
    	this.view = view;
    	this.typeRequest = typeRequest;
    	this.actualModule = movilModule;
    	message = userMessage;
    	if(userMessage == null ){
    		message = "Cargando "+actualModule.getModuleName().toLowerCase()+" ...";
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
        		 String key = params[0];
            	 String id = params[1];
        		 ControlConnection.addHeader(key, id);
        	}
            // Resultado
            response= "";

            // Intento de obtener datos
            
            response  = ControlConnection.getInfo(typeRequest,view);
         
            return true;
        } catch (Exception e) {
        	Message.showShortExt(Utils.errorToString(e), view);
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean success) {
    	try{
    	ActivityBeanCommunicator bean = new ActivityBeanCommunicator("","");
    	bean.setAdditionalInfo(response);
    	bean.setModule(actualModule);
    	progressDialog.dismiss();  
    	GatewayPublisher.getInstance().postMessage(bean);
    	
    	}catch(Exception e){
    		Message.showShortExt(Utils.errorToString(e), view);
    		
    	}
    }

	public void setModule(Modules recyclerModule) {
		this.actualModule = recyclerModule;
		
	}

 
}
