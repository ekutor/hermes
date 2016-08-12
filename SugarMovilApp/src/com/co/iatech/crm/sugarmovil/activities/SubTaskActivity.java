package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.SubTasksModuleActions;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.DetailSubTask;
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

public class SubTaskActivity extends SubTasksModuleActions {

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subtask);
		try {
			selectedBean = null;

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_subtask);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			this.applyActions();

			this.chargeViewInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}
	}

	public void showValues(DetailSubTask tareaDetalle) {
		try {
			TextView valorContacto = (TextView) findViewById(R.id.valor_contacto);
			valorContacto.setText(tareaDetalle.getContact_name());

			TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
			valorTipo.setText(
					ListsConversor.convert(ConversorsType.TASKS_TYPE, tareaDetalle.getParent_type(), DataToGet.VALUE));

			TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
			valorNombre.setText(tareaDetalle.getParent_name());

			TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
			valorAsunto.setText(tareaDetalle.getName());

			TextView valorFechaInicio = (TextView) findViewById(R.id.boton_fecha_inicio);
			valorFechaInicio.setText(Utils.transformTimeBakendToUI(tareaDetalle.getFechainicio_c()));

			TextView valorFechaVence = (TextView) findViewById(R.id.boton_fecha_vence);
			valorFechaVence.setText(Utils.transformTimeBakendToUI(tareaDetalle.getFechafin_c()));

			TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
			valorEstado.setText(
					ListsConversor.convert(ConversorsType.TASKS_STATUS, tareaDetalle.getEstado_c(), DataToGet.VALUE));

			TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
			valorDescripcion.setText(tareaDetalle.getDescription());

			TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
			valorAsignado.setText(tareaDetalle.getAssigned_user_name());

		} catch (Exception e) {

			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}

	}

	@Override
	public void onResume() {
		try {
			selectedBean = (DetailSubTask) ActivitiesMediator.getInstance().getBeanInfo();
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
				selectedBean = new DetailSubTask(obj);
				showValues(selectedBean);
			}

		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}

	}

	@Override
	public void chargeViewInfo() {
		Intent intent = getIntent();

		if (intent.getExtras().get(MODULE.getModuleName()) instanceof DetailSubTask) {
			selectedBean = (DetailSubTask) intent.getExtras().get(MODULE.getModuleName());
			this.showValues(selectedBean);
		} else {
			beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "idSubTask", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getSubTask);
		}
	}
}
