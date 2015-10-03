package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.AddOpportunityActivity.AddOpportunityTask;
import com.co.iatech.crm.sugarmovil.activities.AddOpportunityActivity.DatePickerCierreFragment;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.LanguageType;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCampaignsConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;


public class AddCallActivity extends AppCompatActivity {


    /**
     * Debug.
     */
    private static final String TAG = "AddCallActivity";


    /**
     * Member Variables.
     */

    private String idCuentaAsociada;
    private boolean modoEdicion;
    private Llamada llamdaSeleccionada;

    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;
    private ImageButton mImageButtonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_call);

        Intent intent = getIntent();
        idCuentaAsociada = intent.getStringExtra(Info.CUENTA_ACTUAL.name());
        llamdaSeleccionada = intent.getParcelableExtra(Info.LLAMADA_SELECCIONADA.name());      
        Log.d(TAG, "idCuentaAsociada " + idCuentaAsociada);
        
        if(llamdaSeleccionada != null){
        	modoEdicion = true;
        	Log.d(TAG, "Modo Edicion" + llamdaSeleccionada.getId());
        }
      
        // Main Toolbar
        mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
        setSupportActionBar(mLlamadaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

//        chargeLists();
//        createWidgets();
    }
    
    private void showMessage(CharSequence text){
   	 int duration = Toast.LENGTH_SHORT;
   	 Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 20);
        toast.show();
   }
   /* private void chargeLists() {
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
        mImageButtonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
        });
    }
    public void ponerValores(Llamada llamadaDetalle) {
        TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
        valorAsunto.setText(llamadaDetalle.getName());
        TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
        valorEstado.setText(llamadaDetalle.getStatus(LanguageType.SPANISH));
        TextView valorInicio = (TextView) findViewById(R.id.valor_inicio);
        valorInicio.setText(llamadaDetalle.getDate_start());
        TextView valorDuracion = (TextView) findViewById(R.id.valor_duracion);
        valorDuracion.setText(llamadaDetalle.getDuration_minutes());
        TextView valorResultado = (TextView) findViewById(R.id.valor_resultado);
        valorResultado.setText(llamadaDetalle.getResultadodelallamada_c());
        TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(llamadaDetalle.getDescription());
        TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
        valorAsignado.setText(llamadaDetalle.getAssigned_user_name());
        TextView valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
        valorCuenta.setText(llamadaDetalle.getParent_name());
        TextView valorCampana = (TextView) findViewById(R.id.valor_campana);
        valorCampana.setText(llamadaDetalle.getCampaign_name());
    }

   */
}
