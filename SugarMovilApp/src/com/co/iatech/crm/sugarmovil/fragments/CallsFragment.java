package com.co.iatech.crm.sugarmovil.fragments;


import java.util.ArrayList;

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
import com.co.iatech.crm.sugarmovil.adapters.RecyclerCallsAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.software.shell.fab.ActionButton;

public class CallsFragment extends Fragment {
    /**
     * Debug.
     */
    private static final String TAG = "CallsFragment";

    /**
     * Tasks.
     */
    private GetCallsTask mTareaObtenerLlamadas = null;

    /**
     * Member Variables.
     */
    private GlobalClass mGlobalVariable;
    private ArrayList<Llamada> mCallsArray = new ArrayList<>();

    /**
     * UI References.
     */
    private View mRootView;
    private TextView mMainTextView;
    private SearchView mMainSearchView;
    private RecyclerView mRecyclerViewCalls;
    private RecyclerView.Adapter mRecyclerViewCallsAdapter;
    private RecyclerView.LayoutManager mRecyclerViewCallsLayoutManager;
    private ActionButton mActionButton;

    public CallsFragment() {
        // Required empty public constructor
    }

    /*
    * Nueva Instancia de CallsFragment.
    */
    public static CallsFragment newInstance() {
        CallsFragment fragment = new CallsFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_calls, container, false);

        // Variable Global
        mGlobalVariable = (GlobalClass) getActivity()
                .getApplicationContext();

        mGlobalVariable.setmSelectedButton(3);

        // Main Toolbar
        mMainTextView = ((MainActivity) getActivity()).getMainTextView();
        mMainTextView.setText("LLAMADAS");
        mMainTextView.setVisibility(View.VISIBLE);
        mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
        mMainSearchView.setVisibility(View.VISIBLE);

        // Recycler
        mRecyclerViewCalls = (RecyclerView) mRootView.findViewById(R.id.recycler_view_calls);
        mRecyclerViewCalls.setHasFixedSize(true);

        mRecyclerViewCallsLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewCalls.setLayoutManager(mRecyclerViewCallsLayoutManager);

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
                    ((RecyclerCallsAdapter) mRecyclerViewCalls.getAdapter()).flushFilter();
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });

        mMainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    // Filtro para llamadas
                    ((RecyclerCallsAdapter) mRecyclerViewCalls.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error aÃ±adiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para llamadas
                    ((RecyclerCallsAdapter) mRecyclerViewCalls.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });

//        mActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create Client Activity
//                Intent intentCrearLlamada = new Intent(getActivity(),
//                        AddCallActivity.class);
//                getActivity().startActivity(intentCrearLlamada);
//            }
//        });

        // Tarea para consultar llamadas
        mTareaObtenerLlamadas = new GetCallsTask();
        mTareaObtenerLlamadas.execute();

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

        // Tarea para consultar llamadas
        mTareaObtenerLlamadas = new GetCallsTask();
        mTareaObtenerLlamadas.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Representa una tarea asincrona de obtencion de oportunidades.
     */
    public class GetCallsTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando llamadas...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String resultado = null;

                // Intento de obtener llamadas

                resultado  = ControlConnection.getInfo(TypeInfoServer.getCalls);
            
                mCallsArray.clear();

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    mCallsArray.add(new Llamada(obj));
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Llamadas Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerLlamadas = null;
            progressDialog.dismiss();

            if (success) {
                if (mCallsArray.size() > 0) {
                    mRecyclerViewCallsAdapter = new RecyclerCallsAdapter(getActivity(), mCallsArray);
                    mRecyclerViewCalls.setAdapter(mRecyclerViewCallsAdapter);
                } else {
                    Log.d(TAG,
                            "No hay Llamadas: "
                                    + mCallsArray.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerLlamadas = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
