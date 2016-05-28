package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activities.ui.SlidingTabLayout;
import com.co.iatech.crm.sugarmovil.activtities.modules.AccountsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.ViewPagerAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;
import com.software.shell.fab.ActionButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class AccountActivity extends AccountsModuleActions implements View.OnClickListener{

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
    	
		String[] params = { "idAccount", idAccount };
		this.executeTask(params, TypeInfoServer.getAccount);
   
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
		ActivitiesMediator.getInstance().showList(AccountActivity.this, module, MODULE);
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
	public void applyActions() {	
		imgButtonEdit = (ImageButton) findViewById(R.id.ic_edit);       
		imgButtonEdit.setVisibility(View.INVISIBLE);
    	
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
	public void chargeViewInfo() {
	    ListsHolder.clear(ListsHolderType.CONTACTS_ACCOUNTS);
	   viewAdapter.setActualAccount(selectedBean);
  	   viewPager.setAdapter(viewAdapter);
  	   slidingTabLayout.setViewPager(viewPager);
		
	}
	
	   
	@Override
	public void addInfo(String serverResponse) {
		try {
			JSONObject jObj = new JSONObject(serverResponse);
			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
			
			if (jArr.length() > 0) {
				JSONObject obj = jArr.getJSONObject(0);
				selectedBean = new CuentaDetalle(obj);
				chargeViewInfo();
			}
			
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}
		
	}


}
