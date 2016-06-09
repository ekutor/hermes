package com.co.iatech.crm.sugarmovil.fragments;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.DetailAccount;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AccountFragmentActivity extends Fragment {

    /**
     * Debug.
     */
    private static final String TAG = "AccountActivity";


    /**
     * UI References.
     */
  
    
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
    
    private TextView valorClase;
    private TextView valorActActual;
    private TextView valorActAnterior;
    private TextView valorPasActual;
    private TextView valorPasAnterior;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
       
        View rootView = inflater.inflate(R.layout.fragment_account_general, container, false);
        
        Bundle args = getArguments();
        DetailAccount cuenta = args.getParcelable(Modules.ACCOUNTS.getModuleName());
  
        cargarComponentes(rootView);
        ponerValores(cuenta);
        Log.d(TAG, "Id cuenta " + cuenta.getId());
        	
        
       
        return rootView;
    }
    
    
    public void ponerValores(DetailAccount detailAccount) {
    	try{
    	valorRazon.setText(detailAccount.getName());
    	valorNit.setText(detailAccount.getNit_c());
    	valorCodigo.setText(detailAccount.getCod_alterno_c());
    	valorCanal.setText(ListsConversor.convert(ConversorsType.CHANNEL, detailAccount.getCanal_c(), DataToGet.VALUE));
    	valorSector.setText(detailAccount.getSector_c());
    	valorClase.setText(detailAccount.getClase_c());
    	valorTel1.setText(detailAccount.getPhone_office());
    	valorExt1.setText(detailAccount.getExtension1_c());
    	valorTel2.setText(detailAccount.getPhone_alternate());
    	valorExt2.setText(detailAccount.getExtension2_c());
    	valorCelular.setText(detailAccount.getCelular_c());
    	valorFax.setText(detailAccount.getPhone_fax());
    	valorDireccion.setText(detailAccount.getDireccion_c());
    	valorMunicipio.setText(detailAccount.getMunicipio_c());
    	valorDepartamento.setText(
    			ListsConversor.convert(ConversorsType.DPTO, detailAccount.getDepartamento_c(), DataToGet.VALUE)
    			);
    	valorZona.setText(ListsConversor.convert(ConversorsType.ZONE, detailAccount.getZona_c(), DataToGet.VALUE));
    	valorUen.setText(detailAccount.getUen_c());
    	valorEmail.setText(detailAccount.getEmail_address());
    	valorWeb.setText(Utils.getLinkFormat(detailAccount.getWebsite()));
    	valorGrupo.setText(detailAccount.getGrupo_objetivo_c());
    	valorSegmento.setText(detailAccount.getSegmento_c());
    	valorEstado.setText(detailAccount.getEstado_c());
    	valorDescuento.setText(detailAccount.getDescuentocomercial2_c());
    	valorDescuento2.setText(detailAccount.getDescuentocomercial_c());
    	valorPresupuesto.setText(detailAccount.getPresupuestoanual_c());
    	valorDescripcion.setText(detailAccount.getDescription());
    	valorTransporte.setText(detailAccount.getCorreotransporte_c());
    	valorCreado.setText(detailAccount.getDate_entered());
    	valorUsuario.setText(detailAccount.getAssigned_user_name());
    	valorConstitucion.setText(detailAccount.getFechaempresa_c());
    	valorActual.setText(detailAccount.getVentasactual_c());
    	valorAnterior.setText(detailAccount.getVentasanterior_c());
   /* 	valorNumeroAlianzas.setText(detailAccount.getNumeroalianzas_c());
    	valorAlianzas.setText(detailAccount.getAlianzasestrategicas_c());*/
    	valorOrigen.setText(detailAccount.getOrigencuenta_c());
    	valorFecha.setText(detailAccount.getFechafacturacion_c());
    	valorDiaria.setText(detailAccount.getFacturaciondiara_c());
    	valorAcumulada.setText(detailAccount.getFacturacionmes_c());
    	valorCumplimiento.setText(detailAccount.getPorcentaje_cumplimiento_c());
    	valorAutorizada.setText(detailAccount.getFacturacionautorizada_c());
    	valorNoAutorizada.setText(detailAccount.getFacturacionnoautorizada_c());
    	valorFechaDespacho.setText(detailAccount.getFecha_despacho_c());
    	valorRemesa.setText(detailAccount.getRemesa_c());
    	valorDestino.setText(detailAccount.getDestino_c());
    	valorDestinatario.setText(detailAccount.getNombredestinatario_c());
    	valorUnidades.setText(detailAccount.getUnidades_c());
    	valorDocumentos.setText(detailAccount.getDocumento_c());
    	valorDestinatarioPendientes.setText(detailAccount.getNombredestinatario2_c());
    	valorDestinoPendientes.setText(detailAccount.getNombredestinatario2_c());
    	valorMotivo.setText(detailAccount.getMotivo_c());
    	valorCupo.setText(detailAccount.getCupodisponible_c());
    	valorCupoCr.setText(detailAccount.getCupocr_c());
    	valorTotal.setText(detailAccount.getTotalcartera_c());
    	valorCondicion.setText(detailAccount.getCondpago_c());
    	valorPlazo.setText(detailAccount.getPlpago_c());
    	valorPromedio.setText(detailAccount.getPrompago_c());
    	valorVencida.setText(detailAccount.getCarteravencida_c());
    	valorAVencer.setText(detailAccount.getCarteravencer_c());
    	
    	valorActActual.setText(detailAccount.getActivos_actual_c());
    	valorActAnterior.setText(detailAccount.getActivos_anterior_c());
    	valorPasActual.setText(detailAccount.getPasivos_actual_c());
    	valorPasAnterior.setText(detailAccount.getPasivos_anterior_c());
    	}catch(Exception ne){
    		Message.showShortExt(Utils.errorToString(ne), this.getActivity());
    	}
    }
    
    public void cargarComponentes(View view) {
    	valorRazon = (TextView) view.findViewById(R.id.valor_razon);
    	valorNit = (TextView) view.findViewById(R.id.valor_nit);
    	valorCodigo = (TextView) view.findViewById(R.id.valor_codigo);
    	valorCanal = (TextView) view.findViewById(R.id.valor_canal);
    	valorSector = (TextView) view.findViewById(R.id.valor_sector);
    	
    	valorClase = (TextView) view.findViewById(R.id.valor_clase);
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
    	valorWeb.setMovementMethod(LinkMovementMethod.getInstance());
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
    	
    	valorActActual = (TextView) view.findViewById(R.id.valor_activos_actual);
    	valorActAnterior = (TextView) view.findViewById(R.id.valor_activos_anterior);
    	valorPasActual = (TextView) view.findViewById(R.id.valor_pasivos_actual);
    	valorPasAnterior = (TextView) view.findViewById(R.id.valor_pasivos_anterior);

    }

}
