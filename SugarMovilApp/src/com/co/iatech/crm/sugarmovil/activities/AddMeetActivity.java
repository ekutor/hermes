package com.co.iatech.crm.sugarmovil.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitorsManager;
import com.co.iatech.crm.sugarmovil.activities.ui.DatePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activities.ui.TimePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorActivities;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.CalendarModuleEditableActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModuleEditableActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Account;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Meeting;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListContactConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListModelConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddMeetActivity extends CalendarModuleEditableActions {


	/**
	 * Member Variables.
	 */

	private Meeting selectedMeet;

	private ListUsersConverter lc = new ListUsersConverter();
	private ListAccountConverter lac = new ListAccountConverter();
	private ListContactConverter listContacts = new ListContactConverter(ListsHolderType.CONTACTS_ACCOUNTS);
	private ListContactConverter allContacts = new ListContactConverter(ListsHolderType.CONTACTS);
	
	private TypeActions tipoPermiso;

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;
	private ImageButton imgButtonGuardar;
	private EditText valorAsunto, valorDescripcion, valorObjetivos,valorCompromisos,valorLugar, asistentes;
	private Spinner valorEstado, valorTipo;
	private Button botonFechaInicio, botonFechaVen, botonHoraInicio, botonHoraVen;
	private TextView valorFechaInicio, asignadoA, valorFechaVen, valorNombre;

	public String resultado;

	private boolean selectedTypeTaskAccount;

	private AddMeet editMeet;

	private ActivityBeanCommunicator accountId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_calendar);
		try {

			// SoftKey
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			createWidgets();
			getInfoFromMediator();

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_task);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			defineValidations();
			
		    this.chargeLists();
			asignadoA.setOnClickListener(this);

			if (isEditMode) {
				TextView title = (TextView) findViewById(R.id.text_cal_toolbar);
				title.setText("EDITAR REUNION");
				chargeViewInfo();
			}

		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddMeetActivity.this, MODULE);
		}
	}

	@Override
	public void getInfoFromMediator() {
		super.getInfoFromMediator();

		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
		Intent intent = getIntent();

		if (isEditMode) {
			selectedMeet = intent.getParcelableExtra(MODULE.getModuleName());
		} else {
			selectedMeet = new Meeting();
			int pos = 0;
			
			pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
					actualInfo.getActualParentModule().getSugarDBName());
			valorTipo.setSelection(0);
	
		}

		

	}

	@Override
	public void chargeLists() {


		// Estado

		ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.MEETS_STATUS));
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorEstado.setAdapter(estadoAdapter);


		int contactPosition = 0;

		if (isEditMode) {

	
			// Carga Tipos
			if (selectedMeet.getType() != null) {
				int pos = ListsConversor.getPosItemOnList(ConversorsType.MEETS_TYPE,
						selectedMeet.getType());
				valorTipo.setSelection(pos);
			}
			
			if (selectedMeet.getStatus()!= null) {
				int pos = ListsConversor.getPosItemOnList(ConversorsType.MEETS_STATUS,
						selectedMeet.getStatus());
				valorEstado.setSelection(pos);
			}



		} 

//		valorDescripcion.setText("Parent " + actualInfo.getActualParentModule() + " actual "
//				+ actualInfo.getActualPrincipalModule() + " " + actualInfo.getActualParentId() + " "
//				+ actualInfo.getActualPrincipalId() + " pos " + contactPosition + " id account " + accountId);


		GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		//selectedMeet.setCreated_by(u.getId());

		asignadoA.setText(u.getFirst_name() + " " + u.getLast_name());
		//selectedMeet.setAssigned_user_id(u.getId());
	

	}

	@Override
	public void createWidgets() {
		valorAsunto = (EditText) findViewById(R.id.valor_asunto);
		valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
		valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
		valorFechaVen = (TextView) findViewById(R.id.valor_fecha_vence);
		valorNombre = (TextView) findViewById(R.id.valor_nombre);
		valorNombre.setOnClickListener(this);
		
		valorObjetivos = (EditText) findViewById(R.id.valor_objetivos);
		
		valorCompromisos = (EditText) findViewById(R.id.valor_compromisos);
		asistentes = (EditText) findViewById(R.id.valor_asistentes);
		valorLugar = (EditText) findViewById(R.id.valor_lugar);

		// Fecha Inicio
		botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
		botonFechaInicio.setOnClickListener(this);

		botonHoraInicio = (Button) findViewById(R.id.boton_hora_inicio);
		botonHoraInicio.setOnClickListener(this);

		botonFechaVen = (Button) findViewById(R.id.boton_fecha_vence);
		botonFechaVen.setOnClickListener(this);

		botonHoraVen = (Button) findViewById(R.id.boton_hora_vence);
		botonHoraVen.setOnClickListener(this);

		imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
		imgButtonGuardar.setOnClickListener(this);
		asignadoA = (TextView) findViewById(R.id.valor_asignado_a);

		// Contacto
	
		valorEstado = (Spinner) findViewById(R.id.valor_estado);

		valorTipo = (Spinner) findViewById(R.id.valor_tipo);
		ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.MEETS_TYPE));
		tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorTipo.setAdapter(tipoAdapter);
//		valorTipo.setOnItemSelectedListener(new OnItemSelectedListener() {
//			@Override
//			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//				int visibility;
//				visibility = View.INVISIBLE;
//				selectedTypeTaskAccount = false;
//				Modules selectedModuleType = null;
//				for (Modules mod : nameVisibleOnPermitedModules) {
//					if (valorTipo.getSelectedItem().toString().toLowerCase()
//							.contains(mod.getVisualName().toLowerCase())) {
//						visibility = View.VISIBLE;
//						selectedTypeTaskAccount = true;
//						selectedModuleType = mod;
//						break;
//					}
//				}
//
//				valorNombre.setVisibility(visibility);
//				if (actualInfo.getActualParentInfo() == null && selectedModuleType != null) {
//					valorNombre.setText(
//							ValidatorActivities.SELECT_MESSAGE + " " +selectedModuleType.getVisualName().toLowerCase());
//				}
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//
//			}
//		});

	}

	@Override
	public void chargeViewInfo() {

		valorAsunto.setText(selectedMeet.getName());
		int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_STATUS, selectedMeet.getStatus());
		valorEstado.setSelection(pos);
		valorFechaInicio.setText(Utils.transformTimeBakendToUI(selectedMeet.getDateStart()));
		valorFechaVen.setText(Utils.transformTimeBakendToUI(selectedMeet.getDateEnd()));

		valorDescripcion.setText(selectedMeet.getDescription());

		// Asignado
		//asignadoA.setText(lc.convert(selectedMeet.getAssigned_user_id(), DataToGet.VALUE));
		imgButtonGuardar.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == asignadoA.getId()) {
			if(isEditMode){
				switch (tipoPermiso) {
				case OWNER:
					break;
				case ALL:
					Message.showUsersDialog(getSupportFragmentManager(), v.getId());
					break;
				case GROUP:
					break;
				}
			}else{
				Message.showUsersDialog(getSupportFragmentManager(), v.getId());
			}

		} else if (v.getId() == botonHoraInicio.getId()) {
			DialogFragment newFragment = new TimePickerFragment(this, valorFechaInicio, isEditMode);
			newFragment.show(getFragmentManager(), "hourCierrePicker");
		} else if (v.getId() == botonFechaInicio.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaInicio, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == botonHoraVen.getId()) {
			DialogFragment newFragment = new TimePickerFragment(this, valorFechaVen, isEditMode);
			newFragment.show(getFragmentManager(), "hourCierrePicker");
		} else if (v.getId() == botonFechaVen.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaVen, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == valorNombre.getId()) {
			switch (actualInfo.getActualParentModule()) {
			case ACCOUNTS:
				Message.showGenericDialog(getSupportFragmentManager(),
						lac,actualInfo.getActualParentModule());
				break;
			case CONTACTS:
				Message.showGenericDialog(getSupportFragmentManager(),
						allContacts,actualInfo.getActualParentModule());
				break;
			default:
				break;
			}
		} else if (v.getId() == imgButtonGuardar.getId()) {
			// Realizar Validaciones
			if (!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())) {
				return;
			}
			if (selectedTypeTaskAccount && !ValidatorGeneric.getInstance().execute(valorNombre,
					"Seleccionó un tipo, debe seleccionar la informacion relacionada", getApplicationContext())) {
				return;
			}
			imgButtonGuardar.setVisibility(View.INVISIBLE);

			selectedMeet.setName(valorAsunto.getText().toString());
			selectedMeet.setStatus(ListsConversor.convert(ConversorsType.TASKS_STATUS,
					valorEstado.getSelectedItem().toString(), DataToGet.CODE));
		
			if (valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1) {
				selectedMeet.setDateStart(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
			}

			if (valorFechaVen.getText() != null && valorFechaVen.getText().toString().length() > 1) {
				selectedMeet.setDateEnd(Utils.transformTimeUItoBackend(valorFechaVen.getText().toString()));
			}

			selectedMeet.setDescription(valorDescripcion.getText().toString());
			selectedMeet.setCompromisos(valorCompromisos.getText().toString());
			selectedMeet.setObjetivos(valorObjetivos.getText().toString());
			selectedMeet.setLocation(valorLugar.getText().toString());
			String selectedType = valorTipo.getSelectedItem().toString();
			selectedMeet.setType(ListsConversor.convert(ConversorsType.MEETS_TYPE, selectedType, DataToGet.CODE));
			
			String selectedState = valorEstado.getSelectedItem().toString();
			selectedMeet.setStatus(ListsConversor.convert(ConversorsType.MEETS_TYPE, selectedState, DataToGet.CODE));

			
			if(!isEditMode){
			
			/*	if (selectedMeet.getParent_type().equals(actualInfo.getActualParentModule().getSugarDBName())) {
					selectedMeet.setParent_id(actualInfo.getActualParentInfo().id);
				}else if(accountId != null){//aplica para contacts
					selectedMeet.setParent_id(accountId.id);
				} */
			
			}
			String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(), DataToGet.CODE);
			selectedMeet.setUserId(idUsuarioAsignado);

			editMeet = new AddMeet();

			editMeet.execute(selectedMeet);
		}

	}

	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();

		data.put(valorAsunto, "El campo Asunto no puede estar vacio");
		data.put(valorEstado, "Debe seleccionar un Estado de la Tarea");


		ValidatorGeneric.getInstance().define(data);

	}

	@Override
	public void onFinishSearchDialog(GenericBean selectedBean, int elementId) {
		if (selectedBean instanceof User) {
			User selectedUser = (User) selectedBean;
			asignadoA.setText(selectedUser.getUser_name());
		} else if (selectedBean instanceof Account) {
			Account ac = (Account) selectedBean;
			valorNombre.setText(ac.getName());
		}

	}

	@Override
	public void addInfo(String serverResponse) {
		DataManager manager = DataManager.getInstance();
		try {

			JSONObject jObj = new JSONObject(serverResponse);
			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
			manager.contactsxAccountsInfo.clear();
			for (int i = 0; i < jArr.length(); i++) {
				JSONObject obj = jArr.getJSONObject(i);
				manager.contactsxAccountsInfo.add(new Contacto(obj));
			}
			if (manager.contactsxAccountsInfo.size() > 0) {
				ListsHolder.saveList(ListsHolderType.CONTACTS_ACCOUNTS,
						DataManager.getInstance().contactsxAccountsInfo);
			}
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}
		chargeLists();
		if (isEditMode) {
			chargeViewInfo();
		}
	}

	/**
	 * Representa una tarea asincrona de creacion de Reuniones.
	 */
	public class AddMeet extends AsyncTask<Object, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Object... params) {
			try {

				Meeting obj = (Meeting) params[0];

				// Resultado
				resultado = null;

				if (isEditMode) {
					resultado = ControlConnection.putInfo(TypeInfoServer.addMeet, obj.getDataBean(), Modo.EDITAR,
							AddMeetActivity.this);
				} else {
					resultado = ControlConnection.putInfo(TypeInfoServer.addMeet, obj.getDataBean(), Modo.AGREGAR,
							AddMeetActivity.this);
				}
	

				if (resultado.contains("OK")) {
					obj.id = Utils.getIDFromBackend(resultado);
				
					ActivitiesMediator.getInstance().addObjectInfo(obj);
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				
				resultado += Utils.errorToString(e);
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			// Message.showShortExt(resultado+" "+success+ resultado.length(),
			// AddTaskActivity.this);
			if (success) {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddMeetActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddMeetActivity.this, MODULE);

				}

			} else {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.NO_EDITED, AddMeetActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddMeetActivity.this, MODULE);
					// Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED,
					// AddTaskActivity.this, MODULE );

				}
				
			}

		}

		@Override
		protected void onCancelled() {

		}
	}

}
