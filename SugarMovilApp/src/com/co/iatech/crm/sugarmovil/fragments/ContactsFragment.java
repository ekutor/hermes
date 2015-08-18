package com.co.iatech.crm.sugarmovil.fragments;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerContactsAdapter;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.software.shell.fab.ActionButton;

public class ContactsFragment extends Fragment {
    /**
     * Debug.
     */
    private static final String TAG = "ContactsFragment";

    /**
     * Tasks.
     */
    private GetContactsTask mTareaObtenerContactos = null;

    /**
     * Member Variables.
     */
    private GlobalClass mGlobalVariable;
    private String mUrl;
    private ArrayList<Contacto> mContactsArray = new ArrayList<>();

    /**
     * UI References.
     */
    private View mRootView;
    private TextView mMainTextView;
    private SearchView mMainSearchView;
    private RecyclerView mRecyclerViewContacts;
    private RecyclerView.Adapter mRecyclerViewContactsAdapter;
    private RecyclerView.LayoutManager mRecyclerViewContactsLayoutManager;
    private ActionButton mActionButton;

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
        mUrl = mGlobalVariable.getUrl();
        mGlobalVariable.setmSelectedButton(1);

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

        // Action Button
//        mActionButton = (ActionButton) mRootView.findViewById(R.id.action_button);

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

//        mActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create Contact Activity
//                Intent intentCrearContacto = new Intent(getActivity(),
//                        AddContactActivity.class);
//                getActivity().startActivity(intentCrearContacto);
//            }
//        });

        // Tarea para consultar contactos
        mTareaObtenerContactos = new GetContactsTask();
        mTareaObtenerContactos.execute();

        return mRootView;
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

        // Tarea para consultar contactos
        mTareaObtenerContactos = new GetContactsTask();
        mTareaObtenerContactos.execute();
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
                String contacts = null;

                // Intento de obtener contactos
                HttpClient httpClientContacts = new DefaultHttpClient();
                HttpGet httpGetContacts = new HttpGet(mUrl
                        + "getContacts");

                try {
                    HttpResponse response = httpClientContacts
                            .execute(httpGetContacts);
                    contacts = EntityUtils.toString(response
                            .getEntity());
                    contacts = contacts.replace("\n", "")
                            .replace("\r", "");
                    Log.d(TAG, "Contactos Response: "
                            + contacts);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                mContactsArray.clear();

                JSONObject jObj = new JSONObject(contacts);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    mContactsArray.add(new Contacto(obj));
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
            mTareaObtenerContactos = null;
            progressDialog.dismiss();

            if (success) {
                if (mContactsArray.size() > 0) {
                    mRecyclerViewContactsAdapter = new RecyclerContactsAdapter(getActivity(), mContactsArray);
                    mRecyclerViewContacts.setAdapter(mRecyclerViewContactsAdapter);
                } else {
                    Log.d(TAG,
                            "No hay Contactos: "
                                    + mContactsArray.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerContactos = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
