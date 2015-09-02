package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;

public class LoginActivity extends FragmentActivity implements View.OnFocusChangeListener {


    /**
     * Debug.
     */
    private static final String TAG = "LoginActivity";

    /**
     * Tasks.
     */
    private LoginTask loginTask = null;

    /**
     * Member Variables.
     */
    GlobalClass globalVariable;
    private User mUsuario;
    private String mensajeAutenticacion;

    /**
     * UI References.
     */
    private View mVistaFormularioIngreso;
    private View mVistaEstadoIngreso;
    private ImageView mImageViewIcHeader;
    private EditText textUser, textPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Variable Global
        globalVariable = (GlobalClass) getApplicationContext();
       
        String android_id = Secure.getString(this.getApplicationContext().getContentResolver(),
                Secure.ANDROID_ID); 
    	
    	TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
    	
    	String device_id = tm.getDeviceId();
        
    	ControlConnection.android_id = android_id;
    	ControlConnection.device_id = device_id;
    	
        // Progress Views
        mVistaFormularioIngreso = findViewById(R.id.login);
        mVistaFormularioIngreso.setVisibility(View.VISIBLE);
        mVistaEstadoIngreso = findViewById(R.id.login_status);
        mVistaEstadoIngreso.setVisibility(View.GONE);


        textUser = (EditText) findViewById(R.id.text_user);
        textUser.setOnFocusChangeListener(this);
        textPass = (EditText) findViewById(R.id.text_password);
        textPass.setOnFocusChangeListener(this);

        // Botones Login
        Button signInButton = (Button) findViewById(R.id.login_button);

        // Eventos
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tarea login
                showProgress(true);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(textPass.getWindowToken(), 0);
                attemptLogin();
            }
        });
    }

    /**
     * Intenta iniciar sesion. Si se encuentran errores, no se hace inicio de
     * sesion y se muestran los errores.
     */
    private void attemptLogin() {
        if (loginTask != null) {
            return;
        }
        
        // Reiniciar errores
        textUser.setError(null);
        textPass.setError(null);

        // Guardar valores en el momento de intento de ingreso de usuario
        String usuario = textUser.getText().toString();
        String clave = textPass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Verificar clave valida
        if (TextUtils.isEmpty(clave)) {
        	textPass.setError(getString(R.string.error_field_required));
            focusView = textPass;
            cancel = true;
        }

        // Verificar usuario valido
        if (TextUtils.isEmpty(usuario)) {
        	textUser.setError(getString(R.string.error_field_required));
            focusView = textUser;
            cancel = true;
        }

        if (cancel) {
            // Se presento un error
            showProgress(false);
            focusView.requestFocus();
        } else {
            // TODO: Con el modelo de datos y el ws adecuado, quitar el showProgress
            showProgress(false);
            // Dispara una tarea por debajo para el ingresar usuario
            loginTask = new LoginTask();
            loginTask.execute(usuario, clave);
        }
    }

    /**
     * Muestra progreso login.
     */
    private void showProgress(final boolean show) {

        int shortAnimTime = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        mVistaEstadoIngreso.setVisibility(View.VISIBLE);
        mVistaEstadoIngreso.animate().setDuration(shortAnimTime)
                .alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mVistaEstadoIngreso.setVisibility(show ? View.VISIBLE
                        : View.GONE);
            }
        });

        mVistaFormularioIngreso.setVisibility(View.VISIBLE);
        mVistaFormularioIngreso.animate().setDuration(shortAnimTime)
                .alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mVistaFormularioIngreso.setVisibility(show ? View.GONE
                        : View.VISIBLE);
            }
        });
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v == textUser && hasFocus) {
            mVistaFormularioIngreso.scrollBy(0, mVistaFormularioIngreso.getHeight());
        }

        if (v == textPass && hasFocus) {
            mVistaFormularioIngreso.scrollBy(0, mVistaFormularioIngreso.getHeight());
        }
    }

    /**
     * Inner Class
     */
    public class LoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            // Parametros
            String user = params[0];
            String passwordUser = params[1];

            // Cifrado MD5 para consultar en la base de datos
//            MDS5 mds5 = new MDS5();
//            passwordUser = mds5.md5(passwordUser);

            // Respuesta
            String login;

            // Intento de login usuarios
            try {
            	
            	ControlConnection.addHeader("usuario", user);
            	ControlConnection.addHeader("password", passwordUser);
            	
            	login = ControlConnection.getInfo(TypeInfoServer.loginUsuarios);

                // Creacion de usuario
                JSONObject obj = new JSONObject(login);
                Boolean auth = new Boolean(obj.getString("auth"));
                if(auth){
                	mUsuario = new User(obj);
                	 return true;
                }else{
                	mensajeAutenticacion = obj.getString("message");
                	return false;
                }

               
            } catch (Exception e) {
                Log.d(TAG, "Login Usuarios Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
        	loginTask = null;

            if (success) {
                Intent intentMain = new Intent(LoginActivity.this,
                        MainActivity.class);
                ControlConnection.hash = mUsuario.getUser_hash();
                Log.d(TAG, "hash seteado: "
                        + mUsuario.getUser_hash());
                intentMain.putExtra(Info.USUARIO.name(), mUsuario);
                startActivity(intentMain);
                LoginActivity.this.finish();
            } else {
                showProgress(false);
                Context context = getApplicationContext();
                CharSequence text = mensajeAutenticacion;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.BOTTOM, 0, 80);
                toast.show();
            }
        }
    }
}
