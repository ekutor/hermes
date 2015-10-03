package com.co.iatech.crm.sugarmovil.activities;

import java.util.Calendar;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCampaignsConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;




public class AddOpportunityActivity extends AppCompatActivity implements View.OnClickListener {

 
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
    private boolean modoEdicion;
    private String idCuentaAsociada;

    
    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonGuardar;
    private static TextView mValorFechaCierre;
    private EditText valorNombre,valorUsuario,valorEstimado,valorProbabilidad,valorFuente,valorPaso,valorDescripcion;
    private Spinner valorTipo,valorEtapa,valorMedio,valorEnergia,valorComunicaciones,valorIluminacion,valorMoneda;
    private Spinner mValorCuenta, mValorCampana, mValorAsignadoA;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opportunity);
        
        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent intent = getIntent();
//        oportSeleccionada = (OportunidadDetalle) intent.getExtras().get(Info.OPORTUNIDAD_SELECCIONADA.name());
        idCuentaAsociada = intent.getStringExtra(Info.CUENTA_ACTUAL.name());
        Log.d(TAG, "idCuentaAsociada " + idCuentaAsociada);
        oportSeleccionada = intent.getParcelableExtra(Info.OPORTUNIDAD_SELECCIONADA.name());
        
        if(oportSeleccionada != null){
        	modoEdicion = true;
        	Log.d(TAG, "Oportunidad Recibida " + oportSeleccionada);
        }
        Log.d(TAG, "Modo Edicion " + modoEdicion);
        // Main Toolbar
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
        setSupportActionBar(mCuentaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

        chargeLists();
        createWidgets();
        if(modoEdicion){
        	chargeValues();
        }
    }
    
    
    private void showMessage(CharSequence text){
    	 int duration = Toast.LENGTH_SHORT;
    	 Context context = getApplicationContext();
         Toast toast = Toast.makeText(context, text, duration);
         toast.setGravity(Gravity.CENTER, 0, 20);
         toast.show();
    }
    
    private void chargeLists() {
    	//Tipo Oportunidad
        valorTipo = (Spinner) findViewById(R.id.valor_tipo);
        
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_PROYECT));
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        valorTipo.setAdapter(estadoAdapter);
        
      //Etapa Oportunidad
        valorEtapa = (Spinner) findViewById(R.id.valor_etapa);
        ArrayAdapter<String> etapaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_STAGE));
        etapaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEtapa.setAdapter(etapaAdapter);
      
        // Medio Oportunidad
        valorMedio = (Spinner) findViewById(R.id.valor_medio);
        ArrayAdapter<String> medioAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_MEDIUM));
        medioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMedio.setAdapter(medioAdapter);
        
        //Energia
        valorEnergia = (Spinner) findViewById(R.id.valor_energia);       
        ArrayAdapter<String> energiaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ENERGY));
        energiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEnergia.setAdapter(energiaAdapter);

        // Comunicaciones
        valorComunicaciones = (Spinner) findViewById(R.id.valor_comunicaciones);
        ArrayAdapter<String> comunicacionesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_COMUNICATIONS));
        comunicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorComunicaciones.setAdapter(comunicacionesAdapter);
        
        // Iluminacion
        valorIluminacion = (Spinner) findViewById(R.id.valor_iluminacion);
        ArrayAdapter<String> iluminacionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ILUM));
        iluminacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorIluminacion.setAdapter(iluminacionAdapter);        
        
        // Moneda
        valorMoneda = (Spinner) findViewById(R.id.valor_moneda);
        ArrayAdapter<String> monedaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_CURRENCY));
        monedaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMoneda.setAdapter(monedaAdapter);
        
      //Carga Cuentas
        mValorCuenta = (Spinner) findViewById(R.id.valor_cuenta);
        ListAccountConverter lac = new ListAccountConverter();
        ArrayAdapter<String> cuentaAdapter = new ArrayAdapter<>(AddOpportunityActivity.this,
                android.R.layout.simple_spinner_item,  lac.getListInfo());
        mValorCuenta.setAdapter(cuentaAdapter);
        if(idCuentaAsociada != null){
        	String nombreCuenta = lac.convert(idCuentaAsociada, DataToGet.VALUE);
        	mValorCuenta.setSelection(lac.getListInfo().indexOf(nombreCuenta));
        }
        
      //Carga Usuarios
        mValorAsignadoA = (Spinner) findViewById(R.id.valor_asignado_a);
        ListUsersConverter luc = new ListUsersConverter();
        ArrayAdapter<String> usuarioAdapter = new ArrayAdapter<>(AddOpportunityActivity.this,
                android.R.layout.simple_spinner_item,  luc.getListInfo());
        mValorAsignadoA.setAdapter(usuarioAdapter);
        
      //Carga Campañas
        mValorCampana = (Spinner) findViewById(R.id.valor_campana);
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        ArrayAdapter<String> campAdapter = new ArrayAdapter<>(AddOpportunityActivity.this,
                android.R.layout.simple_spinner_item,  lcc.getListInfo());
        mValorCampana.setAdapter(campAdapter);
        mValorCampana.setSelection(campAdapter.getCount()-1);
        
    }

    public void createWidgets() {
        valorNombre = (EditText) findViewById(R.id.valor_nombre);

        // Usuario Final
        valorUsuario = (EditText) findViewById(R.id.valor_usuario);

        // Fecha Cierre
        Button botonFechaInicio = (Button) findViewById(R.id.boton_fecha_cierre);
        botonFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerCierreFragment();
                newFragment.show(getFragmentManager(), "dateCierrePicker");
            }
        });
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
        mValorFechaCierre.setText(oportSeleccionada.getDate_closed());
        valorEstimado.setText(oportSeleccionada.getAmount());
        valorProbabilidad.setText(oportSeleccionada.getProbability());
        // Paso
        valorPaso.setText(oportSeleccionada.getNext_step());
        // Descripcion
        valorDescripcion.setText(oportSeleccionada.getDescription());
     
       // valorEstimado.setText(oportSeleccionada.getValoroportunidad_c());
      
        valorPaso.setText(oportSeleccionada.getNext_step());
  
        
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
        mValorCuenta.setSelection(pos);
        
        //Campaña
        ListCampaignsConverter lcc = new ListCampaignsConverter();
        pos = Integer.parseInt(lac.convert(oportSeleccionada.getCampaign_id(), DataToGet.POS ));
        mValorCampana.setSelection(pos);
        
        // Asignado
        ListUsersConverter lc = new ListUsersConverter();
        pos = Integer.parseInt(lac.convert(oportSeleccionada.getAssigned_user_id(), DataToGet.POS ));
        mValorAsignadoA.setSelection(pos);
     
    }
    

    public static class DatePickerCierreFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            mValorFechaCierre.setText(new StringBuilder()
                    .append(year).append("-").append(month + 1).append("-")
                    .append(day).append(" "));
        }
    }

    /**
     * Clase para el mensaje de creacion de oportunidad.
     */
    @SuppressLint("ValidFragment")
    public class CrearOpportunityDialogFragment extends DialogFragment {
    	private int tipo;
    	
    	public CrearOpportunityDialogFragment( int tipo ){
    		this.tipo = tipo ;
    	}
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            int mensaje, titulo;
            if(tipo == 1){
            	titulo = R.string.dialog_activity_opportunity;
            	mensaje = R.string.dialog_message_opportunity; 
            	
            }else{
            	titulo = R.string.dialog_activity_opportunity_error;
            	mensaje = R.string.dialog_message_opportunity_error; 
            }
            builder.setTitle(titulo)
                    .setMessage(mensaje)
                    .setPositiveButton(R.string.dialog_ok_opportunity, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AddOpportunityActivity.this.finish();
                        }
                    });

            return builder.create();
        }
    }

    /**
     * Representa una tarea asincrona de creacion de oportunidad.
     */
    public class AddOpportunityTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                // Parametros
                String idOpportunity = "idOpportunity";
                OportunidadDetalle op = (OportunidadDetalle)params[0];

                // Resultado
                String resultado = null;
                
                if(modoEdicion){
                	modoEdicion = false;
                	
                	resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean(),Modo.EDITAR);
                }else{
                	ControlConnection.addHeader("modo", "agregar");
                	resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean(),Modo.AGREGAR);
                }
                Log.d(TAG, "Crear Oportunidad RESPr: "+ resultado);
                if(resultado.contains("OK")){
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

            if (success) {
                Log.d(TAG, "Crear Oportunidad exitoso");
                CrearOpportunityDialogFragment newFragment = new CrearOpportunityDialogFragment(1);
                newFragment.show(getFragmentManager(), "createOpportunity");
            } else {
            	 CrearOpportunityDialogFragment newFragment = new CrearOpportunityDialogFragment(2);
                 newFragment.show(getFragmentManager(), "createOpportunity");
                Log.d(TAG, "Crear Oportunidad error");
            }
        }

        @Override
        protected void onCancelled() {
            mTareaCrearOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
    }

	@Override
	public void onClick(View v) {
		if(v.getId() == mImageButtonGuardar.getId()){
	    	OportunidadDetalle op = new OportunidadDetalle();
	        // Nombre
	        if (valorNombre.getText().toString().equals("")) {
	            
	            CharSequence text = "El campo Nombre no puede estar vacio";
	            showMessage(text);
	            return;
	        }
	        op.setName(valorNombre.getText().toString());
	        
	        op.setTipo_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
	        if (valorTipo.getSelectedItem() == null){
	        	
	            CharSequence text = "Debe seleccionar un Tipo de Oportunidad";
	            showMessage(text);
	            return;
	        }
	        op.setTipo_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
	        
	        if (valorEtapa.getSelectedItem()== null) {
	            
	            CharSequence text = "Debe seleccionar una Etapa de Oportunidad";
	            showMessage(text);
	            return;
	        }
	        op.setSales_stage(ListsConversor.convert(ConversorsType.OPPORTUNITY_STAGE, valorEtapa.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Cuenta
	        
	        if (mValorCuenta.getSelectedItem() == null ||
	        		mValorCuenta.getSelectedItemPosition() == mValorCuenta.getCount() -1) {
	        	 CharSequence text = "Debe seleccionar una Cuenta ";
	             showMessage(text);
	             return;
	        	
	        }
	        ListAccountConverter lac = new ListAccountConverter();
	        op.setIdAccount(lac.convert(mValorCuenta.getSelectedItem().toString(), DataToGet.CODE));
	
	        
	        try{
	        	// Fecha Cierre
	            if(mValorFechaCierre.getText().toString() != null && mValorFechaCierre.getText().toString().length() > 1){
	            	op.setDate_closed(mValorFechaCierre.getText().toString());
	            }
	            
			}catch(java.lang.NullPointerException ne){
				
			}
	//                // Camapana
	        if (mValorCampana.getSelectedItem() == null ||
	        		mValorCampana.getSelectedItemPosition() == mValorCampana.getCount() -1) {
	        	 CharSequence text = "Debe seleccionar una Campaña ";
	             showMessage(text);
	             return;
	        	
	        }
	        Log.d(TAG, "posicionCampana Sel: "+mValorCampana.getSelectedItemPosition() );
	        ListCampaignsConverter lcc = new ListCampaignsConverter();
	        op.setCampaign_id(lcc.convert(mValorCampana.getSelectedItem().toString(), DataToGet.CODE));
	        
	        // Medio              
	        if (valorMedio.getSelectedItem() == null) {
	           
	            CharSequence text = "Debe seleccionar un Medio ";
	            showMessage(text);
	            return;
	        }
	        op.setMedio_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM, valorMedio.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Asignado
	        ListUsersConverter lc = new ListUsersConverter();
	        String userSel = mValorAsignadoA.getSelectedItem().toString();
	        op.setAssigned_user_id(lc.convert(userSel, DataToGet.CODE));
	        Log.d("AddOportunity",userSel);
	        // Marca energia
	        op.setEnergia_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_ENERGY, valorEnergia.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Marca comunicaciones
	        op.setComunicaciones_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_COMUNICATIONS, valorComunicaciones.getSelectedItem().toString(), DataToGet.CODE));
	
	        // Marca iluminacion
	        op.setIluminacion_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_ILUM, valorIluminacion.getSelectedItem().toString(), DataToGet.CODE));
	        
	        //Moneda
	        
	        if (valorEstimado.getText().toString().equals("")) {
	            
	            CharSequence text = "El Valor Estimado no puede estar vacio";
	            showMessage(text);
	            return;
	        }
	        
	        op.setAmount_usdollar(ListsConversor.convert(ConversorsType.OPPORTUNITY_CURRENCY, valorMoneda.getSelectedItem().toString(), DataToGet.CODE));
	        op.setAmount(valorEstimado.getText().toString());
	        mTareaCrearOportunidad = new AddOpportunityTask();
	        
	        op.setCreated_by(ControlConnection.userId);
	        //op.setUsuario_final_c(usuario_final_c)
	        op.setValoroportunidad_c(valorEstimado.getText().toString());
	        op.setProbability(valorProbabilidad.getText().toString());
	        op.setNext_step(valorPaso.getText().toString());
	        op.setDescription(valorDescripcion.getText().toString());
	        
	        // Crear oportunidad
	        mTareaCrearOportunidad.execute(op);
		}
    }

}
