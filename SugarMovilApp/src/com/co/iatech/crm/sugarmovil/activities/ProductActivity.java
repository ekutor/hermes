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
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.ProductsModuleActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.ProductDetail;
import com.co.iatech.crm.sugarmovil.util.Utils;


public class ProductActivity extends ProductsModuleActions {

    /**
     * Member Variables.
     */
    private String cantidadStock;

    /**
     * UI References.
     */
    private Toolbar mProductoToolbar;

	private ActivityBeanCommunicator beanCommunicator;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        try{
	        Intent intent = getIntent();
	        beanCommunicator = intent.getParcelableExtra(Modules.PRODUCTS.name());
	        //cantidadStock = intent.getStringExtra("cantidad");
	 
	
	        // Main Toolbar
	        mProductoToolbar = (Toolbar) findViewById(R.id.toolbar_product);
	        setSupportActionBar(mProductoToolbar);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	        getSupportActionBar().setHomeButtonEnabled(false);
	        
	        applyActions();
	        chargeViewInfo();
	     
        }catch(Exception e){
       	   Message.showShortExt(Utils.errorToString(e), this);
         }
        
    }

    public void showValues(ProductDetail detail) {
    	try{
	        TextView valorCodigo = (TextView) findViewById(R.id.valor_codigo);
	        valorCodigo.setText(detail.getCodigo_c());
	        TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
	        valorNombre.setText(detail.getName());
	        TextView valorReferencia = (TextView) findViewById(R.id.valor_referencia);
	        valorReferencia.setText(detail.getReferencia_c());
	        TextView valorMarca = (TextView) findViewById(R.id.valor_marca);
	        valorMarca.setText(detail.getMarca_c());
	        TextView valorEnInventario = (TextView) findViewById(R.id.valor_en_inventario);
	        valorEnInventario.setText(detail.getEn_inventario_c());
	        TextView valorPrecioPesos = (TextView) findViewById(R.id.valor_precio_pesos);
	        valorPrecioPesos.setText(detail.getPrecio1_c());
	        TextView valorPrecioDolares = (TextView) findViewById(R.id.valor_precio_dolares);
	        valorPrecioDolares.setText(detail.getPrecio2_c());
	        TextView valorGrupo = (TextView) findViewById(R.id.valor_grupo);
	        valorGrupo.setText(detail.getGrupo_c());
	        
	        TextView numPed = (TextView) findViewById(R.id.valor_num_pedido);
	        numPed.setText(detail.getNumero_pedido_c());
	        TextView cant = (TextView) findViewById(R.id.valor_cant);
	        cant.setText(detail.getCantidad_c());
	        TextView fechaLau = (TextView) findViewById(R.id.valor_fecha_est_lau);
	        fechaLau.setText(detail.getFecha_est_llegada_lau_c());
	        TextView nota = (TextView) findViewById(R.id.valor_nota);
	        nota.setText(detail.getNota_c());
    	 }catch(Exception e){
        	   Message.showShortExt(Utils.errorToString(e), this);
          }
    }

    
	@Override
	public void addInfo(String serverResponse) {
		try {
  			JSONObject jObj = new JSONObject(serverResponse);
  			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
  			
  			if (jArr.length() > 0) {
  				JSONObject obj = jArr.getJSONObject(0);
  				selectedBean = new ProductDetail(obj);
  				showValues(selectedBean);
  			}
  			
  		} catch (Exception e) {
  			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
  		}
	}

	@Override
	public void chargeViewInfo() {

        Intent intent = getIntent();
        if(intent.getExtras().get(MODULE.getModuleName()) instanceof  ProductDetail ){
        	selectedBean = (ProductDetail) intent.getExtras().get(MODULE.getModuleName());
        	this.showValues(selectedBean);
        }else{
        	beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "idProducto", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getProducto);
        }

	}

	@Override
	public void applyActions() {
		imgButtonEdit = (ImageButton) findViewById(R.id.ic_edit);
		imgButtonEdit.setVisibility(View.INVISIBLE);
	}
}
