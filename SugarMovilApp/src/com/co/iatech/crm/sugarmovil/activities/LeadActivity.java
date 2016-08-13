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
			
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			TextView valorRazon = (TextView) findViewById(R.id.valor_razon);
			valorRazon.setText(lead.getRazonsocial_c());
			
			
			TextView valorFechaInicio = (TextView) findViewById(R.id.boton_fecha_inicio);
			valorFechaInicio.setText(Utils.transformTimeBakendToUI(lead.getActive_date()));

			TextView valorFechaVence = (TextView) findViewById(R.id.boton_fecha_vence);
			valorFechaVence.setText(Utils.transformTimeBakendToUI(lead.getExp_date()));

			TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
			valorEstado.setText(
					ListsConversor.convert(ConversorsType.TASKS_STATUS, lead.getStatus_id(), DataToGet.VALUE));

			TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
			valorDescripcion.setText(lead.getDescription());
			
			TextView valorSubtarea = (TextView) findViewById(R.id.valor_subtarea);
			valorSubtarea.setText(lead.getParent_name());

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
