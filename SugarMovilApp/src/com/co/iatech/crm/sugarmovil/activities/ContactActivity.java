package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.ContactsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.fragments.OpportunitiesFragment;
import com.co.iatech.crm.sugarmovil.model.ContactoDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.software.shell.fab.ActionButton;

public class ContactActivity extends AppCompatActivity implements
		View.OnClickListener, ContactsModuleActions {

	/**
	 * Debug.
	 */
	private static final String TAG = "ContactActivity";

	/**
	 * Tasks.
	 */
	private GetContactTask mTareaObtenerContacto = null;

	/**
	 * Member Variables.
	 */
	private String mIdContacto;
	private ContactoDetalle contactoDetalle;

	/**
	 * UI References.
	 */
	private Toolbar mContactoToolbar;
	private ImageButton imageButtonEdit, imageButtonAccounts;

	private ImageButton imageButtonOpps;
	private ImageButton imageButtonTasks;
	private ImageButton imageButtonCalls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);

		Intent intent = getIntent();
		mIdContacto = intent.getStringExtra(Info.ID.name());
		Log.d(TAG, "Id contacto " + mIdContacto);

		// Main Toolbar
		mContactoToolbar = (Toolbar) findViewById(R.id.toolbar_contact);
		setSupportActionBar(mContactoToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		this.applyActions();

		// Tarea obtener contacto
		mTareaObtenerContacto = new GetContactTask();
		mTareaObtenerContacto.execute(String.valueOf(mIdContacto));
	}

	@Override
	public ActionButton getActionButton() {
		return null;
	}

	@Override
	public ImageButton getEditButton() {
		return null;
	}

	@Override
	public Modules getModule() {
		return MODULE;
	}

	@Override
	public String getAssignedUser() {
		return contactoDetalle.getAssigned_user_id();
	}

	@Override
	public Parcelable getBean() {
		return contactoDetalle;
	}

	@Override
	public void applyActions() {
		imageButtonEdit = (ImageButton) findViewById(R.id.ic_edit);
		imageButtonEdit.setVisibility(View.INVISIBLE);

		// ToolBar Opciones
		imageButtonAccounts = (ImageButton) findViewById(R.id.image_accounts);
	    imageButtonAccounts.setOnClickListener(this);   

		imageButtonOpps = (ImageButton) findViewById(R.id.image_opportunities);
		imageButtonOpps.setOnClickListener(this);

		imageButtonTasks = (ImageButton) findViewById(R.id.image_tasks);
		imageButtonTasks.setOnClickListener(this);

		imageButtonCalls = (ImageButton) findViewById(R.id.image_calls);
		imageButtonCalls.setOnClickListener(this);

		ActionsStrategy.definePermittedActions(this,  getApplicationContext(), (GlobalClass) getApplicationContext());
	}

	public void ponerValores(ContactoDetalle contactoDetalle) {
		TextView valorContacto = (TextView) findViewById(R.id.valor_contacto);
		valorContacto.setText(contactoDetalle.getFirst_name());
		TextView valorIdentificacion = (TextView) findViewById(R.id.valor_identificacion);
		valorIdentificacion.setText(contactoDetalle.getIdentificacion_c());
		TextView valorCumpleanos = (TextView) findViewById(R.id.valor_cumpleanos);
		valorCumpleanos.setText(contactoDetalle.getBirthdate());
		TextView valorGenero = (TextView) findViewById(R.id.valor_genero);
		valorGenero.setText(contactoDetalle.getGenero_c());
		TextView valorCargo = (TextView) findViewById(R.id.valor_cargo);
		valorCargo.setText(contactoDetalle.getTitle());
		TextView valorCertificaciones = (TextView) findViewById(R.id.valor_certificaciones);
		valorCertificaciones.setText(contactoDetalle.getCertificaciones_c());
		TextView valorProfesion = (TextView) findViewById(R.id.valor_profesion);
		valorProfesion.setText(contactoDetalle.getProfesion_c());
		TextView valorTipo = (TextView) findViewById(R.id.valor_tipo);
		valorTipo.setText(contactoDetalle.getTipocontacto_c());
		TextView valorTel1 = (TextView) findViewById(R.id.valor_tel1);
		valorTel1.setText(contactoDetalle.getPhone_work());
		TextView valorExt1 = (TextView) findViewById(R.id.valor_ext1);
		valorExt1.setText(contactoDetalle.getExtension1_c());
		TextView valorTel2 = (TextView) findViewById(R.id.valor_tel2);
		valorTel2.setText(contactoDetalle.getPhone_other());
		TextView valorExt2 = (TextView) findViewById(R.id.valor_ext2);
		valorExt2.setText(contactoDetalle.getExtension2_c());
		TextView valorCelular = (TextView) findViewById(R.id.valor_celular);
		valorCelular.setText(contactoDetalle.getPhone_mobile());
		TextView valorFax = (TextView) findViewById(R.id.valor_fax);
		valorFax.setText(contactoDetalle.getPhone_fax());
		TextView valorEmail = (TextView) findViewById(R.id.valor_email);
		valorEmail.setText(contactoDetalle.getEmail_address());
		TextView valorCuenta = (TextView) findViewById(R.id.valor_cuenta);
		valorCuenta.setText(contactoDetalle.getNameAccount());

		TextView valorDepartamento = (TextView) findViewById(R.id.valor_departamento);
		valorDepartamento.setText(ListsConversor.convert(ConversorsType.DPTO,
				contactoDetalle.getDepartamento_c(), DataToGet.VALUE));

		TextView valorMunicipio = (TextView) findViewById(R.id.valor_municipio);
		valorMunicipio.setText(contactoDetalle.getMunicipio_c());
		TextView valorDireccion = (TextView) findViewById(R.id.valor_direccion);
		valorDireccion.setText(contactoDetalle.getDireccion_c());
		TextView valorSegmento = (TextView) findViewById(R.id.valor_segmento);
		valorSegmento.setText(contactoDetalle.getSegmento_c());
		TextView valorGrupo = (TextView) findViewById(R.id.valor_grupo);
		valorGrupo.setText(contactoDetalle.getGrupo_objetivo_c());
		TextView valorUen = (TextView) findViewById(R.id.valor_uen);
		valorUen.setText(contactoDetalle.getUen_c());

		TextView valorZona = (TextView) findViewById(R.id.valor_zona);
		valorZona.setText(ListsConversor.convert(ConversorsType.ZONE,
				contactoDetalle.getZona_c(), DataToGet.VALUE));

		TextView valorCanal = (TextView) findViewById(R.id.valor_canal);
		valorCanal.setText(ListsConversor.convert(ConversorsType.CHANNEL,
				contactoDetalle.getCanal_c(), DataToGet.VALUE));

		TextView valorSector = (TextView) findViewById(R.id.valor_sector);
		valorSector.setText(contactoDetalle.getSector_c());
		TextView valorEstado = (TextView) findViewById(R.id.valor_estado);
		valorEstado.setText(contactoDetalle.getEstado_cliente_c());
		TextView valorRegalo1 = (TextView) findViewById(R.id.valor_regalo1);
		valorRegalo1.setText(contactoDetalle.getRegalo1_c());
		TextView valorFechaRegalo1 = (TextView) findViewById(R.id.valor_fecha_regalo1);
		valorFechaRegalo1.setText(contactoDetalle.getFecregalo1_c());
		TextView valorMotivoRegalo1 = (TextView) findViewById(R.id.valor_motivo_regalo1);
		valorMotivoRegalo1.setText(contactoDetalle.getMregalo1_c());
		TextView valorRegalo2 = (TextView) findViewById(R.id.valor_regalo2);
		valorRegalo2.setText(contactoDetalle.getRegalo2_c());
		TextView valorFechaRegalo2 = (TextView) findViewById(R.id.valor_fecha_regalo2);
		valorFechaRegalo2.setText(contactoDetalle.getFecregalo2_c());
		TextView valorMotivoRegalo2 = (TextView) findViewById(R.id.valor_motivo_regalo2);
		valorMotivoRegalo2.setText(contactoDetalle.getMregalo2_c());
		TextView valorRegalo3 = (TextView) findViewById(R.id.valor_regalo3);
		valorRegalo3.setText(contactoDetalle.getRegalo3_c());
		TextView valorFechaRegalo3 = (TextView) findViewById(R.id.valor_fecha_regalo3);
		valorFechaRegalo3.setText(contactoDetalle.getFecregalo3_c());
		TextView valorMotivoRegalo3 = (TextView) findViewById(R.id.valor_motivo_regalo3);
		valorMotivoRegalo3.setText(contactoDetalle.getMregalo3_c());
		TextView valorRegalo4 = (TextView) findViewById(R.id.valor_regalo4);
		valorRegalo4.setText(contactoDetalle.getRegalo4_c());
		TextView valorFechaRegalo4 = (TextView) findViewById(R.id.valor_fecha_regalo4);
		valorFechaRegalo4.setText(contactoDetalle.getFecregalo4_c());
		TextView valorMotivoRegalo4 = (TextView) findViewById(R.id.valor_motivo_regalo4);
		valorMotivoRegalo4.setText(contactoDetalle.getMregalo4_c());
		TextView valorRegalo5 = (TextView) findViewById(R.id.valor_regalo5);
		valorRegalo5.setText(contactoDetalle.getRegalo5_c());
		TextView valorFechaRegalo5 = (TextView) findViewById(R.id.valor_fecha_regalo5);
		valorFechaRegalo5.setText(contactoDetalle.getFecregalo5_c());
		TextView valorMotivoRegalo5 = (TextView) findViewById(R.id.valor_motivo_regalo5);
		valorMotivoRegalo5.setText(contactoDetalle.getMregalo5_c());
		valorRegalo1.setText(contactoDetalle.getRegalo1_c());
		TextView valorInforma = (TextView) findViewById(R.id.valor_informa);
		valorInforma.setText(contactoDetalle.getReports_to_name());
		TextView valorToma = (TextView) findViewById(R.id.valor_toma);
		valorToma.setText(contactoDetalle.getLead_source());
		TextView valorNo = (TextView) findViewById(R.id.valor_no);
		valorNo.setText(contactoDetalle.getDo_not_call());
		TextView valorCampana = (TextView) findViewById(R.id.valor_campana);
		valorCampana.setText(contactoDetalle.getNameCampaign());
		TextView valorDiligenciado = (TextView) findViewById(R.id.valor_diligenciado);
		valorDiligenciado.setText(contactoDetalle.getCreated_by_name());
		TextView valorModificado = (TextView) findViewById(R.id.valor_modificado);
		valorModificado.setText(contactoDetalle.getModified_user_name());
		TextView valorUsuario = (TextView) findViewById(R.id.valor_responsable);
		valorUsuario.setText(contactoDetalle.getAssigned_user_name());
	}
	
	@Override
    public void onBackPressed() {
    	String prevID = ActivitiesMediator.getInstance().getPreviusID();
    	ActivitiesMediator.getInstance().returnPrevID();
    }
	
	@Override
	public void onClick(View v) {
		if (contactoDetalle.getIdAccount() != null) {
			ActivitiesMediator.getInstance().setActualID(
					contactoDetalle.getIdAccount());
		} else {
			Message.showShortExt("Este Contacto no Tiene Cuentas Asociadas",
					this);
			return;
		}

		Modules module = null;
		if (v.getId() == imageButtonAccounts.getId()) {
			Log.d(TAG, "Cuenta de Contacto ");
			ActivitiesMediator.getInstance().showActivity(ContactActivity.this,
					Modules.ACCOUNTS);
			return;
		} else if (v.getId() == imageButtonOpps.getId()) {
			module = Modules.OPPORTUNITIES;
		} else if (v.getId() == imageButtonTasks.getId()) {
			module = Modules.TASKS;
		} else if (v.getId() == imageButtonCalls.getId()) {
			module = Modules.CALLS;
		}
		ActivitiesMediator.getInstance().showList(ContactActivity.this, module);

	}

	/**
	 * Representa una tarea asincrona de obtencion de contacto.
	 */
	public class GetContactTask extends AsyncTask<String, Void, Boolean> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ContactActivity.this,
					ProgressDialog.THEME_HOLO_DARK);
			progressDialog.setMessage("Cargando información contacto...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				// Parametros
				String idContact = params[0];

				// Respuesta
				String contact = null;

				// Intento de obtener cuenta
				ControlConnection.addHeader("idContact", idContact);
				contact = ControlConnection.getInfo(TypeInfoServer.getContact,
						ContactActivity.this);
				JSONObject jObj = new JSONObject(contact);

				JSONArray jArr = jObj.getJSONArray("results");
				if (jArr.length() > 0) {
					JSONObject obj = jArr.getJSONObject(0);
					contactoDetalle = new ContactoDetalle(obj);
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				Log.d(TAG, "Buscar Contacto Error: " + e.getClass().getName()
						+ ":" + e.getMessage());
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mTareaObtenerContacto = null;
			progressDialog.dismiss();

			if (success) {
				ponerValores(contactoDetalle);
			}
		}

		@Override
		protected void onCancelled() {
			mTareaObtenerContacto = null;
			Log.d(TAG, "Cancelado ");
		}
	}

}
