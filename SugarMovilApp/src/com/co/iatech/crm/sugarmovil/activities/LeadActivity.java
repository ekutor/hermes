package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.LeadsModuleActions;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Lead;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

public class LeadActivity extends LeadsModuleActions {

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lead);
		try {
			selectedBean = null;

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_client);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			this.applyActions();

			this.chargeViewInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}
	}

	public void showValues(Lead lead) {
		try {
			
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			
			TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
			valorNombre.setText(lead.getFirst_name());
			
			TextView valorApellidos = (TextView) findViewById(R.id.valor_apellidos);
			valorApellidos.setText(lead.getLast_name());
			
			TextView valorTelOficina = (TextView) findViewById(R.id.valor_oficina);
			valorTelOficina.setText(lead.getPhone_work());
			
			TextView celular = (TextView) findViewById(R.id.valor_celular);
			celular.setText(lead.getPhone_mobile());
			
			TextView valorFax = (TextView) findViewById(R.id.valor_fax);
			valorFax.setText(lead.getPhone_fax());
			
			TextView cargo = (TextView) findViewById(R.id.valor_cargo);
			cargo.setText(lead.getTitle());
			
			TextView departamento = (TextView) findViewById(R.id.valor_departamento);
			departamento.setText(lead.getDepartment());
			
			TextView direccion = (TextView) findViewById(R.id.valor_direccion);
			direccion.setText(lead.getPrimary_address_street()+ "\n" + lead.getPrimary_address_city());
			
			TextView correo = (TextView) findViewById(R.id.valor_correo);
			correo.setText(lead.getEmail_address());
			
			TextView sitio = (TextView) findViewById(R.id.valor_sitio);
			sitio.setText(lead.getWebsite());
			
			TextView requerimiento = (TextView) findViewById(R.id.valor_requerimiento);
			requerimiento.setText(lead.getDescription());
			
			TextView medio = (TextView) findViewById(R.id.valor_medio);
			medio.setText(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM, lead.getMedio_c(),
					DataToGet.VALUE));
			
			TextView fuente = (TextView) findViewById(R.id.valor_fuente);
			fuente.setText(lead.getFuente_c());
			
			TextView otro = (TextView) findViewById(R.id.valor_otro);
			otro.setText(lead.getOtro_c());
			
			
			TextView estimado = (TextView) findViewById(R.id.valor_estimado);
			estimado.setText(lead.getOpportunity_amount());
			
			TextView marca = (TextView) findViewById(R.id.valor_marca);
			marca.setText(ListsConversor.convert(ConversorsType.LEADS_BRAND, lead.getMarca_c(),
					DataToGet.VALUE));
			 
			TextView estado = (TextView) findViewById(R.id.valor_estado);
			estado.setText(ListsConversor.convert(ConversorsType.LEADS_STATUS, lead.getStatus(),
					DataToGet.VALUE));
			
			TextView accion = (TextView) findViewById(R.id.valor_accion);
			accion.setText(lead.getAccion_c());
			
			TextView retroalimenta = (TextView) findViewById(R.id.valor_retroalimenta);
			retroalimenta.setText(lead.getStatus_description());
			
			TextView fecha = (TextView) findViewById(R.id.valor_fecha);
			fecha.setText(Utils.transformTimeBakendToUI(lead.getFecha_c()));
			
			TextView responsable = (TextView) findViewById(R.id.valor_responsable);
			responsable.setText(lead.getResponsable_c());
			
			TextView retroalimenta2 = (TextView) findViewById(R.id.valor_retroalimenta2);
			retroalimenta2.setText(lead.getRetroalimentacion2_c());
			
			TextView fecha2 = (TextView) findViewById(R.id.valor_fecha2);
			fecha2.setText(Utils.transformTimeBakendToUI(lead.getFecha2_c()));
			
			TextView responsable2 = (TextView) findViewById(R.id.valor_responsable2);
			responsable2.setText(lead.getResponsable2_c());
			
			TextView retroalimenta3 = (TextView) findViewById(R.id.valor_retroalimenta3);
			retroalimenta3.setText(lead.getRetroalimentacion3_c());
			
			TextView fecha3 = (TextView) findViewById(R.id.valor_fecha3);
			fecha3.setText(Utils.transformTimeBakendToUI(lead.getFecha3_c()));
			
			TextView responsable3 = (TextView) findViewById(R.id.valor_responsable3);
			responsable3.setText(lead.getResponsable3_c());
			
			TextView valorReal = (TextView) findViewById(R.id.valor_real);
			valorReal.setText(lead.getValor_real_c());
			
			TextView campana = (TextView) findViewById(R.id.valor_campana);
			campana.setText(lead.getCampaign_name());

			TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
			valorAsignado.setText(lead.getAssigned_user_name());

		} catch (Exception e) {

			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}

	}

	@Override
	public void onResume() {
		try {
			selectedBean = (Lead) ActivitiesMediator.getInstance().getBeanInfo();
			if (selectedBean != null) {
				this.showValues(selectedBean);
			}
		} catch (Exception e) {

		}
		super.onResume();

	}

	@Override
	public void applyActions() {
		imgButtonEdit = (ImageButton) findViewById(R.id.ic_edit);
		ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());
	}

	@Override
	public void addInfo(String serverResponse) {
		try {
			JSONObject jObj = new JSONObject(serverResponse);
			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);

			if (jArr.length() > 0) {
				JSONObject obj = jArr.getJSONObject(0);
				selectedBean = new Lead(obj);
				showValues(selectedBean);
			}

		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}

	}

	@Override
	public void chargeViewInfo() {
		Intent intent = getIntent();

		if (intent.getExtras().get(MODULE.getModuleName()) instanceof Lead) {
			selectedBean = (Lead) intent.getExtras().get(MODULE.getModuleName());
			this.showValues(selectedBean);
		} else {
			beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "idLead", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getLead);
		}
	}
}
