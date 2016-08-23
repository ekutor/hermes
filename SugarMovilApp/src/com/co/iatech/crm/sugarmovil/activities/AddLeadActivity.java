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
import com.co.iatech.crm.sugarmovil.activtities.modules.LeadsModuleEditableActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection.Modo;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.acl.TypeActions;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Lead;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListCampaignsConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListDefaultSourceConverter;
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

public class AddLeadActivity extends LeadsModuleEditableActions {

	/**
	 * Member Variables.
	 */

	private Lead selectedLead;

	private ListUsersConverter lc = new ListUsersConverter();
	private ListCampaignsConverter campaigns;

	private TypeActions tipoPermiso;

	/**
	 * UI References.
	 */
	private Toolbar mTareaToolbar;
	private ImageButton imgButtonGuardar;
	private EditText valorNombre;
	private EditText valorApellidos;
	private EditText valorTelOficina;
	private EditText celular;
	private EditText valorFax;
	private EditText cargo;
	private EditText departamento;
	private EditText direccion;
	private EditText ciudad;
	private EditText provincia;
	private EditText correo;
	private EditText sitio;
	private EditText requerimiento;
	private Spinner medio;
	private Spinner fuente;
	private Spinner marca;
	private Spinner estado;
	private EditText otro;
	private EditText estimado;
	private Spinner accion;
	private EditText retroalimenta;
	private TextView fecha;
	private TextView responsable;
	private EditText retroalimenta2;
	private TextView fecha2;
	private TextView responsable2;
	private EditText retroalimenta3;
	private TextView fecha3;
	private TextView responsable3;
	private EditText valorReal;
	private TextView campana;
	private TextView asignadoA;
	private EditText valorRazon;

	private Button botonFecha, botonFecha2, botonFecha3;

	public String resultado;

	private AddLeadTask editTask;

	private ActivityBeanCommunicator parentId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_lead);
		try {
			campaigns = new ListCampaignsConverter();
			// SoftKey
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			createWidgets();
			getInfoFromMediator();

			// Main Toolbar
			mTareaToolbar = (Toolbar) findViewById(R.id.toolbar_client);
			setSupportActionBar(mTareaToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);

			defineValidations();
			this.chargeLists();
			asignadoA.setOnClickListener(this);

			if (isEditMode) {
				TextView title = (TextView) findViewById(R.id.text_client_toolbar);
				title.setText("EDITAR CLIENTE P");
				chargeViewInfo();
			}

		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddLeadActivity.this, MODULE);
		}
	}

	@Override
	public void getInfoFromMediator() {
		super.getInfoFromMediator();

		tipoPermiso = AccessControl.getTypeEdit(MODULE, (GlobalClass) getApplicationContext());
		Intent intent = getIntent();

		if (isEditMode) {
			selectedLead = intent.getParcelableExtra(MODULE.getModuleName());
		} else {
			selectedLead = new Lead();
			switch (actualInfo.getActualParentModule()) {
			case OPPORTUNITIES:
				parentId = actualInfo.getActualParentInfo();

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
				ListsConversor.getValuesList(ConversorsType.LEADS_STATUS));
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		estado.setAdapter(estadoAdapter);
		
		ArrayAdapter<String> accionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.LEADS_ACTIONS));
		accionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		accion.setAdapter(accionAdapter);

		ArrayAdapter<String> marcaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.LEADS_BRAND));
		marcaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		marca.setAdapter(marcaAdapter);

		ArrayAdapter<String> medioAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_MEDIUM));
		medioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		medio.setAdapter(medioAdapter);
		medio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				fuente.setAdapter(null);
				ListDefaultSourceConverter source = ListDefaultSourceConverter.getInstance();
				ArrayAdapter<String> adapter = source.getListAdatper(AddLeadActivity.this,
						medio.getSelectedItem().toString());
				if (adapter != null) {
					fuente.setAdapter(adapter);
					fuente.setSelection(0, true);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		if (isEditMode) {

			// Parent
			/*
			 * if (selectedLead.getParent_id() != null &&
			 * selectedLead.getParent_id().length() > 1) {
			 * valorSubtarea.setText(selectedLead.getParent_name()); }
			 */

		} else {

			GlobalClass global = (GlobalClass) getApplicationContext();
			User u = global.getUsuarioAutenticado();
			selectedLead.setCreated_by(u.getId());

			asignadoA.setText(u.getFirst_name() + " " + u.getLast_name());
			selectedLead.setAssigned_user_id(u.getId());
		}

	}

	@Override
	public void createWidgets() {
		valorRazon = (EditText) findViewById(R.id.valor_razon);
		valorNombre = (EditText) findViewById(R.id.valor_nombre);
		valorApellidos = (EditText) findViewById(R.id.valor_apellidos);
		valorTelOficina = (EditText) findViewById(R.id.valor_oficina);
		celular = (EditText) findViewById(R.id.valor_celular);
		valorFax = (EditText) findViewById(R.id.valor_fax);
		cargo = (EditText) findViewById(R.id.valor_cargo);
		departamento = (EditText) findViewById(R.id.valor_departamento);
		direccion = (EditText) findViewById(R.id.valor_direccion);
		ciudad = (EditText) findViewById(R.id.valor_ciudad);
		provincia = (EditText) findViewById(R.id.valor_provincia);
		correo = (EditText) findViewById(R.id.valor_correo);
		sitio = (EditText) findViewById(R.id.valor_sitio);
		requerimiento = (EditText) findViewById(R.id.valor_requerimiento);
		fuente = (Spinner) findViewById(R.id.valor_fuente);
		medio = (Spinner) findViewById(R.id.valor_medio);
		marca = (Spinner) findViewById(R.id.valor_marca);
		estado = (Spinner) findViewById(R.id.valor_estado);
		otro = (EditText) findViewById(R.id.valor_otro);
		estimado = (EditText) findViewById(R.id.valor_estimado);
		accion = (Spinner) findViewById(R.id.valor_accion);
		retroalimenta = (EditText) findViewById(R.id.valor_retroalimenta);
		fecha = (TextView) findViewById(R.id.valor_fecha);
		responsable = (TextView) findViewById(R.id.valor_responsable);
		responsable.setOnClickListener(this);
		retroalimenta2 = (EditText) findViewById(R.id.valor_retroalimenta2);
		fecha2 = (TextView) findViewById(R.id.valor_fecha2);
		responsable2 = (TextView) findViewById(R.id.valor_responsable2);
		responsable2.setOnClickListener(this);
		retroalimenta3 = (EditText) findViewById(R.id.valor_retroalimenta3);
		fecha3 = (TextView) findViewById(R.id.valor_fecha3);
		responsable3 = (TextView) findViewById(R.id.valor_responsable3);
		responsable3.setOnClickListener(this);
		valorReal = (EditText) findViewById(R.id.valor_real);
		campana = (TextView) findViewById(R.id.valor_campana);

		// Fecha Inicio
		botonFecha = (Button) findViewById(R.id.boton_fecha);
		botonFecha.setOnClickListener(this);
		botonFecha2 = (Button) findViewById(R.id.boton_fecha2);
		botonFecha2.setOnClickListener(this);
		botonFecha3 = (Button) findViewById(R.id.boton_fecha3);
		botonFecha3.setOnClickListener(this);

		imgButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);
		imgButtonGuardar.setOnClickListener(this);

		asignadoA = (TextView) findViewById(R.id.valor_asignado_a);

	}

	@Override
	public void chargeViewInfo() {
		valorRazon.setText(selectedLead.getRazonsocial_c());
		valorNombre.setText(selectedLead.getFirst_name());
		valorApellidos.setText(selectedLead.getLast_name());
		valorTelOficina.setText(selectedLead.getPhone_work());
		celular.setText(selectedLead.getPhone_mobile());
		valorFax.setText(selectedLead.getPhone_fax());
		cargo.setText(selectedLead.getTitle());
		departamento.setText(selectedLead.getDepartment());
		direccion.setText(selectedLead.getPrimary_address_street());
		ciudad.setText(selectedLead.getPrimary_address_city());
		provincia.setText(selectedLead.getPrimary_address_state());
		correo.setText(selectedLead.getEmail_address());
		sitio.setText(selectedLead.getWebsite());
		requerimiento.setText(selectedLead.getDescription());

		otro.setText(selectedLead.getOtro_c());
		estimado.setText(selectedLead.getOpportunity_amount());

		retroalimenta.setText(selectedLead.getStatus_description());
		fecha.setText(Utils.transformTimeBakendToUI(selectedLead.getFecha_c()));
		responsable.setText(selectedLead.getResponsable_c());
		retroalimenta2.setText(selectedLead.getRetroalimentacion2_c());
		fecha2.setText(Utils.transformTimeBakendToUI(selectedLead.getFecha2_c()));
		responsable2.setText(selectedLead.getResponsable2_c());
		retroalimenta3.setText(selectedLead.getRetroalimentacion3_c());
		fecha3.setText(Utils.transformTimeBakendToUI(selectedLead.getFecha3_c()));

		responsable3.setText(lc.convert(selectedLead.getResponsable3_c(), DataToGet.VALUE));
		valorReal.setText(selectedLead.getValor_real_c());
		campana.setText(selectedLead.getCampaign_name());

		int pos = ListsConversor.getPosItemOnList(ConversorsType.LEADS_STATUS, selectedLead.getStatus());
		estado.setSelection(pos);
		
		pos = ListsConversor.getPosItemOnList(ConversorsType.LEADS_ACTIONS, selectedLead.getAccion_c());
		accion.setSelection(pos);

		pos = ListsConversor.getPosItemOnList(ConversorsType.OPPORTUNITY_MEDIUM, selectedLead.getMedio_c());
		medio.setSelection(pos);

		pos = ListsConversor.getPosItemOnList(ConversorsType.LEADS_BRAND, selectedLead.getMarca_c());
		marca.setSelection(pos);

		ListDefaultSourceConverter source = ListDefaultSourceConverter.getInstance();
		fuente.setSelection(source.getPosItemOnList(selectedLead.getMedio_c(), selectedLead.getFuente_c()));

		// Asignado
		asignadoA.setText(lc.convert(selectedLead.getAssigned_user_id(), DataToGet.VALUE));
		imgButtonGuardar.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
	  try{
		if (v.getId() == responsable.getId() ||
				v.getId() == responsable2.getId() ||
					v.getId() == responsable3.getId()) {
			Message.showUsersDialog(getSupportFragmentManager(), v.getId());
			
		}
		if (v.getId() == asignadoA.getId()) {
			if (isEditMode) {
				switch (tipoPermiso) {
				case OWNER:
					break;
				case ALL:
					Message.showUsersDialog(getSupportFragmentManager() ,v.getId());
					break;
				case GROUP:
					break;
				}
			} else {
				Message.showUsersDialog(getSupportFragmentManager(),v.getId());
			}

		} else if (v.getId() == botonFecha.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, fecha, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == botonFecha2.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, fecha2, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == botonFecha3.getId()) {
			DialogFragment newFragment = new DatePickerFragment(this, fecha3, isEditMode);
			newFragment.show(getFragmentManager(), "dateCierrePicker");
		} else if (v.getId() == imgButtonGuardar.getId()) {
			// Realizar Validaciones
			if (!ValidatorGeneric.getInstance().executeValidations(getApplicationContext())) {
				return;
			}
			imgButtonGuardar.setVisibility(View.INVISIBLE);

			selectedLead.setName(valorNombre.getText().toString());
			
			if (fecha.getText() != null && fecha.getText().toString().length() > 1) {
				selectedLead.setFecha_c(Utils.transformTimeUItoBackend(fecha.getText().toString()));
			}

			if (fecha2.getText() != null && fecha2.getText().toString().length() > 1) {
				selectedLead.setFecha2_c(Utils.transformTimeUItoBackend(fecha2.getText().toString()));
			}
			if (fecha3.getText() != null && fecha3.getText().toString().length() > 1) {
				selectedLead.setFecha3_c(Utils.transformTimeUItoBackend(fecha3.getText().toString()));
			}

			selectedLead.setRazonsocial_c(valorRazon.getText().toString());
			selectedLead.setFirst_name(valorNombre.getText().toString());
			selectedLead.setLast_name(valorApellidos.getText().toString());
			selectedLead.setPhone_work(valorTelOficina.getText().toString());
			selectedLead.setPhone_mobile(celular.getText().toString());
			selectedLead.setPhone_fax(valorFax.getText().toString());
			selectedLead.setTitle(cargo.getText().toString());
			selectedLead.setDepartment(departamento.getText().toString());
			selectedLead.setPrimary_address_street(direccion.getText().toString());
			selectedLead.setPrimary_address_city(ciudad.getText().toString());
			selectedLead.setPrimary_address_state(provincia.getText().toString());
			selectedLead.setEmail_address(correo.getText().toString());
			selectedLead.setWebsite(sitio.getText().toString());
			selectedLead.setDescription(requerimiento.getText().toString());
			selectedLead.setOtro_c(otro.getText().toString());
			selectedLead.setOpportunity_amount(estimado.getText().toString());
			
			if(responsable.getText() != null){
				selectedLead.setStatus_description(retroalimenta.getText().toString());
				selectedLead.setFecha_c(Utils.transformTimeUItoBackend(fecha.getText().toString()));
				selectedLead.setResponsable_c(responsable.getText().toString());
			}
			
			if(responsable2.getText() != null){
				selectedLead.setRetroalimentacion2_c(retroalimenta2.getText().toString());
				selectedLead.setFecha2_c(Utils.transformTimeUItoBackend(fecha2.getText().toString()));
				selectedLead.setResponsable2_c(responsable2.getText().toString());
			}
			
			if(responsable3.getText() != null){
				selectedLead.setRetroalimentacion3_c(retroalimenta3.getText().toString());
				selectedLead.setFecha3_c(Utils.transformTimeUItoBackend(fecha3.getText().toString()));		
				selectedLead.setResponsable3_c(responsable3.getText().toString());
			}
			
			selectedLead.setValor_real_c(valorReal.getText().toString());
			selectedLead.setCampaign_name(campaigns.convert(campana.getText().toString(), DataToGet.CODE));
			
			selectedLead.setMedio_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM, medio.getSelectedItem().toString(), DataToGet.CODE));
			selectedLead.setStatus(ListsConversor.convert(ConversorsType.LEADS_STATUS, estado.getSelectedItem().toString(), DataToGet.CODE));
			selectedLead.setAccion_c(ListsConversor.convert(ConversorsType.LEADS_ACTIONS, accion.getSelectedItem().toString(), DataToGet.CODE));
			selectedLead.setMarca_c(ListsConversor.convert(ConversorsType.LEADS_BRAND, marca.getSelectedItem().toString(), DataToGet.CODE));

			// tipo Tarea

			if (!isEditMode) {

				if (Modules.OPPORTUNITIES.equals(actualInfo.getActualParentModule())) {
					// selectedLead.setParent_id(parentId.id);
				}

			}
			String idUsuarioAsignado = lc.convert(asignadoA.getText().toString(), DataToGet.CODE);
			selectedLead.setAssigned_user_id(idUsuarioAsignado);

			editTask = new AddLeadTask();

			editTask.execute(selectedLead);
		}
	  } catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), AddLeadActivity.this, MODULE);
	  }

	}

	@Override
	public void defineValidations() {
		Map<View, CharSequence> data;
		data = new LinkedHashMap<View, CharSequence>();

		data.put(valorNombre, "El campo Nombre no puede estar vacio");

		ValidatorGeneric.getInstance().define(data);

	}

	@Override
	public void onFinishSearchDialog(GenericBean selectedBean, int elementId) {
		if (selectedBean instanceof User) {
			User selectedUser = (User) selectedBean;
			if (elementId == responsable.getId()){
				responsable.setText(selectedUser.getUser_name());
			}else if (elementId == responsable2.getId()){
				responsable2.setText(selectedUser.getUser_name());
			}else if (elementId == responsable3.getId()){
				responsable3.setText(selectedUser.getUser_name());
			}else{
				asignadoA.setText(selectedUser.getUser_name());
			}
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
	 * Representa una tarea asincrona de creacion de Cliente Potencial
	 */
	public class AddLeadTask extends AsyncTask<Object, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Object... params) {
			try {

				Lead obj = (Lead) params[0];

				// Resultado
				resultado = null;

				if (isEditMode) {
					resultado = ControlConnection.putInfo(TypeInfoServer.addLead, obj.getDataBean(), Modo.EDITAR,
							AddLeadActivity.this);
				} else {
					resultado = ControlConnection.putInfo(TypeInfoServer.addLead, obj.getDataBean(), Modo.AGREGAR,
							AddLeadActivity.this);
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
					Message.showFinalMessage(getFragmentManager(), DialogType.EDITED, AddLeadActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), DialogType.CREATED, AddLeadActivity.this, MODULE);

				}

			} else {
				if (isEditMode) {
					Message.showFinalMessage(getFragmentManager(), DialogType.NO_EDITED, AddLeadActivity.this, MODULE);

				} else {
					Message.showFinalMessage(getFragmentManager(), resultado, AddLeadActivity.this, MODULE);

				}

			}

		}

		@Override
		protected void onCancelled() {

		}
	}

}
