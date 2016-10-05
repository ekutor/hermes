package com.co.iatech.crm.sugarmovil.activities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.DataVisitorsManager;
import com.co.iatech.crm.sugarmovil.activities.ui.DatePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activities.ui.TimePickerFragment;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorActivities;
import com.co.iatech.crm.sugarmovil.activities.validators.ValidatorGeneric;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator.ActionActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleEditableActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Account;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCampaignsConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddCallActivity extends CallsModuleEditableActions {

	/**
	 * Debug.
	 */
	private static final String TAG = "AddCallActivity";

	/**
	 * Member Variables.
	 */
	private Call selectedCall;

	private ListUsersConverter lc = new ListUsersConverter();
	private ListAccountConverter lac = new ListAccountConverter();
	private TypeActions tipoPermiso;

	/**
	 * UI References.
	 */
	private Toolbar mLlamadaToolbar;
	private Button botonFechaInicio, botonHoraInicio;
	private ImageButton imgButtonGuardar;
	private TextView asignadoA, valorFechaInicio, txtParentName;
	private EditText valorAsunto, valorDescripcion, valorDuracionHrs;
	private Spinner spinnerCampaing, spinnerResult, spinnerDirection, spinnerState;
	private Spinner spinnerMinutes, spinnerType;

	private boolean selectedTypeTaskAccount;

	public String resultado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_call);
		try {
			createWidgets();
			getInfoFromMediator();

			// Main Toolbar
			mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
			setSupportActionBar(mLlamadaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			chargeLists();
			defineValidations();
			asignadoA.setOnClickListener(this);

			if (isEditMode) {
				TextView title = (TextView) findViewById(R.id.text_call_toolbar);
				title.setText("EDITAR LLAMADA");
				chargeViewInfo();
			} else {
				if (actualInfo.getActualParentInfo() != null
						&& ActionActivity.MAKE_CALL.equals(actualInfo.getActualParentInfo().getAction())) {
					try {
						spinnerDirection.setSelection(2);
						spinnerState.setSelection(2);
						valorFechaInicio.setText(Utils.convertTimetoStringFrontEnd(null));
						Intent my_callIntent = new Intent(Intent.ACTION_CALL);
						my_callIntent.setData(Uri.parse("tel:" + actualInfo.getActualParentInfo().getAdditionalInfo()));
						startActivity(my_callIntent);
					} catch (Exception e) {
						Message.showShortExt("Fallo al realizar la llamada " + e.getMessage(), this);
					}
				}

			}
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), this);
		}
	}

	public void getInfoFromMediator() {
		super.getInfoFromMediator();
		try {
			tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
			if (isEditMode) {
				Intent intent = getIntent();
				selectedCall = intent.getParcelableExtra(MODULE.getModuleName());
			} else {
				selectedCall = new Call();
				int pos = 0;

				pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
						actualInfo.getActualParentModule().getSugarDBName());

				boolean enabled = false;

				/*
				 * Message.showShortExt("parent "
				 * +actualInfo.getActualParentModule().name()+ "id "+
				 * actualInfo.getActualParentInfo().id+ " principal : "
				 * +actualInfo.getActualPrincipalModule()
				 * +actualInfo.getActualPrincipalInfo().id+actualInfo.
				 * getActualParentInfo().name, AddCallActivity.this);
				 */

				switch (actualInfo.getActualParentModule()) {
				case ACCOUNTS:
					txtParentName.setText(lac.convert(actualInfo.getActualParentInfo().id, DataToGet.VALUE));

					ActivityBeanCommunicator info = ActivitiesMediator.getInstance().getActualID(Modules.CONTACTS);
					if (info != null && !ActionActivity.NONE.equals(info.getAction())) {
						actualInfo.getActualParentInfo().setAction(info.getAction());
						actualInfo.getActualParentInfo().setAdditionalInfo(info.getAdditionalInfo());
						txtParentName.setText(info.name);
						pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
								Modules.CONTACTS.getSugarDBName());
						selectedCall.setParent_id(info.id);
					}
					break;
				case OPPORTUNITIES:
					OportunidadDetalle bean = (OportunidadDetalle) ActivitiesMediator.getInstance().getParentBean();
					if (bean != null) {
						txtParentName.setText(bean.getName());
					}
					break;
				case CONTACTS:
					selectedCall.setContact_id(actualInfo.getActualParentInfo().id);
					if (Modules.ACCOUNTS.equals(actualInfo.getActualPrincipalModule())) {
						ActivityBeanCommunicator account = ActivitiesMediator.getInstance()
								.getActualID(Modules.ACCOUNTS);
						txtParentName.setText(lac.convert(account.id, DataToGet.VALUE));
						pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
								Modules.ACCOUNTS.getSugarDBName());

					} else {
						String assocAccount = null;
						for (Contacto c : DataManager.getInstance().contactsInfo) {
							if (actualInfo.getActualParentInfo().id.equals(c.getId())) {
								assocAccount = c.getAccount_id();
								break;
							}
						}
						if (assocAccount != null) {
							txtParentName.setText(lac.convert(assocAccount, DataToGet.VALUE));
							pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE,
									Modules.ACCOUNTS.getSugarDBName());
						} else {
							txtParentName.setText(actualInfo.getActualParentInfo().name);
						}
					}
					break;
				default:
					pos = 0;
					enabled = true;
					break;
				}

				spinnerType.setSelection(pos);
				spinnerType.setEnabled(enabled);
			}
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), this);
		}

	}

	@Override
	public void onFinishSearchDialog(GenericBean selectedBean, int elementId) {
		if (selectedBean instanceof User) {
			User su = (User) selectedBean;
			asignadoA.setText(su.getUser_name());
		} else if (selectedBean instanceof Account) {
			Account ac = (Account) selectedBean;
			txtParentName.setText(ac.getName());
		}
	}

	public void chargeLists() {
		asignadoA = (TextView) findViewById(R.id.txt_valor_asignado_a);

		// Direccion Llamada
		spinnerDirection = (Spinner) findViewById(R.id.valor_direccion);
		ArrayAdapter<String> dirAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.CALLS_DIRECTION));
		dirAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDirection.setAdapter(dirAdapter);

		// Estado
		spinnerState = (Spinner) findViewById(R.id.valor_estado);
		ArrayAdapter<String> estadoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.CALLS_STATUS));
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerState.setAdapter(estadoAdapter);
		// Resultado
		spinnerResult = (Spinner) findViewById(R.id.valor_resultado);
		ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.CALLS_RESULT));
		resAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerResult.setAdapter(resAdapter);

		// Duracion
		spinnerMinutes = (Spinner) findViewById(R.id.valor_duracion);
		ArrayAdapter<String> durAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.CALLS_MINS_DURATION));
		durAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMinutes.setAdapter(durAdapter);

		// Carga Campañas
		spinnerCampaing = (Spinner) findViewById(R.id.valor_campana);
		ListCampaignsConverter lcc = new ListCampaignsConverter();
		ArrayAdapter<String> campAdapter = new ArrayAdapter<String>(AddCallActivity.this,
				android.R.layout.simple_spinner_item, lcc.getListInfo());
		spinnerCampaing.setAdapter(campAdapter);
		spinnerCampaing.setSelection(0);

		GlobalClass global = (GlobalClass) getApplicationContext();
		User u = global.getUsuarioAutenticado();
		selectedCall.setCreated_by(u.getId());
		asignadoA.setText(u.getFirst_name() + " " + u.getLast_name());
		selectedCall.setAssigned_user_id(u.getId());

		if (isEditMode) {
			// Parent
			if (selectedCall.getParent_id() != null && selectedCall.getParent_id().length() > 1) {
				txtParentName.setText(selectedCall.getParent_name());
			}
			// Carga Tipos
			if (selectedCall.getParent_type() != null) {
				int pos = ListsConversor.getPosItemOnList(ConversorsType.TASKS_TYPE, selectedCall.getParent_type());
				spinnerType.setSelection(pos);
			}

		}
	}

	public void createWidgets() {
		valorAsunto = (EditText) findViewById(R.id.valor_asunto);
		valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
		valorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);

		valorDuracionHrs = (EditText) findViewById(R.id.valor_duracion_horas);

		// Fecha Inicio
		botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
		botonFechaInicio.setOnClickListener(this);

		botonHoraInicio = (Button) findViewById(R.id.boton_hora_inicio);
		botonHoraInicio.setOnClickListener(this);

		imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
		imgButtonGuardar.setOnClickListener(this);

		// Cuentas
		txtParentName = (TextView) findViewById(R.id.valor_nombre);
		txtParentName.setOnClickListener(this);

		spinnerType = (Spinner) findViewById(R.id.valor_tipo);
		ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.TASKS_TYPE));
		tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerType.setAdapter(tipoAdapter);
		spinnerType.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				int visibility;
				visibility = View.INVISIBLE;
				selectedTypeTaskAccount = false;
				Modules selectedModuleType = null;
				for (Modules mod : nameVisibleOnPermitedModules) {
					if (spinnerType.getSelectedItem().toString().toLowerCase()
							.contains(mod.getVisualName().toLowerCase())) {
						visibility = View.VISIBLE;
						selectedTypeTaskAccount = true;
						selectedModuleType = mod;
						break;
					}
				}

				txtParentName.setVisibility(visibility);
				if (actualInfo.getActualParentInfo() == null && selectedModuleType != null) {
					txtParentName.setText(ValidatorActivities.SELECT_MESSAGE + " "
							+ selectedModuleType.getVisualName().toLowerCase());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	@Override
	public void chargeViewInfo() {
		valorAsunto.setText(selectedCall.getName());
		valorDescripcion.setText(selectedCall.getDescription());
		valorFechaInicio.setText(selectedCall.getDate_start());

		int pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_DIRECTION, selectedCall.getDirection());
		spinnerDirection.setSelection(pos);

		pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_STATUS, selectedCall.getStatus());
		spinnerState.setSelection(pos);

		pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_RESULT, selectedCall.getResultadodelallamada_c());
		spinnerResult.setSelection(pos);

		pos = ListsConversor.getPosItemOnList(ConversorsType.CALLS_MINS_DURATION, selectedCall.getDuration_minutes());
		spinnerMinutes.setSelection(pos);

		valorDuracionHrs.setText(selectedCall.getDuration_hours());

		// Campaña
		ListCampaignsConverter lcc = new ListCampaignsConverter();
		pos = Integer.parseInt(lcc.convert(selectedCall.getCampaign_id(), DataToGet.POS));
		spinnerCampaing.setSelection(pos);

		// Asignado
		asignadoA.setText(lc.convert(selectedCall.getAssigned_user_id(), DataToGet.VALUE));

		imgButtonGuardar.setVisibility(View.VISIBLE);

	}

	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();

		data.put(valorAsunto, "El campo Asunto no puede estar vacio");
		data.put(valorFechaInicio, "Debe definir una fecha de llamada");
	//	data.put(spinnerResult, "Debe seleccionar un Resultado de Llamada");
		data.put(spinnerDirection, "Debe seleccionar un Estado");
		data.put(spinnerState, "Debe seleccionar un Estado ");

		ValidatorGeneric.getInstance().define(data);

	}

	@Override
	public void onClick(View v) {
		try{
			if (v.getId() == asignadoA.getId()) {
				if(isEditMode){
					switch (tipoPermiso) {
						case OWNER:
							break;
						case ALL:
							Message.showUsersDialog(getSupportFragmentManager(),v.getId());
							break;
						case GROUP:
							break;
						}
				}else{
					Message.showUsersDialog(getSupportFragmentManager(),v.getId());
				}
			} else if (v.getId() == botonHoraInicio.getId()) {
				DialogFragment newFragment = new TimePickerFragment(this, valorFechaInicio, isEditMode);
				newFragment.show(getFragmentManager(), "hourCierrePicker");
			} else if (v.getId() == txtParentName.getId()) {
				switch (actualInfo.getActualParentModule()) {
				case ACCOUNTS:
					Message.showGenericDialog(getSupportFragmentManager(), lac, actualInfo.getActualParentModule());
					break;
				default:
					break;
				}
			} else if (v.getId() == botonFechaInicio.getId()) {
				DialogFragment newFragment = new DatePickerFragment(this, valorFechaInicio, isEditMode);
				newFragment.show(getFragmentManager(), "dateCierrePicker");
			} else if (v.getId() == imgButtonGuardar.getId()) {
				// Realizar Validaciones
				if (!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())) {
					return;
				}
				if (selectedTypeTaskAccount && !ValidatorGeneric.getInstance().execute(txtParentName,
						"Seleccionó un tipo, debe seleccionar la informacion relacionada", getApplicationContext())) {
					return;
				}
	
				if (valorFechaInicio.getText() != null && valorFechaInicio.getText().toString().length() > 1) {
					selectedCall.setDate_start(Utils.transformTimeUItoBackend(valorFechaInicio.getText().toString()));
				}
	
				imgButtonGuardar.setVisibility(View.INVISIBLE);
				selectedCall.setDescription(valorDescripcion.getText().toString());
				selectedCall.setName(valorAsunto.getText().toString());
				selectedCall.setDuration_hours(valorDuracionHrs.getText().toString());
				selectedCall.setDirection(ListsConversor.convert(ConversorsType.CALLS_DIRECTION,
						spinnerDirection.getSelectedItem().toString(), DataToGet.CODE));
				selectedCall.setStatus(ListsConversor.convert(ConversorsType.CALLS_STATUS,
						spinnerState.getSelectedItem().toString(), DataToGet.CODE));
				selectedCall.setDuration_minutes(ListsConversor.convert(ConversorsType.CALLS_MINS_DURATION,
						spinnerMinutes.getSelectedItem().toString(), DataToGet.CODE));
				selectedCall.setResultadodelallamada_c(ListsConversor.convert(ConversorsType.CALLS_RESULT,
						spinnerResult.getSelectedItem().toString(), DataToGet.CODE));
	
				// tipo Tarea
	
				if (!isEditMode) {
					
					String selectedType = spinnerType.getSelectedItem().toString();
					selectedCall.setParent_type(
							ListsConversor.convert(ConversorsType.TASKS_TYPE, selectedType, DataToGet.CODE));
	
					if (selectedCall.getParent_type().equals(actualInfo.getActualParentModule().getSugarDBName())) {
						selectedCall.setParent_id(actualInfo.getActualParentInfo().id);
					}else if(selectedCall.getParent_type().equals(Modules.ACCOUNTS.getSugarDBName())){
						selectedCall.setParent_id(lac.convert(txtParentName.getText().toString(), DataToGet.CODE));
					}
					
				}
	
				ListCampaignsConverter lcc = new ListCampaignsConverter();
				selectedCall.setCampaign_id(lcc.convert(spinnerCampaing.getSelectedItem().toString(), DataToGet.CODE));
	
				String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(), DataToGet.CODE);
				selectedCall.setAssigned_user_id(idUsuarioAsignado);
	
				AddCallTask tareaEditarLlamada = new AddCallTask();
	
				tareaEditarLlamada.execute(selectedCall);
			}
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), this);
		}
	}

	/**
	 * Representa una tarea asincrona de creacion de llamada.
	 */
	public class AddCallTask extends AsyncTask<Object, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Object... params) {
			try {

				Call obj = (Call) params[0];

				// Resultado
				resultado = null;

				if (isEditMode) {
					resultado = ControlConnection.putInfo(TypeInfoServer.addCall, obj.getDataBean(), Modo.EDITAR,
							AddCallActivity.this);
				} else {
					resultado = ControlConnection.putInfo(TypeInfoServer.addCall, obj.getDataBean(), Modo.AGREGAR,
							AddCallActivity.this);
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
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddCallActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddCallActivity.this, MODULE);

				}

			} else {
				if (isEditMode) {
					// Message.showFinalMessage(getFragmentManager(),DialogType.NO_EDITED,
					// AddCallActivity.this, MODULE );
					Message.showFinalMessage(getFragmentManager(), resultado, AddCallActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddCallActivity.this, MODULE);
					// Message.showFinalMessage(getFragmentManager(),DialogType.NO_CREATED,
					// AddCallActivity.this, MODULE );

				}
				Log.d(TAG, "Crear Llamda error");
			}

		}

		@Override
		protected void onCancelled() {

			Log.d(TAG, "Cancelado ");
		}
	}

	@Override
	public void addInfo(String serverResp) {
		// TODO Auto-generated method stub

	}

}
