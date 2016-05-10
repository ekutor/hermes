package com.co.iatech.crm.sugarmovil.activities;

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
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.software.shell.fab.ActionButton;


public class CallActivity extends AppCompatActivity implements CallsModuleActions{


    /**
     * Debug.
     */
    private static final String TAG = "CallActivity";

    /**
     * Tasks.
     */
    private GetCallTask mTareaObtenerLlamada = null;

    /**
     * Member Variables.
     */

    private String idLlamada;
    private Llamada llamadaDetalle;
    private ListUsersConverter lc = new ListUsersConverter();
    
    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;
    private ImageButton imageButtonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Intent intent = getIntent();
        idLlamada = intent.getStringExtra(Info.ID.name());
        Log.d(TAG, "Id llamada " + idLlamada);

        // Main Toolbar
        mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
        setSupportActionBar(mLlamadaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        
        this.applyActions();
        // Tarea obtener llamada
        mTareaObtenerLlamada = new GetCallTask();
        mTareaObtenerLlamada.execute(String.valueOf(idLlamada));
    }

    public void ponerValores(Llamada llamadaDetalle) {
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

    /**
     * Representa una tarea asincrona de obtencion de llamada.
     */
    public class GetCallTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CallActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando informacion llamada...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idLlamada = params[0];

                // Respuesta
                String call = null;

                // Intento de obtener cuenta
                ControlConnection.addHeader("idCall", idLlamada);
                call  = ControlConnection.getInfo(TypeInfoServer.getCall, CallActivity.this);

                JSONObject jObj = new JSONObject(call);

                JSONArray jArr = jObj.getJSONArray("results");
                if(jArr.length() > 0) {
                    JSONObject obj = jArr.getJSONObject(0);

                    llamadaDetalle = new Llamada(obj);
                    }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Llamada Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerLlamada = null;
            progressDialog.dismiss();

            if (success) {
                ponerValores(llamadaDetalle);
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerLlamada = null;
            Log.d(TAG, "Cancelado ");
        }
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
		return llamadaDetalle.getAssigned_user_id();
	}


	@Override
	public Parcelable getBean() {
		return llamadaDetalle;
	}
}
