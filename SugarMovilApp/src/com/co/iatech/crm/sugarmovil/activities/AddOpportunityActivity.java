package com.co.iatech.crm.sugarmovil.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.model.Campana;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.User;



public class AddOpportunityActivity extends AppCompatActivity {

 
    /**
     * Debug.
     */
    private static final String TAG = "AddOpportunityActivity";

    /**
     * Tasks.
     */
    private GetOpportunityTask mTareaObtenerOportunidad = null;
    private AddOpportunityTask mTareaCrearOportunidad = null;
    private GetAccountsTask mTareaObtenerCuentas = null;
    private GetCampaignsTask mTareaObtenerCampanas = null;
    private GetUsersTask mTareaObtenerUsuarios = null;

    /**
     * Member Variables.
     */
    private String mUrl;
    private String mUsuario;
    private String mIdOportunidad;
    private OportunidadDetalle mOportunidadDetalle;
    private ArrayList<Cuenta> mAccountsArray = new ArrayList<>();
    private ArrayList<String> mAccountNamesArray = new ArrayList<>();
    private ArrayList<Campana> mCampaignsArray = new ArrayList<>();
    private ArrayList<String> mNamesCapaignsArray = new ArrayList<>();
    private ArrayList<User> mUsersArray = new ArrayList<>();
    private ArrayList<String> mNamesUsersArray = new ArrayList<>();
    private String mTipo, mEtapa, mIdCuenta, mFechaCierre, mIdCampana, mMedio, mAsignado, mEnergia, mComunicaciones, mIluminacion;

    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonGuardar;
    private LinearLayout mLayoutContenido;
    private static TextView mValorFechaCierre;
    private Spinner mValorCuenta, mValorCampana, mValorAsignadoA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opportunity);

        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent intent = getIntent();
       // mIdOportunidad = intent.getStringExtra(RecyclerOpportunitiesAdapter.EXTRA_ID_OPORTUNIDAD);
        Log.d(TAG, "Id oportunidad " + mIdOportunidad);

        // Main Toolbar
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
        setSupportActionBar(mCuentaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

        // Contenido
        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);

        // Tarea obtener cuenta
//        mTareaObtenerOportunidad = new GetOpportunityTask();
//        mTareaObtenerOportunidad.execute(String.valueOf(mIdOportunidad));
    }

    public void ponerValores(OportunidadDetalle oportunidadDetalle) {
        // Nombre
        final EditText valorNombre = (EditText) findViewById(R.id.valor_nombre);
//        valorNombre.setText(oportunidadDetalle.getName());
        // Tipo
        final Spinner valorTipo = (Spinner) findViewById(R.id.valor_tipo);
        List<String> listTipo = new ArrayList<>();
        listTipo.add("Seleccionar tipo");
        listTipo.add("Proyecto de Ingenieria");
        listTipo.add("Proyecto de Componentes");
        listTipo.add("Proyecto de Especificacion");
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listTipo);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorTipo.setAdapter(estadoAdapter);
//        valorTipo.setText(oportunidadDetalle.getTipo_c());
        // Etapa
        final Spinner valorEtapa = (Spinner) findViewById(R.id.valor_etapa);
        List<String> listEtapa = new ArrayList<>();
        listEtapa.add("Seleccionar etapa");
        listEtapa.add("ESPECIFICACION");
        listEtapa.add("OFERTADO");
        listEtapa.add("PENDIENTE POR OFERTA");
        listEtapa.add("EN ESPERA DE DECISION");
        listEtapa.add("ADJUDICADO");
        listEtapa.add("PERDIDO");
        listEtapa.add("APLAZADO");
        listEtapa.add("CANCELADO");
        listEtapa.add("GANADO POR CANAL");
        ArrayAdapter<String> etapaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listEtapa);
        valorEtapa.setAdapter(etapaAdapter);
//        valorEtapa.setText(oportunidadDetalle.getSales_stage());
        // Cuenta
        mValorCuenta = (Spinner) findViewById(R.id.valor_cuenta);
        mTareaObtenerCuentas = new GetAccountsTask();
        mTareaObtenerCuentas.execute();
//        valorCuenta.setText(oportunidadDetalle.getNameAccount());
        // Usuario Final
        final EditText valorUsuario = (EditText) findViewById(R.id.valor_usuario);
//        valorUsuario.setText(oportunidadDetalle.getUsuario_final_c());
        // Fecha Cierre
        Button botonFechaInicio = (Button) findViewById(R.id.boton_fecha_cierre);
        botonFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerCierreFragment();
                newFragment.show(getFragmentManager(), "dateCierrePicker");
            }
        });
        mValorFechaCierre = (TextView) findViewById(R.id.valor_fecha_cierre);
//        valorFecha.setText(oportunidadDetalle.getDate_closed());
        final EditText valorEstimado = (EditText) findViewById(R.id.valor_estimado);
//        valorEstimado.setText(oportunidadDetalle.getValoroportunidad_c());
        final EditText valorProbabilidad = (EditText) findViewById(R.id.valor_probabilidad);
//        valorProbabilidad.setText(oportunidadDetalle.getProbability());
        // Campana
        mValorCampana = (Spinner) findViewById(R.id.valor_campana);
        mTareaObtenerCampanas = new GetCampaignsTask();
        mTareaObtenerCampanas.execute();
//        valorCampana.setText(oportunidadDetalle.getNameCampaign());
        // Medio
        final Spinner valorMedio = (Spinner) findViewById(R.id.valor_medio);
        List<String> listMedio = new ArrayList<>();
        listMedio.add("Seleccionar medio");
        listMedio.add("CAPACITACIONES-EVENTOS");
        listMedio.add("FERIAS");
        listMedio.add("PAUTAS PUBLICITARIAS");
        listMedio.add("E-MAIL MARKETING");
        listMedio.add("ESPECIFICACION");
        listMedio.add("PORTALES WEB");
        listMedio.add("REMITIDO POR PROVEEDOR");
        listMedio.add("NO APLICA");
        listMedio.add("OTRO");
        ArrayAdapter<String> medioAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listMedio);
        medioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMedio.setAdapter(medioAdapter);
//        valorMedio.setText(oportunidadDetalle.getMedio_c());
        // Fuente
        TextView valorFuente = (TextView) findViewById(R.id.valor_fuente);
//        valorFuente.setText(oportunidadDetalle.getFuente_c());
        // Paso
        final EditText valorPaso = (EditText) findViewById(R.id.valor_paso);
//        valorPaso.setText(oportunidadDetalle.getNext_step());
        // Descripcion
        final EditText valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
//        valorDescripcion.setText(oportunidadDetalle.getDescription());
        // Asignado a
        mValorAsignadoA = (Spinner) findViewById(R.id.valor_asignado_a);
        mTareaObtenerUsuarios = new GetUsersTask();
        mTareaObtenerUsuarios.execute();
//        valorAsignado.setText(oportunidadDetalle.getAssigned_user_name());
        // Energia
        final Spinner valorEnergia = (Spinner) findViewById(R.id.valor_energia);
        List<String> listEnergia = new ArrayList<>();
        listEnergia.add("Seleccionar marca energia");
        listEnergia.add("CABUR");
        listEnergia.add("CIRPROTEC");
        listEnergia.add("DELTA");
        listEnergia.add("DKC");
        listEnergia.add("EATON");
        listEnergia.add("ENERLUX");
        listEnergia.add("ENSAMBLES");
        listEnergia.add("GEROS");
        listEnergia.add("GREENLEE");
        listEnergia.add("KLAUKE");
        listEnergia.add("LEVITON WID");
        listEnergia.add("LOVATO");
        listEnergia.add("VCP ELECTRIC");
        listEnergia.add("WOHNER");
        ArrayAdapter<String> energiaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listEnergia);
        energiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEnergia.setAdapter(energiaAdapter);
//        valorEnergia.setText(oportunidadDetalle.getEnergia_c());
        // Comunicaciones
        final Spinner valorComunicaciones = (Spinner) findViewById(R.id.valor_comunicaciones);
        List<String> listComunicaciones = new ArrayList<>();
        listComunicaciones.add("Seleccionar marca comunicaciones");
        listComunicaciones.add("CIRPROTEC");
        listComunicaciones.add("GREENLEE");
        listComunicaciones.add("KLAUKE");
        listComunicaciones.add("LEVELONE");
        listComunicaciones.add("LEVITON");
        listComunicaciones.add("SUPERIOR ESSEX");
        listComunicaciones.add("VCP CONNECT +");
        ArrayAdapter<String> comunicacionesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listComunicaciones);
        comunicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorComunicaciones.setAdapter(comunicacionesAdapter);
//        valorComunicaciones.setText(oportunidadDetalle.getComunicaciones_c());
        // Iluminacion
        final Spinner valorIluminacion = (Spinner) findViewById(R.id.valor_iluminacion);
        List<String> listIluminacion = new ArrayList<>();
        listIluminacion.add("Seleccionar marca iluminacion");
        listIluminacion.add("VCP");
        ArrayAdapter<String> iluminacionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listIluminacion);
        iluminacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorIluminacion.setAdapter(iluminacionAdapter);
//        valorIluminacion.setText(oportunidadDetalle.getIluminacion_c());

        // Eventos
        // Guardar Tarea
        mImageButtonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Nombre
                if (valorNombre.getText().toString().equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Nombre no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Tipo
                switch (valorTipo.getSelectedItem().toString()) {
                    case "Proyecto de Ingenieria":
                        mTipo = "Proyecto1";
                        break;
                    case "Proyecto de Componentes":
                        mTipo = "Proyecto2";
                        break;
                    case "Proyecto de Especificacion":
                        mTipo = "Proyecto3";
                        break;
                }

                if (mTipo.equals("Seleccionar tipo")) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo tipo no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Etapa
                mEtapa = valorEtapa.getSelectedItem().toString();

                if (mEtapa.equals("Seleccionar etapa")) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo etapa no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Cuenta
                try {
                    mIdCuenta = mValorCuenta.getSelectedItem().toString();
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Cuenta no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Fecha Cierre
                mFechaCierre = mValorFechaCierre.getText().toString();

                // Camapana
                try {
                    mIdCampana = mValorCampana.getSelectedItem().toString();
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Camapana no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Medio
                switch (valorMedio.getSelectedItem().toString()) {
                    case "CAPACITACIONES-EVENTOS":
                        mMedio = "001";
                        break;
                    case "FERIAS":
                        mMedio = "002";
                        break;
                    case "PAUTAS PUBLICITARIAS":
                        mMedio = "003";
                        break;
                    case "E-MAIL MARKETING":
                        mMedio = "006";
                        break;
                    case "ESPECIFICACION":
                        mMedio = "007";
                        break;
                    case "PORTALES WEB":
                        mMedio = "008";
                        break;
                    case "REMITIDO POR PROVEEDOR":
                        mMedio = "011";
                        break;
                    case "NO APLICA":
                        mMedio = "009";
                        break;
                    case "OTRO":
                        mMedio = "010";
                        break;
                    default:
                        mMedio = "Seleccionar medio";
                        break;
                }

                // Asignado
                try {
                    for (User usuario : mUsersArray) {
                        if (usuario.getUser_name() != null && usuario.getUser_name().contains(mValorAsignadoA.getSelectedItem().toString())) {
                            mAsignado = usuario.getId();
                        }
                    }
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Asignado a no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Marca energia
                mEnergia = valorEnergia.getSelectedItem().toString();

                // Marca comunicaciones
                mComunicaciones = valorComunicaciones.getSelectedItem().toString();

                // Marca iluminacion
                mIluminacion = valorIluminacion.getSelectedItem().toString();

                mTareaCrearOportunidad = new AddOpportunityTask();
                // TODO: Cambiar id usuario logueado
                mUsuario = "2d644d36-14d9-9722-d62e-55269fa6a81d";

                // Crear oportunidad
                mTareaCrearOportunidad.execute(valorNombre.getText().toString(), valorUsuario.getText().toString(), valorEstimado.getText().toString(), valorProbabilidad.getText().toString(), valorPaso.getText().toString(), valorDescripcion.getText().toString(), mTipo, mEtapa, mIdCuenta, mFechaCierre, mIdCampana, mMedio, mAsignado, mEnergia, mComunicaciones, mIluminacion, mUsuario, mUsuario);
            }
        });
    }

    public static class DatePickerCierreFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            mValorFechaCierre.setText(new StringBuilder()
                    .append(year).append("-").append(month + 1).append("-")
                    .append(day).append(" "));
        }
    }

    /**
     * Clase para el mensaje de creacion de oportunidad.
     */
    @SuppressLint("ValidFragment")
    public class CrearOpportunityDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.dialog_activity_opportunity)
                    .setMessage(R.string.dialog_message_opportunity)
                    .setPositiveButton(R.string.dialog_ok_opportunity, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AddOpportunityActivity.this.finish();
                        }
                    });

            return builder.create();
        }
    }

    /**
     * Representa una tarea asincrona de obtencion de oportunidad.
     */
    public class GetOpportunityTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AddOpportunityActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando información oportunidad...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idOportunidad = params[0];

                // Respuesta
                String account = null;

                // Intento de obtener cuenta
                HttpClient httpClientOpportunity = new DefaultHttpClient();
                HttpGet httpGetAccount = new HttpGet(mUrl
                        + "getOpportunity");
                httpGetAccount.setHeader("idOpportunity", idOportunidad);

                try {
                    HttpResponse response = httpClientOpportunity
                            .execute(httpGetAccount);
                    account = EntityUtils.toString(response
                            .getEntity());
                    account = account.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Oportunidad Response: "
                            + account);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                JSONObject jObj = new JSONObject(account);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    mOportunidadDetalle = new OportunidadDetalle(obj);
                    
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Oportunidad Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerOportunidad = null;
            progressDialog.dismiss();

            if (success) {
                ponerValores(mOportunidadDetalle);
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
    }

    /**
     * Representa una tarea asincrona de obtencion de cuentas.
     */
    public class GetAccountsTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String accounts = null;

                // Intento de obtener cuentas
                HttpClient httpClientAccounts = new DefaultHttpClient();
                HttpGet httpGetAccounts = new HttpGet(mUrl
                        + "getAccounts");

                try {
                    HttpResponse response = httpClientAccounts
                            .execute(httpGetAccounts);
                    accounts = EntityUtils.toString(response
                            .getEntity());
                    accounts = accounts.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Cuentas Response: "
                            + accounts);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                mAccountNamesArray.clear();
                mAccountsArray.clear();

                JSONObject jObj = new JSONObject(accounts);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                   
                    String name = obj.getString("name");
                    mAccountNamesArray.add(name);
                    mAccountsArray.add(new Cuenta(obj));
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Cuentas Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerCuentas = null;

            if (success) {
                if (mAccountsArray.size() > 0) {
                    ArrayAdapter<String> cuentaAdapter = new ArrayAdapter<>(AddOpportunityActivity.this,
                            android.R.layout.simple_spinner_item, mAccountNamesArray);
                    mValorCuenta.setAdapter(cuentaAdapter);
                } else {
                    Log.d(TAG,
                            "No hay Cuentas: "
                                    + mAccountsArray.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerCuentas = null;
            Log.d(TAG, "Cancelado ");
        }
    }

    /**
     * Representa una tarea asincrona de obtencion de campanas.
     */
    public class GetCampaignsTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String campana = null;

                // Intento de obtener capanas
                HttpClient httpClientCampaign = new DefaultHttpClient();
                HttpGet httpGetCampaign = new HttpGet(mUrl
                        + "getCampaigns");

                try {
                    HttpResponse response = httpClientCampaign
                            .execute(httpGetCampaign);
                    campana = EntityUtils.toString(response
                            .getEntity());
                    campana = campana.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Campanas Response: "
                            + campana);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                mNamesCapaignsArray.clear();
                mCampaignsArray.clear();

                JSONObject jObj = new JSONObject(campana);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    String id = obj.getString("id");
                    String name = obj.getString("name");

                    mNamesCapaignsArray.add(name);
                    mCampaignsArray.add(new Campana(id, name));

                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Campanas Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerCampanas = null;

            if (success) {
                if (mCampaignsArray.size() > 0) {
                    ArrayAdapter<String> campanaAdapter = new ArrayAdapter<>(AddOpportunityActivity.this,
                            android.R.layout.simple_spinner_item, mNamesCapaignsArray);
                    mValorCampana.setAdapter(campanaAdapter);
                } else {
                    Log.d(TAG,
                            "No hay Usuarios: "
                                    + mCampaignsArray.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerCampanas = null;
            Log.d(TAG, "Cancelado ");
        }
    }

    /**
     * Representa una tarea asincrona de obtencion de usuarios.
     */
    public class GetUsersTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String usuario = null;

                // Intento de obtener contactos
                HttpClient httpClientUsers = new DefaultHttpClient();
                HttpGet httpGetUsers = new HttpGet(mUrl
                        + "getUsers");

                try {
                    HttpResponse response = httpClientUsers
                            .execute(httpGetUsers);
                    usuario = EntityUtils.toString(response
                            .getEntity());
                    usuario = usuario.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Usuarios Response: "
                            + usuario);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                mNamesUsersArray.clear();
                mUsersArray.clear();

                JSONObject jObj = new JSONObject(usuario);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
     
                    String user_name = obj.getString("user_name");
                   

                    mNamesUsersArray.add(user_name);
                    mUsersArray.add(new User( obj ));

                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Usuarios Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerUsuarios = null;

            if (success) {
                if (mUsersArray.size() > 0) {
                    ArrayAdapter<String> usuarioAdapter = new ArrayAdapter<>(AddOpportunityActivity.this,
                            android.R.layout.simple_spinner_item, mNamesUsersArray);
                    mValorAsignadoA.setAdapter(usuarioAdapter);
                } else {
                    Log.d(TAG,
                            "No hay Usuarios: "
                                    + mUsersArray.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerUsuarios = null;
            Log.d(TAG, "Cancelado ");
        }
    }

    /**
     * Representa una tarea asincrona de creacion de oportunidad.
     */
    public class AddOpportunityTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String idOpportunity = "idOpportunity";
                String name = params[0];
                String usuarioFinal = params[1];
                String valorEstimado = params[2];
                String proximoPaso = params[3];
                String descripcion = params[4];
                String tipoProyecto = params[5];
                String etapa = params[6];
                String idCuenta = params[7];
                String fechaCierre = params[8];
                String idCampana = params[9];
                String medio = params[10];
                String idUsuarioAsignado = params[11];
                String marcasEnergia = params[12];
                String marcasComunicaciones = params[13];
                String marcasIluminacion = params[14];
                String idUsuarioLogueado = params[15];
                String idContacto = params[16];

                String oportunidad = null;

                // Intento de crear llamada
                HttpClient httpClientOpportunity = new DefaultHttpClient();
                HttpPut httpPutOpportunity = new HttpPut(ControlConnection.URL
                        + "addOpportunity");
                httpPutOpportunity.setHeader("idOpportunity", idOpportunity);
                httpPutOpportunity.setHeader("name", name);
                httpPutOpportunity.setHeader("usuarioFinal", usuarioFinal);
                httpPutOpportunity.setHeader("valorEstimado", valorEstimado);
                httpPutOpportunity.setHeader("proximoPaso", proximoPaso);
                httpPutOpportunity.setHeader("descripcion", descripcion);
                httpPutOpportunity.setHeader("tipoProyecto", tipoProyecto);
                httpPutOpportunity.setHeader("etapa", etapa);
                httpPutOpportunity.setHeader("idCuenta", idCuenta);
                httpPutOpportunity.setHeader("fechaCierre", fechaCierre);
                httpPutOpportunity.setHeader("idCampana", idCampana);
                httpPutOpportunity.setHeader("medio", medio);
                httpPutOpportunity.setHeader("idUsuarioAsignado", idUsuarioAsignado);
                httpPutOpportunity.setHeader("marcasEnergia", marcasEnergia);
                httpPutOpportunity.setHeader("marcasComunicaciones", marcasComunicaciones);
                httpPutOpportunity.setHeader("marcasIluminacion", marcasIluminacion);
                httpPutOpportunity.setHeader("idUsuarioLogueado", idUsuarioLogueado);
                httpPutOpportunity.setHeader("idContacto", idContacto);

                try {
                    HttpResponse response = httpClientOpportunity
                            .execute(httpPutOpportunity);
                    oportunidad = EntityUtils.toString(response
                            .getEntity());
                    oportunidad = oportunidad.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Crear Oportunidad Response: "
                            + oportunidad);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Crear Oportunidad Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaCrearOportunidad = null;

            if (success) {
                Log.d(TAG, "Crear Oportunidad exitoso");
                CrearOpportunityDialogFragment newFragment = new CrearOpportunityDialogFragment();
                newFragment.show(getFragmentManager(), "createOpportunity");
            } else {
                Log.d(TAG, "Crear Oportunidad error");
            }
        }

        @Override
        protected void onCancelled() {
            mTareaCrearOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
