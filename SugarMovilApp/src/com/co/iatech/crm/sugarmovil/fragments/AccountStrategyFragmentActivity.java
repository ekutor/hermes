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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;


public class AccountStrategyFragmentActivity extends Fragment {

    /**
     * Debug.
     */
    private static final String TAG = "AccountStrategyFragmentActivity";


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
  
    
    
    private TextView descAdicional1,descAdicional2,descAdicional3,descAdicional4,descAdicional5,
    				descAdicional6,descAdicional7,descAdicional8,descAdicional9,descAdicional10;
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
    private CheckBox moeller;
    private CheckBox enerlux;
    private CheckBox delta;
    private CheckBox cbDkc;
    private CheckBox cbLeviton;
    private CheckBox cbVcai;
    private CheckBox cbWohner;
    private CheckBox cbLP;
    private CheckBox cbEaton;
    private CheckBox cbxenergy;
    private CheckBox cbMinEaton;
    private CheckBox cbBonEspecial;
    private CheckBox cbBonCompra;
    private CheckBox cbBonEaton;
    private CheckBox cbBonLeviton;
    private CheckBox cbBonDespRem;
    private CheckBox cbBonCobroAnticipo;
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
       
        View rootView = inflater.inflate(R.layout.fragment_account_strategy, container, false);
    	 
        Bundle args = getArguments();

        
        String idCuenta = args.getString(Info.CUENTA_ACTUAL.name());
  
        this.cargarComponentes(rootView);
        GetAccountTask mTareaObtenerCuenta = new GetAccountTask();
        mTareaObtenerCuenta.execute(idCuenta);
        Log.d(TAG, "Id cuenta " + idCuenta);
        	
        
       
        return rootView;
    }
    
    
    public void ponerValores(CuentaDetalle cuentaDetalle) {
    	chargeCheck(moeller, cuentaDetalle.getAutomatizacionmoeller_c());
    	chargeCheck(delta, cuentaDetalle.getDelta_c());
    	chargeCheck(enerlux, cuentaDetalle.getEnerlux_c());
    	chargeCheck(cbDkc, cuentaDetalle.getDkc_c());
		chargeCheck(cbLeviton, cuentaDetalle.getLeviton_c());
		chargeCheck(cbVcai, cuentaDetalle.getWvcai_c());
		chargeCheck(cbWohner, cuentaDetalle.getWohner_c());
		chargeCheck(cbLP, cuentaDetalle.getComunicaciones_c());
		chargeCheck(cbEaton, cuentaDetalle.getManiobraeaton_c());
		//chargeCheck(cbxenergy, cuentaDetalle.getx);
		chargeCheck(cbMinEaton, cuentaDetalle.getPreciosmineaton_c());
		chargeCheck(cbBonEspecial, cuentaDetalle.getBonosespeciales_c());
		chargeCheck(cbBonCompra, cuentaDetalle.getBonificacioncompra_c());
		chargeCheck(cbBonEaton, cuentaDetalle.getManiobraeaton_c());
		chargeCheck(cbBonLeviton, cuentaDetalle.getBonificacionleviton_c());
		chargeCheck(cbBonDespRem, cuentaDetalle.getDespachoremsion_c());
		chargeCheck(cbBonCobroAnticipo, cuentaDetalle.getNocobro_c());
		
		
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
    	enerlux = (CheckBox) view.findViewById(R.id.checkbox_enerlux);
    	cbDkc = (CheckBox) view.findViewById(R.id.checkbox_dkc);
    	cbLeviton = (CheckBox) view.findViewById(R.id.checkbox_leviton);
    	cbVcai = (CheckBox) view.findViewById(R.id.checkbox_vcai);
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

    }



    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        

        /*
        switch(view.getId()) {
            case R.id.checkbox_aut_moeller:
                if (checked)
                    // Put some meat on the sandwich
                else
                    // Remove the meat
                break;
            case R.id.checkbox_cheese:
                if (checked)
                    // Cheese me
                else
                    // I'm lactose intolerant
                break;
            // TODO: Veggie sandwich
        }*/
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
            progressDialog = new ProgressDialog(AccountStrategyFragmentActivity.this.getActivity(), ProgressDialog.THEME_HOLO_DARK);
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
