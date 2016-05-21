package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.SlidingTabLayout;
import com.co.iatech.crm.sugarmovil.activtities.modules.AccountsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.ViewPagerAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.software.shell.fab.ActionButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class AccountActivity extends AppCompatActivity implements View.OnClickListener, AccountsModuleActions{

    /**
     * Debug.
     */
    private static final String TAG = "AccountActivity";

    /**
     * Member Variables.
     */
   
    private ViewPagerAdapter viewAdapter;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
  

    /**
     * UI References.
     */
    private Toolbar mCuentaToolbar;
    private ImageButton imageButtonEdit;
 
    private ImageButton imageButtonContacts;
    private ImageButton imageButtonOpps;
    private ImageButton imageButtonTasks;
    private ImageButton imageButtonCalls;
    private String idAccount;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_activity_account);

        Intent intent = getIntent();
        idAccount= intent.getStringExtra(MODULE.name());       
                
        mCuentaToolbar = (Toolbar) findViewById(R.id.toolbar_account);
     	setSupportActionBar(mCuentaToolbar);
     	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
    	slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.abc_search_url_text));   
    	//mSlidingTabLayout.setDistributeEvenly(true);
    	
              
       this.applyActions();

        GetAccountTask mTareaObtenerCuenta = new GetAccountTask();
        mTareaObtenerCuenta.execute(idAccount);

    }
    

	@Override
	public void onClick(View v) {
		
		Modules module = null;
		if(v.getId() == imageButtonContacts.getId()){
			Log.d(TAG, "Contactos x cuenta ");
			module = Modules.CONTACTS;
		}else if(v.getId() == imageButtonOpps.getId()){
			Log.d(TAG, "Oportunidades X Cuenta ");
			module = Modules.OPPORTUNITIES;
		}else if(v.getId() == imageButtonTasks.getId()){
			Log.d(TAG, "Tareas X Cuenta ");
			module = Modules.TASKS;
		}else if(v.getId() == imageButtonCalls.getId()){
			Log.d(TAG, "Llamadas X Cuenta ");
			module = Modules.CALLS;
		}
		ActivitiesMediator.getInstance().setActualID(idAccount, MODULE);
		ActivitiesMediator.getInstance().showList(AccountActivity.this, module, true);
	}


	@Override
	protected void onResume() {
		ActivitiesMediator.getInstance().setActualID(idAccount, MODULE);
		super.onResume();
	}



	/**
    * Representa una tarea asincrona de obtencion de cuenta.
    */
   public class GetAccountTask extends AsyncTask<String, Void, Boolean> {
       private ProgressDialog progressDialog;
       private CuentaDetalle cuentaActual;
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           progressDialog = new ProgressDialog(AccountActivity.this, ProgressDialog.THEME_HOLO_DARK);
           progressDialog.setMessage("Cargando informacion de cuenta...");
           progressDialog.setIndeterminate(true);
           progressDialog.show();
       }

       @Override
       protected Boolean doInBackground(String... params) {
           try {
               // Parametros
               String idAccount = params[0];

               // Respuesta
               String account = null;

               // Intento de obtener cuenta
               ControlConnection.addHeader("idAccount", idAccount);
               account  = ControlConnection.getInfo(TypeInfoServer.getAccount, AccountActivity.this);
               JSONObject jObj = new JSONObject(account);

               JSONArray jArr = jObj.getJSONArray("results");
               for (int i = 0; i < jArr.length(); i++) {
                   JSONObject obj = jArr.getJSONObject(i);
                  
                   cuentaActual = new CuentaDetalle(obj);
                   
               }

               return true;
           } catch (Exception e) {
               Log.d(TAG, "Buscar Cuenta Error: "
                       + e.getClass().getName() + ":" + e.getMessage());
               return false;
           }
       }

       @Override
       protected void onPostExecute(final Boolean success) {
    	   
           progressDialog.dismiss();

           if (success) {
        	   viewAdapter.setActualAccount(cuentaActual);
        	   viewPager.setAdapter(viewAdapter);
        	   slidingTabLayout.setViewPager(viewPager);
           }
       }

       @Override
       protected void onCancelled() {
          // mTareaObtenerCuenta = null;
           Log.d(TAG, "Cancelado ");
       }
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
		return null;
	}


	@Override
	public Parcelable getBean() {
		return null;
	}

	@Override
	public void applyActions() {
		imageButtonEdit = (ImageButton) findViewById(R.id.ic_edit); 
		imageButtonEdit.setVisibility(View.INVISIBLE);
    	
      //ToolBar Opciones
		imageButtonContacts = (ImageButton) findViewById(R.id.image_contacts);
        imageButtonContacts.setOnClickListener(this);
        
        imageButtonOpps = (ImageButton) findViewById(R.id.image_opportunities);
        imageButtonOpps.setOnClickListener(this);
        
        imageButtonTasks =  (ImageButton) findViewById(R.id.image_tasks);
        imageButtonTasks.setOnClickListener(this);
        
        imageButtonCalls =  (ImageButton) findViewById(R.id.image_calls);
        imageButtonCalls.setOnClickListener(this);
	    
        GlobalClass gc = (GlobalClass)getApplicationContext();
        ActionsStrategy.definePermittedActions(this, gc);

	}


	@Override
	public boolean chargeIdPreviousModule() {
		return true;
	}
}
