package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.NotesModuleActions;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.DetailSubTask;
import com.co.iatech.crm.sugarmovil.model.Notes;
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

public class NoteActivity extends NotesModuleActions {

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes);
		try {
			selectedBean = null;

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_notes);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			this.applyActions();

			this.chargeViewInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}
	}

	public void showValues(Notes note) {
		try {
			
			TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);
			valorNombre.setText(note.getName());
			
			TextView valorFechaInicio = (TextView) findViewById(R.id.boton_fecha_inicio);
			valorFechaInicio.setText(Utils.transformTimeBakendToUI(note.getActive_date()));

			TextView valorFechaVence = (TextView) findViewById(R.id.boton_fecha_vence);
			valorFechaVence.setText(Utils.transformTimeBakendToUI(note.getExp_date()));

			TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
			valorEstado.setText(
					ListsConversor.convert(ConversorsType.TASKS_STATUS, note.getStatus_id(), DataToGet.VALUE));

			TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
			valorDescripcion.setText(note.getDescription());
			
			TextView valorSubtarea = (TextView) findViewById(R.id.valor_subtarea);
			valorSubtarea.setText(note.getParent_name());

			TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
			valorAsignado.setText(note.getAssigned_user_name());

		} catch (Exception e) {

			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}

	}

	@Override
	public void onResume() {
		try {
			selectedBean = (Notes) ActivitiesMediator.getInstance().getBeanInfo();
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
				selectedBean = new Notes(obj);
				showValues(selectedBean);
			}

		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}

	}

	@Override
	public void chargeViewInfo() {
		Intent intent = getIntent();

		if (intent.getExtras().get(MODULE.getModuleName()) instanceof Notes) {
			selectedBean = (Notes) intent.getExtras().get(MODULE.getModuleName());
			this.showValues(selectedBean);
		} else {
			beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "idNote", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getNote);
		}
	}
}
