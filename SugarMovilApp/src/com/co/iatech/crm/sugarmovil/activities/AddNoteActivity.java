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
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.NotesModuleEditableActions;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Notes;
import com.co.iatech.crm.sugarmovil.model.User;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddNoteActivity extends NotesModuleEditableActions {

	/**
	 * Member Variables.
	 */

	private Notes selectedNote;

	private ListUsersConverter lc = new ListUsersConverter();

	private TypeActions tipoPermiso;

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;
	private ImageButton imgButtonGuardar;
	private TextView valorDescripcion, valorSubtarea, valorNombre;
	private Spinner valorEstado;
	private Button botonFechaInicio, botonFechaVen;
	private TextView valorFechaInicio, asignadoA, valorFechaVen;

	public String resultado;

	private AddNoteTask editTask;

	private ActivityBeanCommunicator parentId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		try {

			// SoftKey
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			createWidgets();
			getInfoFromMediator();

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_note);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			defineValidations();
			this.chargeLists();
			asignadoA.setOnClickListener(this);

			if (isEditMode) {
				TextView title = (TextView) findViewById(R.id.text_note_toolbar);
				title.setText("EDITAR NOTA");
				chargeViewInfo();
			}

		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddNoteActivity.this, MODULE);
		}
	}

	@Override
	public void getInfoFromMediator() {
		super.getInfoFromMediator();

		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
		Intent intent = getIntent();

		if (isEditMode) {
			selectedNote = intent.getParcelableExtra(MODULE.getModuleName());
		} else {
			selectedNote = new Notes();
			switch (actualInfo.getActualParentModule()) {
			case SUBTASKS:
				parentId = actualInfo.getActualParentInfo();
				valorSubtarea.setText(parentId.name);
				break;
			default:
				break;
			}

		}

	}

	@Override
	public void chargeLists() {

		// Estado

		ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_STATUS));
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		valorEstado.setAdapter(estadoAdapter);

		if (isEditMode) {

			// Parent
			if (selectedNote.getParent_id() != null && selectedNote.getParent_id().length() > 1) {
				valorSubtarea.setText(selectedNote.getParent_name());
			}

		} else {

			GlobalClass global = (GlobalClass) getApplicationContext();
			User u = global.getUsuarioAutenticado();
			selectedNote.setCreated_by(u.getId());

			asignadoA.setText(u.getFirst_name() + " " + u.getLast_name());
			selectedNote.setAssigned_user_id(u.getId());
		}

	}

	@Override
	public void createWidgets() {
		valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
		valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
		valorFechaVen = (TextView) findViewById(R.id.valor_fecha_vence);
		valorNombre = (TextView) findViewById(R.id.valor_nombre);
		valorSubtarea = (TextView) findViewById(R.id.valor_subtarea);

		// Fecha Inicio
		botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
		botonFechaInicio.setOnClickListener(this);

		botonFechaVen = (Button) findViewById(R.id.boton_fecha_vence);
		botonFechaVen.setOnClickListener(this);

		imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
		imgButtonGuardar.setOnClickListener(this);

		asignadoA = (TextView) findViewById(R.id.valor_asignado_a);

		valorEstado = (Spinner) findViewById(R.id.valor_estado);

	}

	@Override
	public void chargeViewInfo() {

		int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_STATUS, selectedNote.getStatus_id());
		valorEstado.setSelection(pos);
		valorFechaInicio.setText(Utils.transformTimeBakendToUI(selectedNote.getActive_date()));
		valorFechaVen.setText(Utils.transformTimeBakendToUI(selectedNote.getExp_date()));
		valorDescripcion.setText(selectedNote.getDescription());
		valorNombre.setText(selectedNote.getName());

		// Asignado
		asignadoA.setText(lc.convert(selectedNote.getAssigned_user_id(), DataToGet.VALUE));
		imgButtonGuardar.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == asignadoA.getId()) {
			if (isEditMode) {
				switch (tipoPermiso) {
				case OWNER:
					break;
				case ALL:
					Message.showUsersDialog(getSupportFragmentManager(),v.getId());
					break;
				case GROUP:
					break;
				}
			} else {
				Message.showUsersDialog(getSupportFragmentManager(),v.getId());
			}

		} else if (v.getId() == botonFechaInicio.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaInicio, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == botonFechaVen.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, valorFechaVen, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == imgButtonGuardar.getId()) {
			// Realizar Validaciones
			if (!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())) {
				return;
			}
			imgButtonGuardar.setVisibility(View.INVISIBLE);

			selectedNote.setName(valorNombre.getText().toString());
			selectedNote.setStatus_id(ListsConversor.convert(ConversorsType.TASKS_STATUS,
					valorEstado.getSelectedItem().toString(), DataToGet.CODE));

			if (valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1) {
				selectedNote.setActive_date(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
			}

			if (valorFechaVen.getText() != null && valorFechaVen.getText().toString().length() > 1) {
				selectedNote.setExp_date(Utils.transformTimeUItoBackend(valorFechaVen.getText().toString()));
			}

			selectedNote.setDescription(valorDescripcion.getText().toString());

			// tipo Tarea

			if (!isEditMode) {

				if (Modules.SUBTASKS.equals(actualInfo.getActualParentModule())) {
					selectedNote.setParent_id(parentId.id);
				}

			}
			String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(), DataToGet.CODE);
			selectedNote.setAssigned_user_id(idUsuarioAsignado);

			editTask = new AddNoteTask();

			editTask.execute(selectedNote);
		}

	}

	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();

		data.put(valorNombre, "El campo Nombre no puede estar vacio");
		data.put(valorFechaInicio, "Debe seleccionar una Fecha de Publicacion");

		ValidatorGeneric.getInstance().define(data);

	}

	@Override
	public void onFinishSearchDialog(GenericBean selectedBean, int elementId) {
		if (selectedBean instanceof User) {
			User selectedUser = (User) selectedBean;
			asignadoA.setText(selectedUser.getUser_name());
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
	 * Representa una tarea asincrona de creacion de Notas.
	 */
	public class AddNoteTask extends AsyncTask<Object, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Object... params) {
			try {

				Notes obj = (Notes) params[0];

				// Resultado
				resultado = null;

				if (isEditMode) {
					resultado = ControlConnection.putInfo(TypeInfoServer.addNote, obj.getDataBean(), Modo.EDITAR,
							AddNoteActivity.this);
				} else {
					resultado = ControlConnection.putInfo(TypeInfoServer.addNote, obj.getDataBean(), Modo.AGREGAR,
							AddNoteActivity.this);
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
			if (success) {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddNoteActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddNoteActivity.this, MODULE);

				}

			} else {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.NO_EDITED, AddNoteActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddNoteActivity.this, MODULE);

				}

			}

		}

		@Override
		protected void onCancelled() {

		}
	}

}
