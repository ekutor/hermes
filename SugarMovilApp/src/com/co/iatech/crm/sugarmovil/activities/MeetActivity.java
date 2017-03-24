package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.CalendarModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.model.Meeting;
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

public class MeetActivity extends CalendarModuleActions {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meet);
		try {
			selectedBean = null;

			// Main Toolbar
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_meet);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			this.applyActions();

			this.chargeViewInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}
	}

	public void showValues(Meeting meet) {
		try {

			TextView valorAsunto = (TextView) findViewById(R.id.valor_asunto);
			valorAsunto.setText(meet.getName());

			TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
			valorEstado.setText(
					ListsConversor.convert(ConversorsType.MEETS_STATUS, meet.getStatus(), DataToGet.VALUE));
			
			
			TextView valorL = (TextView) findViewById(R.id.valor_lugar);
			valorL.setText(meet.getLocation());
			
			TextView valorFechaInicio = (TextView) findViewById(R.id.boton_fecha_inicio);
			valorFechaInicio.setText(Utils.transformTimeBakendToUI(meet.getDateStart()));

			TextView valorFechaVence = (TextView) findViewById(R.id.boton_fecha_vence);
			valorFechaVence.setText(Utils.transformTimeBakendToUI(meet.getDateEnd()));
			
			TextView valorDuracion = (TextView) findViewById(R.id.valor_duracion);
			valorDuracion.setText(meet.getDurationHours());
			
			TextView valorDescripcion = (TextView) findViewById(R.id.valor_descripcion);
			valorDescripcion.setText(meet.getDescription());
			
			TextView valorObj= (TextView) findViewById(R.id.valor_objetivos);
			valorObj.setText(meet.getObjetivos());
			
			TextView valorComp = (TextView) findViewById(R.id.valor_compromisos);
			valorComp.setText(meet.getCompromisos());

			TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
//			valorTipo.setText(
//					ListsConversor.convert(ConversorsType.MEETS_TYPE, meet.getParentType(), DataToGet.VALUE));

			TextView valorAsignado = (TextView) findViewById(R.id.valor_asignado_a);
			valorAsignado.setVisibility(View.INVISIBLE);
			//valorAsignado.setText(meet.ge);

			TextView valorNombre = (TextView) findViewById(R.id.valor_nombre);

			//valorNombre.setText(meet.getParent_name());
			
			
			TextView valorTipoR= (TextView) findViewById(R.id.valor_tipoReu);
			valorTipoR.setText(
					ListsConversor.convert(ConversorsType.MEETS_TYPE, meet.getTipo_c(), DataToGet.VALUE));
		} catch (Exception e) {

			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}

	}

	@Override
	public void onResume() {
		try {
			selectedBean = (Meeting) ActivitiesMediator.getInstance().getBeanInfo();
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
		imgButtonEdit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					ActivitiesMediator.getInstance().addObjectInfo( selectedBean );
					ActivitiesMediator.getInstance().showEditActivity(MeetActivity.this, 
							MODULE, true);
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
				selectedBean = new Meeting(obj);
				showValues(selectedBean);
			}

		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}

	}

	@Override
	public void chargeViewInfo() {
		Intent intent = getIntent();
			beanCommunicator = intent.getParcelableExtra(MODULE.name());
			String[] params = { "MeetingId", beanCommunicator.id };
			this.executeTask(params, TypeInfoServer.getOneMeeting);
		
	}

}
