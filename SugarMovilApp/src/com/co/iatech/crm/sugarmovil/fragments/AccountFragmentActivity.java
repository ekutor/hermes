package com.co.iatech.crm.sugarmovil.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;


public class AccountFragmentActivity extends Fragment {

    /**
     * Debug.
     */
    private static final String TAG = "AccountActivity";


    /**
     * Member Variables.
     */
    private String mIdCuenta;
    private CuentaDetalle mCuentaDetalle;

    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonEdit;
    private LinearLayout mLayoutContenido;
    private ImageButton imageButtonContacts;
    private ImageButton imageButtonOpps;
    private ImageButton imageButtonTasks;
    
    private TextView valorRazon;
    private TextView valorNit;
    private TextView valorCodigo;
    private TextView valorCanal;
    private TextView valorSector;
    private TextView valorTel1;
    private TextView valorExt1;
    private TextView valorTel2;
    private TextView valorExt2;
    private TextView valorCelular;
    private TextView valorFax;
    private TextView valorDireccion;
    private TextView valorMunicipio;
    private TextView valorDepartamento;
    private TextView valorZona;
    private TextView valorUen;
    private TextView valorEmail;
    private TextView valorWeb;
    private TextView valorGrupo;
    private TextView valorSegmento;
    private TextView valorEstado;
    private TextView valorDescuento;
    private TextView valorDescuento2;
    private TextView valorPresupuesto;
    private TextView valorDescripcion;
    private TextView valorTransporte;
    private TextView valorCreado;
    private TextView valorUsuario;
    private TextView valorConstitucion;
    private TextView valorActual;
    private TextView valorAnterior;
    private TextView valorNumeroAlianzas;
    private TextView valorAlianzas;
    private TextView valorOrigen;
    private TextView valorFecha;
    private TextView valorDiaria;
    private TextView valorAcumulada;
    private TextView valorCumplimiento;
    private TextView valorAutorizada;
    private TextView valorNoAutorizada;
    private TextView valorFechaDespacho;
    private TextView valorRemesa;
    private TextView valorDestino;
    private TextView valorDestinatario;
    private TextView valorUnidades;
    private TextView valorDocumentos;
    private TextView valorDestinatarioPendientes;
    private TextView valorDestinoPendientes;
    private TextView valorMotivo;
    private TextView valorCupo;
    private TextView valorCupoCr;
    private TextView valorTotal;
    private TextView valorCondicion;
    private TextView valorPlazo;
    private TextView valorPromedio;
    private TextView valorVencida;
    private TextView valorAVencer;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
       
        View rootView = inflater.inflate(R.layout.fragment_account_general, container, false);
    	 
        Bundle args = getArguments();

        
        String idCuenta = args.getString(Info.CUENTA_ACTUAL.name());
  
        this.cargarComponentes(rootView);
        GetAccountTask mTareaObtenerCuenta = new GetAccountTask();
        mTareaObtenerCuenta.execute(idCuenta);
        Log.d(TAG, "Id cuenta " + idCuenta);
        	
        
       
        return rootView;
    }
    
    
    public void ponerValores(CuentaDetalle cuentaDetalle) {
    	valorRazon.setText(cuentaDetalle.getName());
    	valorNit.setText(cuentaDetalle.getNit_c());
    	valorCodigo.setText(cuentaDetalle.getCod_alterno_c());
    	valorCanal.setText(ListsConversor.convert(ConversorsType.CHANNEL, cuentaDetalle.getCanal_c(), DataToGet.VALUE));
    	valorSector.setText(cuentaDetalle.getSector_c());
    	valorTel1.setText(cuentaDetalle.getPhone_office());
    	valorExt1.setText(cuentaDetalle.getExtension1_c());
    	valorTel2.setText(cuentaDetalle.getPhone_alternate());
    	valorExt2.setText(cuentaDetalle.getExtension2_c());
    	valorCelular.setText(cuentaDetalle.getCelular_c());
    	valorFax.setText(cuentaDetalle.getPhone_fax());
    	valorDireccion.setText(cuentaDetalle.getDireccion_c());
    	valorMunicipio.setText(cuentaDetalle.getMunicipio_c());
    	valorDepartamento.setText(
    			ListsConversor.convert(ConversorsType.DPTO, cuentaDetalle.getDepartamento_c(), DataToGet.VALUE)
    			);
    	valorZona.setText(ListsConversor.convert(ConversorsType.ZONE, cuentaDetalle.getZona_c(), DataToGet.VALUE));
    	valorUen.setText(cuentaDetalle.getUen_c());
    	valorEmail.setText(cuentaDetalle.getEmail_address());
    	valorWeb.setText(cuentaDetalle.getWebsite());
    	valorGrupo.setText(cuentaDetalle.getGrupo_objetivo_c());
    	valorSegmento.setText(cuentaDetalle.getSegmento_c());
    	valorEstado.setText(cuentaDetalle.getEstado_c());
    	valorDescuento.setText(cuentaDetalle.getDescuentocomercial2_c());
    	valorDescuento2.setText(cuentaDetalle.getDescuentocomercial_c());
    	valorPresupuesto.setText(cuentaDetalle.getPresupuestoanual_c());
    	valorDescripcion.setText(cuentaDetalle.getDescription());
    	valorTransporte.setText(cuentaDetalle.getCorreotransporte_c());
    	valorCreado.setText(cuentaDetalle.getDate_entered());
    	valorUsuario.setText(cuentaDetalle.getAssigned_user_name());
    	valorConstitucion.setText(cuentaDetalle.getFechaempresa_c());
    	valorActual.setText(cuentaDetalle.getVentasactual_c());
    	valorAnterior.setText(cuentaDetalle.getVentasanterior_c());
    	valorNumeroAlianzas.setText(cuentaDetalle.getNumeroalianzas_c());
    	valorAlianzas.setText(cuentaDetalle.getAlianzasestrategicas_c());
    	valorOrigen.setText(cuentaDetalle.getOrigencuenta_c());
    	valorFecha.setText(cuentaDetalle.getFechafacturacion_c());
    	valorDiaria.setText(cuentaDetalle.getFacturaciondiara_c());
    	valorAcumulada.setText(cuentaDetalle.getFacturacionmes_c());
    	valorCumplimiento.setText(cuentaDetalle.getPorcentaje_cumplimiento_c());
    	valorAutorizada.setText(cuentaDetalle.getFacturacionautorizada_c());
    	valorNoAutorizada.setText(cuentaDetalle.getFacturacionnoautorizada_c());
    	valorFechaDespacho.setText(cuentaDetalle.getFecha_despacho_c());
    	valorRemesa.setText(cuentaDetalle.getRemesa_c());
    	valorDestino.setText(cuentaDetalle.getDestino_c());
    	valorDestinatario.setText(cuentaDetalle.getNombredestinatario_c());
    	valorUnidades.setText(cuentaDetalle.getUnidades_c());
    	valorDocumentos.setText(cuentaDetalle.getDocumento_c());
    	valorDestinatarioPendientes.setText(cuentaDetalle.getNombredestinatario2_c());
    	valorDestinoPendientes.setText(cuentaDetalle.getNombredestinatario2_c());
    	valorMotivo.setText(cuentaDetalle.getMotivo_c());
    	valorCupo.setText(cuentaDetalle.getCupodisponible_c());
    	valorCupoCr.setText(cuentaDetalle.getCupocr_c());
    	valorTotal.setText(cuentaDetalle.getTotalcartera_c());
    	valorCondicion.setText(cuentaDetalle.getCondpago_c());
    	valorPlazo.setText(cuentaDetalle.getPlpago_c());
    	valorPromedio.setText(cuentaDetalle.getPrompago_c());
    	valorVencida.setText(cuentaDetalle.getCarteravencida_c());
    	valorAVencer.setText(cuentaDetalle.getCarteravencer_c());
    }
    
    public void cargarComponentes(View view) {
    	valorRazon = (TextView) view.findViewById(R.id.valor_razon);
    	valorNit = (TextView) view.findViewById(R.id.valor_nit);
    	valorCodigo = (TextView) view.findViewById(R.id.valor_codigo);
    	valorCanal = (TextView) view.findViewById(R.id.valor_canal);
    	valorSector = (TextView) view.findViewById(R.id.valor_sector);
    	valorTel1 = (TextView) view.findViewById(R.id.valor_tel1);
    	valorExt1 = (TextView) view.findViewById(R.id.valor_ext1);
    	valorTel2 = (TextView) view.findViewById(R.id.valor_tel2);
    	valorExt2 = (TextView) view.findViewById(R.id.valor_ext2);
    	valorCelular = (TextView) view.findViewById(R.id.valor_celular);
    	valorFax = (TextView) view.findViewById(R.id.valor_fax);
    	valorDireccion = (TextView) view.findViewById(R.id.valor_direccion);
    	valorMunicipio = (TextView) view.findViewById(R.id.valor_municipio);
    	valorDepartamento = (TextView) view.findViewById(R.id.valor_departamento);
    	valorZona = (TextView) view.findViewById(R.id.valor_zona);
    	valorUen = (TextView) view.findViewById(R.id.valor_uen);
    	valorEmail = (TextView) view.findViewById(R.id.valor_email);
    	valorWeb = (TextView) view.findViewById(R.id.valor_web);
    	valorGrupo = (TextView) view.findViewById(R.id.valor_grupo);
    	valorSegmento = (TextView) view.findViewById(R.id.valor_segmento);
    	valorEstado = (TextView) view.findViewById(R.id.valor_estado);
    	valorDescuento = (TextView) view.findViewById(R.id.valor_descuento);
    	valorDescuento2 = (TextView) view.findViewById(R.id.valor_descuento2);
    	valorPresupuesto = (TextView) view.findViewById(R.id.valor_presupuesto);
    	valorDescripcion = (TextView) view.findViewById(R.id.valor_descripcion);
    	valorTransporte = (TextView) view.findViewById(R.id.valor_c_transporte);
    	valorCreado = (TextView) view.findViewById(R.id.valor_creado);
    	valorUsuario = (TextView) view.findViewById(R.id.valor_usuario);
    	valorConstitucion = (TextView) view.findViewById(R.id.valor_constitucion);
    	valorActual = (TextView) view.findViewById(R.id.valor_actual);
    	valorAnterior = (TextView) view.findViewById(R.id.valor_anterior);
    	valorNumeroAlianzas = (TextView) view.findViewById(R.id.valor_numero_alianzas);
    	valorAlianzas = (TextView) view.findViewById(R.id.valor_alianzas);
    	valorOrigen = (TextView) view.findViewById(R.id.valor_origen);
    	valorFecha = (TextView) view.findViewById(R.id.valor_fecha);
    	valorDiaria = (TextView) view.findViewById(R.id.valor_diaria);
    	valorAcumulada = (TextView) view.findViewById(R.id.valor_acumulada);
    	valorCumplimiento = (TextView) view.findViewById(R.id.valor_cumplimiento);
    	valorAutorizada = (TextView) view.findViewById(R.id.valor_autorizada);
    	valorNoAutorizada = (TextView) view.findViewById(R.id.valor_no_autorizada);
    	valorFechaDespacho = (TextView) view.findViewById(R.id.valor_fecha_despacho);
    	valorRemesa = (TextView) view.findViewById(R.id.valor_remesa);
    	valorDestino = (TextView) view.findViewById(R.id.valor_destino);
    	valorDestinatario = (TextView) view.findViewById(R.id.valor_destinatario);
    	valorUnidades = (TextView) view.findViewById(R.id.valor_unidades);
    	valorDocumentos = (TextView) view.findViewById(R.id.valor_documento);
    	valorDestinatarioPendientes = (TextView) view.findViewById(R.id.valor_destinatario_pendientes);
    	valorDestinoPendientes = (TextView) view.findViewById(R.id.valor_destino_pendientes);
    	valorMotivo = (TextView) view.findViewById(R.id.valor_motivo);
    	valorCupo = (TextView) view.findViewById(R.id.valor_cupo);
    	valorCupoCr = (TextView) view.findViewById(R.id.valor_cupo_cr);
    	valorTotal = (TextView) view.findViewById(R.id.valor_total);
    	valorCondicion = (TextView) view.findViewById(R.id.valor_condicion);
    	valorPlazo = (TextView) view.findViewById(R.id.valor_plazo);
    	valorPromedio = (TextView) view.findViewById(R.id.valor_promedio);
    	valorVencida = (TextView) view.findViewById(R.id.valor_vencida);
    	valorAVencer = (TextView) view.findViewById(R.id.valor_a_vencer);

    }





		
		/*if(v.getId() == imageButtonContacts.getId()){
			Log.d(TAG, "Contactos x cuenta ");
			Intent intent = new Intent(AccountFragmentActivity.this,
					ListContactActivity.class);
			intent.putExtra(Info.CUENTA_ACTUAL.name(), mIdCuenta);
			startActivity(intent);

		}else if(v.getId() == imageButtonOpps.getId()){
			Log.d(TAG, "Oportunidades X Cuenta ");
			Intent intent = new Intent(AccountFragmentActivity.this,
					ListOpportunityActivity.class);
			intent.putExtra(Info.CUENTA_ACTUAL.name(), mIdCuenta);
			startActivity(intent);

		}else if(v.getId() == imageButtonTasks.getId()){
			Log.d(TAG, "Tareas X Cuenta ");
			Intent intent = new Intent(AccountFragmentActivity.this,
					ListTasksActivity.class);
			intent.putExtra(Info.CUENTA_ACTUAL.name(), mIdCuenta);
			startActivity(intent);

		}*/
		
	
	
	 /**
     * Representa una tarea asincrona de obtencion de cuenta.
     */
    public class GetAccountTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AccountFragmentActivity.this.getActivity(), ProgressDialog.THEME_HOLO_DARK);
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
                ControlConnection.addHeader("idAccount", idCuenta);
                account  = ControlConnection.getInfo(TypeInfoServer.getAccount);
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
