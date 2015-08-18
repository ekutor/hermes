package com.co.iatech.crm.sugarmovil.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Campana;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.OportunidadDetalle;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;


public class EditOpportunityActivity extends AppCompatActivity {


    /**
     * Childs
     */
    public final int USER_SELECT = 1;
    public final int ACCOUNT_SELECT = 2;
    public final int CAMPAIGN_SELECT = 3;

    /**
     * Debug.
     */
    private static final String TAG = "EditOpportunityActivity";

    /**
     * Tasks.
     */
    private EditOpportunityTask mTareaEditarOportunidad = null;

    /**
     * Member Variables.
     */
    private String mUrl;
    private String mUsuario;
    private OportunidadDetalle mOportunidadDetalle;
    private User mAsignedTo;
    private Cuenta mAccount;
    private Campana mCampaign;
    private String mTipo, mEtapa, mCuenta, mFechaCierre, mCampana, mMedio, mAsignado, mEnergia, mComunicaciones, mIluminacion;

    /**
     * UI References.
     */
    private Toolbar mOpportunityToolbar;
    private ImageButton mImageButtonGuardar;
    private LinearLayout mLayoutContenido;
    private static TextView mValorFechaCierre;
    private TextView mValorCuenta, mValorCampana, mValorAsignadoA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_opportunity);

        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Variable Global
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        mUrl = globalVariable.getUrl();
        Log.d(TAG, mUrl);

        Intent intent = getIntent();
        mOportunidadDetalle = intent.getParcelableExtra(Info.OPORTUNIDAD_SELECCIONADA.name());
        Log.d(TAG, "Id oportunidad " + mOportunidadDetalle.getId());

        // Main Toolbar
        mOpportunityToolbar = (Toolbar) findViewById(R.id.toolbar_opportunity);
        setSupportActionBar(mOpportunityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mImageButtonGuardar = (ImageButton) findViewById(R.id.ic_ok);

        // Contenido
        mLayoutContenido = (LinearLayout) findViewById(R.id.layout_contenido);

        // Valores Llamada
        ponerValores(mOportunidadDetalle);
    }

    public void ponerValores(OportunidadDetalle oportunidadDetalle) {
        // Nombre
        final EditText valorNombre = (EditText) findViewById(R.id.valor_nombre);
        valorNombre.setText(oportunidadDetalle.getName());
        // Tipo
        //TODO ajustar estas listas que se obtengan del backend
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
        valorTipo.setSelection(listTipo.indexOf(oportunidadDetalle.getTipo_c()));
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
        valorEtapa.setSelection(listEtapa.indexOf(oportunidadDetalle.getSales_stage()));
        // Cuenta
        mValorCuenta = (TextView) findViewById(R.id.valor_cuenta);
        mValorCuenta.setText(oportunidadDetalle.getNameAccount());
        // Usuario Final
        final EditText valorUsuario = (EditText) findViewById(R.id.valor_usuario);
        valorUsuario.setText(oportunidadDetalle.getUsuario_final_c());
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
        mValorFechaCierre.setText(oportunidadDetalle.getDate_closed());
        final EditText valorEstimado = (EditText) findViewById(R.id.valor_estimado);
        valorEstimado.setText(oportunidadDetalle.getValoroportunidad_c());
        final EditText valorProbabilidad = (EditText) findViewById(R.id.valor_probabilidad);
        valorProbabilidad.setText(oportunidadDetalle.getProbability());
        // Campana
        mValorCampana = (TextView) findViewById(R.id.valor_campana);
        mValorCampana.setText(oportunidadDetalle.getNameCampaign());
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
        valorMedio.setSelection(listMedio.indexOf(oportunidadDetalle.getMedio_c()));
        // Fuente
        TextView valorFuente = (TextView) findViewById(R.id.valor_fuente);
        valorFuente.setText(oportunidadDetalle.getFuente_c());
        // Paso
        final EditText valorPaso = (EditText) findViewById(R.id.valor_paso);
        valorPaso.setText(oportunidadDetalle.getNext_step());
        // Descripcion
        final EditText valorDescripcion = (EditText) findViewById(R.id.valor_descripcion);
        valorDescripcion.setText(oportunidadDetalle.getDescription());
        // Asignado a
        mValorAsignadoA = (TextView) findViewById(R.id.valor_asignado_a);
        mValorAsignadoA.setText(oportunidadDetalle.getAssigned_user_name());
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
        valorEnergia.setSelection(listEnergia.indexOf(oportunidadDetalle.getEnergia_c()));
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
        valorComunicaciones.setSelection(listComunicaciones.indexOf(oportunidadDetalle.getComunicaciones_c()));
        // Iluminacion
        final Spinner valorIluminacion = (Spinner) findViewById(R.id.valor_iluminacion);
        List<String> listIluminacion = new ArrayList<>();
        listIluminacion.add("Seleccionar marca iluminacion");
        listIluminacion.add("VCP");
        ArrayAdapter<String> iluminacionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, listIluminacion);
        iluminacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        valorIluminacion.setAdapter(iluminacionAdapter);
        valorIluminacion.setSelection(listIluminacion.indexOf(oportunidadDetalle.getIluminacion_c()));

        // Eventos
        mValorAsignadoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentSeleccionar = new Intent(EditOpportunityActivity.this,
//                        SelectUserActivity.class);
//                startActivityForResult(intentSeleccionar, USER_SELECT);
            }
        });

        mValorCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentSeleccionar = new Intent(EditOpportunityActivity.this,
//                        SelectAccountActivity.class);
//                startActivityForResult(intentSeleccionar, ACCOUNT_SELECT);
            }
        });

        mValorCampana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentSeleccionar = new Intent(EditOpportunityActivity.this,
//                        SelectCampaignActivity.class);
//                startActivityForResult(intentSeleccionar, CAMPAIGN_SELECT);
            }
        });

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
                    mCuenta = mAccount.getId();
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
                    mCampana = mCampaign.getId();
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
                    mAsignado = mAsignedTo.getId();
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

                mTareaEditarOportunidad = new EditOpportunityTask();
                // TODO: Cambiar id usuario logueado
                mUsuario = "2d644d36-14d9-9722-d62e-55269fa6a81d";

                // Crear oportunidad
                mTareaEditarOportunidad.execute(valorNombre.getText().toString(), valorUsuario.getText().toString(), valorEstimado.getText().toString(), valorProbabilidad.getText().toString(), valorPaso.getText().toString(), valorDescripcion.getText().toString(), mTipo, mEtapa, mCuenta, mFechaCierre, mCampana, mMedio, mAsignado, mEnergia, mComunicaciones, mIluminacion, mUsuario, mUsuario);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == USER_SELECT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mAsignedTo = data.getParcelableExtra("result");
                mValorAsignadoA.setText(mAsignedTo.getUser_name());
                mAsignado = mAsignedTo.getId();
            }
        } else if (requestCode == ACCOUNT_SELECT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mAccount = data.getParcelableExtra("result");
                mValorCuenta.setText(mAccount.getName());
                mCuenta = mAccount.getId();
            }
        } else if (requestCode == CAMPAIGN_SELECT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                mCampaign = data.getParcelableExtra("result");
                mValorCampana.setText(mCampaign.getName());
                mCampana = mCampaign.getId();
            }
        }
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
     * Clase para el mensaje de edicion de oportunidad.
     */
    @SuppressLint("ValidFragment")
    public class EditarOpportunityDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.dialog_edit_activity_opportunity)
                    .setMessage(R.string.dialog_edit_message_opportunity)
                    .setPositiveButton(R.string.dialog_ok_opportunity, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            EditOpportunityActivity.this.finish();
                        }
                    });

            return builder.create();
        }
    }

    /**
     * Representa una tarea asincrona de edicion de oportunidad.
     */
    public class EditOpportunityTask extends AsyncTask<String, Void, Boolean> {

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
                HttpPut httpPutOpportunity = new HttpPut(mUrl
                        + "editOpportunity");
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
            mTareaEditarOportunidad = null;

            if (success) {
                Log.d(TAG, "Crear Oportunidad exitoso");
                EditarOpportunityDialogFragment newFragment = new EditarOpportunityDialogFragment();
                newFragment.show(getFragmentManager(), "createOpportunity");
            } else {
                Log.d(TAG, "Crear Oportunidad error");
            }
        }

        @Override
        protected void onCancelled() {
            mTareaEditarOportunidad = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
