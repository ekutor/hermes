package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModuleActions;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TaskActivity extends TasksModuleActions implements OnClickListener {

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;
	private ImageButton imgButtonSubTasks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		try {
			selectedBean = null;

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_task);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			this.applyActions();

			this.chargeViewInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}
	}

	public void showValues(DetailTask tareaDetalle) {
		try {

			TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
			valorAsunto.setText(tareaDetalle.getName());

			TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
			valorEstado.setText(
					ListsConversor.convert(ConversorsType.TASKS_STATUS, tareaDetalle.getStatus(), DataToGet.VALUE));

			TextView valorFechaInicio = (TextView) findViewById(R.id.boton_fecha_inicio);
			valorFechaInicio.setText(Utils.transformTimeBakendToUI(tareaDetalle.getDate_start()));

			TextView valorFechaVence = (TextView) findViewById(R.id.boton_fecha_vence);
			valorFechaVence.setText(Utils.transformTimeBakendToUI(tareaDetalle.getDate_due()));

			TextView valorContacto = (TextView) findViewById(R.id.valor_contacto);
			valorContacto.setText(tareaDetalle.getContact_name());

			TextView valorEstimado = (TextView) findViewById(R.id.valor_estimado);
			valorEstimado.setText(tareaDetalle.getTrabajo_estimado_c());
			TextView valorPrioridad = (TextView) findViewById(R.id.valor_prioridad);

			valorPrioridad.setText(
					ListsConversor.convert(ConversorsType.TASKS_PRIORITY, tareaDetalle.getPriority(), DataToGet.VALUE));

			TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
			valorDescripcion.setText(tareaDetalle.getDescription());

			TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
			valorTipo.setText(
					ListsConversor.convert(ConversorsType.TASKS_TYPE, tareaDetalle.getParent_type(), DataToGet.VALUE));

			TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
			valorAsignado.setText(tareaDetalle.getAssigned_user_name());

			TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);

			valorNombre.setText(tareaDetalle.getParent_name());
		} catch (Exception e) {

			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}

	}

	@Override
	public void onResume() {
		try {
			selectedBean = (DetailTask) ActivitiesMediator.getInstance().getBeanInfo();
			if (selectedBean != null) {
				this.showValues(selectedBean);
			}
		} catch (Exception e) {

		}
		super.onResume();

	}

	@Override
	public void applyActions() {
		imgButtonSubTasks = (ImageButton) findViewById(R.id.image_subtasks);
		imgButtonSubTasks.setOnClickListener(this);

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
				selectedBean = new DetailTask(obj);
				showValues(selectedBean);
			}

		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}

	}

	@Override
	public void chargeViewInfo() {
		Intent intent = getIntent();

		if (intent.getExtras().get(MODULE.getModuleName()) instanceof DetailTask) {
			selectedBean = (DetailTask) intent.getExtras().get(MODULE.getModuleName());
			this.showValues(selectedBean);
		} else {
			beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "idTask", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getTask);
		}
	}

	@Override
	public void onClick(View v) {
		try {
			Modules moduletoStart = null;

			if (v.getId() == imgButtonSubTasks.getId()) {
				moduletoStart = Modules.SUBTASKS;
				if (selectedBean != null) {
					ActivitiesMediator.getInstance().setParentBean(selectedBean);
					ActivitiesMediator.getInstance().setActualID(
							new ActivityBeanCommunicator(selectedBean.getId(), selectedBean.getName()), MODULE);
				}
				ActivitiesMediator.getInstance().showList(TaskActivity.this, moduletoStart, MODULE);
			}
			
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), this);
		}
	}

}
