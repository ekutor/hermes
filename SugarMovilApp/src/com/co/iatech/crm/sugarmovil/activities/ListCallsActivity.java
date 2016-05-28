package com.co.iatech.crm.sugarmovil.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.software.shell.fab.ActionButton;
import com.squareup.picasso.Picasso;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


public class ListCallsActivity extends CallsModuleActions {

    /**
     * Member Variables.
     */
    private ArrayList<Call> callsXParent;

    /**
     * UI References.
     */
    private Toolbar mToolbar;
    private TextView mToolbarTextView;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    
    private ActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_call);
        try{
        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        callsXParent = new ArrayList<Call>();
        getInfoFromMediator();
        
        // Main Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_list_call);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        mToolbarTextView = (TextView) findViewById(R.id.text_toolbar_list_call);

        // SearchView
        mSearchView = (SearchView) findViewById(R.id.search_view_list_call);
        int searchPlateId = mSearchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = mSearchView.findViewById(searchPlateId);
        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView searchIcon = (ImageView) mSearchView.findViewById(searchImgId);
        searchIcon.getLayoutParams().height = 56;
        searchIcon.getLayoutParams().width = 56;
        searchIcon.requestLayout();
        Picasso.with(getApplicationContext()).load(R.drawable.ic_search).resize(56, 56).into(searchIcon);
        int closeSearchImgId = getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeSearchIcon = (ImageView) mSearchView.findViewById(closeSearchImgId);
        closeSearchIcon.getLayoutParams().height = 56;
        closeSearchIcon.getLayoutParams().width = 56;
        closeSearchIcon.requestLayout();
        Picasso.with(getApplicationContext()).load(R.drawable.ic_close_search).resize(56, 56).into(closeSearchIcon);
        int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
        searchText.setTextColor(Color.WHITE);
        searchText.setHintTextColor(Color.GRAY);

        // Recycler
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_call);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerViewLayoutManager = new LinearLayoutManager(ListCallsActivity.this);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);

        // Eventos
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToolbarTextView.setVisibility(View.GONE);
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mToolbarTextView.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager) ListCallsActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);

                try {
                    ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).flushFilter();
                } catch (Exception e) {
                   
                }

                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    // Filtro para select
                    ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para select
                    ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    
                }

                return false;
            }
        });
        
        this.applyActions();
        }catch(Exception e){
        	Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE );
        }
    }
    
    
    @Override
	public void chargeViewInfo() {
    	
        TypeInfoServer infoServer = null;
        String message = "",keyID= "";

        switch(actualInfo.getActualParentModule()){
			case ACCOUNTS:
				infoServer = TypeInfoServer.getAccountCalls;
				keyID = "idAccount";
				message = "Cargando Llamadas x Cuenta...";
				break;
			case OPPORTUNITIES:
				infoServer = TypeInfoServer.getAccountCalls;
				keyID = "idOpportunity";
				message = "Cargando Tareas x Oportunidad...";
				break;
			case CONTACTS:
				infoServer = TypeInfoServer.getContactCalls;
				keyID = "idContact";
				message = "Cargando Llamadas x Contacto...";
				break;
			default:
					break;
		}
      
		
        if(infoServer != null){
	        String[] params = { keyID, actualInfo.getActualParentInfo().id };
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
			callsXParent.clear();
			for (int i = 0; i < jArr.length(); i++) {
				JSONObject obj = jArr.getJSONObject(i);
				callsXParent.add(new Call(obj));
			}
			if (callsXParent.size() > 0) {

				RecyclerView.Adapter rv = new RecyclerGenericAdapter(this.getApplicationContext(),
						AdapterSearchUtil.transform(callsXParent), MODULE);
				this.mRecyclerView.setAdapter(rv);
			} else {
				Message.showShort("No tiene Llamadas asociadas", getApplicationContext());
			}
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}
	}

}
