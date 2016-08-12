package com.co.iatech.crm.sugarmovil.fragments;


import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.TasksModule;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
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

public class TasksFragment extends Fragment implements IMovilModuleActions,TasksModule {

    /**
     * Debug.
     */
    private static final String TAG = "TasksFragment";

    /**
     * Tasks.
     */
    private GetTasksTask obtenerTareas = null;

    /**
     * Member Variables.
     */
    private GlobalClass mGlobalVariable;
    private String mUrl;

    /**
     * UI References.
     */
    private View mRootView;
    private TextView mMainTextView;
    private SearchView mMainSearchView;
    private RecyclerView mRecyclerViewTasks;
    private RecyclerView.Adapter mRecyclerViewTasksAdapter;
    private RecyclerView.LayoutManager mRecyclerViewTasksLayoutManager;
    private ActionButton actionButton;

    public TasksFragment() {
        // Required empty public constructor
    }

    /*
    * Nueva Instancia de TasksFragment.
    */
    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_tasks, container, false);

        // Variable Global
        mGlobalVariable = (GlobalClass) getActivity()
                .getApplicationContext();
        mGlobalVariable.setSelectedItem(6);

        // Main Toolbar
        mMainTextView = ((MainActivity) getActivity()).getMainTextView();
        mMainTextView.setText("TAREAS");
        mMainTextView.setVisibility(View.VISIBLE);
        mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
        mMainSearchView.setVisibility(View.VISIBLE);

        // Recycler
        mRecyclerViewTasks = (RecyclerView) mRootView.findViewById(R.id.recycler_view_tasks);
        mRecyclerViewTasks.setHasFixedSize(true);

        mRecyclerViewTasksLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewTasks.setLayoutManager(mRecyclerViewTasksLayoutManager);


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
                    ((RecyclerGenericAdapter) mRecyclerViewTasks.getAdapter()).flushFilter();
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
                    // Filtro para cuentas
                    ((RecyclerGenericAdapter) mRecyclerViewTasks.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error aÃ±adiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para cuentas
                    ((RecyclerGenericAdapter) mRecyclerViewTasks.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });

        this.applyActions();
        
        if( !DataManager.getInstance().IsSynchronized(MODULE) ){
        	// Tarea para consultar llamadas
        	Log.d(TAG,"Cargando Contactos desde BACKEND");
        	 // Tarea para consultar llamadas
        	 // Tarea para consultar tares
            obtenerTareas = new GetTasksTask();
            obtenerTareas.execute();
            
        }else{
        	Log.d(TAG,"Cargando Contactos desde MEMORIA");
        	chargeViewInfo();
        }
        return mRootView;
    }

    @Override
    public void chargeViewInfo() {
       mRecyclerViewTasksAdapter = new RecyclerGenericAdapter(getActivity(), 
    		   AdapterSearchUtil.transform(DataManager.getInstance().tasksInfo), MODULE);
 	   mRecyclerViewTasks.setAdapter(mRecyclerViewTasksAdapter);
		
	}
    @Override
	public boolean chargeIdPreviousModule() {
		return false;
	}

	@Override
    public void onResume() {
        Log.d(TAG, "onResume Fragment Contacts");
        super.onResume();
        mMainSearchView.clearFocus();
        try {
            mMainSearchView.setIconified(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chargeViewInfo();
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

    /**
     * Representa una tarea asincrona de obtencion de tareas.
     */
    public class GetTasksTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando tareas...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String tasks;
                
                ControlConnection.addCurrentUser(getActivity());
                // Intento de obtener tareas

                tasks  = ControlConnection.getInfo(TypeInfoServer.getTasks, getActivity());
            
                DataManager.getInstance().tasksInfo.clear();

                JSONObject jObj = new JSONObject(tasks);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    String id = obj.getString("id");
                    String name = obj.getString("name");
                    DataManager.getInstance().tasksInfo.add(new DetailTask(id, name));
                }
                DataManager.getInstance().defSynchronize(MODULE);
                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Tareas Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            obtenerTareas = null;
            progressDialog.dismiss();

            if (success) {
                if (DataManager.getInstance().tasksInfo.size() > 0) {
                	chargeViewInfo();
                } else {
                    Log.d(TAG,
                            "No hay Tareas: "
                                    + DataManager.getInstance().tasksInfo.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            obtenerTareas = null;
            Log.d(TAG, "Cancelado ");
        }
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
