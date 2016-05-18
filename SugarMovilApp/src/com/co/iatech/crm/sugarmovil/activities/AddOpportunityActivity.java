package com.co.iatech.crm.sugarmovil.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitorsManager;
import com.co.iatech.crm.sugarmovil.activities.listeners.SearchDialogInterface;
import com.co.iatech.crm.sugarmovil.activities.ui.DatePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.OpportunitiesModuleValidations;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCampaignsConverter;
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




public class AddOpportunityActivity extends AppCompatActivity implements View.OnClickListener, 
SearchDialogInterface, OpportunitiesModuleValidations {

 
    /**
     * Debug.
     */
    private static final String TAG = "AddOpportunityActivity";

    /**
     * Tasks.
     */
    private AddOpportunityTask mTareaCrearOportunidad = null;
    

    /**
     * Member Variables.
     */
 
    private OportunidadDetalle oportSeleccionada;
    private static boolean modoEdicion;
    private String idCuentaAsociada;
    private TypeActions tipoPermiso;
    
    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonGuardar;
    private Button botonFechaCierre;
    private static TextView mValorFechaCierre,asignadoA;
    private EditText valorNombre,valorUsuario,valorEstimado,valorProbabilidad,valorFuente,valorPaso,valorDescripcion;
    private Spinner valorTipo,valorEtapa,valorMedio,valorEnergia,valorComunicaciones,valorIluminacion,valorMoneda;
    private Spinner valorCuenta, valorCampana;
    private ListUsersConverter lc = new ListUsersConverter();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opportunity);
        modoEdicion = false;
        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        getInfoFromMediator();
        
        Log.d(TAG, "Modo Edicion " + modoEdicion);
        // Main Toolbar
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
        setSupportActionBar(mCuentaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        mImageButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

        chargeLists();
        createWidgets();
        defineValidations();
        asignadoA.setOnClickListener(this);
        
        if(modoEdicion){
        	chargeValues();
        }
        
    }

    private void getInfoFromMediator() {
    	
		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
    	 Intent intent = getIntent();
    	
         idCuentaAsociada = intent.getStringExtra(Info.ID.name());
         Log.d(TAG, "idCuentaAsociada " + idCuentaAsociada);
         oportSeleccionada = intent.getParcelableExtra(Info.OBJECT.name());

         if(oportSeleccionada != null){
         	modoEdicion = true;
         	Log.d(TAG, "Oportunidad Recibida " + oportSeleccionada);
         }else{
         	oportSeleccionada = new OportunidadDetalle();
         }
		
	}

	private void chargeLists() {
    	//Tipo Oportunidad
        valorTipo = (Spinner) findViewById(R.id.valor_tipo);
        
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_PROYECT));
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        valorTipo.setAdapter(estadoAdapter);
        
      //Etapa Oportunidad
        valorEtapa = (Spinner) findViewById(R.id.valor_etapa);
        ArrayAdapter<String> etapaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_STAGE));
        etapaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEtapa.setAdapter(etapaAdapter);
      
        // Medio Oportunidad
        valorMedio = (Spinner) findViewById(R.id.valor_medio);
        ArrayAdapter<String> medioAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_MEDIUM));
        medioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMedio.setAdapter(medioAdapter);
        
        //Energia
        valorEnergia = (Spinner) findViewById(R.id.valor_energia);       
        ArrayAdapter<String> energiaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ENERGY));
        energiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEnergia.setAdapter(energiaAdapter);

        // Comunicaciones
        valorComunicaciones = (Spinner) findViewById(R.id.valor_comunicaciones);
        ArrayAdapter<String> comunicacionesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_COMUNICATIONS));
        comunicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorComunicaciones.setAdapter(comunicacionesAdapter);
        
        // Iluminacion
        valorIluminacion = (Spinner) findViewById(R.id.valor_iluminacion);
        ArrayAdapter<String> iluminacionAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ILUM));
        iluminacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorIluminacion.setAdapter(iluminacionAdapter);        
        
        // Moneda
        valorMoneda = (Spinner) findViewById(R.id.valor_moneda);
        ArrayAdapter<String> monedaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_CURRENCY));
        monedaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMoneda.setAdapter(monedaAdapter);
        
      //Carga Cuentas
        valorCuenta = (Spinner) findViewById(R.id.valor_cuenta);
        ListAccountConverter lac = new ListAccountConverter();
        ArrayAdapter<String> cuentaAdapter = new ArrayAdapter<String>(AddOpportunityActivity.this,
                android.R.layout.simple_spinner_item,  lac.getListInfo());
        valorCuenta.setAdapter(cuentaAdapter);
        valorCuenta.setSelection(0);
        if(idCuentaAsociada != null){
        	String nombreCuenta = lac.convert(idCuentaAsociada, DataToGet.VALUE);
        	valorCuenta.setSelection(lac.getListInfo().indexOf(nombreCuenta));
        }
        
      //Carga Usuarios

        asignadoA = (TextView) findViewById(R.id.txt_valor_asignado_a);
        
        GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		
		oportSeleccionada.setCreated_by(u.getId());
		if(!modoEdicion){
			
			Log.d(TAG, "NOOO ES MODO EDICION");
			
	        asignadoA.setText(u.getFirst_name()+" "+u.getLast_name());
	        oportSeleccionada.setAssigned_user_id(u.getId());
		}
         
      //Carga Campañas
        valorCampana = (Spinner) findViewById(R.id.valor_campana);
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        ArrayAdapter<String> campAdapter = new ArrayAdapter<String>(AddOpportunityActivity.this,
                android.R.layout.simple_spinner_item,  lcc.getListInfo());
        valorCampana.setAdapter(campAdapter);
        valorCampana.setSelection(0);
        
    }

    public void createWidgets() {
        valorNombre = (EditText) findViewById(R.id.valor_nombre);

        // Usuario Final
        valorUsuario = (EditText) findViewById(R.id.valor_usuario);

        // Fecha Cierre
        botonFechaCierre = (Button) findViewById(R.id.boton_fecha_cierre);
        botonFechaCierre.setOnClickListener(this);
        mValorFechaCierre = (TextView) findViewById(R.id.valor_fecha_cierre);

        valorEstimado = (EditText) findViewById(R.id.valor_estimado);

        valorProbabilidad = (EditText) findViewById(R.id.valor_probabilidad);

        // Fuente no tiene datos en el crm
       // TextView valorFuente = (TextView) findViewById(R.id.valor_fuente);

        // Paso
        valorPaso = (EditText) findViewById(R.id.valor_paso);
        // Descripcion
        valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
       
       
        // Eventos
        // Guardar Tarea
        mImageButtonGuardar.setOnClickListener(this);
 
    }
    
    public void chargeValues() {
        valorNombre.setText(oportSeleccionada.getName());

        // Usuario Final
        valorUsuario.setText(oportSeleccionada.getUsuario_final_c());
        // Fecha Cierre
        mValorFechaCierre.setText(Utils.transformTimeBakendToUI(oportSeleccionada.getDate_closed()));
        valorEstimado.setText(oportSeleccionada.getValoroportunidad_c());
        valorProbabilidad.setText(oportSeleccionada.getProbability());
        // Paso
        valorPaso.setText(oportSeleccionada.getNext_step());
        // Descripcion
        valorDescripcion.setText(oportSeleccionada.getDescription());
     
       // valorEstimado.setText(oportSeleccionada.getValoroportunidad_c());
  
        
        int pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_PROYECT, oportSeleccionada.getTipo_c());
        valorTipo.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_STAGE, oportSeleccionada.getSales_stage());
        valorEtapa.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_MEDIUM, oportSeleccionada.getMedio_c());
        valorMedio.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_ENERGY, oportSeleccionada.getEnergia_c());
        valorEnergia.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_COMUNICATIONS, oportSeleccionada.getComunicaciones_c());
        valorComunicaciones.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_ILUM, oportSeleccionada.getIluminacion_c());
        valorIluminacion.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_CURRENCY, oportSeleccionada.getAmount_usdollar());
        valorMoneda.setSelection(pos);
        
     // Cuenta
        ListAccountConverter lac = new ListAccountConverter();
        pos = Integer.parseInt(lac.convert(oportSeleccionada.getIdAccount(), DataToGet.POS ));
        Log.d("EditOportunidad","pos Cuenta ");
        valorCuenta.setSelection(pos);
        
        //Campaña
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        pos = Integer.parseInt(lcc.convert(oportSeleccionada.getCampaign_id(), DataToGet.POS ));
        valorCampana.setSelection(pos);
        
        // Asignado
        asignadoA.setText(lc.convert(oportSeleccionada.getAssigned_user_id(), DataToGet.VALUE ));
        Log.d(TAG, "VALOR CARGADO "+asignadoA.getText());
        
        Log.d(TAG, "id usuario asignado  "+oportSeleccionada.getAssigned_user_id());
     
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
		}else if(v.getId() == botonFechaCierre.getId()){
			DialogFragment newFragment = new DatePickerFragment(this,mValorFechaCierre,modoEdicion);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		}else if(v.getId() == mImageButtonGuardar.getId()){
	        //Realizar Validaciones
			if(!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())){
				return;
			}
			mImageButtonGuardar.setEnabled(false);
	        // Nombre
	        oportSeleccionada.setName(valorNombre.getText().toString());
	        
	        oportSeleccionada.setTipo_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
	 
	        oportSeleccionada.setSales_stage(ListsConversor.convert(ConversorsType.OPPORTUNITY_STAGE, valorEtapa.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Cuenta
	        ListAccountConverter lac = new ListAccountConverter();
	        oportSeleccionada.setIdAccount(lac.convert(valorCuenta.getSelectedItem().toString(), DataToGet.CODE));

	        try{
	        	// Fecha Cierre
	            if(mValorFechaCierre.getText().toString() != null && mValorFechaCierre.getText().toString().length() > 1){
	            	oportSeleccionada.setDate_closed(Utils.transformTimeUItoBackend( mValorFechaCierre.getText().toString() ));
	            }
	            
			}catch(java.lang.NullPointerException ne){
				
			}

	        ListCampaignsConverter lcc = new ListCampaignsConverter();
	        oportSeleccionada.setCampaign_id(lcc.convert(valorCampana.getSelectedItem().toString(), DataToGet.CODE));

	        oportSeleccionada.setMedio_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM, valorMedio.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Asignado
	        String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(),DataToGet.CODE);
	        oportSeleccionada.setAssigned_user_id(idUsuarioAsignado);
	      
	        // Marca energia
	        oportSeleccionada.setEnergia_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_ENERGY, valorEnergia.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Marca comunicaciones
	        oportSeleccionada.setComunicaciones_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_COMUNICATIONS, valorComunicaciones.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Marca iluminacion
	        oportSeleccionada.setIluminacion_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_ILUM, valorIluminacion.getSelectedItem().toString(), DataToGet.CODE));
	        
	        //Moneda
	    
	        oportSeleccionada.setAmount_usdollar(ListsConversor.convert(ConversorsType.OPPORTUNITY_CURRENCY, valorMoneda.getSelectedItem().toString(), DataToGet.CODE));
	        oportSeleccionada.setValoroportunidad_c(valorEstimado.getText().toString());
	        mTareaCrearOportunidad = new AddOpportunityTask();
	        
	        oportSeleccionada.setCreated_by(ControlConnection.userId);
	        //op.setUsuario_final_c(usuario_final_c)
	        
	        oportSeleccionada.setProbability(valorProbabilidad.getText().toString());
	        oportSeleccionada.setNext_step(valorPaso.getText().toString());
	        oportSeleccionada.setDescription(valorDescripcion.getText().toString());
	        
	        // Crear oportunidad
	        mTareaCrearOportunidad.execute(oportSeleccionada);
		}
    }
	
	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();
		
		data.put(valorNombre,"El campo Nombre no puede estar vacio");
		data.put(valorTipo, "Debe seleccionar un Tipo de Oportunidad");
		data.put(valorEtapa, "Debe seleccionar una Etapa de Oportunidad"); 
		data.put(valorCuenta,"Debe seleccionar una Cuenta ");
		data.put(valorMedio,"Debe seleccionar un Medio ");
		data.put(valorEstimado,"El Valor Estimado no puede estar vacio");

        
		ValidatorGeneric.getInstance().define(data);
	}

	@Override
	public void onFinishSearchDialog(GenericBean selectedUser) {
		if(selectedUser instanceof User){
			User su = (User) selectedUser;
			asignadoA.setText(su.getUser_name());
		}
	}
	
	 /**
     * Representa una tarea asincrona de creacion de oportunidad.
     */
    public class AddOpportunityTask extends AsyncTask<Object, Void, Boolean> {

        

		@Override
        protected Boolean doInBackground(Object... params) {
            try {
                
                OportunidadDetalle op = (OportunidadDetalle)params[0];

                // Resultado
                String resultado = null;
                
                if(modoEdicion){
                	resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean(),Modo.EDITAR, AddOpportunityActivity.this);
                }else{
                   	resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean(),Modo.AGREGAR, AddOpportunityActivity.this);
                }
                Log.d(TAG, "Crear Oportunidad RESPr: "+ resultado);
                if(resultado.contains("OK")){
                	Oportunidad oportunidad = new Oportunidad( op );
                	oportunidad.accept(new DataVisitorsManager());
                	 return true;
                }else{
                	 return false;
                }
               
            } catch (Exception e) {
                Log.d(TAG, "Crear Oportunidad Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaCrearOportunidad = null;
        	mImageButtonGuardar.setEnabled(true);
            if (success) {
            	 if(modoEdicion){
            		 Message.showFinalMessage(getFragmentManager(),DialogType.EDITED, AddOpportunityActivity.this, MODULE );
            		 
            	 }else{
            		 Message.showFinalMessage(getFragmentManager(),DialogType.CREATED, AddOpportunityActivity.this, MODULE );
            		 
            	 }
            	 chargeValues();
            } else {
            	if(modoEdicion){
           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_EDITED, AddOpportunityActivity.this, MODULE );
           		 
           	 }else{
           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED, AddOpportunityActivity.this, MODULE );
           		 
           	 }
                Log.d(TAG, "Crear Oportunidad error");
            }
            modoEdicion = false;
        }

        @Override
        protected void onCancelled() {
            mTareaCrearOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
    }

}
