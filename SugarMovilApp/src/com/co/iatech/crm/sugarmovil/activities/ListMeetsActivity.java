package com.co.iatech.crm.sugarmovil.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleActions;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerMeetsAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Meeting;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.software.shell.fab.ActionButton;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;


public class ListMeetsActivity extends CallsModuleActions {


    /**
     * UI References.
     */
    private Toolbar mToolbar;
    private TextView mToolbarTextView;
 
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    
    private ActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meetings);
        try{
        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
      
        getInfoFromMediator();
        
        // Main Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_list_meet);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        mToolbarTextView = (TextView) findViewById(R.id.text_toolbar_list_meet);


        // Recycler
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_meet);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerViewLayoutManager = new LinearLayoutManager(ListMeetsActivity.this);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        
        this.chargeViewInfo();
      
        this.applyActions();
        }catch(Exception e){
        	Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE );
        }
    }
    
    
    @Override
	public void chargeViewInfo() {

        TypeInfoServer infoServer = TypeInfoServer.getMeeting;
        String message = "";
        Calendar c = Utils.convertSpecialDate(actualInfo.getActualParentInfo().id);
        String m =  c.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es"));
        m = m!=null?m.toUpperCase():"";
        mToolbarTextView.setText( m + " " + c.get(Calendar.DATE) );
        if(infoServer != null){
	        String[] params = { "currentUser", ControlConnection.userId, "dateStart", actualInfo.getActualParentInfo().id };
	        this.executeTask(params, infoServer, message);
        }
	}
    

	@Override
   	public ActionButton getActionButton() {
   		return actionButton;
   	}

   	@Override
   	public ImageButton getEditButton() {
   		return null;
   	}


   	@Override
   	public void applyActions() {
   		actionButton = (ActionButton) findViewById(R.id.action_button);
   		ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());
   		actionButton.setVisibility(View.INVISIBLE);
   	}
   	
   	@Override
	protected void onResume() {
		this.chargeViewInfo();
		super.onResume();
	}

	@Override
	public void addInfo(String serverResponse) {

		try {
	  		JSONObject jObj = new JSONObject(serverResponse);
	  		
	        JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
	        
	        List<Meeting> lm = new ArrayList<Meeting>();
	        for (int i = 0; i < jArr.length(); i++) {
	            JSONObject obj = jArr.getJSONObject(i);
	            Meeting m = new Meeting(obj);
	            
            	lm.add(m);

	        }
	        
	        if (lm.size() > 0) {

				RecyclerView.Adapter rv = new RecyclerMeetsAdapter(this.getApplicationContext(),lm);
				this.mRecyclerView.setAdapter(rv);
			} else {
				Message.showShort("No tiene Reuniones asociadas", getApplicationContext());
			}
			
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}
	}

}
