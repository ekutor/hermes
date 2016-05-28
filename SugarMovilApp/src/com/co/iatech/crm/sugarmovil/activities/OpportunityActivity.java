package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.OpportunitiesModuleActions;
import com.co.iatech.crm.sugarmovil.adapters.search.GenericAdapterSearch;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OpportunityActivity extends OpportunitiesModuleActions {

	/**
	 * UI References.
	 */
	private Toolbar mCuentaToolbar;
	private ImageButton imgButtonTasks;
	private ListUsersConverter lc = new ListUsersConverter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opportunity);
		try {

			// Main Toolbar
			mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
			setSupportActionBar(mCuentaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			this.applyActions();
			this.chargeViewInfo();
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), this);
		}
	}

	public void showValues(OportunidadDetalle oportunidadDetalle) {
		TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
		valorNombre.setText(oportunidadDetalle.getName());

		TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
		valorTipo.setText(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, oportunidadDetalle.getTipo_c(),
				DataToGet.VALUE));

		TextView valorEtapa = (TextView) findViewById(R.id.valor_etapa);
		valorEtapa.setText(oportunidadDetalle.getSales_stage());
		TextView valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
		valorCuenta.setText(oportunidadDetalle.getNameAccount());
		TextView valorUsuario = (TextView) findViewById(R.id.valor_usuario);
		valorUsuario.setText(oportunidadDetalle.getUsuario_final_c());
		TextView valorFecha = (TextView) findViewById(R.id.valor_fecha);
		valorFecha.setText(Utils.transformTimeBakendToUI(oportunidadDetalle.getDate_closed()));
		TextView valorEstimado = (TextView) findViewById(R.id.valor_estimado);
		valorEstimado.setText(Utils.addSepMiles(oportunidadDetalle.getValoroportunidad_c()));
		TextView valorProbabilidad = (TextView) findViewById(R.id.valor_probabilidad);
		valorProbabilidad.setText(oportunidadDetalle.getProbability());
		TextView valorCampana = (TextView) findViewById(R.id.valor_campana);
		valorCampana.setText(oportunidadDetalle.getNameCampaign());

		TextView valorMedio = (TextView) findViewById(R.id.valor_medio);
		valorMedio.setText(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM, oportunidadDetalle.getMedio_c(),
				DataToGet.VALUE));

		TextView valorFuente = (TextView) findViewById(R.id.valor_fuente);
		valorFuente.setText(oportunidadDetalle.getFuente_c());
		TextView valorPaso = (TextView) findViewById(R.id.valor_paso);
		valorPaso.setText(oportunidadDetalle.getNext_step());
		TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
		valorDescripcion.setText(oportunidadDetalle.getDescription());
		TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);

		valorAsignado.setText(lc.convert(oportunidadDetalle.getAssigned_user_id(), DataToGet.VALUE));

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

	@Override
    public void onResume() {
    	try{
    		selectedBean = (OportunidadDetalle) ActivitiesMediator.getInstance().getBeanInfo();
	    	if(selectedBean != null){
	    		this.showValues(selectedBean);
	    	}
    	}catch(Exception e){
    		
    	}
        super.onResume();

    }
    
    @Override
   	public void applyActions() {
    	imgButtonEdit = (ImageButton) findViewById(R.id.ic_edit);       
        ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());
        
        imgButtonTasks = (ImageButton) findViewById(R.id.image_tasks);
		imgButtonTasks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivitiesMediator.getInstance().setParentBean(selectedBean);
				ActivitiesMediator.getInstance().setActualID(new ActivityBeanCommunicator(selectedBean.getId(), selectedBean.getName()), MODULE);
				ActivitiesMediator.getInstance().showList(OpportunityActivity.this, Modules.TASKS, MODULE);

			}

		});
   	}

	@Override
	public void addInfo(String serverResponse) {
		try {
			JSONObject jObj = new JSONObject(serverResponse);
			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
			
			if (jArr.length() > 0) {
				JSONObject obj = jArr.getJSONObject(0);
				selectedBean = new OportunidadDetalle(obj);
				showValues(selectedBean);
			}
			
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}
		
	}


	@Override
	public void chargeViewInfo() {
        Intent intent = getIntent();
        
		if (intent.getExtras().get(MODULE.getModuleName()) instanceof OportunidadDetalle) {
			selectedBean = (OportunidadDetalle) intent.getExtras().get(MODULE.getModuleName());
			this.showValues(selectedBean);
		} else {
			beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "idOpportunity", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getOpportunity);
		}
		
	}
}
