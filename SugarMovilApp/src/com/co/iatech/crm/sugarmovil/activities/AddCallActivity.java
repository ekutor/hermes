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
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleValidations;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
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


public class AddCallActivity extends AppCompatActivity 
implements View.OnClickListener, SearchDialogInterface, CallsModuleValidations {


    /**
     * Debug.
     */
    private static final String TAG = "AddCallActivity";


    /**
     * Member Variables.
     */

    private String idCuentaAsociada;
    private static boolean modoEdicion;
    private Llamada llamadaSeleccionada;
    private ListUsersConverter lc = new ListUsersConverter();
    private TypeActions tipoPermiso;
    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;
    private Button botonFechaInicio, botonHoraInicio;
    private ImageButton imgButtonGuardar;
    private TextView asignadoA,valorFechaInicio;
    private EditText valorAsunto,valorDescripcion,valorDuracionHrs;
    private Spinner valorCuenta, valorCampana, valorResultado,valorDireccion, valorEstado, valorDuracionMin;

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
	
	        
	        chargeLists();
	        createWidgets();
	        defineValidations();
	        asignadoA.setOnClickListener(this);
	        
	        if(modoEdicion){
	        	TextView title = (TextView) findViewById(R.id.text_call_toolbar);
	        	title.setText("EDITAR TAREA");
	        	chargeValues();
	        }
        }catch(Exception e){
       	   Message.showShortExt(Utils.errorToString(e), this);
          }
    }
    
    private void getInfoFromMediator() {
    
		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
    	 Intent intent = getIntent();
 
         idCuentaAsociada = intent.getStringExtra(Modules.ACCOUNTS.name());
         Log.d(TAG, "idCuentaAsociada " + idCuentaAsociada);
         llamadaSeleccionada = intent.getParcelableExtra(MODULE.getModuleName());
         
         if(llamadaSeleccionada != null){
         	modoEdicion = true;
         	Log.d(TAG, "Modo Edicion" + llamadaSeleccionada.getId());
         }else{
         	llamadaSeleccionada = new Llamada();
         }
         
	}
	
	@Override
	public void onFinishSearchDialog(GenericBean selectedUser) {
		if(selectedUser instanceof User){
			User su = (User) selectedUser;
			asignadoA.setText(su.getUser_name());
		}
	}
	
	
	private void chargeLists() {
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
       
        
      //Carga Cuentas
        valorCuenta = (Spinner) findViewById(R.id.valor_cuenta);
        ListAccountConverter lac = new ListAccountConverter();
        ArrayAdapter<String> cuentaAdapter = new ArrayAdapter<String>(AddCallActivity.this,
                android.R.layout.simple_spinner_item,  lac.getListInfo());
        valorCuenta.setAdapter(cuentaAdapter);
        valorCuenta.setSelection(0);
        if(idCuentaAsociada != null){
        	String nombreCuenta = lac.convert(idCuentaAsociada, DataToGet.VALUE);
        	valorCuenta.setSelection(lac.getListInfo().indexOf(nombreCuenta));
        }
        
        GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		llamadaSeleccionada.setCreated_by(u.getId());
		if(!modoEdicion){
			Log.d(TAG, "NOOO ES MODO EDICION");
			
	        asignadoA.setText(u.getFirst_name()+" "+u.getLast_name());
	        llamadaSeleccionada.setAssigned_user_id(u.getId());
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
        ListAccountConverter lac = new ListAccountConverter();
        pos = Integer.parseInt(lac.convert(llamadaSeleccionada.getParent_id(), DataToGet.POS ));
        valorCuenta.setSelection(pos);
        
        //Campaña
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        pos = Integer.parseInt(lcc.convert(llamadaSeleccionada.getCampaign_id(), DataToGet.POS ));
        valorCampana.setSelection(pos);
        
        // Asignado
        asignadoA.setText(lc.convert(llamadaSeleccionada.getAssigned_user_id(), DataToGet.VALUE ));
        
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
				DialogFragment newFragment = new TimePickerFragment(this,valorFechaInicio,modoEdicion);
				newFragment.show(getFragmentManager(), "hourCierrePicker");
			}else if(v.getId() == botonFechaInicio.getId()){
				DialogFragment newFragment = new DatePickerFragment(this,valorFechaInicio,modoEdicion);
				newFragment.show(getFragmentManager(), "dateCierrePicker");
			}else if(v.getId() == imgButtonGuardar.getId()){
				 //Realizar Validaciones
				if(!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())){
					return;
				}
				

	            if(valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1){
	            	llamadaSeleccionada.setDate_start(valorFechaInicio.getText().toString());
	            }
	            
	            llamadaSeleccionada.setDescription(valorDescripcion.getText().toString());
	            llamadaSeleccionada.setName(valorAsunto.getText().toString());
	            llamadaSeleccionada.setDuration_hours(valorDuracionHrs.getText().toString());
	            llamadaSeleccionada.setDirection(ListsConversor.convert(ConversorsType.CALLS_DIRECTION, valorDireccion.getSelectedItem().toString(), DataToGet.CODE));
	            llamadaSeleccionada.setStatus(ListsConversor.convert(ConversorsType.CALLS_STATUS, valorEstado.getSelectedItem().toString(), DataToGet.CODE));
	            llamadaSeleccionada.setDuration_minutes(ListsConversor.convert(ConversorsType.CALLS_MINS_DURATION, valorDuracionMin.getSelectedItem().toString(), DataToGet.CODE));
	            llamadaSeleccionada.setResultadodelallamada_c(ListsConversor.convert(ConversorsType.CALLS_RESULT, valorResultado.getSelectedItem().toString(), DataToGet.CODE));
	            
		        ListAccountConverter lac = new ListAccountConverter();
		        llamadaSeleccionada.setParent_id(lac.convert(valorCuenta.getSelectedItem().toString(), DataToGet.CODE));
		        
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
	                String resultado = null;
	                
	                if(modoEdicion){
	                	resultado  = ControlConnection.putInfo(TypeInfoServer.addCall, obj.getDataBean(),Modo.EDITAR, AddCallActivity.this );
	                }else{
	                   	resultado  = ControlConnection.putInfo(TypeInfoServer.addCall, obj.getDataBean(),Modo.AGREGAR, AddCallActivity.this );
	                }
	                Log.d(TAG, "Crear Llamada Resp: "+ resultado);
	                if(resultado.contains("OK")){
	 
	                	obj.accept(new DataVisitorsManager());
	                	 return true;
	                }else{
	                	 return false;
	                }
	               
	            } catch (Exception e) {
	                Log.d(TAG, "Crear Llamada Error: "
	                        + e.getClass().getName() + ":" + e.getMessage());
	                return false;
	            }
	        }

	        @Override
	        protected void onPostExecute(final Boolean success) {
	         
	            if (success) {
	            	 if(modoEdicion){
	            		 Message.showFinalMessage(getFragmentManager(),DialogType.EDITED, AddCallActivity.this, MODULE );
	            		 
	            	 }else{
	            		 Message.showFinalMessage(getFragmentManager(),DialogType.CREATED, AddCallActivity.this, MODULE );
	            		 
	            	 }
	            	 chargeValues();
	            } else {
	            	if(modoEdicion){
	           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_EDITED, AddCallActivity.this, MODULE );
	           		 
	           	 }else{
	           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED, AddCallActivity.this, MODULE );
	           		 
	           	 }
	                Log.d(TAG, "Crear Llamda error");
	            }
	            modoEdicion = false;
	        }

	        @Override
	        protected void onCancelled() {
	            
	            Log.d(TAG, "Cancelado ");
	        }
	    }

}
