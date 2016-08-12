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
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorActivities;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.SubTasksModuleEditableActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.DetailSubTask;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListContactConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
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

public class AddSubTaskActivity extends SubTasksModuleEditableActions {


	/**
	 * Member Variables.
	 */

	private DetailSubTask selectedTask;

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
	private TextView valorAsunto, valorDescripcion, valorNombre;
	private Spinner valorEstado, valorTipo, valorContacto;
	private Button botonFechaInicio, botonFechaVen;
	private TextView valorFechaInicio, asignadoA, valorFechaVen;

	public String resultado;

	private boolean selectedTypeTaskAccount;

	private AddTask editTask;

	private ActivityBeanCommunicator parentId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_subtask);
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
				TextView title = (TextView) findViewById(R.id.text_task_toolbar);
				title.setText("EDITAR SUBTAREA");
				chargeViewInfo();
			}

		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddSubTaskActivity.this, MODULE);
		}
	}

	@Override
	public void getInfoFromMediator() {
		super.getInfoFromMediator();

		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
		Intent intent = getIntent();

		if (isEditMode) {
			selectedTask = intent.getParcelableExtra(MODULE.getModuleName());
		} else {
			selectedTask = new DetailSubTask();
			int pos = 0;
			
			pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
					actualInfo.getActualParentModule().getSugarDBName());
			
			boolean enabled = false;

			 
			switch (actualInfo.getActualParentModule()) {
			case TASKS:
				parentId = actualInfo.getActualParentInfo();
				valorNombre.setText(parentId.name);
				break;
			default:
				pos = 0;
				enabled = true;
				break;
			}
			
			valorTipo.setSelection(pos);
			valorTipo.setEnabled(enabled);
		}

		

	}

	@Override
	public void chargeLists() {

		ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				listContacts.getListInfo());
		contactAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorContacto.setAdapter(contactAdapter);
		// Estado

		ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_STATUS));
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorEstado.setAdapter(estadoAdapter);

		int contactPosition = 0;

		if (isEditMode) {
			// Contact
			String contact = selectedTask.getContact_name();
			valorContacto.setSelection(listContacts.getListInfo().indexOf(contact));
			valorContacto.setVisibility(View.INVISIBLE);
			TextView valorContactoOculto = (TextView) findViewById(R.id.text_contacto_hidden);
			
			valorContactoOculto.setVisibility(View.VISIBLE);
			valorContactoOculto.setText(contact);
			// Parent
			if (selectedTask.getParent_id() != null && selectedTask.getParent_id().length() > 1) {
				valorNombre.setText(selectedTask.getParent_name());
			}

			// Carga Tipos
			if (selectedTask.getParent_type() != null) {
				int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
						selectedTask.getParent_type());
				valorTipo.setSelection(pos);
			}

		} else {

			// Contact
			if (actualInfo.getActualParentModule().equals(Modules.CONTACTS)
					|| actualInfo.getActualPrincipalModule().equals(Modules.CONTACTS)) {
				if (actualInfo.getActualParentModule().equals(Modules.CONTACTS)) {
					contactPosition = Integer
							.parseInt(listContacts.convert(actualInfo.getActualParentInfo().id, DataToGet.POS));
				} else if (actualInfo.getActualPrincipalModule().equals(Modules.CONTACTS)) {
					contactPosition = Integer
							.parseInt(listContacts.convert(actualInfo.getActualPrincipalInfo().id, DataToGet.POS));
				}
				valorContacto.setSelection(contactPosition);
			}

//		valorDescripcion.setText("Parent " + actualInfo.getActualParentModule() + " actual "
//				+ actualInfo.getActualPrincipalModule() + " " + actualInfo.getActualParentId() + " "
//				+ actualInfo.getActualPrincipalId() + " pos " + contactPosition + " id account " + accountId);



		GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		selectedTask.setCreated_by(u.getId());

		asignadoA.setText(u.getFirst_name() + " " + u.getLast_name());
		selectedTask.setAssigned_user_id(u.getId());
		}

	}

	@Override
	public void createWidgets() {
		valorAsunto = (EditText) findViewById(R.id.valor_asunto);
		valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
		valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
		valorFechaVen = (TextView) findViewById(R.id.valor_fecha_vence);
		valorNombre = (TextView) findViewById(R.id.valor_nombre);
		valorNombre.setOnClickListener(this);

		// Fecha Inicio
		botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
		botonFechaInicio.setOnClickListener(this);

		botonFechaVen = (Button) findViewById(R.id.boton_fecha_vence);
		botonFechaVen.setOnClickListener(this);

		imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
		imgButtonGuardar.setOnClickListener(this);
		asignadoA = (TextView) findViewById(R.id.valor_asignado_a);

		// Contacto
		valorContacto = (Spinner) findViewById(R.id.valor_contacto);
		valorEstado = (Spinner) findViewById(R.id.valor_estado);

		valorTipo = (Spinner) findViewById(R.id.valor_tipo);
		ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_TYPE));
		tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorTipo.setAdapter(tipoAdapter);
		valorTipo.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				int visibility;
				visibility = View.INVISIBLE;
				selectedTypeTaskAccount = false;
				Modules selectedModuleType = null;
				for (Modules mod : nameVisibleOnPermitedModules) {
					if (valorTipo.getSelectedItem().toString().toLowerCase()
							.contains(mod.getVisualName().toLowerCase())) {
						visibility = View.VISIBLE;
						selectedTypeTaskAccount = true;
						selectedModuleType = mod;
						break;
					}
				}

				valorNombre.setVisibility(visibility);
				if (actualInfo.getActualParentInfo() == null && selectedModuleType != null) {
					valorNombre.setText(
							ValidatorActivities.SELECT_MESSAGE + " " +selectedModuleType.getVisualName().toLowerCase());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	@Override
	public void chargeViewInfo() {

		valorAsunto.setText(selectedTask.getName());
		int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_STATUS, selectedTask.getEstado_c());
		valorEstado.setSelection(pos);
		valorFechaInicio.setText(Utils.transformTimeBakendToUI(selectedTask.getFechainicio_c()));
		valorFechaVen.setText(Utils.transformTimeBakendToUI(selectedTask.getFechafin_c()));

		// contacto
		valorDescripcion.setText(selectedTask.getDescription());

		// Asignado
		asignadoA.setText(lc.convert(selectedTask.getAssigned_user_id(), DataToGet.VALUE));
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
					Message.showUsersDialog(getSupportFragmentManager());
					break;
				case GROUP:
					break;
				}
			}else{
				Message.showUsersDialog(getSupportFragmentManager());
			}

		} else if (v.getId() == botonFechaInicio.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaInicio, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
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

			selectedTask.setName(valorAsunto.getText().toString());
			selectedTask.setEstado_c(ListsConversor.convert(ConversorsType.TASKS_STATUS,
					valorEstado.getSelectedItem().toString(), DataToGet.CODE));

			if (valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1) {
				selectedTask.setFechainicio_c(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
			}

			if (valorFechaVen.getText() != null && valorFechaVen.getText().toString().length() > 1) {
				selectedTask.setFechafin_c(Utils.transformTimeUItoBackend(valorFechaVen.getText().toString()));
			}

			// contacto

			if (valorContacto.getSelectedItemPosition() > 0) {
				selectedTask.setContact_name(valorContacto.getSelectedItem().toString());
				selectedTask.setContact_id(
						listContacts.convert(valorContacto.getSelectedItem().toString(), DataToGet.CODE));
			}

			selectedTask.setDescription(valorDescripcion.getText().toString());

			// tipo Tarea
			
			if(!isEditMode){
				String selectedType = valorTipo.getSelectedItem().toString();
				selectedTask
						.setParent_type(ListsConversor.convert(ConversorsType.TASKS_TYPE, selectedType, DataToGet.CODE));
	
				if (selectedTask.getParent_type().equals(actualInfo.getActualParentModule().getSugarDBName())) {
					selectedTask.setParent_id(parentId.id);
				}
			
			}
			String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(), DataToGet.CODE);
			selectedTask.setAssigned_user_id(idUsuarioAsignado);

			editTask = new AddTask();

			editTask.execute(selectedTask);
		}

	}

	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();

		data.put(valorAsunto, "El campo Asunto no puede estar vacio");
		data.put(valorEstado, "Debe seleccionar un Estado de la Subtarea");
		
		ValidatorGeneric.getInstance().define(data);

	}

	@Override
	public void onFinishSearchDialog(GenericBean selectedBean) {
		if (selectedBean instanceof User) {
			User selectedUser = (User) selectedBean;
			asignadoA.setText(selectedUser.getUser_name());
		} else if (selectedBean instanceof Cuenta) {
			Cuenta ac = (Cuenta) selectedBean;
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
	 * Representa una tarea asincrona de creacion de Tareas.
	 */
	public class AddTask extends AsyncTask<Object, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Object... params) {
			try {

				DetailSubTask obj = (DetailSubTask) params[0];

				// Resultado
				resultado = null;

				if (isEditMode) {
					resultado = ControlConnection.putInfo(TypeInfoServer.addSubTask, obj.getDataBean(), Modo.EDITAR,
							AddSubTaskActivity.this);
				} else {
					resultado = ControlConnection.putInfo(TypeInfoServer.addSubTask, obj.getDataBean(), Modo.AGREGAR,
							AddSubTaskActivity.this);
				}
				
				if (resultado.contains("OK")) {
					obj.id = Utils.getIDFromBackend(resultado);
					obj.accept(new DataVisitorsManager());
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
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddSubTaskActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddSubTaskActivity.this, MODULE);

				}

			} else {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.NO_EDITED, AddSubTaskActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddSubTaskActivity.this, MODULE);
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
