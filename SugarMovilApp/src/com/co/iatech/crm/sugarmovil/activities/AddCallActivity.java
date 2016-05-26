package com.co.iatech.crm.sugarmovil.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitorsManager;
import com.co.iatech.crm.sugarmovil.activities.listeners.SearchDialogInterface;
import com.co.iatech.crm.sugarmovil.activities.ui.DatePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activities.ui.TimePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorActivities;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleEditableActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleValidations;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCampaignsConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;


public class AddCallActivity extends CallsModuleEditableActions {


    /**
     * Debug.
     */
    private static final String TAG = "AddCallActivity";


    /**
     * Member Variables.
     */
    private Llamada llamadaSeleccionada;
    
    private ListUsersConverter lc = new ListUsersConverter();
    private ListAccountConverter lac = new ListAccountConverter();
    private TypeActions tipoPermiso;
    
    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;
    private Button botonFechaInicio, botonHoraInicio;
    private ImageButton imgButtonGuardar;
    private TextView asignadoA,valorFechaInicio, valorCuenta;
    private EditText valorAsunto,valorDescripcion,valorDuracionHrs;
    private Spinner valorCampana, valorResultado,valorDireccion, valorEstado, valorDuracionMin;


	public String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
        try{
	        getInfoFromMediator();
	      
	        // Main Toolbar
	        mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
	        setSupportActionBar(mLlamadaToolbar);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setHomeButtonEnabled(false);
	        imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
	
	        createWidgets();
	        chargeLists();
	        defineValidations();
	        asignadoA.setOnClickListener(this);
	        
	        if(isEditMode){
	        	TextView title = (TextView) findViewById(R.id.text_call_toolbar);
	        	title.setText("EDITAR LLAMADA");
	        	chargeValues();
	        }
        }catch(Exception e){
       	   Message.showShortExt(Utils.errorToString(e), this);
          }
    }
    
    public void getInfoFromMediator() {
    	super.getInfoFromMediator();
		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
    	Intent intent = getIntent();
    	
    	llamadaSeleccionada = intent.getParcelableExtra(MODULE.getModuleName());
         
         if(!isEditMode){
         	llamadaSeleccionada = new Llamada();
         }
         
	}
	
	@Override
	public void onFinishSearchDialog(GenericBean selectedBean) {
		if(selectedBean instanceof User){
			User su = (User) selectedBean;
			asignadoA.setText(su.getUser_name());
		}else if(selectedBean instanceof Cuenta){
			Cuenta ac = (Cuenta) selectedBean;
			valorCuenta.setText(ac.getName());
		}
	}
	
	
	public void chargeLists() {
		asignadoA = (TextView) findViewById(R.id.txt_valor_asignado_a);
		 
		 // Direccion Llamada        
        valorDireccion = (Spinner) findViewById(R.id.valor_direccion);
        ArrayAdapter<String> dirAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.CALLS_DIRECTION));
        dirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorDireccion.setAdapter(dirAdapter);
        
        // Estado
        valorEstado = (Spinner) findViewById(R.id.valor_estado);
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.CALLS_STATUS));
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEstado.setAdapter(estadoAdapter);
        //Resultado
        valorResultado = (Spinner) findViewById(R.id.valor_resultado);
        ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.CALLS_RESULT));
        resAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorResultado.setAdapter(resAdapter);
        
        // Duracion
        valorDuracionMin = (Spinner) findViewById(R.id.valor_duracion);
        ArrayAdapter<String> durAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.CALLS_MINS_DURATION));
        durAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorDuracionMin.setAdapter(durAdapter);
        
      
      //Carga Campañas
        valorCampana = (Spinner) findViewById(R.id.valor_campana);
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        ArrayAdapter<String> campAdapter = new ArrayAdapter<String>(AddCallActivity.this,
                android.R.layout.simple_spinner_item,  lcc.getListInfo());
        valorCampana.setAdapter(campAdapter);
        valorCampana.setSelection(0);
        
        GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		llamadaSeleccionada.setCreated_by(u.getId());
		if(!isEditMode){
	        asignadoA.setText(u.getFirst_name()+" "+u.getLast_name());
	        llamadaSeleccionada.setAssigned_user_id(u.getId());
		}
        
		
		 //Carga Cuentas
        if(actualInfo.getActualParentModule().equals(Modules.ACCOUNTS)){
        	valorCuenta.setText(lac.convert(actualInfo.getActualParentId(), DataToGet.VALUE ));
        }
    }
	
	 public void createWidgets() {
	    valorAsunto = (EditText) findViewById(R.id.valor_asunto);
	    valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
	    valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
	    
	    valorDuracionHrs = (EditText) findViewById(R.id.valor_duracion_horas);
	    
     // Fecha Inicio
        botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
        botonFechaInicio.setOnClickListener(this);
        
        botonHoraInicio = (Button) findViewById(R.id.boton_hora_inicio);
        botonHoraInicio.setOnClickListener(this);
        
        imgButtonGuardar.setOnClickListener(this);
        
        //Cuentas
        valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
        valorCuenta.setOnClickListener(this);
        valorCuenta.setText(ValidatorActivities.SELECT_MESSAGE);
	 
	  }
	 
	 public void chargeValues() {
		valorAsunto.setText(llamadaSeleccionada.getName());
	    valorDescripcion.setText(llamadaSeleccionada.getDescription());
	    valorFechaInicio.setText(llamadaSeleccionada.getDate_start());
	
		int pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_DIRECTION, llamadaSeleccionada.getDirection());
		valorDireccion.setSelection(pos);
		
		pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_STATUS, llamadaSeleccionada.getStatus());
		valorEstado.setSelection(pos);
		
		pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_RESULT, llamadaSeleccionada.getResultadodelallamada_c());
		valorResultado.setSelection(pos);
		
		pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_MINS_DURATION, llamadaSeleccionada.getDuration_minutes());
		valorDuracionMin.setSelection(pos);
		
		valorDuracionHrs.setText(llamadaSeleccionada.getDuration_hours());
  
        // Cuenta
		if(llamadaSeleccionada.getParent_id() != null && llamadaSeleccionada.getParent_id().length() > 1){
			valorCuenta.setText(lac.convert(llamadaSeleccionada.getParent_id(), DataToGet.VALUE));
		}
        
        //Campaña
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        pos = Integer.parseInt(lcc.convert(llamadaSeleccionada.getCampaign_id(), DataToGet.POS ));
        valorCampana.setSelection(pos);
        
        // Asignado
        asignadoA.setText(lc.convert(llamadaSeleccionada.getAssigned_user_id(), DataToGet.VALUE ));
        
        imgButtonGuardar.setVisibility(View.VISIBLE);
        
	    }

		@Override
		public void defineValidations() {
			Map<View, CharSequence> data;
			data = new LinkedHashMap<View, CharSequence>();
			
			data.put(valorAsunto,"El campo Asunto no puede estar vacio");
			data.put(valorResultado, "Debe seleccionar un Resultado de Llamada");
			data.put(valorDireccion, "Debe seleccionar un Estado"); 
			data.put(valorEstado,"Debe seleccionar un Estado ");
			
	        
			ValidatorGeneric.getInstance().define(data);
			
		}

		@Override
		public void onClick(View v) {
			if(v.getId() == asignadoA.getId()){
				switch(tipoPermiso){
				case OWNER:
					break;
				case ALL:
					Message.showUsersDialog(getSupportFragmentManager());
					break;
				case GROUP:
					break;
			}
			}else if(v.getId() == botonHoraInicio.getId()){
				DialogFragment newFragment = new TimePickerFragment(this,valorFechaInicio,isEditMode);
				newFragment.show(getFragmentManager(), "hourCierrePicker");
			}else if(v.getId() == valorCuenta.getId()){
				Message.showAccountsDialog(getSupportFragmentManager());
			}else if(v.getId() == botonFechaInicio.getId()){
				DialogFragment newFragment = new DatePickerFragment(this,valorFechaInicio,isEditMode);
				newFragment.show(getFragmentManager(), "dateCierrePicker");
			}else if(v.getId() == imgButtonGuardar.getId()){
				 //Realizar Validaciones
				if(!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())){
					return;
				}
				

	            if(valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1){
	            	llamadaSeleccionada.setDate_start(valorFechaInicio.getText().toString());
	            }
	            imgButtonGuardar.setVisibility(View.INVISIBLE);
	            llamadaSeleccionada.setDescription(valorDescripcion.getText().toString());
	            llamadaSeleccionada.setName(valorAsunto.getText().toString());
	            llamadaSeleccionada.setDuration_hours(valorDuracionHrs.getText().toString());
	            llamadaSeleccionada.setDirection(ListsConversor.convert(ConversorsType.CALLS_DIRECTION, valorDireccion.getSelectedItem().toString(), DataToGet.CODE));
	            llamadaSeleccionada.setStatus(ListsConversor.convert(ConversorsType.CALLS_STATUS, valorEstado.getSelectedItem().toString(), DataToGet.CODE));
	            llamadaSeleccionada.setDuration_minutes(ListsConversor.convert(ConversorsType.CALLS_MINS_DURATION, valorDuracionMin.getSelectedItem().toString(), DataToGet.CODE));
	            llamadaSeleccionada.setResultadodelallamada_c(ListsConversor.convert(ConversorsType.CALLS_RESULT, valorResultado.getSelectedItem().toString(), DataToGet.CODE));
	            
	            // Cuenta
	            if(valorCuenta.getText() != null && valorCuenta.getText().length() > 0 ){
	            	llamadaSeleccionada.setParent_id(lac.convert(valorCuenta.getText().toString(), DataToGet.CODE));
	            }
		        
		        llamadaSeleccionada.setParent_type("Accounts");
		        
		        ListCampaignsConverter lcc = new ListCampaignsConverter();
		        llamadaSeleccionada.setCampaign_id(lcc.convert(valorCampana.getSelectedItem().toString(), DataToGet.CODE));
		        
		        String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(),DataToGet.CODE);
		        llamadaSeleccionada.setAssigned_user_id(idUsuarioAsignado);
		        
		        
		
		        AddCallTask tareaEditarLlamada = new AddCallTask();
		        
		        tareaEditarLlamada.execute(llamadaSeleccionada);
			}
		}
		
		/**
	     * Representa una tarea asincrona de creacion de llamada.
	     */
	    public class AddCallTask extends AsyncTask<Object, Void, Boolean> {

	        @Override
	        protected Boolean doInBackground(Object... params) {
	            try {
	                
	                Llamada obj = (Llamada)params[0];

	                // Resultado
	                resultado = null;
	                
	                if(isEditMode){
	                	resultado  = ControlConnection.putInfo(TypeInfoServer.addCall, obj.getDataBean(),Modo.EDITAR, AddCallActivity.this );
	                }else{
	                   	resultado  = ControlConnection.putInfo(TypeInfoServer.addCall, obj.getDataBean(),Modo.AGREGAR, AddCallActivity.this );
	                }
	                
	                if(resultado.contains("OK")){
	                	obj.id = Utils.getIDFromBackend(resultado);
	                	obj.accept(new DataVisitorsManager());
	                	ActivitiesMediator.getInstance().addObjectInfo(obj);
	                	 return true;
	                }else{
	                	 return false;
	                }
	               
	            } catch (Exception e) {
	            	 resultado += Utils.errorToString(e);
	                return false;
	            }
	        }

	        @Override
	        protected void onPostExecute(final Boolean success) {
	         
	            if (success) {
	            	 if(isEditMode){
	            		 Message.showFinalMessage(getFragmentManager(),DialogType.EDITED, AddCallActivity.this, MODULE );
	            		 
	            	 }else{
	            		 Message.showFinalMessage(getFragmentManager(),DialogType.CREATED, AddCallActivity.this, MODULE );
	            		 
	            	 }
	            	
	            } else {
	            	if(isEditMode){
	           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_EDITED, AddCallActivity.this, MODULE );
	           		 
	           	 }else{
	           		 Message.showFinalMessage(getFragmentManager(), resultado, AddCallActivity.this, MODULE );
	           		// Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED, AddCallActivity.this, MODULE );
	           		 
	           	 }
	                Log.d(TAG, "Crear Llamda error");
	            }

	        }

	        @Override
	        protected void onCancelled() {
	            
	            Log.d(TAG, "Cancelado ");
	        }
	    }

		@Override
		public void addInfo(String serverResp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void chargeViewInfo() {
			// TODO Auto-generated method stub
			
		}

}
