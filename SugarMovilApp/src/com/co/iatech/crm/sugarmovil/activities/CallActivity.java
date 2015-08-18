package com.co.iatech.crm.sugarmovil.activities;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
import com.co.iatech.crm.sugarmovil.adapters.RecyclerCallsAdapter;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;


public class CallActivity extends AppCompatActivity {


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
    private String mUrl;
    private String mIdLlamada;
    private Llamada mLlamadaDetalle;

    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;
    private ImageButton mImageButtonEdit;
    private LinearLayout mLayoutContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Variable Global
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        mUrl = globalVariable.getUrl();
        Log.d(TAG, mUrl);

        Intent intent = getIntent();
        mIdLlamada = intent.getStringExtra(Info.ID_LLAMADA.name());
        Log.d(TAG, "Id llamada " + mIdLlamada);

        // Main Toolbar
        mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
        setSupportActionBar(mLlamadaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);

        // Contenido
        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);

        //Eventos
        mImageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Editar llamada ");
                // Edit Call Activity
                Intent intentEditarLlamada = new Intent(CallActivity.this,
                        EditCallActivity.class);
                intentEditarLlamada.putExtra(Info.ID_LLAMADA_ACTUAL.name(), mLlamadaDetalle);
                startActivity(intentEditarLlamada);
            }
        });

        // Tarea obtener llamada
        mTareaObtenerLlamada = new GetCallTask();
        mTareaObtenerLlamada.execute(String.valueOf(mIdLlamada));
    }

    public void ponerValores(Llamada llamadaDetalle) {
        TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
        valorAsunto.setText(llamadaDetalle.getName());
        TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
        valorEstado.setText(llamadaDetalle.getStatus());
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

    /**
     * Representa una tarea asincrona de obtencion de llamada.
     */
    public class GetCallTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CallActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando información llamada...");
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
                HttpClient httpClientAccount = new DefaultHttpClient();
                HttpGet httpGetAccount = new HttpGet(mUrl
                        + "getCall");
                httpGetAccount.setHeader("idCall", idLlamada);

                try {
                    HttpResponse response = httpClientAccount
                            .execute(httpGetAccount);
                    call = EntityUtils.toString(response
                            .getEntity());
                    call = call.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Llamada Response: "
                            + call);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                JSONObject jObj = new JSONObject(call);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    

                    mLlamadaDetalle = new Llamada(obj);
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
                ponerValores(mLlamadaDetalle);
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerLlamada = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
