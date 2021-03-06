package com.co.iatech.crm.sugarmovil.fragments;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.DetailAccount;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


public class AccountStrategyFragmentActivity extends Fragment {

    /**
     * Debug.
     */
    private static final String TAG = "AccountStrategyFragmentActivity";


    /**
     * UI References.
     */
        
    private TextView descAdicional1,descAdicional2,descAdicional3,descAdicional4,descAdicional5,
    				descAdicional6,descAdicional7,descAdicional8,descAdicional9,descAdicional10,descAdicional11,
    				descAdicional12,descAdicional13;
    private TextView txtMetaAnual;
    private TextView txtMetaTri;
    private TextView txtMetaEaton;
    private TextView txtMetaLeviton;
    private TextView txtBonAnual;
    private TextView txtBonTri;
    private TextView txtBonEaton;
    private TextView txtBonLeviton;
    private TextView txtEstrategia1;
    private TextView txtEstrategia2;
    private TextView txtEstrategia3;
    private TextView txtEstrategia4;
    private TextView txtExhibidor,txtEntExhibidor1,txtActaEntrega1,txtImagen1,txtEntExhibidor2,txtActaEntrega2,txtImagen2,
    				txtEntExhibidor3,txtActaEntrega3,txtImagen3,txtLaumayer,txtEntrega1,txtPieza1,txtEntrega2,txtPieza2,
    				txtDiaMarca,txtFechaMarca,txtPlaca,txtFechaPlaca,txtCertificaciones,txtOtrasEstr,
    				txtPorBonFlexible,txtBonFlexible,txtBonManLovato,txtVlrManLovato;
    private CheckBox moeller;
    private CheckBox delta2;
    private CheckBox delta;
    private CheckBox cbDkc;
    private CheckBox cbLeviton;
    private CheckBox cbDkc2;
    private CheckBox cbWohner;
    private CheckBox cbLP;
    private CheckBox cbEaton;
    private CheckBox cbxenergy,cbBarras;
    private CheckBox cbMinEaton;
    private CheckBox cbBonEspecial;
    private CheckBox cbBonCompra;
    private CheckBox cbBonEaton;
    private CheckBox cbBonLeviton;
    private CheckBox cbBonDespRem;
    private CheckBox cbBonCobroAnticipo;
	private CheckBox cbMiniEaton;
	private CheckBox cbSenaEATON;
	private CheckBox cbNetosLovato;
	private CheckBox cbBOnFlex;
	private CheckBox cbManLovato;
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
       
        View rootView = inflater.inflate(R.layout.fragment_account_strategy, container, false);
    	 
        Bundle args = getArguments();
        DetailAccount cuenta = args.getParcelable(Modules.ACCOUNTS.getModuleName());
  
        cargarComponentes(rootView);
        ponerValores(cuenta);
        Log.d(TAG, "Id cuenta " + cuenta.getId());
       
        return rootView;
    }
    
    
    public void ponerValores(DetailAccount cuentaDetalle) {
    	try{
    	chargeCheck(moeller, cuentaDetalle.getAutomatizacionmoeller_c());
    	chargeCheck(delta, cuentaDetalle.getDelta_c());
    	chargeCheck(delta2, cuentaDetalle.getEnerlux_c());
    	chargeCheck(cbDkc, cuentaDetalle.getDkc_c());
    	chargeCheck(cbDkc2, cuentaDetalle.getDkc2_c());
		chargeCheck(cbLeviton, cuentaDetalle.getLeviton_c());
		chargeCheck(cbBarras, cuentaDetalle.getWvcai_c());
		chargeCheck(cbWohner, cuentaDetalle.getWohner_c());
		chargeCheck(cbLP, cuentaDetalle.getComunicaciones_c());
		chargeCheck(cbEaton, cuentaDetalle.getManiobraeaton_c());
		chargeCheck(cbMinEaton, cuentaDetalle.getPreciosmineaton_c());
		chargeCheck(cbBonEspecial, cuentaDetalle.getBonosespeciales_c());
		chargeCheck(cbBonCompra, cuentaDetalle.getBonificacioncompra_c());
		chargeCheck(cbBonEaton, cuentaDetalle.getManiobraeaton_c());
		chargeCheck(cbBonLeviton, cuentaDetalle.getBonificacionleviton_c());
		chargeCheck(cbBonDespRem, cuentaDetalle.getDespachoremsion_c());
		chargeCheck(cbBonCobroAnticipo, cuentaDetalle.getNocobro_c());
		chargeCheck(cbxenergy, cuentaDetalle.getOemxenergy_c());
		
		chargeCheck(cbMiniEaton, cuentaDetalle.getMini_c());
		chargeCheck(cbSenaEATON, cuentaDetalle.getSenalizacion_c());
		chargeCheck(cbNetosLovato, cuentaDetalle.getPrecios_netos_c());
		
		chargeCheck(cbBonEaton, cuentaDetalle.getManiobraeatonanual_c());
		chargeCheck(cbBOnFlex, cuentaDetalle.getBonificacion_flexible_c());
		chargeCheck(cbManLovato, cuentaDetalle.getManiobra_lovato_c());
		
		
    	descAdicional1.setText(cuentaDetalle.getDescuento1_c());
    	descAdicional2.setText(cuentaDetalle.getDescuento2_c());
    	descAdicional3.setText(cuentaDetalle.getDescuento3_c());
    	descAdicional4.setText(cuentaDetalle.getDescuento4_c());
    	descAdicional5.setText(cuentaDetalle.getDescuento5_c());
    	descAdicional6.setText(cuentaDetalle.getDescuento6_c());
    	descAdicional7.setText(cuentaDetalle.getDescuento7_c());
    	descAdicional8.setText(cuentaDetalle.getDescuento8_c());
    	descAdicional9.setText(cuentaDetalle.getDescuento9_c());
    	descAdicional10.setText(cuentaDetalle.getDescuento10_c());
    	descAdicional11.setText(cuentaDetalle.getDescuento11_c());
    	
    	descAdicional12.setText(cuentaDetalle.getDescuento12_c());
    	descAdicional13.setText(cuentaDetalle.getDescuento13_c());
    	
    	txtMetaAnual.setText(cuentaDetalle.getMeta1_c());
    	txtMetaTri.setText(cuentaDetalle.getMeta2_c());
    	txtMetaEaton.setText(cuentaDetalle.getMeta3_c());
    	txtMetaLeviton.setText(cuentaDetalle.getMeta4_c());
    	txtBonAnual.setText(cuentaDetalle.getBonificacion1_c());
    	txtBonTri.setText(cuentaDetalle.getBonificacion2_c());
    	txtBonEaton.setText(cuentaDetalle.getBonificacion3_c());
    	txtBonLeviton.setText(cuentaDetalle.getBonificacion4_c());
    	txtEstrategia1.setText(cuentaDetalle.getEstrategia1_c());
    	txtEstrategia2.setText(cuentaDetalle.getEstrategia2_c());
    	txtEstrategia3.setText(cuentaDetalle.getEstrategia3_c());
    	txtEstrategia4.setText(cuentaDetalle.getEstrategia4_c());
    	
    	txtBonFlexible.setText(cuentaDetalle.getMeta_mensual_c());
    	txtPorBonFlexible.setText(cuentaDetalle.getValor_bono_c());
    	txtVlrManLovato.setText(cuentaDetalle.getMeta_bimensual_c());
    	txtBonManLovato.setText(cuentaDetalle.getBimensual_c());
    	
    	txtExhibidor.setText(cuentaDetalle.getExhibidor_c());
    	
    	txtEntExhibidor1.setText(cuentaDetalle.getEntregaexhibidor1_c());
    	txtActaEntrega1.setText(Utils.getLinkFormat(cuentaDetalle.getActa1_c()));
    	txtImagen1.setText(Utils.getLinkFormat(cuentaDetalle.getImagen1_c()));
    	
    	txtEntExhibidor2.setText(cuentaDetalle.getEntregaexhibidor2_c());
    	txtActaEntrega2.setText(Utils.getLinkFormat(cuentaDetalle.getActa1_c()));
    	txtImagen2.setText(Utils.getLinkFormat(cuentaDetalle.getImagen2_c()));
    	
    	txtEntExhibidor3.setText(cuentaDetalle.getEntregaexhibidor3_c());
    	txtActaEntrega3.setText(Utils.getLinkFormat(cuentaDetalle.getActa3_c()));
    	txtImagen3.setText(Utils.getLinkFormat(cuentaDetalle.getImagen3_c()));
    	
    	txtLaumayer.setText(cuentaDetalle.getLaumayer2_c());
    	//txtEntrega1.setText(cuentaDetalle.gete);
    	txtPieza1.setText(Utils.getLinkFormat(cuentaDetalle.getPieza_c()));
    	//txtEntrega2.setText(cuentaDetalle);
    	txtPieza2.setText(Utils.getLinkFormat(cuentaDetalle.getPieza2_c()));
    	txtDiaMarca.setText(cuentaDetalle.getDiamarca_c());
    	txtFechaMarca.setText(cuentaDetalle.getFechamarca_c());
    	txtPlaca.setText(cuentaDetalle.getPlacaaniversario_c());
    	txtFechaPlaca.setText(cuentaDetalle.getFechaplaca_c());
    	txtCertificaciones.setText(cuentaDetalle.getCertificaciones_c());
    	txtOtrasEstr.setText(cuentaDetalle.getOtrasestrategias_c());
    }catch(java.lang.NullPointerException ne){
		
	}
    }
    
    private void chargeCheck(CheckBox check , String value) {
		if("0".equals(value)){
			check.setChecked(false);
		}else{
			check.setChecked(true);
		}
	}


	public void cargarComponentes(View view) {
    	moeller = (CheckBox) view.findViewById(R.id.checkbox_moeller);
    	delta = (CheckBox) view.findViewById(R.id.checkbox_delta);
    	delta2 = (CheckBox) view.findViewById(R.id.checkbox_delta2);
    	cbDkc = (CheckBox) view.findViewById(R.id.checkbox_dkc);
    	cbDkc2 = (CheckBox) view.findViewById(R.id.checkbox_dkc2);
    	cbLeviton = (CheckBox) view.findViewById(R.id.checkbox_leviton);
    	cbBarras = (CheckBox) view.findViewById(R.id.checkbox_barras);
    	cbWohner = (CheckBox) view.findViewById(R.id.checkbox_wohner);
    	cbLP = (CheckBox) view.findViewById(R.id.checkbox_lp);
    	cbEaton = (CheckBox) view.findViewById(R.id.checkbox_eaton);
    	cbxenergy = (CheckBox) view.findViewById(R.id.checkbox_xenergy);
    	cbMinEaton = (CheckBox) view.findViewById(R.id.checkbox_min_eaton);
    	
    	cbBonEspecial = (CheckBox) view.findViewById(R.id.checkbox_bono_especial);
    	cbBonCompra = (CheckBox) view.findViewById(R.id.checkbox_bono_compra);
    	cbBonEaton = (CheckBox) view.findViewById(R.id.checkbox_bono_eaton);
    	cbBonLeviton = (CheckBox) view.findViewById(R.id.checkbox_bono_leviton);
    	cbBonDespRem = (CheckBox) view.findViewById(R.id.checkbox_desp_rem);
    	cbBonCobroAnticipo = (CheckBox) view.findViewById(R.id.checkbox_cobro_anticipo);
    	
    	cbMiniEaton = (CheckBox) view.findViewById(R.id.checkbox_minieaton);
    	cbSenaEATON = (CheckBox) view.findViewById(R.id.checkbox_mandoeaton);
    	cbNetosLovato = (CheckBox) view.findViewById(R.id.checkbox_netos);
    	
    	cbBOnFlex = (CheckBox) view.findViewById(R.id.checkbox_bon_flexible);
    	cbManLovato = (CheckBox) view.findViewById(R.id.checkbox_man_lovato);
    	
    	descAdicional1 = (TextView) view.findViewById(R.id.valor_desc_ad_1);
    	descAdicional2 = (TextView) view.findViewById(R.id.valor_desc_ad_2);
    	descAdicional3 = (TextView) view.findViewById(R.id.valor_desc_ad_3);
    	descAdicional4 = (TextView) view.findViewById(R.id.valor_desc_ad_4);
    	descAdicional5 = (TextView) view.findViewById(R.id.valor_desc_ad_5);
    	descAdicional6 = (TextView) view.findViewById(R.id.valor_desc_ad_6);
    	descAdicional7 = (TextView) view.findViewById(R.id.valor_desc_ad_7);
    	descAdicional8 = (TextView) view.findViewById(R.id.valor_desc_ad_8);
    	descAdicional9 = (TextView) view.findViewById(R.id.valor_desc_ad_9);
    	descAdicional10 = (TextView) view.findViewById(R.id.valor_desc_ad_10);
    	descAdicional11 = (TextView) view.findViewById(R.id.valor_desc_ad_11);
    	descAdicional12 = (TextView) view.findViewById(R.id.valor_desc_ad_12);
    	descAdicional13 = (TextView) view.findViewById(R.id.valor_desc_ad_13);
    	
    	txtMetaAnual = (TextView) view.findViewById(R.id.valor_meta_anual);
    	txtMetaTri = (TextView) view.findViewById(R.id.valor_meta_tri);
    	txtMetaEaton = (TextView) view.findViewById(R.id.valor_meta_eaton_bi);
    	txtMetaLeviton = (TextView) view.findViewById(R.id.valor_meta_leviton_bi);
    	txtBonAnual = (TextView) view.findViewById(R.id.valor_bon_anual);
    	txtBonTri = (TextView) view.findViewById(R.id.valor_bon_tri);
    	txtBonEaton = (TextView) view.findViewById(R.id.valor_bon_bi_eaton);
    	txtBonLeviton = (TextView) view.findViewById(R.id.valor_bon_bi_leviton);
    	txtEstrategia1 = (TextView) view.findViewById(R.id.valor_estrategia1);
    	txtEstrategia2 = (TextView) view.findViewById(R.id.valor_estrategia2);
    	txtEstrategia3 = (TextView) view.findViewById(R.id.valor_estrategia3);
    	txtEstrategia4 = (TextView) view.findViewById(R.id.valor_estrategia4);
    	
    	txtBonFlexible = (TextView) view.findViewById(R.id.valor_meta_flexible);
    	txtPorBonFlexible = (TextView) view.findViewById(R.id.valor_bon_flexible);
    	
    	txtVlrManLovato = (TextView) view.findViewById(R.id.valor_man_lovato);
    	txtBonManLovato = (TextView) view.findViewById(R.id.valor_bon_bi_lovato);
    	
    	txtExhibidor = (TextView) view.findViewById(R.id.valor_exhibidor);
    	txtEntExhibidor1 = (TextView) view.findViewById(R.id.valor_EntExh1);
    	
    	txtActaEntrega1 = (TextView) view.findViewById(R.id.valor_ActEnt1);
    	txtActaEntrega1.setMovementMethod(LinkMovementMethod.getInstance());
    	
    	txtImagen1 = (TextView) view.findViewById(R.id.valor_ActImg1);
    	txtImagen1.setMovementMethod(LinkMovementMethod.getInstance());
    	
    	txtEntExhibidor2 = (TextView) view.findViewById(R.id.valor_EntExh2);
    	txtActaEntrega2 = (TextView) view.findViewById(R.id.valor_ActEnt2);
    	txtActaEntrega2.setMovementMethod(LinkMovementMethod.getInstance());
    	
    	txtImagen2 = (TextView) view.findViewById(R.id.valor_ActImg2);
    	txtImagen2.setMovementMethod(LinkMovementMethod.getInstance());
    	
    	txtEntExhibidor3 = (TextView) view.findViewById(R.id.valor_EntExh3);
    	txtActaEntrega3 = (TextView) view.findViewById(R.id.valor_ActEnt3);
    	txtActaEntrega3.setMovementMethod(LinkMovementMethod.getInstance());
    	
    	txtImagen3 = (TextView) view.findViewById(R.id.valor_ActImg3);
    	txtImagen3.setMovementMethod(LinkMovementMethod.getInstance());
    	
    	txtLaumayer = (TextView) view.findViewById(R.id.valor_laumayer);
    	txtEntrega1 = (TextView) view.findViewById(R.id.valor_Ent1);
    	txtPieza1 = (TextView) view.findViewById(R.id.valor_Pieza1);
    	txtPieza1.setMovementMethod(LinkMovementMethod.getInstance());
    	txtEntrega2 = (TextView) view.findViewById(R.id.valor_Ent2);
    	txtPieza2 = (TextView) view.findViewById(R.id.valor_Pieza2);
    	txtPieza2.setMovementMethod(LinkMovementMethod.getInstance());
    	txtDiaMarca = (TextView) view.findViewById(R.id.valor_DiaMarca);
    	txtFechaMarca = (TextView) view.findViewById(R.id.valor_FechaMarca);
    	txtPlaca = (TextView) view.findViewById(R.id.valor_PlacaMarca);
    	txtFechaPlaca = (TextView) view.findViewById(R.id.valor_FechaPlaca);
    	txtCertificaciones = (TextView) view.findViewById(R.id.valor_Certificaciones);
    	txtOtrasEstr = (TextView) view.findViewById(R.id.valor_OtrasEstr);

    }



    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
 
    }
	

}
