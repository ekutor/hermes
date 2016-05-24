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
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModuleEditableActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;
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

	private TareaDetalle tareaSeleccionada;
	private static boolean modoEdicion;

	private ListUsersConverter lc = new ListUsersConverter();
	private ListAccountConverter lac = new ListAccountConverter();
	private ListContactConverter listContacts = new ListContactConverter(ListsHolderType.CONTACTS_ACCOUNTS);
	private TypeActions tipoPermiso;

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;
	private ImageButton imgButtonGuardar;
	private TextView valorTrabajoEstimado, valorAsunto, valorDescripcion, valorNombre, txtNombre;
	private Spinner valorEstado, valorTipo, valorPrioridad, valorContacto;
	private Button botonFechaInicio, botonFechaVen, botonHoraInicio, botonHoraVen;
	private TextView valorFechaInicio, asignadoA, valorFechaVen;

	public String resultado;

	private boolean selectedTypeTaskAccount;
	private Modules[] nameVisibleOnPermitedModules = {Modules.ACCOUNTS, Modules.OPPORTUNITIES};

	private AddTask editarTarea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		try {
			modoEdicion = false;

			// SoftKey
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

			getInfoFromMediator();

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_task);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);
			imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

			createWidgets();
			defineValidations();
			//carga los contactos de la cuenta actual - aplica solo cuando venga del modulo de cuentas
			if (!listContacts.hasItems() && actualInfo.getActualModuleId() != null) {
				String[] params = { "idAccount", actualInfo.getActualModuleId() };
				this.executeTask(params, TypeInfoServer.getContactsxAccount);

			} else {
				this.chargeLists();
			}
			asignadoA.setOnClickListener(this);

			if (modoEdicion) {
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
		tareaSeleccionada = intent.getParcelableExtra(MODULE.getModuleName());
		//idCuentas solo cuando el actual module sea cuentas
		actualInfo.setActualModuleId(intent.getStringExtra(Modules.ACCOUNTS.name()));
		if (tareaSeleccionada != null) {
			modoEdicion = true;			
		} else {
			
			tareaSeleccionada = new TareaDetalle();
		}

	}

	@Override
	public void chargeLists() {

		ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				listContacts.getListInfo());
		contactAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorContacto.setAdapter(contactAdapter);
		
		if(!"".equals(tareaSeleccionada.getContact_id()) ){
			String contact = tareaSeleccionada.getContact_name();
			valorContacto.setSelection(listContacts.getListInfo().indexOf(contact));
		}else if(actualInfo.getActualParentId() != null){
			int pos = Integer.parseInt(listContacts.convert(actualInfo.getActualParentId(), DataToGet.POS));
			valorContacto.setSelection(pos);
		}

		// Estado

		ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_STATUS));
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorEstado.setAdapter(estadoAdapter);

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
				for(Modules mod : nameVisibleOnPermitedModules){
					if (valorTipo.getSelectedItem().toString().toLowerCase().contains(mod.getVisualName().toLowerCase())) {
						visibility = View.VISIBLE;
						selectedTypeTaskAccount = true;
						selectedModuleType = mod;
						break;
					}
				}
				
				valorNombre.setVisibility(visibility);
				if ( actualInfo.getActualParentId() == null && selectedModuleType!= null) {
					valorNombre.setText(ValidatorActivities.SELECT_MESSAGE + selectedModuleType.getVisualName().toLowerCase());
				}
				//txtNombre.setVisibility(visibility);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		// Cuenta
		
		if (tareaSeleccionada.getParent_id() != null && tareaSeleccionada.getParent_id().length() > 1) {
			valorNombre.setText(tareaSeleccionada.getParent_name());
		}
		if (actualInfo.getActualParentId()!= null ) {
			valorNombre.setText(lac.convert(actualInfo.getActualParentId(), DataToGet.VALUE));
		}
		
		// Carga Tipos
		if (tareaSeleccionada.getParent_type() != null) {
			int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE, tareaSeleccionada.getParent_type());
			valorTipo.setSelection(pos);
		}

		ArrayAdapter<String> prioridadAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_PRIORITY));
		prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorPrioridad.setAdapter(prioridadAdapter);
		valorPrioridad.setSelection(1);

		GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		tareaSeleccionada.setCreated_by(u.getId());
		if (!modoEdicion) {

			asignadoA.setText(u.getFirst_name() + " " + u.getLast_name());
			tareaSeleccionada.setAssigned_user_id(u.getId());
		}

	}

	@Override
	public void createWidgets() {
		valorAsunto = (EditText) findViewById(R.id.valor_asunto);
		valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
		valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
		valorFechaVen = (TextView) findViewById(R.id.valor_fecha_vence);
		txtNombre = (TextView) findViewById(R.id.text_nombre);
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

		imgButtonGuardar.setOnClickListener(this);
		asignadoA = (TextView) findViewById(R.id.valor_asignado_a);

		// Contacto
		valorContacto = (Spinner) findViewById(R.id.valor_contacto);
		valorEstado = (Spinner) findViewById(R.id.valor_estado);
		valorTipo = (Spinner) findViewById(R.id.valor_tipo);
		valorPrioridad = (Spinner) findViewById(R.id.valor_prioridad);

	}

	@Override
	public void chargeViewInfo() {

		valorAsunto.setText(tareaSeleccionada.getName());
		int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_STATUS, tareaSeleccionada.getStatus());
		valorEstado.setSelection(pos);
		valorFechaInicio.setText(Utils.transformTimeBakendToUI(tareaSeleccionada.getDate_start()));
		valorFechaVen.setText(Utils.transformTimeBakendToUI(tareaSeleccionada.getDate_due()));

		// contacto

		valorTrabajoEstimado.setText(tareaSeleccionada.getTrabajo_estimado_c());
		pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_PRIORITY, tareaSeleccionada.getPriority());
		valorPrioridad.setSelection(pos);
		valorDescripcion.setText(tareaSeleccionada.getDescription());
	
		// Asignado
		asignadoA.setText(lc.convert(tareaSeleccionada.getAssigned_user_id(), DataToGet.VALUE));
		imgButtonGuardar.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == asignadoA.getId()) {
			switch (tipoPermiso) {
			case OWNER:
				break;
			case ALL:
				Message.showUsersDialog(getSupportFragmentManager());
				break;
			case GROUP:
				break;
			}

		} else if (v.getId() == botonHoraInicio.getId()) {
			DialogFragment newFragment = new TimePickerFragment(this, valorFechaInicio, modoEdicion);
			newFragment.show(getFragmentManager(), "hourCierrePicker");
		} else if (v.getId() == botonFechaInicio.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaInicio, modoEdicion);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == botonHoraVen.getId()) {
			DialogFragment newFragment = new TimePickerFragment(this, valorFechaVen, modoEdicion);
			newFragment.show(getFragmentManager(), "hourCierrePicker");
		} else if (v.getId() == botonFechaVen.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaVen, modoEdicion);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == valorNombre.getId()) {
			Message.showAccountsDialog(getSupportFragmentManager());
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

			tareaSeleccionada.setName(valorAsunto.getText().toString());
			tareaSeleccionada.setStatus(ListsConversor.convert(ConversorsType.TASKS_STATUS,
					valorEstado.getSelectedItem().toString(), DataToGet.CODE));

			if (valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1) {
				tareaSeleccionada.setDate_start(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
			}

			if (valorFechaVen.getText() != null && valorFechaVen.getText().toString().length() > 1) {
				tareaSeleccionada.setDate_due(Utils.transformTimeUItoBackend(valorFechaVen.getText().toString()));
			}

			// contacto

			tareaSeleccionada.setTrabajo_estimado_c(valorTrabajoEstimado.getText().toString());
			tareaSeleccionada.setPriority(ListsConversor.convert(ConversorsType.TASKS_PRIORITY,
					valorPrioridad.getSelectedItem().toString(), DataToGet.CODE));
			tareaSeleccionada.setDescription(valorDescripcion.getText().toString());

			tareaSeleccionada.setParent_type(ListsConversor.convert(ConversorsType.TASKS_TYPE,
					valorTipo.getSelectedItem().toString(), DataToGet.CODE));
			if (Modules.ACCOUNTS.getSugarDBName().equals(tareaSeleccionada.getParent_type())) {
				tareaSeleccionada.setParent_id(lac.convert(valorNombre.getText().toString(), DataToGet.CODE));
			}else if (Modules.OPPORTUNITIES.getSugarDBName().equals(tareaSeleccionada.getParent_type())){
				if(valorNombre.getText() != null && !valorNombre.getText().equals(tareaSeleccionada.getParent_name())){
					tareaSeleccionada.setParent_id(listContacts.convert(valorNombre.getText().toString(), DataToGet.CODE));
				}
				
			}

			String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(), DataToGet.CODE);
			tareaSeleccionada.setAssigned_user_id(idUsuarioAsignado);

			editarTarea = new AddTask();

			editarTarea.execute(tareaSeleccionada);
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
		if (modoEdicion) {
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

				TareaDetalle obj = (TareaDetalle) params[0];

				// Resultado
				resultado = null;

				if (modoEdicion) {
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
				if (modoEdicion) {
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddTaskActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddTaskActivity.this, MODULE);

				}

			} else {
				if (modoEdicion) {
					Message.showFinalMessage(getFragmentManager(), DialogType.NO_EDITED, AddTaskActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddTaskActivity.this, MODULE);
					// Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED,
					// AddTaskActivity.this, MODULE );

				}
				Log.d(TAG, "Crear Tarea error");
			}
			modoEdicion = false;
		}

		@Override
		protected void onCancelled() {

			Log.d(TAG, "Cancelado ");
		}
	}

}
