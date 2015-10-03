package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;


public class OpportunityActivity extends AppCompatActivity {


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
    private String cuentaAsociada;
    private OportunidadDetalle mOportunidadDetalle;

    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonEdit;
    private LinearLayout mLayoutContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity);

        Intent intent = getIntent();
        mIdOportunidad = intent.getStringExtra(Info.OPORTUNIDAD_SELECCIONADA.name());
        cuentaAsociada = intent.getStringExtra(Info.CUENTA_ACTUAL.name());
        
        Log.d(TAG, "Id cuenat Asociada " + cuentaAsociada);
        Log.d(TAG, "Id oportunidad " + mIdOportunidad);

        // Main Toolbar
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
        setSupportActionBar(mCuentaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);

        // Contenido
        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);

        //Eventos
        mImageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Editar oportunidad ");
                // Edit Oportunidad Activity
                Intent intentEditarOportunidad = new Intent(OpportunityActivity.this,
                        AddOpportunityActivity.class);
          
                intentEditarOportunidad.putExtra(Info.OPORTUNIDAD_SELECCIONADA.name(), mOportunidadDetalle);
                startActivity(intentEditarOportunidad);
            }
        });

        // Tarea obtener cuenta
        mTareaObtenerOportunidad = new GetOpportunityTask();
        mTareaObtenerOportunidad.execute(String.valueOf(mIdOportunidad));
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
        valorFecha.setText(oportunidadDetalle.getDate_closed());
        TextView valorEstimado = (TextView) findViewById(R.id.valor_estimado);
        valorEstimado.setText(oportunidadDetalle.getValoroportunidad_c());
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
        valorAsignado.setText(oportunidadDetalle.getAssigned_user_name());
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
            progressDialog.setMessage("Cargando información oportunidad...");
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
                resultado  = ControlConnection.getInfo(TypeInfoServer.getOpportunity);

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    mOportunidadDetalle = new OportunidadDetalle(obj);
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
                ponerValores(mOportunidadDetalle);
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
