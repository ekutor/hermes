package com.co.iatech.crm.sugarmovil.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.squareup.picasso.Picasso;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


public class ListContactActivity extends AppCompatActivity {
    /**
     * Debug.
     */
    private static final String TAG = "ListContactActivity";

    /**
     * Tasks.
     */
    private GetContactsTask mTareaObtenerContactos = null;

    /**
     * Member Variables.
     */

    private String idCuentaActual;
    private Contacto mContacto;
    private ArrayList<Contacto> mContactsArray = new ArrayList<Contacto>();

    /**
     * UI References.
     */
    private Toolbar mToolbar;
    private TextView mToolbarTextView;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        // SoftKey
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        Intent intent = getIntent();
        idCuentaActual = intent.getStringExtra(Modules.ACCOUNTS.name());
        Log.d(TAG, "Id cuenta " + idCuentaActual);

        // Main Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar_list_contact);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        mToolbarTextView = (TextView) findViewById(R.id.text_toolbar_list_contact);

        // SearchView
        mSearchView = (SearchView) findViewById(R.id.search_view_list_contact);
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
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_contact);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerViewLayoutManager = new LinearLayoutManager(ListContactActivity.this);
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

                InputMethodManager imm = (InputMethodManager) ListContactActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);

                try {
                    ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).flushFilter();
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
                    ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para select
                    ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });

    }

    
    public void seleccionarContacto(Contacto contact) {
        mContacto = contact;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", mContacto);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    
	@Override
	protected void onResume() {
		this.chargeListInfo();
		super.onResume();
	}

    private void chargeListInfo() {
    	  // Tarea obtener select
        mTareaObtenerContactos = new GetContactsTask();
        mTareaObtenerContactos.execute(idCuentaActual);
		
	}

	/**
     * Representa una tarea asincrona de obtencion de contactos.
     */
    public class GetContactsTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ListContactActivity.this, ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando contactos...");
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
                resultado  = ControlConnection.getInfo(TypeInfoServer.getContactsxAccount, ListContactActivity.this);
               
                mContactsArray.clear();

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    mContactsArray.add(new Contacto(obj));
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
            mTareaObtenerContactos = null;
           

            if (success) {
                if (mContactsArray.size() > 0) {
                    mRecyclerViewAdapter = new RecyclerGenericAdapter(ListContactActivity.this,
                    		AdapterSearchUtil.transform(mContactsArray), Modules.CONTACTS);
                    mRecyclerView.setAdapter(mRecyclerViewAdapter);
                } else {
                	progressDialog.setMessage("Esta cuenta no tiene contactos asociados.");
                    Log.d(TAG,
                            "No hay valores: "
                                    + mContactsArray.size());
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
            mTareaObtenerContactos = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
