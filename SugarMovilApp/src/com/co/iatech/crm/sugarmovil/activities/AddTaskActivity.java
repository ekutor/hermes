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

public class AddTaskActivity extends TasksModuleEditableActions {

	/**
	 * Debug.
	 */
	private static final String TAG = "AddTaskActivity";

	/**
	 * Member Variables.
	 */

	private DetailTask selectedTask;

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
	private TextView valorTrabajoEstimado, valorAsunto, valorDescripcion, valorNombre;
	private Spinner valorEstado, valorTipo, valorPrioridad, valorContacto;
	private Button botonFechaInicio, botonFechaVen, botonHoraInicio, botonHoraVen;
	private TextView valorFechaInicio, asignadoA, valorFechaVen;

	public String resultado;

	private boolean selectedTypeTaskAccount;

	private AddTask editTask;

	private ActivityBeanCommunicator accountId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
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
			// carga los contactos de la cuenta actual - aplica solo cuando
			// venga del modulo de cuentas
			if (!listContacts.hasItems() && accountId != null) {
				String[] params = { "idAccount", accountId.id };
				this.executeTask(params, TypeInfoServer.getContactsxAccount);

			} else {
				this.chargeLists();
			}
			asignadoA.setOnClickListener(this);

			if (isEditMode) {
				TextView title = (TextView) findViewById(R.id.text_task_toolbar);
				title.setText("EDITAR TAREA");
				chargeViewInfo();
			}

		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddTaskActivity.this, MODULE);
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
			selectedTask = new DetailTask();
			int pos = 0;
			
			pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
					actualInfo.getActualParentModule().getSugarDBName());
			
			boolean enabled = false;
			
			 /* Message.showFinalMessage(getFragmentManager(),"parent "
			  +actualInfo.getActualParentModule().name()+
			  "id "+ actualInfo.getActualParentId() + " principal : "+actualInfo.getActualPrincipalModule()
			  +actualInfo.getActualPrincipalId(), AddTaskActivity.this, MODULE);*/
			 
			switch (actualInfo.getActualParentModule()) {
			case ACCOUNTS:
				valorNombre.setText(lac.convert(actualInfo.getActualParentInfo().id, DataToGet.VALUE));
				accountId = actualInfo.getActualParentInfo();
				break;
			case OPPORTUNITIES:
				OportunidadDetalle bean = (OportunidadDetalle) ActivitiesMediator.getInstance().getParentBean();
				if (bean != null) {
					valorNombre.setText(bean.getName());
				}
				accountId = intent.getParcelableExtra(Modules.ACCOUNTS.name());
				break;
			case CONTACTS:
				if(Modules.ACCOUNTS.equals(actualInfo.getActualPrincipalModule())){
					accountId = ActivitiesMediator.getInstance().getActualID(Modules.ACCOUNTS);
					valorNombre.setText(lac.convert(accountId.id, DataToGet.VALUE));
					
					pos =  ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
							Modules.ACCOUNTS.getSugarDBName());
				}else{
					
					valorNombre.setText(
							allContacts.convert(actualInfo.getActualParentInfo().id, DataToGet.VALUE));
					
				}
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

		ArrayAdapter<String> prioridadAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_PRIORITY));
		prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorPrioridad.setAdapter(prioridadAdapter);

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

			if (selectedTask.getPriority() != null) {
				valorPrioridad.setSelection(ListsConversor.getPosItemOnList(ConversorsType.TASKS_PRIORITY, selectedTask.getPriority()));
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

		valorPrioridad.setSelection(1);

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

		valorTrabajoEstimado = (EditText) findViewById(R.id.valor_estimado);

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
		valorContacto = (Spinner) findViewById(R.id.valor_contacto);
		valorEstado = (Spinner) findViewById(R.id.valor_estado);

		valorPrioridad = (Spinner) findViewById(R.id.valor_prioridad);

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
		int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_STATUS, selectedTask.getStatus());
		valorEstado.setSelection(pos);
		valorFechaInicio.setText(Utils.transformTimeBakendToUI(selectedTask.getDate_start()));
		valorFechaVen.setText(Utils.transformTimeBakendToUI(selectedTask.getDate_due()));

		// contacto

		valorTrabajoEstimado.setText(selectedTask.getTrabajo_estimado_c());
		pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_PRIORITY, selectedTask.getPriority());
		valorPrioridad.setSelection(pos);
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

			selectedTask.setName(valorAsunto.getText().toString());
			selectedTask.setStatus(ListsConversor.convert(ConversorsType.TASKS_STATUS,
					valorEstado.getSelectedItem().toString(), DataToGet.CODE));
		
			if (valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1) {
				selectedTask.setDate_start(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
			}

			if (valorFechaVen.getText() != null && valorFechaVen.getText().toString().length() > 1) {
				selectedTask.setDate_due(Utils.transformTimeUItoBackend(valorFechaVen.getText().toString()));
			}

			// contacto

			if (valorContacto.getSelectedItemPosition() > 0) {
				selectedTask.setContact_name(valorContacto.getSelectedItem().toString());
				selectedTask.setContact_id(
						listContacts.convert(valorContacto.getSelectedItem().toString(), DataToGet.CODE));
			}

			selectedTask.setTrabajo_estimado_c(valorTrabajoEstimado.getText().toString());
			selectedTask.setPriority(ListsConversor.convert(ConversorsType.TASKS_PRIORITY,
					valorPrioridad.getSelectedItem().toString(), DataToGet.CODE));
			selectedTask.setDescription(valorDescripcion.getText().toString());

			// tipo Tarea
			
			if(!isEditMode){
				String selectedType = valorTipo.getSelectedItem().toString();
				selectedTask
						.setParent_type(ListsConversor.convert(ConversorsType.TASKS_TYPE, selectedType, DataToGet.CODE));
	
				if (selectedTask.getParent_type().equals(actualInfo.getActualParentModule().getSugarDBName())) {
					selectedTask.setParent_id(actualInfo.getActualParentInfo().id);
				}else if(accountId != null){//aplica para contacts
					selectedTask.setParent_id(accountId.id);
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
		data.put(valorEstado, "Debe seleccionar un Estado de la Tarea");
		data.put(valorPrioridad, "Debe seleccionar una Prioridad de Tarea");

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
	 * Representa una tarea asincrona de creacion de Tareas.
	 */
	public class AddTask extends AsyncTask<Object, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Object... params) {
			try {

				DetailTask obj = (DetailTask) params[0];

				// Resultado
				resultado = null;

				if (isEditMode) {
					resultado = ControlConnection.putInfo(TypeInfoServer.addTask, obj.getDataBean(), Modo.EDITAR,
							AddTaskActivity.this);
				} else {
					resultado = ControlConnection.putInfo(TypeInfoServer.addTask, obj.getDataBean(), Modo.AGREGAR,
							AddTaskActivity.this);
				}
				Log.d(TAG, "Crear Tarea Resp: " + resultado);

				if (resultado.contains("OK")) {
					obj.id = Utils.getIDFromBackend(resultado);
					obj.accept(new DataVisitorsManager());
					ActivitiesMediator.getInstance().addObjectInfo(obj);
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				Log.d(TAG, "Crear Llamada Error: " + e.getClass().getName() + ":" + e.getMessage());
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
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddTaskActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddTaskActivity.this, MODULE);

				}

			} else {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.NO_EDITED, AddTaskActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddTaskActivity.this, MODULE);
					// Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED,
					// AddTaskActivity.this, MODULE );

				}
				Log.d(TAG, "Crear Tarea error");
			}

		}

		@Override
		protected void onCancelled() {

			Log.d(TAG, "Cancelado ");
		}
	}

}
