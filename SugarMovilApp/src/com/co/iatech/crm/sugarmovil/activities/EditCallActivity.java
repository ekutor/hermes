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
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Campana;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.User;


public class EditCallActivity extends AppCompatActivity {

    /**
     * Extras
     */
    public final static String EXTRA_CALL = "com.taktil.crm.laumayer.CALL";

    /**
     * Debug.
     */
    private static final String TAG = "CallActivity";

    /**
     * Tasks.
     */
    private EditCallTask mTareaEditarLlamada = null;
    private GetUsersTask mTareaObtenerUsuarios = null;
    private GetAccountsTask mTareaObtenerCuentas = null;
    private GetCampaignsTask mTareaObtenerCampanas = null;

    /**
     * Member Variables.
     */
    private String mUsuario;
    private Llamada mLlamadaDetalle;
    private ArrayList<String> mNamesArray = new ArrayList<>();
    private ArrayList<User> mUsersArray = new ArrayList<>();
    private ArrayList<Cuenta> mAccountsArray = new ArrayList<>();
    private ArrayList<Campana> mCampaignsArray = new ArrayList<>();
    private ArrayList<String> mNamesCapaignsArray = new ArrayList<>();
    private String mDireccion, mEstado, mDuracion, mFechaInicio, mAsignadoA, mCuenta, mCampana;

    /**
     * UI References.
     */
    private Toolbar mLlamadaToolbar;
    private ImageButton mImageButtonGuardar;
    private LinearLayout mLayoutContenido;
    private static TextView mValorFechaInicio;
    private Spinner mValorAsignadoA, mValorCuenta, mValorCampana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_call);

        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent intent = getIntent();
        mLlamadaDetalle = intent.getParcelableExtra( Info.ID_LLAMADA_ACTUAL.name() );
        Log.d(TAG, "Id llamada " + mLlamadaDetalle.getId());

        // Main Toolbar
        mLlamadaToolbar = (Toolbar) findViewById(R.id.toolbar_call);
        setSupportActionBar(mLlamadaToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

        // Contenido
        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);

        // Valores Llamada
        ponerValores(mLlamadaDetalle);
    }

    public void ponerValores(Llamada llamadaDetalle) {
        // Asunto
        final EditText valorAsunto = (EditText) findViewById(R.id.valor_asunto);
        valorAsunto.setText(llamadaDetalle.getName());
        // Direccion
        final Spinner valorDireccion = (Spinner) findViewById(R.id.valor_direccion);
        List<String> list = new ArrayList<>();
        list.add("Seleccionar direccion");
        list.add("Entrante");
        list.add("Saliente");
        ArrayAdapter<String> direccionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        direccionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorDireccion.setAdapter(direccionAdapter);
        valorDireccion.setSelection(list.indexOf(llamadaDetalle.getDirection()));
        // Estado
        final Spinner valorEstado = (Spinner) findViewById(R.id.valor_estado);
        List<String> listEstado = new ArrayList<>();
        listEstado.add("Seleccionar estado");
        listEstado.add("Realizada");
        listEstado.add("No Realizada");
        listEstado.add("Planeada");
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listEstado);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorEstado.setAdapter(estadoAdapter);
        valorEstado.setSelection(listEstado.indexOf(llamadaDetalle.getStatus()));
        // Fecha Inicio
        Button botonFechaInicio = (Button) findViewById(R.id.boton_fecha_inicio);
        botonFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerInicioFragment();
                newFragment.show(getFragmentManager(), "dateInicioPicker");
            }
        });
        mValorFechaInicio = (TextView) findViewById(R.id.valor_fecha_inicio);
        mValorFechaInicio.setText(llamadaDetalle.getDate_start());
        // Duracion
        final Spinner valorDuracion = (Spinner) findViewById(R.id.valor_duracion);
        List<String> listDuracion = new ArrayList<>();
        listDuracion.add("Seleccionar duracion");
        listDuracion.add("15");
        listDuracion.add("30");
        listDuracion.add("45");
        ArrayAdapter<String> duracionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listDuracion);
        duracionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorDuracion.setAdapter(duracionAdapter);
        valorEstado.setSelection(listDuracion.indexOf(llamadaDetalle.getDuration_minutes()));
        // Resultado
        final EditText valorResultado = (EditText) findViewById(R.id.valor_resultado);
        valorResultado.setText(llamadaDetalle.getResultadodelallamada_c());
        // Descripcion
        final EditText valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(llamadaDetalle.getDescription());
        // Asignado a
        mValorAsignadoA = (Spinner) findViewById(R.id.valor_asignado_a);
        mTareaObtenerUsuarios = new GetUsersTask();
        mTareaObtenerUsuarios.execute();
        // Cuenta
        mValorCuenta = (Spinner) findViewById(R.id.valor_cuenta);
        mTareaObtenerCuentas = new GetAccountsTask();
        mTareaObtenerCuentas.execute();
        // Campana
        mValorCampana = (Spinner) findViewById(R.id.valor_campana);
        mTareaObtenerCampanas = new GetCampaignsTask();
        mTareaObtenerCampanas.execute();

        // Eventos
        // Guardar Llamada
        mImageButtonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Asunto
                if (valorAsunto.getText().toString().equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Asunto no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Direccion
                mDireccion = valorDireccion.getSelectedItem().toString();

                // Estado
                mEstado = valorEstado.getSelectedItem().toString();

                // Fecha
                mFechaInicio = mValorFechaInicio.getText().toString();

                // Duracion
                mDuracion = valorDuracion.getSelectedItem().toString();

                // Asignado
                try {
                    for (User usuario : mUsersArray) {
                        if (usuario.getUser_name() != null && usuario.getUser_name().contains(mValorAsignadoA.getSelectedItem().toString())) {
                            mAsignadoA = usuario.getId();
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

                // Cuenta
                try {
                    mCuenta = mValorCuenta.getSelectedItem().toString();
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Cuenta no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // Camapana
                try {
                    mCampana = mValorCampana.getSelectedItem().toString();
                } catch (Exception e) {
                    Context context = getApplicationContext();
                    CharSequence text = "El campo Camapana no puede estar vacio";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER, 0, 20);
                    toast.show();
                    return;
                }

                // TODO: Salir de aqui si hay algun toast y no guardar la cuenta! con return;

                mTareaEditarLlamada = new EditCallTask();
                // TODO: Cambiar id usuario logueado
                mUsuario = "2d644d36-14d9-9722-d62e-55269fa6a81d";

                // Crear llamada
                mTareaEditarLlamada.execute(valorAsunto.getText().toString(), valorResultado.getText().toString(), valorDescripcion.getText().toString(), mDireccion, mEstado, mFechaInicio, mDuracion, mCuenta, mCampana, mUsuario, mAsignadoA);
            }
        });
    }

    public static class DatePickerInicioFragment extends DialogFragment
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
            mValorFechaInicio.setText(new StringBuilder()
                    .append(year).append("-").append(month + 1).append("-")
                    .append(day).append(" "));
        }
    }

    /**
     * Clase para el mensaje de creacion de llamada.
     */
    @SuppressLint("ValidFragment")
    public class EditCallDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.dialog_edit_activity_call)
                    .setMessage(R.string.dialog_edit_message_call)
                    .setPositiveButton(R.string.dialog_ok_call, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            EditCallActivity.this.finish();
                        }
                    });

            return builder.create();
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
                HttpGet httpGetUsers = new HttpGet(ControlConnection.URL
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

                mNamesArray.clear();
                mUsersArray.clear();

                JSONObject jObj = new JSONObject(usuario);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    String id = obj.getString("id");
                    String user_name = obj.getString("user_name");
                    String user_hash = obj.getString("user_hash");
                    String system_generated_password = obj.getString("system_generated_password");
                    String pwd_last_changed = obj.getString("pwd_last_changed");
                    String authenticate_id = obj.getString("authenticate_id");
                    String sugar_login = obj.getString("sugar_login");

                    mNamesArray.add(user_name);
                   //TODO  mUsersArray.add(new User(id, user_name, user_hash, system_generated_password, pwd_last_changed, authenticate_id, sugar_login));

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
                    ArrayAdapter<String> usuarioAdapter = new ArrayAdapter<>(EditCallActivity.this,
                            android.R.layout.simple_spinner_item, mNamesArray);
                    mValorAsignadoA.setAdapter(usuarioAdapter);
                    mValorAsignadoA.setSelection(mUsersArray.indexOf(mLlamadaDetalle.getAssigned_user_name()));
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
     * Representa una tarea asincrona de obtencion de cuentas.
     */
    public class GetAccountsTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String accounts = null;

                // Intento de obtener cuentas
                
                accounts  = ControlConnection.getInfo(TypeInfoServer.getAccounts);
          
                mNamesArray.clear();
                mAccountsArray.clear();

                JSONObject jObj = new JSONObject(accounts);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                   
                    String name = obj.getString("name");
                    mNamesArray.add(name);
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
                    ArrayAdapter<String> cuentaAdapter = new ArrayAdapter<>(EditCallActivity.this,
                            android.R.layout.simple_spinner_item, mNamesArray);
                    mValorCuenta.setAdapter(cuentaAdapter);
                    mValorCuenta.setSelection(mAccountsArray.indexOf(mLlamadaDetalle.getParent_name()));
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
                campana = ControlConnection.getInfo(TypeInfoServer.getCampaigns);
               
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
                    ArrayAdapter<String> campanaAdapter = new ArrayAdapter<>(EditCallActivity.this,
                            android.R.layout.simple_spinner_item, mNamesCapaignsArray);
                    mValorCampana.setAdapter(campanaAdapter);
                    mValorCampana.setSelection(mCampaignsArray.indexOf(mLlamadaDetalle.getCampaign_name()));
                } else {
                    Log.d(TAG,
                            "No hay Usuarios: "
                                    + mUsersArray.size());
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
     * Representa una tarea asincrona de edicion de llamada.
     */
    public class EditCallTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Parametros
                String id = "idCall";
                String asunto = params[0];
                String resultado = params[1];
                String descripcion = params[2];
                String direccion = params[3];
                String estado = params[4];
                String fechaInicio = params[5];
                String duracion = params[6];
                String idAccount = params[7];
                String idCampana = params[8];
                String idUsuarioLogueado = params[9];
                String idContacto = params[10];

                String llamada = null;

                // Intento de editar llamada
                HttpClient httpClientCall = new DefaultHttpClient();
                HttpPut httpPutCall = new HttpPut(ControlConnection.URL
                        + "editCall");
                httpPutCall.setHeader("id", id);
                httpPutCall.setHeader("asunto", asunto);
                httpPutCall.setHeader("resultado", resultado);
                httpPutCall.setHeader("descripcion", descripcion);
                httpPutCall.setHeader("direccion", direccion);
                httpPutCall.setHeader("estado", estado);
                httpPutCall.setHeader("fechaInicio", fechaInicio);
                httpPutCall.setHeader("duracion", duracion);
                httpPutCall.setHeader("direccion", direccion);
                httpPutCall.setHeader("idAccount", idAccount);
                httpPutCall.setHeader("idCampana", idCampana);
                httpPutCall.setHeader("idUsuarioLogueado", idUsuarioLogueado);
                httpPutCall.setHeader("idContacto", idContacto);

                try {
                    HttpResponse response = httpClientCall
                            .execute(httpPutCall);
                    llamada = EntityUtils.toString(response
                            .getEntity());
                    llamada = llamada.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Editar Call Response: "
                            + llamada);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Editar Call Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaEditarLlamada = null;

            if (success) {
                Log.d(TAG, "Editar llamada exitoso");
                EditCallDialogFragment newFragment = new EditCallDialogFragment();
                newFragment.show(getFragmentManager(), "editCall");
            } else {
                Log.d(TAG, "Editar llamada error");
            }
        }

        @Override
        protected void onCancelled() {
            mTareaEditarLlamada = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
