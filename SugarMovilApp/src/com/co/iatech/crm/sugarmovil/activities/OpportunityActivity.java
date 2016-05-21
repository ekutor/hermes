package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.TaskActivity.GetTaskTask;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.OpportunitiesModuleActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
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
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class OpportunityActivity extends AppCompatActivity implements OpportunitiesModuleActions{

    /**
     * Debug.
     */
    private static final String TAG = "OpportunityActivity";

    /**
     * Tasks.
     */
    private GetOpportunityTask mTareaObtenerOportunidad = null;

    /**
     * Member Variables.
     */
    private String mIdOportunidad;
	protected ImageButton imageButtonEdit, imageButtonTasks;
	private OportunidadDetalle oportunidadDetalle;
	
    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;

    private ListUsersConverter lc = new ListUsersConverter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity);
try{
        Intent intent = getIntent();
        mIdOportunidad = intent.getStringExtra(MODULE.name());
      
        Log.d(TAG, "Id oportunidad " + mIdOportunidad);

        // Main Toolbar
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
        setSupportActionBar(mCuentaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        
        this.applyActions();
		
        
        if(intent.getExtras().get(MODULE.getModuleName()) instanceof  OportunidadDetalle ){
        	oportunidadDetalle = (OportunidadDetalle) intent.getExtras().get(MODULE.getModuleName());
        	this.ponerValores( oportunidadDetalle );
        }else{
        	mIdOportunidad = intent.getStringExtra(MODULE.name());

	        mTareaObtenerOportunidad = new GetOpportunityTask();
	        mTareaObtenerOportunidad.execute(String.valueOf(mIdOportunidad));
        }
        
}catch(Exception e){
	   Message.showShortExt(Utils.errorToString(e), this);
 }
    }


	public void ponerValores(OportunidadDetalle oportunidadDetalle) {
        TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
        valorNombre.setText(oportunidadDetalle.getName());
        
        TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
        valorTipo.setText(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT,oportunidadDetalle.getTipo_c(), DataToGet.VALUE));
        
        TextView valorEtapa = (TextView) findViewById(R.id.valor_etapa);
        valorEtapa.setText(oportunidadDetalle.getSales_stage());
        TextView valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
        valorCuenta.setText(oportunidadDetalle.getNameAccount());
        TextView valorUsuario = (TextView) findViewById(R.id.valor_usuario);
        valorUsuario.setText(oportunidadDetalle.getUsuario_final_c());
        TextView valorFecha = (TextView) findViewById(R.id.valor_fecha);
        valorFecha.setText(Utils.transformTimeBakendToUI(oportunidadDetalle.getDate_closed()));
        TextView valorEstimado = (TextView) findViewById(R.id.valor_estimado);
        valorEstimado.setText(Utils.addSepMiles(oportunidadDetalle.getValoroportunidad_c()));
        TextView valorProbabilidad = (TextView) findViewById(R.id.valor_probabilidad);
        valorProbabilidad.setText(oportunidadDetalle.getProbability());
        TextView valorCampana = (TextView) findViewById(R.id.valor_campana);
        valorCampana.setText(oportunidadDetalle.getNameCampaign());
        
        TextView valorMedio = (TextView) findViewById(R.id.valor_medio);
        valorMedio.setText(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM,oportunidadDetalle.getMedio_c(), DataToGet.VALUE));
        
        TextView valorFuente = (TextView) findViewById(R.id.valor_fuente);
        valorFuente.setText(oportunidadDetalle.getFuente_c());
        TextView valorPaso = (TextView) findViewById(R.id.valor_paso);
        valorPaso.setText(oportunidadDetalle.getNext_step());
        TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(oportunidadDetalle.getDescription());
        TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
        
        valorAsignado.setText(lc.convert(oportunidadDetalle.getAssigned_user_id(), DataToGet.VALUE ));
        
        TextView valorEnergia = (TextView) findViewById(R.id.valor_energia);
        valorEnergia.setText(oportunidadDetalle.getEnergia_c());
        TextView valorComunicaciones = (TextView) findViewById(R.id.valor_comunicaciones);
        valorComunicaciones.setText(oportunidadDetalle.getComunicaciones_c());
        TextView valorIluminacion = (TextView) findViewById(R.id.valor_iluminacion);
        valorIluminacion.setText(oportunidadDetalle.getIluminacion_c());
        
        TextView valorMoneda = (TextView) findViewById(R.id.valor_moneda);
        valorMoneda.setText(ListsConversor.convert(ConversorsType.OPPORTUNITY_CURRENCY,
        		oportunidadDetalle.getAmount_usdollar(), DataToGet.VALUE));
        
    }

    /**
     * Representa una tarea asincrona de obtencion de oportunidad.
     */
    public class GetOpportunityTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(OpportunityActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando informaci�n oportunidad...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idOportunidad = params[0];

                // Respuesta
                String resultado = null;

                // Intento de obtener cuenta
                ControlConnection.addHeader("idOpportunity", idOportunidad);
                resultado  = ControlConnection.getInfo(TypeInfoServer.getOpportunity, OpportunityActivity.this);

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    oportunidadDetalle = new OportunidadDetalle(obj);
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Oportunidad Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerOportunidad = null;
            progressDialog.dismiss();

            if (success) {
                ponerValores(oportunidadDetalle);
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
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
		return oportunidadDetalle.getAssigned_user_id();
	}


	@Override
	public Parcelable getBean() {
		return oportunidadDetalle;
	}


	@Override
	public void applyActions() {
		imageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);  

        imageButtonTasks = (ImageButton) findViewById(R.id.image_tasks);
		imageButtonTasks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivitiesMediator.getInstance().setActualID( mIdOportunidad , MODULE);
				ActivitiesMediator.getInstance().showList(OpportunityActivity.this, Modules.TASKS, true);
				
			}

		});
		
		ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());
	}
	
	 @Override
	    public void onResume() {
	    	try{
	    		oportunidadDetalle = (OportunidadDetalle) ActivitiesMediator.getInstance().getBeanInfo();
		    	if(oportunidadDetalle != null){
		    		this.ponerValores(oportunidadDetalle);
		    	}
	    	}catch(Exception e){
	    		
	    	}
	        super.onResume();

	    }


	@Override
	public boolean chargeIdPreviousModule() {
		return true;
	}

}
