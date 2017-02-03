package com.co.iatech.crm.sugarmovil.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitorsManager;
import com.co.iatech.crm.sugarmovil.activities.ui.DatePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.MultiSelectionSpinner;
import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorActivities;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.OpportunitiesModuleEditableActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.model.Account;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;




public class AddOpportunityActivity extends OpportunitiesModuleEditableActions {

 
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

    private TypeActions tipoPermiso;
    private ListAccountConverter lac = new ListAccountConverter();
    private final String VALUE_SELECTED = "PORTALES WEB";
    
    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton imgButtonGuardar;
    private Button botonFechaCierre;
    private TextView mValorFechaCierre,asignadoA,valorCuenta;
    private EditText valorNombre,valorUsuario,valorEstimado,valorProbabilidad,valorPaso,valorDescripcion;
    private Spinner valorTipo,valorEtapa,valorMedio,valorEnergia,valorComunicaciones,valorIluminacion,valorMoneda;
    private Spinner valorCampana, valorFuente;
    private ListUsersConverter lc = new ListUsersConverter();

	public String resultado;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opportunity);
        try{
	      
	        // SoftKey
	        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	        
	        getInfoFromMediator();
	        
	        // Main Toolbar
	        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
	        setSupportActionBar(mCuentaToolbar);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setHomeButtonEnabled(false);
	        imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
	
	        createWidgets();
	        chargeLists();
	        defineValidations();
	        asignadoA.setOnClickListener(this);
	        
	        if(isEditMode){
	        	TextView title = (TextView) findViewById(R.id.text_opportunity_toolbar);
	        	title.setText("EDITAR OPORTUNIDAD");
	        	chargeValues();
	        }
        }catch(Exception e){
       	  Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddOpportunityActivity.this, MODULE );
        }
        
    }

    public void getInfoFromMediator() {
    	super.getInfoFromMediator();
		 tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
		 Intent intent = getIntent();
         if(isEditMode){
        	 oportSeleccionada = intent.getParcelableExtra(MODULE.getModuleName());
         }else{
         	oportSeleccionada = new OportunidadDetalle();
         	beanCommunicator = intent.getParcelableExtra(Modules.ACCOUNTS.name());
         }
		
	}

    public void chargeLists() {
    	//Tipo Oportunidad
        valorTipo = (Spinner) findViewById(R.id.valor_tipo);
        
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_PROYECT));
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
        
        valorTipo.setAdapter(estadoAdapter);
        
      //Etapa Oportunidad
        valorEtapa = (Spinner) findViewById(R.id.valor_etapa);
        ArrayAdapter<String> etapaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_STAGE));
        etapaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEtapa.setAdapter(etapaAdapter);
       
        valorFuente = (Spinner) findViewById(R.id.valor_fuente);
        final ArrayAdapter<String> fuenteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_SOURCE));
        fuenteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Medio Oportunidad
        valorMedio = (Spinner) findViewById(R.id.valor_medio);
        ArrayAdapter<String> medioAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_MEDIUM));
        medioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMedio.setAdapter(medioAdapter);
        valorMedio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   
				if(valorMedio.getSelectedItem().toString().contains(VALUE_SELECTED)){
					valorFuente.setAdapter(fuenteAdapter);
					valorFuente.setSelection(0,true);
				}else{
					valorFuente.setAdapter(null);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
	
				
			}});
        
        //Energia
        valorEnergia = (Spinner) findViewById(R.id.valor_energia);       
        ArrayAdapter<String> energiaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ENERGY));
        energiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEnergia.setAdapter(energiaAdapter);
        
        final MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(R.id.btnSelected);
        
        //spinner.setItems(ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ENERGY));
        final TextView text_energia2 = (TextView) findViewById(R.id.text_energia2);
        
        Button bt = (Button) findViewById(R.id.boton_multiselect);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = spinner.getSelectedItemsAsString();
                text_energia2.setText(s);
            }
        });
        
        

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
        
      //Carga Usuarios

        asignadoA = (TextView) findViewById(R.id.txt_valor_asignado_a);
        
        GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		
		oportSeleccionada.setCreated_by(u.getId());
		if(!isEditMode){
			
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
        
        
	      //Carga Cuentas
	        if(beanCommunicator != null){
	        	int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE, "Accounts");
	    		valorCuenta.setText(lac.convert(beanCommunicator.id, DataToGet.VALUE ));
	        }
        
    }

    public void createWidgets() {
        valorNombre = (EditText) findViewById(R.id.valor_nombre);
        
        //Carga Cuentas
        valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
        valorCuenta.setOnClickListener(this);
        valorCuenta.setText(ValidatorActivities.SELECT_MESSAGE);
        
        // Usuario Final
        valorUsuario = (EditText) findViewById(R.id.valor_usuario);

        // Fecha Cierre
        botonFechaCierre = (Button) findViewById(R.id.boton_fecha_cierre);
        botonFechaCierre.setOnClickListener(this);
        mValorFechaCierre = (TextView) findViewById(R.id.valor_fecha_cierre);

        valorEstimado = (EditText) findViewById(R.id.valor_estimado);

        valorProbabilidad = (EditText) findViewById(R.id.valor_probabilidad);

        // Paso
        valorPaso = (EditText) findViewById(R.id.valor_paso);
        // Descripcion
        valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
       
       
        // Eventos
        // Guardar Tarea
        imgButtonGuardar.setOnClickListener(this);
 
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
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_SOURCE, oportSeleccionada.getFuente_c());
        valorFuente.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_ENERGY, oportSeleccionada.getEnergia_c());
        valorEnergia.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_COMUNICATIONS, oportSeleccionada.getComunicaciones_c());
        valorComunicaciones.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_ILUM, oportSeleccionada.getIluminacion_c());
        valorIluminacion.setSelection(pos);
        
        pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_CURRENCY, oportSeleccionada.getAmount_usdollar());
        valorMoneda.setSelection(pos);
        
        // Cuenta
        if(oportSeleccionada.getIdAccount() != null){
        	valorCuenta.setText(lac.convert(oportSeleccionada.getIdAccount(), DataToGet.VALUE));
        }
        //Campaña
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        pos = Integer.parseInt(lcc.convert(oportSeleccionada.getCampaign_id(), DataToGet.POS ));
        valorCampana.setSelection(pos);
        
        // Asignado
        asignadoA.setText(lc.convert(oportSeleccionada.getAssigned_user_id(), DataToGet.VALUE ));
        imgButtonGuardar.setVisibility(View.VISIBLE);
     
    }
    
	@Override
	public void onClick(View v) {
	try{
		if(v.getId() == asignadoA.getId()){
			if(isEditMode){
				switch(tipoPermiso){
				case OWNER:
					break;
				case ALL:
					Message.showUsersDialog(getSupportFragmentManager(),v.getId());
					break;
				case GROUP:
					break;
				}
			}else{
				Message.showUsersDialog(getSupportFragmentManager(),v.getId());
			}
			
		}else if(v.getId() == botonFechaCierre.getId()){
			DialogFragment newFragment = new DatePickerFragment(this,mValorFechaCierre,isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		}else if(v.getId() == valorCuenta.getId()){
			Message.showGenericDialog(getSupportFragmentManager(), 
					lac, Modules.ACCOUNTS);
			
		}else if(v.getId() == imgButtonGuardar.getId()){
	        //Realizar Validaciones
			if(!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())){
				return;
			}
			imgButtonGuardar.setVisibility(View.INVISIBLE);
	        // Nombre
	        oportSeleccionada.setName(valorNombre.getText().toString());
	        
	        oportSeleccionada.setTipo_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
	 
	        oportSeleccionada.setSales_stage(ListsConversor.convert(ConversorsType.OPPORTUNITY_STAGE, valorEtapa.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Cuenta
	        oportSeleccionada.setIdAccount(lac.convert(valorCuenta.getText().toString(), DataToGet.CODE));
	      
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
	        
	        if(valorFuente.getSelectedItem() != null){
	        	oportSeleccionada.setFuente_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_SOURCE, valorFuente.getSelectedItem().toString(), DataToGet.CODE));
	        }
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
		
	 }catch(Exception e){
      	  Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddOpportunityActivity.this, MODULE );
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
	public void onFinishSearchDialog(GenericBean selectedBean, int elementId) {
		if(selectedBean instanceof User){
			User su = (User) selectedBean;
			asignadoA.setText(su.getUser_name());
		}else if(selectedBean instanceof Account){
			Account ac = (Account) selectedBean;
			valorCuenta.setText(ac.getName());
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
                resultado = null;
                
                if(isEditMode){
                	resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean(),Modo.EDITAR, AddOpportunityActivity.this);
                }else{
                   	resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean(),Modo.AGREGAR, AddOpportunityActivity.this);
                }

                if(resultado.contains("OK")){
                	op.id = Utils.getIDFromBackend(resultado);
                	Oportunidad oportunidad = new Oportunidad( op );
                	
                	oportunidad.accept(new DataVisitorsManager());
                	ActivitiesMediator.getInstance().addObjectInfo(op);
                	
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
            mTareaCrearOportunidad = null;
            if (success) {
            	 if(isEditMode){
            		 Message.showFinalMessage(getFragmentManager(),DialogType.EDITED, AddOpportunityActivity.this, MODULE );
            		 
            	 }else{
            		 Message.showFinalMessage(getFragmentManager(),DialogType.CREATED, AddOpportunityActivity.this, MODULE );
            		 
            	 }
            	 
            } else {
            	if(isEditMode){
           		 Message.showFinalMessage(getFragmentManager(),DialogType.NO_EDITED, AddOpportunityActivity.this, MODULE );
           		 
           	 }else{
           		 Message.showFinalMessage(getFragmentManager(), resultado, AddOpportunityActivity.this, MODULE );
           		// Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED, AddOpportunityActivity.this, MODULE );
           		 
           	 }
               
            }
          
        }

        @Override
        protected void onCancelled() {
            mTareaCrearOportunidad = null;
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
