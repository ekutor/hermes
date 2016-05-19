package com.co.iatech.crm.sugarmovil.activities;

import java.security.MessageDigestSpi;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModuleActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.software.shell.fab.ActionButton;


public class TaskActivity extends AppCompatActivity implements TasksModuleActions {


    /**
     * Debug.
     */
    private static final String TAG = "TaskActivity";

    /**
     * Tasks.
     */
    private GetTaskTask mTareaObtenerTarea = null;

    /**
     * Member Variables.
     */
    private String mIdTarea;
    private TareaDetalle objTareaDetalle;

    /**
     * UI References.
     */
    private Toolbar mTareaToolbar;
    private ImageButton imageButtonEdit;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        try{
	        Intent intent = getIntent();
	        objTareaDetalle = null;
	        
	        // Main Toolbar
	        mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_task);
	        setSupportActionBar(mTareaToolbar);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setHomeButtonEnabled(false);
	        
	        this.applyActions();
	        
	        if(intent.getExtras().get(Info.OBJECT.name()) instanceof  TareaDetalle ){
	        	objTareaDetalle = (TareaDetalle) intent.getExtras().get(Info.OBJECT.name());
	        	this.ponerValores(objTareaDetalle);
	        }else{
		        mIdTarea = intent.getStringExtra(Info.ID.name());
		        Log.d(TAG, "Id tarea " + mIdTarea);
		        mTareaObtenerTarea = new GetTaskTask();
	  	        mTareaObtenerTarea.execute(String.valueOf(mIdTarea));
		      
	        }
        }catch(Exception e){
     	   Message.showShortExt(Utils.errorToString(e), this);
        }
    }
    

    public void ponerValores(TareaDetalle tareaDetalle) {
    	try{
  
    	TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
        valorAsunto.setText(tareaDetalle.getName());
        
        TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
        valorEstado.setText(ListsConversor.convert(ConversorsType.TASKS_STATUS,tareaDetalle.getStatus(), DataToGet.VALUE));
        
        TextView valorFechaInicio = (TextView) findViewById(R.id.boton_fecha_inicio);
        valorFechaInicio.setText(Utils.transformTimeBakendToUI(tareaDetalle.getDate_start()));
       
        TextView valorFechaVence = (TextView) findViewById(R.id.boton_fecha_vence);
        valorFechaVence.setText(Utils.transformTimeBakendToUI(tareaDetalle.getDate_due()));
        
        TextView valorContacto = (TextView) findViewById(R.id.valor_contacto);
        valorContacto.setText(tareaDetalle.getContact_name());
        
        TextView valorEstimado = (TextView) findViewById(R.id.valor_estimado);
        valorEstimado.setText(tareaDetalle.getTrabajo_estimado_c());
        TextView valorPrioridad = (TextView) findViewById(R.id.valor_prioridad);
        
        valorPrioridad.setText(ListsConversor.convert(ConversorsType.TASKS_PRIORITY,tareaDetalle.getPriority(), DataToGet.VALUE));
        
        TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(tareaDetalle.getDescription());
        
        TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
    	valorTipo.setText(ListsConversor.convert(ConversorsType.TASKS_TYPE, tareaDetalle.getParent_type(), DataToGet.VALUE));
        
    	
        TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
        valorAsignado.setText(tareaDetalle.getAssigned_user_name());
    	 }catch(Exception e){
       	   Message.showShortExt(Utils.errorToString(e), this);
          }
       
       /* TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
        valorNombre.setText(tareaDetalle.getParent_name());*/
    }

    @Override
    public void onResume() {
    	try{
	    	objTareaDetalle = (TareaDetalle) ActivitiesMediator.getInstance().getBeanInfo();
	    	if(objTareaDetalle != null){
	    		this.ponerValores(objTareaDetalle);
	    	}
    	}catch(Exception e){
    		
    	}
        super.onResume();

    }
    
    @Override
   	public void applyActions() {
   		imageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);       
        ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());

   	}
       
       @Override
   	public ActionButton getActionButton() {
   		return null;
   	}

   	@Override
   	public ImageButton getEditButton() {
   		return imageButtonEdit;
   	}

   	@Override
   	public Modules getModule() {
   		return MODULE;
   	}


   	@Override
   	public String getAssignedUser() {
   		return objTareaDetalle.getAssigned_user_id();
   	}


   	@Override
   	public Parcelable getBean() {
   		return objTareaDetalle;
   	}


    /**
     * Representa una tarea asincrona de obtencion de tarea.
     */
    public class GetTaskTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(TaskActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando informacion de tarea...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idTarea = params[0];

                // Respuesta
                String resultado = null;

                // Intento de obtener tarea
                ControlConnection.addHeader("idTask", idTarea);
                resultado  = ControlConnection.getInfo(TypeInfoServer.getTask, TaskActivity.this);
        
                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                if( jArr.length() > 0) {
                    JSONObject obj = jArr.getJSONObject(0);
                    objTareaDetalle = new TareaDetalle(obj);
                 }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Tarea Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerTarea = null;
            progressDialog.dismiss();

            if (success) {
                ponerValores(objTareaDetalle);

            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerTarea = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
