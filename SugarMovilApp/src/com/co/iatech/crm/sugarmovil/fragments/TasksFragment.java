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
import com.co.iatech.crm.sugarmovil.adapters.RecyclerTasksAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.model.Tarea;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.software.shell.fab.ActionButton;

public class TasksFragment extends Fragment {

    /**
     * Debug.
     */
    private static final String TAG = "TasksFragment";

    /**
     * Tasks.
     */
    private GetTasksTask mTareaObtenerTareas = null;

    /**
     * Member Variables.
     */
    private GlobalClass mGlobalVariable;
    private String mUrl;
    private ArrayList<Tarea> mTasksArray = new ArrayList<>();

    /**
     * UI References.
     */
    private View mRootView;
    private TextView mMainTextView;
    private SearchView mMainSearchView;
    private RecyclerView mRecyclerViewTasks;
    private RecyclerView.Adapter mRecyclerViewTasksAdapter;
    private RecyclerView.LayoutManager mRecyclerViewTasksLayoutManager;
    private ActionButton mActionButton;

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
        mGlobalVariable.setmSelectedButton(6);

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
                    ((RecyclerTasksAdapter) mRecyclerViewTasks.getAdapter()).flushFilter();
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
                    ((RecyclerTasksAdapter) mRecyclerViewTasks.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    // Filtro para cuentas
                    ((RecyclerTasksAdapter) mRecyclerViewTasks.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });

//        mActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create Task Activity
//                Intent intentCrearTarrea = new Intent(getActivity(),
//                        AddTaskActivity.class);
//                getActivity().startActivity(intentCrearTarrea);
//            }
//        });

        // Tarea para consultar tares
        mTareaObtenerTareas = new GetTasksTask();
        mTareaObtenerTareas.execute();

        return mRootView;
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

        // Tarea para consultar tares
        mTareaObtenerTareas = new GetTasksTask();
        mTareaObtenerTareas.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
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

                // Intento de obtener tareas

                tasks  = ControlConnection.getInfo(TypeInfoServer.getTasks);
            
                mTasksArray.clear();

                JSONObject jObj = new JSONObject(tasks);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    String id = obj.getString("id");
                    String name = obj.getString("name");
                    mTasksArray.add(new Tarea(id, name));
                }

                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Tareas Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mTareaObtenerTareas = null;
            progressDialog.dismiss();

            if (success) {
                if (mTasksArray.size() > 0) {
                    mRecyclerViewTasksAdapter = new RecyclerTasksAdapter(getActivity(), mUrl, mTasksArray);
                    mRecyclerViewTasks.setAdapter(mRecyclerViewTasksAdapter);
                } else {
                    Log.d(TAG,
                            "No hay Tareas: "
                                    + mTasksArray.size());
                }
            }
        }

        @Override
        protected void onCancelled() {
            mTareaObtenerTareas = null;
            Log.d(TAG, "Cancelado ");
        }
    }
}
