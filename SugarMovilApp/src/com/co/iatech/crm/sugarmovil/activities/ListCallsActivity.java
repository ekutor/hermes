package com.co.iatech.crm.sugarmovil.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
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

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.CallsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerCallsAdapter;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerContactsAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.software.shell.fab.ActionButton;
import com.squareup.picasso.Picasso;


public class ListCallsActivity extends AppCompatActivity implements CallsModuleActions {
    /**
     * Debug.
     */
    private static final String TAG = "ListCallsActivity";

    /**
     * Tasks.
     */
    private GetCallsxAccountTask mTareaObtenerLlamadas = null;

    /**
     * Member Variables.
     */
    private String idCuentaActual;
    private ArrayList<Llamada> LlamadasXAccount = new ArrayList<Llamada>();

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

        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        Intent intent = getIntent();
        idCuentaActual = intent.getStringExtra(Info.ID.name());
        Log.d(TAG, "Id cuenta " + idCuentaActual);

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
                    ((RecyclerContactsAdapter) mRecyclerView.getAdapter()).flushFilter();
                } catch (Exception e) {
                    Log.d(TAG, "Error removiendo el filtro de busqueda");
                }

                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    // Filtro para select
                    ((RecyclerContactsAdapter) mRecyclerView.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para select
                    ((RecyclerContactsAdapter) mRecyclerView.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });
        
        this.applyActions();
        mTareaObtenerLlamadas= new GetCallsxAccountTask();
        mTareaObtenerLlamadas.execute(idCuentaActual);
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
   	public Modules getModule() {
   		return MODULE;
   	}


   	@Override
   	public String getAssignedUser() {
   		return "";
   	}


   	@Override
   	public Parcelable getBean() {
   		return null;
   	}


   	@Override
   	public void applyActions() {
   		actionButton = (ActionButton) findViewById(R.id.action_button); 
   		ActionsStrategy.definePermittedActions(this, (GlobalClass) getApplicationContext());
   	}
    @Override
    public void onBackPressed() {
    	ActivitiesMediator.getInstance().returnPrevID();
    	super.onBackPressed();
    }

    /**
     * Representa una tarea asincrona de obtencion de llamadas por cuenta.
     */
    public class GetCallsxAccountTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ListCallsActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando Llamadas x Cuenta...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
            	
            	//Params
            	
            	 String idCuenta = params[0];
            	 
                // Resultado
                String resultado = null;

                // Intento de obtener datos
                ControlConnection.addHeader("idAccount", idCuenta);
                resultado  = ControlConnection.getInfo(TypeInfoServer.getAccountCalls, ListCallsActivity.this);
                LlamadasXAccount.clear();

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    LlamadasXAccount.add(new Llamada(obj));
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
        	mTareaObtenerLlamadas = null;
           

            if (success) {
                if (LlamadasXAccount.size() > 0) {
                    mRecyclerViewAdapter = new RecyclerCallsAdapter(ListCallsActivity.this, LlamadasXAccount);
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);
                } else {
                	progressDialog.setMessage("Esta cuenta no tiene llamadas asociadas.");
                    Log.d(TAG,
                            "No hay valores: "
                                    + LlamadasXAccount.size());
                }
            }
            try {
				Thread.sleep(5000);
				progressDialog.dismiss();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
        }

        @Override
        protected void onCancelled() {
        	mTareaObtenerLlamadas = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
