package com.co.iatech.crm.sugarmovil.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.ContactsModule;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerContactsAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.software.shell.fab.ActionButton;

public class ContactsFragment extends Fragment implements ContactsModule{
    /**
     * Debug.
     */
    private static final String TAG = "ContactsFragment";

    /**
     * Tasks.
     */
    private GetContactsTask obtenerContactos = null;

    /**
     * Member Variables.
     */
    private GlobalClass mGlobalVariable;

    /**
     * UI References.
     */
    private View mRootView;
    private TextView mMainTextView;
    private SearchView mMainSearchView;
    private RecyclerView mRecyclerViewContacts;
    private RecyclerView.Adapter mRecyclerViewContactsAdapter;
    private RecyclerView.LayoutManager mRecyclerViewContactsLayoutManager;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /*
    * Nueva Instancia de ContactsFragment.
    */
    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Parametros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Variable Global
        mGlobalVariable = (GlobalClass) getActivity()
                .getApplicationContext();

        mGlobalVariable.setSelectedItem(1);

        // Main Toolbar
        
        mMainTextView = ((MainActivity) getActivity()).getMainTextView();
        mMainTextView.setText("CONTACTOS");
        mMainTextView.setVisibility(View.VISIBLE);

        mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
        mMainSearchView.setVisibility(View.VISIBLE);

        // Recycler
        mRecyclerViewContacts = (RecyclerView) mRootView.findViewById(R.id.recycler_view_contacts);
        mRecyclerViewContacts.setHasFixedSize(true);

        mRecyclerViewContactsLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewContacts.setLayoutManager(mRecyclerViewContactsLayoutManager);


        // Eventos
        mMainSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainTextView.setVisibility(View.GONE);
            }
        });

        mMainSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mMainTextView.setVisibility(View.VISIBLE);

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mMainSearchView.getWindowToken(), 0);
                try {
                    ((RecyclerContactsAdapter) mRecyclerViewContacts.getAdapter()).flushFilter();
                } catch (Exception e) {
                    Log.d(TAG, "Error removiendo el filtro de busqueda");
                }

                return false;
            }
        });

        mMainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    // Filtro para contactos
                    ((RecyclerContactsAdapter) mRecyclerViewContacts.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para contactos
                    ((RecyclerContactsAdapter) mRecyclerViewContacts.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });
        

        
        if(DataManager.getInstance().contactsInfo.size() <= 0){
        	// Tarea para consultar llamadas
        	Log.d(TAG,"Cargando Contactos desde BACKEND");
        	 // Tarea para consultar llamadas
        	obtenerContactos = new GetContactsTask();
            obtenerContactos.execute();
            
        }else{
        	Log.d(TAG,"Cargando Contactos desde MEMORIA");
        	showContacts();
        }
      

        return mRootView;
    }

    private void showContacts() {
    	mRecyclerViewContactsAdapter = new RecyclerContactsAdapter(getActivity(), DataManager.getInstance().contactsInfo);
        mRecyclerViewContacts.setAdapter(mRecyclerViewContactsAdapter);
	}

	@Override
    public void onResume() {
        super.onResume();
        mMainSearchView.clearFocus();
        try {
            mMainSearchView.setIconified(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        showContacts();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Representa una tarea asincrona de obtencion de contactos.
     */
    public class GetContactsTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando contactos...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String resultado = null;

                // Intento de obtener contactos

                resultado  = ControlConnection.getInfo(TypeInfoServer.getContacts, getActivity());
              
                DataManager.getInstance().contactsInfo.clear();

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    DataManager.getInstance().contactsInfo.add(new Contacto(obj));
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Contactos Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            obtenerContactos = null;
            progressDialog.dismiss();

            if (success) {
                if (DataManager.getInstance().contactsInfo.size() > 0) {
                	
                    showContacts();
                } else {
                    Log.d(TAG,
                            "No hay Contactos: "
                                    + DataManager.getInstance().contactsInfo.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            obtenerContactos = null;
            Log.d(TAG, "Cancelado ");
        }
    }


}
