package com.co.iatech.crm.sugarmovil.fragments;


import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.OpportunitiesModule;
import com.co.iatech.crm.sugarmovil.activtities.modules.OpportunitiesModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModule;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;
import com.software.shell.fab.ActionButton;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

public class OpportunitiesFragment extends Fragment implements IMovilModuleActions,OpportunitiesModule {
    /**
     * Debug.
     */
    private static final String TAG = "OpportunitiesFragment";

    /**
     * Tasks.
     */
    private GetOpportunitiesTask mTareaObtenerOportunidades = null;

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
    private RecyclerView mRecyclerViewOpportunities;
    private RecyclerView.Adapter mRecyclerViewOpportunitiesAdapter;
    private RecyclerView.LayoutManager mRecyclerViewOpportunitiesLayoutManager;
    private ActionButton actionButton;


    public OpportunitiesFragment() {
        // Required empty public constructor
    }

    /*
    * Nueva Instancia de OpportunitiesFragment.
    */
    public static OpportunitiesFragment newInstance() {
        OpportunitiesFragment fragment = new OpportunitiesFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_opportunities, container, false);

        // Variable Global
        mGlobalVariable = (GlobalClass) getActivity()
                .getApplicationContext();
        mGlobalVariable.setSelectedItem(2);

        // Main Toolbar
        mMainTextView = ((MainActivity) getActivity()).getMainTextView();
        mMainTextView.setText("OPORTUNIDADES");
        mMainTextView.setVisibility(View.VISIBLE);
        mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
        mMainSearchView.setVisibility(View.VISIBLE);

        // Recycler
        mRecyclerViewOpportunities = (RecyclerView) mRootView.findViewById(R.id.recycler_view_opportunities);
        mRecyclerViewOpportunities.setHasFixedSize(true);

        mRecyclerViewOpportunitiesLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewOpportunities.setLayoutManager(mRecyclerViewOpportunitiesLayoutManager);

        

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
                    ((RecyclerGenericAdapter) mRecyclerViewOpportunities.getAdapter()).flushFilter();
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
                    // Filtro para oportunidades
                    ((RecyclerGenericAdapter) mRecyclerViewOpportunities.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error a�adiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para oportunidades
                    ((RecyclerGenericAdapter) mRecyclerViewOpportunities.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });
     
        this.applyActions();
        if( !DataManager.getInstance().IsSynchronized(MODULE) ){
        	// Tarea para consultar oportunidades
        	Log.d(TAG,"Cargando Oportunidades desde BACKEND");
	        mTareaObtenerOportunidades = new GetOpportunitiesTask();
	        mTareaObtenerOportunidades.execute();
        
        }else{
        	Log.d(TAG,"Cargando Oportunidades desde MEMORIA");
        	chargeViewInfo();
        }
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
    }

    @Override
    public void onPause() {
        super.onPause();
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


	public void applyActions() {
		actionButton = (ActionButton) mRootView.findViewById(R.id.action_button); 
		GlobalClass gc =(GlobalClass) getActivity().getApplicationContext();
		ActionsStrategy.definePermittedActions(this, this.getActivity(), gc);
	}
   
	@Override
	public boolean chargeIdPreviousModule() {
		return false;
	}

    /**
     * Representa una tarea asincrona de obtencion de oportunidades.
     */
    public class GetOpportunitiesTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando oportunidades...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String resultado = null;
                ControlConnection.addCurrentUser(getActivity());
                // Intento de obtener oportunidades

                resultado  = ControlConnection.getInfo(TypeInfoServer.getOpportunities, getActivity());
                DataManager.getInstance().opportunitiesInfo.clear();

                JSONObject jObj = new JSONObject(resultado);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    DataManager.getInstance().opportunitiesInfo.add(new Oportunidad(obj));
                }
               // ListsHolder.saveList(ListsHolderType.OPPORTUNITIES, DataManager.getInstance().opportunitiesInfo);
                DataManager.getInstance().defSynchronize(MODULE);
                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Oportunidades Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerOportunidades = null;
            progressDialog.dismiss();

            if (success) {
                if (DataManager.getInstance().opportunitiesInfo.size() > 0) {
                	chargeViewInfo();
                } else {
                    Log.d(TAG,
                            "No hay Oportunidades: "
                                    + DataManager.getInstance().opportunitiesInfo.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerOportunidades = null;
            Log.d(TAG, "Cancelado ");
        }
    }

	public void chargeViewInfo() {
	    mRecyclerViewOpportunitiesAdapter = new RecyclerGenericAdapter(getActivity(), 
	    		AdapterSearchUtil.transform(DataManager.getInstance().opportunitiesInfo), MODULE);
        mRecyclerViewOpportunities.setAdapter(mRecyclerViewOpportunitiesAdapter);
		
	}
	
	@Override
	public void addInfo(String serverResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getInfoFromMediator() {
		// TODO Auto-generated method stub
		
	}


}
