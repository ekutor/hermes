package com.co.iatech.crm.sugarmovil.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitorsManager;
import com.co.iatech.crm.sugarmovil.activities.listeners.SearchDialogInterface;
import com.co.iatech.crm.sugarmovil.activities.ui.DatePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activities.ui.TimePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModuleValidations;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener, 
SearchDialogInterface, TasksModuleValidations{

	/**
     * Debug.
     */
    private static final String TAG = "AddTaskActivity";

    /**
     * Member Variables.
     */

  
    private TareaDetalle tareaSeleccionada;
    private static boolean modoEdicion;
 
    private ListUsersConverter lc = new ListUsersConverter();
    private TypeActions tipoPermiso;


    /**
     * UI References.
     */
    private Toolbar mTareaToolbar;
    private ImageButton imgButtonGuardar;
    private TextView valorTrabajoEstimado,valorAsunto,valorDescripcion;
    private Spinner valorEstado,valorTipo,valorPrioridad,valorNombre;
    private Button botonFechaInicio,botonFechaVen, botonHoraInicio, botonHoraVen;
    private TextView valorFechaInicio, asignadoA, valorFechaVen;

	public String resultado;
	private String associatedAccount;

	private AddTask editarTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        try{
	        modoEdicion = false;
	        
	        // SoftKey
	        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	        
	        getInfoFromMediator();
	      
	        // Main Toolbar
	        mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_task);
	        setSupportActionBar(mTareaToolbar);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setHomeButtonEnabled(false);
	        imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
	
	        
	        createWidgets();
	        chargeLists();
	        defineValidations();
	        asignadoA.setOnClickListener(this);
        
        if(modoEdicion){
        	TextView title = (TextView) findViewById(R.id.text_task_toolbar);
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
		tareaSeleccionada = intent.getParcelableExtra(Info.OBJECT.name());

		if (tareaSeleccionada != null) {
			modoEdicion = true;
			Log.d(TAG, "tarea Recibida " + tareaSeleccionada);
		} else {
			tareaSeleccionada = new TareaDetalle();
			associatedAccount = intent.getStringExtra(Info.ID.name());
		}
		Log.d(TAG, "Modo Edicion " + modoEdicion);
         
	}
    
    private void chargeLists() {
		asignadoA = (TextView) findViewById(R.id.valor_asignado_a);
        
        // Estado
        valorEstado = (Spinner) findViewById(R.id.valor_estado);
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.TASKS_STATUS));
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEstado.setAdapter(estadoAdapter);
      
        valorTipo = (Spinner) findViewById(R.id.valor_tipo);
        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.TASKS_TYPE));
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorTipo.setAdapter(tipoAdapter);
        
        valorPrioridad = (Spinner) findViewById(R.id.valor_prioridad);
        ArrayAdapter<String> prioridadAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.TASKS_PRIORITY));
        prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorPrioridad.setAdapter(prioridadAdapter);
         
        
        GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		tareaSeleccionada.setCreated_by(u.getId());
		if(!modoEdicion){
			Log.d(TAG, "NOOO ES MODO EDICION");
			
	        asignadoA.setText(u.getFirst_name()+" "+u.getLast_name());
	        tareaSeleccionada.setAssigned_user_id(u.getId());
	        
	        //Carga Cuentas
	        	    
	        ListAccountConverter lac = new ListAccountConverter();
	        ArrayAdapter<String> cuentaAdapter = new ArrayAdapter<String>(AddTaskActivity.this,
	                android.R.layout.simple_spinner_item,  lac.getListInfo());
	        valorNombre.setAdapter(cuentaAdapter);
	        valorNombre.setSelection(0);
	        if(associatedAccount != null){
	        	int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE, "Accounts");
	    		valorTipo.setSelection(pos);
	        	String nombreCuenta = lac.convert(associatedAccount, DataToGet.VALUE);
	        	valorNombre.setSelection(lac.getListInfo().indexOf(nombreCuenta));
	        }
	        
		}
        
    }
    
    public void createWidgets() {
	    valorAsunto = (EditText) findViewById(R.id.valor_asunto);
	    valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
	    valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
	    valorFechaVen = (TextView) findViewById(R.id.valor_fecha_vence);
	    valorNombre = (Spinner) findViewById(R.id.valor_nombre);
	    //TODO mejorar la pantalla de cuenta y poner eventos a tipo para que muestre o no un tipo
	    
	    
	    valorTrabajoEstimado = (EditText) findViewById(R.id.valor_estimado);
	    
     // Fecha Inicio
        botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
        botonFechaInicio.setOnClickListener(this);
        
        botonHoraInicio = (Button) findViewById(R.id.boton_hora_inicio);
        botonHoraInicio.setOnClickListener(this);
        
        botonFechaVen = (Button) findViewById(R.id.boton_fecha_vence);
        botonFechaVen.setOnClickListener(this);
        
        botonHoraVen = (Button) findViewById(R.id.boton_hora_vence);
        botonHoraVen.setOnClickListener(this);
        
        imgButtonGuardar.setOnClickListener(this);
	 
	  }
    public void chargeValues() {
    	
    	valorAsunto.setText(tareaSeleccionada.getName());
    	int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_STATUS, tareaSeleccionada.getStatus());
		valorEstado.setSelection(pos);
		valorFechaInicio.setText(Utils.transformTimeBakendToUI(tareaSeleccionada.getDate_start()));
		valorFechaVen.setText(Utils.transformTimeBakendToUI(tareaSeleccionada.getDate_due()));
	    
	    valorTrabajoEstimado.setText(tareaSeleccionada.getTrabajo_estimado_c());
	//contacto
	    valorTrabajoEstimado.setText(tareaSeleccionada.getTrabajo_estimado_c());
	    pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_PRIORITY, tareaSeleccionada.getPriority());
		valorPrioridad.setSelection(pos);
		valorDescripcion.setText(tareaSeleccionada.getDescription());
		pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE, tareaSeleccionada.getParent_type());
		valorTipo.setSelection(pos);
		
		// Cuenta
		if( valorNombre.getSelectedItemPosition() > 0){
	        ListAccountConverter lac = new ListAccountConverter();
	        tareaSeleccionada.setParent_id(lac.convert(valorNombre.getSelectedItem().toString(), DataToGet.CODE));
		}
        
        // Contacto
      //  mValorContacto = (TextView) findViewById(R.id.valor_contacto);
//        mValorContacto.setText(tareaDetalle.getContact_name());
      
        // Asignado
        asignadoA.setText(lc.convert(tareaSeleccionada.getAssigned_user_id(), DataToGet.VALUE ));
        imgButtonGuardar.setVisibility(View.VISIBLE);
        
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
		}else if(v.getId() == botonHoraVen.getId()){
			DialogFragment newFragment = new TimePickerFragment(this,valorFechaVen,modoEdicion);
			newFragment.show(getFragmentManager(), "hourCierrePicker");
		}else if(v.getId() == botonFechaVen.getId()){
			DialogFragment newFragment = new DatePickerFragment(this,valorFechaVen,modoEdicion);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		}else if(v.getId() == imgButtonGuardar.getId()){
			//Realizar Validaciones
			if(!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())){
				return;
			}
			
			imgButtonGuardar.setVisibility(View.INVISIBLE);
            
			tareaSeleccionada.setName(valorAsunto.getText().toString());
			tareaSeleccionada.setStatus(ListsConversor.convert(ConversorsType.TASKS_STATUS, valorEstado.getSelectedItem().toString(), DataToGet.CODE));
			
			if(valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1){
            	tareaSeleccionada.setDate_start(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
            }
            
			if(valorFechaVen.getText() != null && valorFechaVen.getText().toString().length() > 1){
            	tareaSeleccionada.setDate_due(Utils.transformTimeUItoBackend(valorFechaVen.getText().toString()));
            }
			
			//contacto
			
			tareaSeleccionada.setTrabajo_estimado_c(valorTrabajoEstimado.getText().toString());
			tareaSeleccionada.setPriority(ListsConversor.convert(ConversorsType.TASKS_PRIORITY, valorPrioridad.getSelectedItem().toString(), DataToGet.CODE));
			tareaSeleccionada.setDescription(valorDescripcion.getText().toString());
			tareaSeleccionada.setParent_type(ListsConversor.convert(ConversorsType.TASKS_TYPE, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
            
            ListAccountConverter lac = new ListAccountConverter();
	       // tareaSeleccionada.setParent_id(lac.convert(.getSelectedItem().toString(), DataToGet.CODE));
            
//            tareaSeleccionada.setDuration_hours(valorDuracionHrs.getText().toString());
            
//            tareaSeleccionada.setDuration_minutes(ListsConversor.convert(ConversorsType.CALLS_MINS_DURATION, valorDuracionMin.getSelectedItem().toString(), DataToGet.CODE));
            

//	        
//	        llamadaSeleccionada.setParent_type("Accounts");
	        
	        
	        String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(),DataToGet.CODE);
	        tareaSeleccionada.setAssigned_user_id(idUsuarioAsignado);
	        
	        
	
	        editarTarea = new AddTask();
	        
	        editarTarea.execute(tareaSeleccionada);
		}
		
	}

	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();
		
		data.put(valorAsunto,"El campo Asunto no puede estar vacio");
		data.put(valorEstado,"Debe seleccionar un Estado de la Tarea");
		data.put(valorPrioridad, "Debe seleccionar una Prioridad de Tarea");
		
		ValidatorGeneric.getInstance().define(data);
		
	}

	@Override
	public void onFinishSearchDialog(User selectedUser) {
		asignadoA.setText(selectedUser.getUser_name());
		Log.d(TAG, "Recibido por Pattern Listener: "+ selectedUser.getUser_name());

	}
	
	/**
     * Representa una tarea asincrona de creacion de Tareas.
     */
    public class AddTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                
            	TareaDetalle obj = (TareaDetalle)params[0];

                // Resultado
                 resultado = null;
                
                if(modoEdicion){
                	resultado  = ControlConnection.putInfo(TypeInfoServer.addTask, obj.getDataBean(),Modo.EDITAR,  AddTaskActivity.this);
                }else{
                   	resultado  = ControlConnection.putInfo(TypeInfoServer.addTask, obj.getDataBean(),Modo.AGREGAR,  AddTaskActivity.this);
                }
                Log.d(TAG, "Crear Tarea Resp: "+ resultado);
              
                if(resultado.contains("OK")){
                	tareaSeleccionada = obj;
                	obj.accept(new DataVisitorsManager());
                	 return true;
                }else{
                	 return false;
                }
               
            } catch (Exception e) {
                Log.d(TAG, "Crear Llamada Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                resultado = Utils.errorToString(e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
        	//Message.showShortExt(resultado+" "+success+ resultado.length(), AddTaskActivity.this);
            if (success) {
            	 if(modoEdicion){
            		 Message.showFinalMessage(getFragmentManager(),DialogType.EDITED, AddTaskActivity.this, MODULE );
            		 
            	 }else{
            		 Message.showFinalMessage(getFragmentManager(),DialogType.CREATED, AddTaskActivity.this, MODULE );
            		 
            	 }
            	 chargeValues();
            } else {
            	if(modoEdicion){
           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_EDITED, AddTaskActivity.this, MODULE );
           		 
           	 }else{
           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED, AddTaskActivity.this, MODULE );
           		 
           	 }
                Log.d(TAG, "Crear Tarea error");
            }
            modoEdicion = false;
        }

        @Override
        protected void onCancelled() {
            
            Log.d(TAG, "Cancelado ");
        }
    }

}
