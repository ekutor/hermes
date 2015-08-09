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


import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;


public class AccountActivity extends AppCompatActivity {

    /**
     * Extras
     */
    public final static String EXTRA_ACCOUNT = "com.taktil.crm.laumayer.ACCOUNT";

    /**
     * Debug.
     */
    private static final String TAG = "AccountActivity";

    /**
     * Tasks.
     */
   // private GetAccountTask mTareaObtenerCuenta = null;

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
//        setContentView(R.layout.activity_account);
//        
//     // Variable Global
//        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
//        mUrl = globalVariable.getUrl();
//        Log.d(TAG, mUrl);
//        Intent intent = getIntent();
//        
//        
//        // Main Toolbar
//        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_account);
//        setSupportActionBar(mCuentaToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        mImageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);
//        
//        // Contenido
//        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);
//        
//        //Eventos
//        mImageButtonEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "Editar cuenta ");
//                // Create Account Activity
//                // TODO
//            }
//        });
    }
    
    
//    public void ponerValores(CuentaDetalle cuentaDetalle) {
//        TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
//        valorRazon.setText(cuentaDetalle.getName());
//        TextView valorNit = (TextView) findViewById(R.id.valor_nit);
//        valorNit.setText(cuentaDetalle.getNit_c());
//        TextView valorCodigo = (TextView) findViewById(R.id.valor_codigo);
//        valorCodigo.setText(cuentaDetalle.getCod_alterno_c());
//        TextView valorCanal = (TextView) findViewById(R.id.valor_canal);
//        valorCanal.setText(cuentaDetalle.getCanal_c());
//        TextView valorSector = (TextView) findViewById(R.id.valor_sector);
//        valorSector.setText(cuentaDetalle.getSector_c());
//        TextView valorTel1 = (TextView) findViewById(R.id.valor_tel1);
//        valorTel1.setText(cuentaDetalle.getPhone_office());
//        TextView valorExt1 = (TextView) findViewById(R.id.valor_ext1);
//        valorExt1.setText(cuentaDetalle.getExtension1_c());
//        TextView valorTel2 = (TextView) findViewById(R.id.valor_tel2);
//        valorTel2.setText(cuentaDetalle.getPhone_alternate());
//        TextView valorExt2 = (TextView) findViewById(R.id.valor_ext2);
//        valorExt2.setText(cuentaDetalle.getExtension2_c());
//        TextView valorCelular = (TextView) findViewById(R.id.valor_celular);
//        valorCelular.setText(cuentaDetalle.getCelular_c());
//        TextView valorFax = (TextView) findViewById(R.id.valor_fax);
//        valorFax.setText(cuentaDetalle.getPhone_fax());
//        TextView valorDireccion = (TextView) findViewById(R.id.valor_direccion);
//        valorDireccion.setText(cuentaDetalle.getDireccion_c());
//        TextView valorMunicipio = (TextView) findViewById(R.id.valor_municipio);
//        valorMunicipio.setText(cuentaDetalle.getMunicipio_c());
//        TextView valorDepartamento = (TextView) findViewById(R.id.valor_departamento);
//        switch (cuentaDetalle.getDepartamento_c()){
//
//        }
//        valorDepartamento.setText(cuentaDetalle.getDepartamento_c());
//
//        TextView valorZona = (TextView) findViewById(R.id.valor_zona);
//        valorZona.setText(cuentaDetalle.getZona_c());
//        TextView valorUen = (TextView) findViewById(R.id.valor_uen);
//        valorUen.setText(cuentaDetalle.getUen_c());
//        TextView valorEmail = (TextView) findViewById(R.id.valor_email);
//        valorEmail.setText(cuentaDetalle.getEmail_address());
//        TextView valorWeb = (TextView) findViewById(R.id.valor_web);
//        valorWeb.setText(cuentaDetalle.getWebsite());
//        TextView valorGrupo = (TextView) findViewById(R.id.valor_grupo);
//        valorGrupo.setText(cuentaDetalle.getGrupo_objetivo_c());
//        TextView valorSegmento = (TextView) findViewById(R.id.valor_segmento);
//        valorSegmento.setText(cuentaDetalle.getSegmento_c());
//        TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
//        valorEstado.setText(cuentaDetalle.getEstado_c());
//        TextView valorDescuento = (TextView) findViewById(R.id.valor_descuento);
//        valorDescuento.setText(cuentaDetalle.getDescuentocomercial_c());
//        TextView valorPresupuesto = (TextView) findViewById(R.id.valor_presupuesto);
//        valorPresupuesto.setText(cuentaDetalle.getPresupuestoanual_c());
//        TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
//        valorDescripcion.setText(cuentaDetalle.getDescription());
//        TextView valorTransporte = (TextView) findViewById(R.id.valor_c_transporte);
//        valorTransporte.setText(cuentaDetalle.getCorreotransporte_c());
//        TextView valorCreado = (TextView) findViewById(R.id.valor_creado);
//        valorCreado.setText(cuentaDetalle.getDate_entered());
//        TextView valorUsuario = (TextView) findViewById(R.id.valor_usuario);
//        valorUsuario.setText(cuentaDetalle.getAssigned_user_name());
//        TextView valorConstitucion = (TextView) findViewById(R.id.valor_constitucion);
//        valorConstitucion.setText(cuentaDetalle.getFechaempresa_c());
//        TextView valorActual = (TextView) findViewById(R.id.valor_actual);
//        valorActual.setText(cuentaDetalle.getVentasactual_c());
//        TextView valorAnterior = (TextView) findViewById(R.id.valor_anterior);
//        valorAnterior.setText(cuentaDetalle.getVentasanterior_c());
//        TextView valorNumeroAlianzas = (TextView) findViewById(R.id.valor_numero_alianzas);
//        valorNumeroAlianzas.setText(cuentaDetalle.getNumeroalianzas_c());
//        TextView valorAlianzas = (TextView) findViewById(R.id.valor_alianzas);
//        valorAlianzas.setText(cuentaDetalle.getAlianzasestrategicas_c());
//        TextView valorOrigen = (TextView) findViewById(R.id.valor_origen);
//        valorOrigen.setText(cuentaDetalle.getOrigencuenta_c());
//        TextView valorFecha = (TextView) findViewById(R.id.valor_fecha);
//        valorFecha.setText(cuentaDetalle.getFechafacturacion_c());
//        TextView valorDiaria = (TextView) findViewById(R.id.valor_diaria);
//        valorDiaria.setText(cuentaDetalle.getFacturaciondiara_c());
//        TextView valorAcumulada = (TextView) findViewById(R.id.valor_acumulada);
//        valorAcumulada.setText(cuentaDetalle.getFacturacionmes_c());
//        TextView valorCumplimiento = (TextView) findViewById(R.id.valor_cumplimiento);
//        valorCumplimiento.setText(cuentaDetalle.getPorcentaje_cumplimiento_c());
//        TextView valorAutorizada = (TextView) findViewById(R.id.valor_autorizada);
//        valorAutorizada.setText(cuentaDetalle.getFacturacionautorizada_c());
//        TextView valorNoAutorizada = (TextView) findViewById(R.id.valor_no_autorizada);
//        valorNoAutorizada.setText(cuentaDetalle.getFacturacionnoautorizada_c());
//        TextView valorFechaDespacho = (TextView) findViewById(R.id.valor_fecha_despacho);
//        valorFechaDespacho.setText(cuentaDetalle.getFecha_despacho_c());
//        TextView valorRemesa = (TextView) findViewById(R.id.valor_remesa);
//        valorRemesa.setText(cuentaDetalle.getRemesa_c());
//        TextView valorDestino = (TextView) findViewById(R.id.valor_destino);
//        valorDestino.setText(cuentaDetalle.getDestino_c());
//        TextView valorDestinatario = (TextView) findViewById(R.id.valor_destinatario);
//        valorDestinatario.setText(cuentaDetalle.getNombredestinatario_c());
//        TextView valorUnidades = (TextView) findViewById(R.id.valor_unidades);
//        valorUnidades.setText(cuentaDetalle.getUnidades_c());
//        TextView valorDocumentos = (TextView) findViewById(R.id.valor_documento);
//        valorDocumentos.setText(cuentaDetalle.getDocumento_c());
//        TextView valorDestinatarioPendientes = (TextView) findViewById(R.id.valor_destinatario_pendientes);
//        valorDestinatarioPendientes.setText(cuentaDetalle.getNombredestinatario2_c());
//        TextView valorDestinoPendientes = (TextView) findViewById(R.id.valor_destino_pendientes);
//        valorDestinoPendientes.setText(cuentaDetalle.getNombredestinatario2_c());
//        TextView valorMotivo = (TextView) findViewById(R.id.valor_motivo);
//        valorMotivo.setText(cuentaDetalle.getMotivo_c());
//        TextView valorCupo = (TextView) findViewById(R.id.valor_cupo);
//        valorCupo.setText(cuentaDetalle.getCupodisponible_c());
//        TextView valorCupoCr = (TextView) findViewById(R.id.valor_cupo_cr);
//        valorCupoCr.setText(cuentaDetalle.getCupocr_c());
//        TextView valorTotal = (TextView) findViewById(R.id.valor_total);
//        valorTotal.setText(cuentaDetalle.getTotalcartera_c());
//        TextView valorCondicion = (TextView) findViewById(R.id.valor_condicion);
//        valorCondicion.setText(cuentaDetalle.getCondpago_c());
//        TextView valorPlazo = (TextView) findViewById(R.id.valor_plazo);
//        valorPlazo.setText(cuentaDetalle.getPlpago_c());
//        TextView valorPromedio = (TextView) findViewById(R.id.valor_promedio);
//        valorPromedio.setText(cuentaDetalle.getPrompago_c());
//        TextView valorVencida = (TextView) findViewById(R.id.valor_vencida);
//        valorVencida.setText(cuentaDetalle.getCarteravencida_c());
//        TextView valorAVencer = (TextView) findViewById(R.id.valor_a_vencer);
//        valorAVencer.setText(cuentaDetalle.getCarteravencer_c());
//    }
//    
//   
//    /**
//     * Representa una tarea asincrona de obtencion de cuenta.
//     */
//    public class GetAccountTask extends AsyncTask<String, Void, Boolean> {
//        private ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(AccountActivity.this, ProgressDialog.THEME_HOLO_DARK);
//            progressDialog.setMessage("Cargando información cuenta...");
//            progressDialog.setIndeterminate(true);
//            progressDialog.show();
//        }
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            try {
//                // Parametros
//                String idCuenta = params[0];
//
//                // Respuesta
//                String account = null;
//
//                // Intento de obtener cuenta
//                HttpClient httpClientAccount = new DefaultHttpClient();
//                HttpGet httpGetAccount = new HttpGet(mUrl
//                        + "getAccount");
//                httpGetAccount.setHeader("idAccount", idCuenta);
//
//                try {
//                    HttpResponse response = httpClientAccount
//                            .execute(httpGetAccount);
//                    account = EntityUtils.toString(response
//                            .getEntity());
//                    account = account.replace("\n", "")
//                            .replace("\r", "");
//                    Log.d(TAG, "Cuenta Response: "
//                            + account);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//
//                JSONObject jObj = new JSONObject(account);
//
//                JSONArray jArr = jObj.getJSONArray("results");
//                for (int i = 0; i < jArr.length(); i++) {
//                    JSONObject obj = jArr.getJSONObject(i);
//                    String id = obj.getString("id");
//                    String name = obj.getString("name");
//                    String date_entered = obj.getString("date_entered");
//                    String date_modified = obj.getString("date_modified");
//                    String modified_user_id = obj.getString("modified_user_id");
//                    String created_by = obj.getString("created_by");
//                    String description = obj.getString("description");
//                    String deleted = obj.getString("deleted");
//                    String assigned_user_id = obj.getString("assigned_user_id");
//                    String account_type = obj.getString("account_type");
//                    String industry = obj.getString("industry");
//                    String annual_revenue = obj.getString("annual_revenue");
//                    String phone_fax = obj.getString("phone_fax");
//                    String billing_address_street = obj.getString("billing_address_street");
//                    String billing_address_city = obj.getString("billing_address_city");
//                    String billing_address_state = obj.getString("billing_address_state");
//                    String billing_address_postalcode = obj.getString("billing_address_postalcode");
//                    String billing_address_country = obj.getString("billing_address_country");
//                    String rating = obj.getString("rating");
//                    String phone_office = obj.getString("phone_office");
//                    String phone_alternate = obj.getString("phone_alternate");
//                    String website = obj.getString("website");
//                    String ownership = obj.getString("ownership");
//                    String employees = obj.getString("employees");
//                    String ticker_symbol = obj.getString("ticker_symbol");
//                    String shipping_address_street = obj.getString("shipping_address_street");
//                    String shipping_address_city = obj.getString("shipping_address_city");
//                    String shipping_address_state = obj.getString("shipping_address_state");
//                    String shipping_address_postalcode = obj.getString("shipping_address_postalcode");
//                    String shipping_address_country = obj.getString("shipping_address_country");
//                    String parent_id = obj.getString("parent_id");
//                    String sic_code = obj.getString("sic_code");
//                    String campaign_id = obj.getString("campaign_id");
//                    String id_c = obj.getString("id_c");
//                    String canal_c = obj.getString("canal_c");
//                    String sector_c = obj.getString("sector_c");
//                    String extension1_c = obj.getString("extension1_c");
//                    String extension2_c = obj.getString("extension2_c");
//                    String celular_c = obj.getString("celular_c");
//                    String direccion_c = obj.getString("direccion_c");
//                    String departamento_c = obj.getString("departamento_c");
//                    String municipio_c = obj.getString("municipio_c");
//                    String zona_c = obj.getString("zona_c");
//                    String uen_c = obj.getString("uen_c");
//                    String descuentocomercial_c = obj.getString("descuentocomercial_c");
//                    String presupuestoanual_c = obj.getString("presupuestoanual_c");
//                    String condpago_c = obj.getString("condpago_c");
//                    String plpago_c = obj.getString("plpago_c");
//                    String prompago_c = obj.getString("prompago_c");
//                    String carteravencida_c = obj.getString("carteravencida_c");
//                    String carteravencer_c = obj.getString("carteravencer_c");
//                    String grupo_objetivo_c = obj.getString("grupo_objetivo_c");
//                    String segmento_c = obj.getString("segmento_c");
//                    String estado_c = obj.getString("estado_c");
//                    String origencuenta_c = obj.getString("origencuenta_c");
//                    String alianzasestrategicas_c = obj.getString("alianzasestrategicas_c");
//                    String automatizacionmoeller_c = obj.getString("automatizacionmoeller_c");
//                    String enerlux_c = obj.getString("enerlux_c");
//                    String delta_c = obj.getString("delta_c");
//                    String dkc_c = obj.getString("dkc_c");
//                    String leviton_c = obj.getString("leviton_c");
//                    String wvcai_c = obj.getString("wvcai_c");
//                    String wohner_c = obj.getString("wohner_c");
//                    String comunicaciones_c = obj.getString("comunicaciones_c");
//                    String preciosmineaton_c = obj.getString("preciosmineaton_c");
//                    String maniobraeaton_c = obj.getString("maniobraeaton_c");
//                    String descuento1_c = obj.getString("descuento1_c");
//                    String descuento2_c = obj.getString("descuento2_c");
//                    String descuento3_c = obj.getString("descuento3_c");
//                    String descuento4_c = obj.getString("descuento4_c");
//                    String descuento5_c = obj.getString("descuento5_c");
//                    String descuento6_c = obj.getString("descuento6_c");
//                    String descuento7_c = obj.getString("descuento7_c");
//                    String descuento8_c = obj.getString("descuento8_c");
//                    String descuento9_c = obj.getString("descuento9_c");
//                    String descuento10_c = obj.getString("descuento10_c");
//                    String bonosespeciales_c = obj.getString("bonosespeciales_c");
//                    String bonificacioncompra_c = obj.getString("bonificacioncompra_c");
//                    String maniobraeatonanual_c = obj.getString("maniobraeatonanual_c");
//                    String bonificacionleviton_c = obj.getString("bonificacionleviton_c");
//                    String meta1_c = obj.getString("meta1_c");
//                    String meta2_c = obj.getString("meta2_c");
//                    String meta3_c = obj.getString("meta3_c");
//                    String meta4_c = obj.getString("meta4_c");
//                    String bonificacion1_c = obj.getString("bonificacion1_c");
//                    String bonificacion2_c = obj.getString("bonificacion2_c");
//                    String bonificacion3_c = obj.getString("bonificacion3_c");
//                    String bonificacion4_c = obj.getString("bonificacion4_c");
//                    String despachoremsion_c = obj.getString("despachoremsion_c");
//                    String nocobro_c = obj.getString("nocobro_c");
//                    String estrategia1_c = obj.getString("estrategia1_c");
//                    String estrategia2_c = obj.getString("estrategia2_c");
//                    String estrategia3_c = obj.getString("estrategia3_c");
//                    String estrategia4_c = obj.getString("estrategia4_c");
//                    String fechaempresa_c = obj.getString("fechaempresa_c");
//                    String diamarca_c = obj.getString("diamarca_c");
//                    String exhibidor_c = obj.getString("exhibidor_c");
//                    String apoya_c = obj.getString("apoya_c");
//                    String placaaniversario_c = obj.getString("placaaniversario_c");
//                    String certificaciones_c = obj.getString("certificaciones_c");
//                    String fechamarca_c = obj.getString("fechamarca_c");
//                    String entregaexhibidor1_c = obj.getString("entregaexhibidor1_c");
//                    String entregaexhibidor2_c = obj.getString("entregaexhibidor2_c");
//                    String entregaexhibidor3_c = obj.getString("entregaexhibidor3_c");
//                    String fechaapoya_c = obj.getString("fechaapoya_c");
//                    String fechaplaca_c = obj.getString("fechaplaca_c");
//                    String otrasestrategias_c = obj.getString("otrasestrategias_c");
//                    String imagen1_c = obj.getString("imagen1_c");
//                    String imagen2_c = obj.getString("imagen2_c");
//                    String imagen3_c = obj.getString("imagen3_c");
//                    String acta1_c = obj.getString("acta1_c");
//                    String acta2_c = obj.getString("acta2_c");
//                    String acta3_c = obj.getString("acta3_c");
//                    String pieza_c = obj.getString("pieza_c");
//                    String fechaapoya2_c = obj.getString("fechaapoya2_c");
//                    String pieza2_c = obj.getString("pieza2_c");
//                    String fechafacturacion_c = obj.getString("fechafacturacion_c");
//                    String correotransporte_c = obj.getString("correotransporte_c");
//                    String laumayer2_c = obj.getString("laumayer2_c");
//                    String ventasactual_c = obj.getString("ventasactual_c");
//                    String ventasanterior_c = obj.getString("ventasanterior_c");
//                    String numeroalianzas_c = obj.getString("numeroalianzas_c");
//                    String cupocr_c = obj.getString("cupocr_c");
//                    String facturacionmes_c = obj.getString("facturacionmes_c");
//                    String cupodisponible_c = obj.getString("cupodisponible_c");
//                    String nit_c = obj.getString("nit_c");
//                    String facturaciondiara_c = obj.getString("facturaciondiara_c");
//                    String facturacionautorizada_c = obj.getString("facturacionautorizada_c");
//                    String cod_alterno_c = obj.getString("cod_alterno_c");
//                    String totalcartera_c = obj.getString("totalcartera_c");
//                    String remesa_c = obj.getString("remesa_c");
//                    String destino_c = obj.getString("destino_c");
//                    String nombredestinatario_c = obj.getString("nombredestinatario_c");
//                    String unidades_c = obj.getString("unidades_c");
//                    String documento_c = obj.getString("documento_c");
//                    String nombredestinatario2_c = obj.getString("nombredestinatario2_c");
//                    String destino2_c = obj.getString("destino2_c");
//                    String motivo_c = obj.getString("motivo_c");
//                    String facturacionnoautorizada_c = obj.getString("facturacionnoautorizada_c");
//                    String porcentaje_cumplimiento_c = obj.getString("porcentaje_cumplimiento_c");
//                    String fecha_despacho_c = obj.getString("fecha_despacho_c");
//                    String prue_c = obj.getString("prue_c");
//                    String prueba_c = obj.getString("prueba_c");
//                    String preuba_c = obj.getString("preuba_c");
//                    String assigned_user_name = obj.getString("assigned_user_name");
//                    String email_address = obj.getString("email_address");
//
//                    mCuentaDetalle = new CuentaDetalle(id, name, date_entered, date_modified, modified_user_id, created_by, description, deleted, assigned_user_id, account_type, industry, annual_revenue, phone_fax, billing_address_street, billing_address_city, billing_address_state, billing_address_postalcode, billing_address_country, rating, phone_office, phone_alternate, website, ownership, employees, ticker_symbol, shipping_address_street, shipping_address_city, shipping_address_state, shipping_address_postalcode, shipping_address_country, parent_id, sic_code, campaign_id, id_c, canal_c, sector_c, extension1_c, extension2_c, celular_c, direccion_c, departamento_c, municipio_c, zona_c, uen_c, descuentocomercial_c, presupuestoanual_c, condpago_c, plpago_c, prompago_c, carteravencida_c, carteravencer_c, grupo_objetivo_c, segmento_c, estado_c, origencuenta_c, alianzasestrategicas_c, automatizacionmoeller_c, enerlux_c, delta_c, dkc_c, leviton_c, wvcai_c, wohner_c, comunicaciones_c, preciosmineaton_c, maniobraeaton_c, descuento1_c, descuento2_c, descuento3_c, descuento4_c, descuento5_c, descuento6_c, descuento7_c, descuento8_c, descuento9_c, descuento10_c, bonosespeciales_c, bonificacioncompra_c, maniobraeatonanual_c, bonificacionleviton_c, meta1_c, meta2_c, meta3_c, meta4_c, bonificacion1_c, bonificacion2_c, bonificacion3_c, bonificacion4_c, despachoremsion_c, nocobro_c, estrategia1_c, estrategia2_c, estrategia3_c, estrategia4_c, fechaempresa_c, diamarca_c, exhibidor_c, apoya_c, placaaniversario_c, certificaciones_c, fechamarca_c, entregaexhibidor1_c, entregaexhibidor2_c, entregaexhibidor3_c, fechaapoya_c, fechaplaca_c, otrasestrategias_c, imagen1_c, imagen2_c, imagen3_c, acta1_c, acta2_c, acta3_c, pieza_c, fechaapoya2_c, pieza2_c, fechafacturacion_c, correotransporte_c, laumayer2_c, ventasactual_c, ventasanterior_c, numeroalianzas_c, cupocr_c, facturacionmes_c, cupodisponible_c, nit_c, facturaciondiara_c, facturacionautorizada_c, cod_alterno_c, totalcartera_c, remesa_c, destino_c, nombredestinatario_c, unidades_c, documento_c, nombredestinatario2_c, destino2_c, motivo_c, facturacionnoautorizada_c, porcentaje_cumplimiento_c, fecha_despacho_c, prue_c, prueba_c, preuba_c, assigned_user_name, email_address);
//                }
//
//                return true;
//            } catch (Exception e) {
//                Log.d(TAG, "Buscar Cuenta Error: "
//                        + e.getClass().getName() + ":" + e.getMessage());
//                return false;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//           // mTareaObtenerCuenta = null;
//            progressDialog.dismiss();
//
//            if (success) {
//                ponerValores(mCuentaDetalle);
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//           // mTareaObtenerCuenta = null;
//            Log.d(TAG, "Cancelado ");
//        }
//    }
}
