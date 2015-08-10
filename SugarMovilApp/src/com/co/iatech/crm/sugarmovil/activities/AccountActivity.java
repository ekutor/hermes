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
import com.co.iatech.crm.sugarmovil.adapters.RecyclerAccountsAdapter;
import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;


public class AccountActivity extends AppCompatActivity {

    /**
     * Extras
     */
    //public final static String EXTRA_ACCOUNT = "com.taktil.crm.laumayer.ACCOUNT";

    /**
     * Debug.
     */
    private static final String TAG = "AccountActivity";

    /**
     * Tasks.
     */
    private GetAccountTask mTareaObtenerCuenta = null;

    /**
     * Member Variables.
     */
    private String mUrl;
    private String mIdCuenta;
    private CuentaDetalle mCuentaDetalle;

    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonEdit;
    private LinearLayout mLayoutContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        
     // Variable Global
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        mUrl = globalVariable.getUrl();
        Log.d(TAG, mUrl);
        Intent intent = getIntent();
        mIdCuenta = intent.getStringExtra(RecyclerAccountsAdapter.EXTRA_ID_CUENTA);
        Log.d(TAG, "Id cuenta " + mIdCuenta);
        
        
        // Main Toolbar
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_account);
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
                Log.d(TAG, "Editar cuenta ");
                // Create Account Activity
                // TODO
            }
        });

        // Tarea obtener cuenta
        mTareaObtenerCuenta = new GetAccountTask();
        mTareaObtenerCuenta.execute(String.valueOf(mIdCuenta));
    }
    
    
    public void ponerValores(CuentaDetalle cuentaDetalle) {
        TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
        valorRazon.setText(cuentaDetalle.getName());
        TextView valorNit = (TextView) findViewById(R.id.valor_nit);
        valorNit.setText(cuentaDetalle.getNit_c());
        TextView valorCodigo = (TextView) findViewById(R.id.valor_codigo);
        valorCodigo.setText(cuentaDetalle.getCod_alterno_c());
        TextView valorCanal = (TextView) findViewById(R.id.valor_canal);
        valorCanal.setText(cuentaDetalle.getCanal_c());
        TextView valorSector = (TextView) findViewById(R.id.valor_sector);
        valorSector.setText(cuentaDetalle.getSector_c());
        TextView valorTel1 = (TextView) findViewById(R.id.valor_tel1);
        valorTel1.setText(cuentaDetalle.getPhone_office());
        TextView valorExt1 = (TextView) findViewById(R.id.valor_ext1);
        valorExt1.setText(cuentaDetalle.getExtension1_c());
        TextView valorTel2 = (TextView) findViewById(R.id.valor_tel2);
        valorTel2.setText(cuentaDetalle.getPhone_alternate());
        TextView valorExt2 = (TextView) findViewById(R.id.valor_ext2);
        valorExt2.setText(cuentaDetalle.getExtension2_c());
        TextView valorCelular = (TextView) findViewById(R.id.valor_celular);
        valorCelular.setText(cuentaDetalle.getCelular_c());
        TextView valorFax = (TextView) findViewById(R.id.valor_fax);
        valorFax.setText(cuentaDetalle.getPhone_fax());
        TextView valorDireccion = (TextView) findViewById(R.id.valor_direccion);
        valorDireccion.setText(cuentaDetalle.getDireccion_c());
        TextView valorMunicipio = (TextView) findViewById(R.id.valor_municipio);
        valorMunicipio.setText(cuentaDetalle.getMunicipio_c());
        TextView valorDepartamento = (TextView) findViewById(R.id.valor_departamento);
        switch (cuentaDetalle.getDepartamento_c()){

        }
        valorDepartamento.setText(cuentaDetalle.getDepartamento_c());

        TextView valorZona = (TextView) findViewById(R.id.valor_zona);
        valorZona.setText(cuentaDetalle.getZona_c());
        TextView valorUen = (TextView) findViewById(R.id.valor_uen);
        valorUen.setText(cuentaDetalle.getUen_c());
        TextView valorEmail = (TextView) findViewById(R.id.valor_email);
        valorEmail.setText(cuentaDetalle.getEmail_address());
        TextView valorWeb = (TextView) findViewById(R.id.valor_web);
        valorWeb.setText(cuentaDetalle.getWebsite());
        TextView valorGrupo = (TextView) findViewById(R.id.valor_grupo);
        valorGrupo.setText(cuentaDetalle.getGrupo_objetivo_c());
        TextView valorSegmento = (TextView) findViewById(R.id.valor_segmento);
        valorSegmento.setText(cuentaDetalle.getSegmento_c());
        TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
        valorEstado.setText(cuentaDetalle.getEstado_c());
        TextView valorDescuento = (TextView) findViewById(R.id.valor_descuento);
        valorDescuento.setText(cuentaDetalle.getDescuentocomercial_c());
        TextView valorPresupuesto = (TextView) findViewById(R.id.valor_presupuesto);
        valorPresupuesto.setText(cuentaDetalle.getPresupuestoanual_c());
        TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(cuentaDetalle.getDescription());
        TextView valorTransporte = (TextView) findViewById(R.id.valor_c_transporte);
        valorTransporte.setText(cuentaDetalle.getCorreotransporte_c());
        TextView valorCreado = (TextView) findViewById(R.id.valor_creado);
        valorCreado.setText(cuentaDetalle.getDate_entered());
        TextView valorUsuario = (TextView) findViewById(R.id.valor_usuario);
        valorUsuario.setText(cuentaDetalle.getAssigned_user_name());
        TextView valorConstitucion = (TextView) findViewById(R.id.valor_constitucion);
        valorConstitucion.setText(cuentaDetalle.getFechaempresa_c());
        TextView valorActual = (TextView) findViewById(R.id.valor_actual);
        valorActual.setText(cuentaDetalle.getVentasactual_c());
        TextView valorAnterior = (TextView) findViewById(R.id.valor_anterior);
        valorAnterior.setText(cuentaDetalle.getVentasanterior_c());
        TextView valorNumeroAlianzas = (TextView) findViewById(R.id.valor_numero_alianzas);
        valorNumeroAlianzas.setText(cuentaDetalle.getNumeroalianzas_c());
        TextView valorAlianzas = (TextView) findViewById(R.id.valor_alianzas);
        valorAlianzas.setText(cuentaDetalle.getAlianzasestrategicas_c());
        TextView valorOrigen = (TextView) findViewById(R.id.valor_origen);
        valorOrigen.setText(cuentaDetalle.getOrigencuenta_c());
        TextView valorFecha = (TextView) findViewById(R.id.valor_fecha);
        valorFecha.setText(cuentaDetalle.getFechafacturacion_c());
        TextView valorDiaria = (TextView) findViewById(R.id.valor_diaria);
        valorDiaria.setText(cuentaDetalle.getFacturaciondiara_c());
        TextView valorAcumulada = (TextView) findViewById(R.id.valor_acumulada);
        valorAcumulada.setText(cuentaDetalle.getFacturacionmes_c());
        TextView valorCumplimiento = (TextView) findViewById(R.id.valor_cumplimiento);
        valorCumplimiento.setText(cuentaDetalle.getPorcentaje_cumplimiento_c());
        TextView valorAutorizada = (TextView) findViewById(R.id.valor_autorizada);
        valorAutorizada.setText(cuentaDetalle.getFacturacionautorizada_c());
        TextView valorNoAutorizada = (TextView) findViewById(R.id.valor_no_autorizada);
        valorNoAutorizada.setText(cuentaDetalle.getFacturacionnoautorizada_c());
        TextView valorFechaDespacho = (TextView) findViewById(R.id.valor_fecha_despacho);
        valorFechaDespacho.setText(cuentaDetalle.getFecha_despacho_c());
        TextView valorRemesa = (TextView) findViewById(R.id.valor_remesa);
        valorRemesa.setText(cuentaDetalle.getRemesa_c());
        TextView valorDestino = (TextView) findViewById(R.id.valor_destino);
        valorDestino.setText(cuentaDetalle.getDestino_c());
        TextView valorDestinatario = (TextView) findViewById(R.id.valor_destinatario);
        valorDestinatario.setText(cuentaDetalle.getNombredestinatario_c());
        TextView valorUnidades = (TextView) findViewById(R.id.valor_unidades);
        valorUnidades.setText(cuentaDetalle.getUnidades_c());
        TextView valorDocumentos = (TextView) findViewById(R.id.valor_documento);
        valorDocumentos.setText(cuentaDetalle.getDocumento_c());
        TextView valorDestinatarioPendientes = (TextView) findViewById(R.id.valor_destinatario_pendientes);
        valorDestinatarioPendientes.setText(cuentaDetalle.getNombredestinatario2_c());
        TextView valorDestinoPendientes = (TextView) findViewById(R.id.valor_destino_pendientes);
        valorDestinoPendientes.setText(cuentaDetalle.getNombredestinatario2_c());
        TextView valorMotivo = (TextView) findViewById(R.id.valor_motivo);
        valorMotivo.setText(cuentaDetalle.getMotivo_c());
        TextView valorCupo = (TextView) findViewById(R.id.valor_cupo);
        valorCupo.setText(cuentaDetalle.getCupodisponible_c());
        TextView valorCupoCr = (TextView) findViewById(R.id.valor_cupo_cr);
        valorCupoCr.setText(cuentaDetalle.getCupocr_c());
        TextView valorTotal = (TextView) findViewById(R.id.valor_total);
        valorTotal.setText(cuentaDetalle.getTotalcartera_c());
        TextView valorCondicion = (TextView) findViewById(R.id.valor_condicion);
        valorCondicion.setText(cuentaDetalle.getCondpago_c());
        TextView valorPlazo = (TextView) findViewById(R.id.valor_plazo);
        valorPlazo.setText(cuentaDetalle.getPlpago_c());
        TextView valorPromedio = (TextView) findViewById(R.id.valor_promedio);
        valorPromedio.setText(cuentaDetalle.getPrompago_c());
        TextView valorVencida = (TextView) findViewById(R.id.valor_vencida);
        valorVencida.setText(cuentaDetalle.getCarteravencida_c());
        TextView valorAVencer = (TextView) findViewById(R.id.valor_a_vencer);
        valorAVencer.setText(cuentaDetalle.getCarteravencer_c());
    }
    
   
    /**
     * Representa una tarea asincrona de obtencion de cuenta.
     */
    public class GetAccountTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AccountActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando informacion de cuenta...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idCuenta = params[0];

                // Respuesta
                String account = null;

                // Intento de obtener cuenta
                HttpClient httpClientAccount = new DefaultHttpClient();
                HttpGet httpGetAccount = new HttpGet(mUrl
                        + "getAccount");
                httpGetAccount.setHeader("idAccount", idCuenta);

                try {
                    HttpResponse response = httpClientAccount
                            .execute(httpGetAccount);
                    account = EntityUtils.toString(response
                            .getEntity());
                    account = account.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Cuenta Response: "
                            + account);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                JSONObject jObj = new JSONObject(account);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                   
                    mCuentaDetalle = new CuentaDetalle(obj);
                    
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Cuenta Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
           // mTareaObtenerCuenta = null;
            progressDialog.dismiss();

            if (success) {
                ponerValores(mCuentaDetalle);
            }
        }

        @Override
        protected void onCancelled() {
           // mTareaObtenerCuenta = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
