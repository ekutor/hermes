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
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Campana;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;



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

    
    
    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton mImageButtonGuardar;
    private LinearLayout mLayoutContenido;
    private static TextView mValorFechaCierre;
    private EditText valorNombre,valorUsuario,valorEstimado,valorProbabilidad,valorFuente,valorPaso,valorDescripcion;
    private Spinner valorTipo,valorEtapa,valorMedio,valorEnergia,valorComunicaciones,valorIluminacion;
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

        chargeLists();
        createWidgets();
    }
    
    private void chargeLists() {
    	//Tipo Oportunidad
        valorTipo = (Spinner) findViewById(R.id.valor_tipo);
        
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_PROYECT));
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        valorTipo.setAdapter(estadoAdapter);
        
      //Etapa Oportunidad
        valorEtapa = (Spinner) findViewById(R.id.valor_etapa);
        ArrayAdapter<String> etapaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_STAGE));
        etapaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEtapa.setAdapter(etapaAdapter);
      
        // Medio Oportunidad
        valorMedio = (Spinner) findViewById(R.id.valor_medio);
        ArrayAdapter<String> medioAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_MEDIUM));
        medioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorMedio.setAdapter(medioAdapter);
        
        //Energia
        valorEnergia = (Spinner) findViewById(R.id.valor_energia);       
        ArrayAdapter<String> energiaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ENERGY));
        energiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEnergia.setAdapter(energiaAdapter);

        // Comunicaciones
        valorComunicaciones = (Spinner) findViewById(R.id.valor_comunicaciones);
        ArrayAdapter<String> comunicacionesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_COMUNICATIONS));
        comunicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorComunicaciones.setAdapter(comunicacionesAdapter);
        
        // Iluminacion
        valorIluminacion = (Spinner) findViewById(R.id.valor_iluminacion);
        ArrayAdapter<String> iluminacionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ListsConversor.getValuesList(ConversorsType.OPPORTUNITY_ILUM));
        iluminacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorIluminacion.setAdapter(iluminacionAdapter);
    }

    public void createWidgets() {
        valorNombre = (EditText) findViewById(R.id.valor_nombre);

        // Cuenta
        mValorCuenta = (Spinner) findViewById(R.id.valor_cuenta);
        mTareaObtenerCuentas = new GetAccountsTask();
        mTareaObtenerCuentas.execute();

        // Usuario Final
        valorUsuario = (EditText) findViewById(R.id.valor_usuario);

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

        valorEstimado = (EditText) findViewById(R.id.valor_estimado);

        valorProbabilidad = (EditText) findViewById(R.id.valor_probabilidad);

        // Campana
        mValorCampana = (Spinner) findViewById(R.id.valor_campana);
        mTareaObtenerCampanas = new GetCampaignsTask();
        mTareaObtenerCampanas.execute();

        // Fuente
        TextView valorFuente = (TextView) findViewById(R.id.valor_fuente);

        // Paso
        valorPaso = (EditText) findViewById(R.id.valor_paso);
        // Descripcion
        valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
        // Asignado a
        mValorAsignadoA = (Spinner) findViewById(R.id.valor_asignado_a);
        mTareaObtenerUsuarios = new GetUsersTask();
        mTareaObtenerUsuarios.execute();

        
        // Eventos
        // Guardar Tarea
        mImageButtonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	OportunidadDetalle op = new OportunidadDetalle();
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
                op.setName(valorNombre.getText().toString());
                
                op.setTipo_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
                if (valorTipo.getSelectedItem() == null){
                	Context context = getApplicationContext();
                    CharSequence text = "Debe seleccionar un Tipo de Oportunidad";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }
                op.setTipo_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_PROYECT, valorTipo.getSelectedItem().toString(), DataToGet.CODE));
                
                if (valorEtapa.getSelectedItem()== null) {
                    Context context = getApplicationContext();
                    CharSequence text = "Debe seleccionar una Etapa de Oportunidad";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }
                op.setSales_stage(ListsConversor.convert(ConversorsType.OPPORTUNITY_STAGE, valorEtapa.getSelectedItem().toString(), DataToGet.CODE));

                // Cuenta
                

                // Fecha Cierre
              //  mFechaCierre = mValorFechaCierre.getText().toString();

                // Camapana
               

                // Medio              
                if (valorMedio.getSelectedItem() == null) {
                    Context context = getApplicationContext();
                    CharSequence text = "Debe seleccionar una Medio ";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }
                op.setMedio_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_MEDIUM, valorMedio.getSelectedItem().toString(), DataToGet.CODE));

                // Asignado
                

                // Marca energia
                op.setEnergia_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_ENERGY, valorEnergia.getSelectedItem().toString(), DataToGet.CODE));

                // Marca comunicaciones
                op.setComunicaciones_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_COMUNICATIONS, valorComunicaciones.getSelectedItem().toString(), DataToGet.CODE));

                // Marca iluminacion
                op.setIluminacion_c(ListsConversor.convert(ConversorsType.OPPORTUNITY_ILUM, valorIluminacion.getSelectedItem().toString(), DataToGet.CODE));

                mTareaCrearOportunidad = new AddOpportunityTask();
                // TODO: Cambiar id usuario logueado
                mUsuario = "2d644d36-14d9-9722-d62e-55269fa6a81d";
                
                //op.setUsuario_final_c(usuario_final_c)
                op.setValoroportunidad_c(valorEstimado.getText().toString());
                op.setProbability(valorProbabilidad.getText().toString());
                op.setNext_step(valorPaso.getText().toString());
                op.setDescription(valorDescripcion.getText().toString());
                // Crear oportunidad
                mTareaCrearOportunidad.execute(op);
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
            	 Log.d(TAG, "Oportunidad Creada");
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
    public class AddOpportunityTask extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            try {
                // Parametros
                String idOpportunity = "idOpportunity";
                OportunidadDetalle op = (OportunidadDetalle)params[0];

                // Resultado
                String resultado = null;

               
                resultado  = ControlConnection.putInfo(TypeInfoServer.addOpportunity, op.getDataBean());
                Log.d(TAG, "Crear Oportunidad RESPr: "+ resultado);
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
