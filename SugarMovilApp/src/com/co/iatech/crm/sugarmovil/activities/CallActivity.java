package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.software.shell.fab.ActionButton;

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


public class CallActivity extends CallsModuleActions{

    /**
     * Member Variables.
     */

    private String callId;
    private ListUsersConverter lc = new ListUsersConverter();
    
    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        try{	        	
	        // Main Toolbar
	        mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
	        setSupportActionBar(mLlamadaToolbar);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setHomeButtonEnabled(false);
	        
	        this.applyActions();
	        
	        this.chargeViewInfo();
        }catch(Exception e){
      	   Message.showShortExt(Utils.errorToString(e), this);
         }
    }

    public void showValues(Llamada llamadaDetalle) {
        TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
        valorAsunto.setText(llamadaDetalle.getName());
        TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
             
        valorEstado.setText(
        		ListsConversor.convert(ConversorsType.CALLS_DIRECTION,llamadaDetalle.getDirection(), DataToGet.VALUE) + " -> "+
        		ListsConversor.convert(ConversorsType.CALLS_STATUS,llamadaDetalle.getStatus(), DataToGet.VALUE));
        
        TextView valorInicio = (TextView) findViewById(R.id.valor_inicio);
        valorInicio.setText(llamadaDetalle.getDate_start());
        TextView valorDuracion = (TextView) findViewById(R.id.valor_duracion);
        valorDuracion.setText(llamadaDetalle.getDuration_hours()+"h "+llamadaDetalle.getDuration_minutes()+"m");
        TextView valorResultado = (TextView) findViewById(R.id.valor_resultado);
        valorResultado.setText(llamadaDetalle.getResultadodelallamada_c());
        TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(llamadaDetalle.getDescription());
        TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
        valorAsignado.setText(lc.convert(llamadaDetalle.getAssigned_user_id(), DataToGet.VALUE ));
        
        TextView valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
        valorCuenta.setText(llamadaDetalle.getParent_name());
        TextView valorCampana = (TextView) findViewById(R.id.valor_campana);
        valorCampana.setText(llamadaDetalle.getCampaign_name());
    }
    
    @Override
    public void onResume() {
    	try{
    		selectedBean = (Llamada) ActivitiesMediator.getInstance().getBeanInfo();
	    	if(selectedBean != null){
	    		this.showValues(selectedBean);
	    	}
    	}catch(Exception e){
    		
    	}
        super.onResume();

    }
    
    @Override
	public void applyActions() {
		imgButtonEdit = (ImageButton) findViewById(R.id.ic_edit);       
        ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());

	}
    @Override
	public void chargeViewInfo() {
        Intent intent = getIntent();

        if(intent.getExtras().get(MODULE.getModuleName()) instanceof  Llamada ){
        	selectedBean = (Llamada) intent.getExtras().get(MODULE.getModuleName());
        	this.showValues(selectedBean);
        }else{
        	callId = intent.getStringExtra(MODULE.name());
        	   Message.showShortExt("Call id"+ callId, this);
			String[] params = { "idCall", callId };
			this.executeTask(params, TypeInfoServer.getCall);
        }
     
	}

	@Override
	public void addInfo(String serverResponse) {
          try {
  			JSONObject jObj = new JSONObject(serverResponse);
  			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
  			
  			if (jArr.length() > 0) {
  				JSONObject obj = jArr.getJSONObject(0);
  				selectedBean = new Llamada(obj);
  				showValues(selectedBean);
  			}
  			
  		} catch (Exception e) {
  			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
  		}
	}
}
