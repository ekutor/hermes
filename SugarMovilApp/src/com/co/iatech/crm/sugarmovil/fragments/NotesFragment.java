package com.co.iatech.crm.sugarmovil.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActionsStrategy;
import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.NotesModule;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Notes;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

public class NotesFragment extends Fragment implements IMovilModuleActions, NotesModule {

	/**
	 * Tasks.
	 */
	private GetNotesTask obtenerNotas;

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

	public NotesFragment() {
		// Required empty public constructor
	}

	/*
	 * Nueva Instancia de TasksFragment.
	 */
	public static NotesFragment newInstance() {
		NotesFragment fragment = new NotesFragment();
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_tasks, container, false);

		// Variable Global
		mGlobalVariable = (GlobalClass) getActivity().getApplicationContext();
		mGlobalVariable.setSelectedItem(8);

		// Main Toolbar
		mMainTextView = ((MainActivity) getActivity()).getMainTextView();
		mMainTextView.setText("NOTAS");
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

				InputMethodManager imm = (InputMethodManager) getActivity()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mMainSearchView.getWindowToken(), 0);

				try {
					((RecyclerGenericAdapter) mRecyclerViewTasks.getAdapter()).flushFilter();
				} catch (Exception e) {
					e.printStackTrace();
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
					e.printStackTrace();
				}

				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				try {
					// Filtro para cuentas
					((RecyclerGenericAdapter) mRecyclerViewTasks.getAdapter()).setFilter(newText);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		});

		this.applyActions();

		if (!DataManager.getInstance().IsSynchronized(MODULE)) {
			obtenerNotas = new GetNotesTask();
			obtenerNotas.execute();

		} else {

			chargeViewInfo();
		}
		return mRootView;
	}

	@Override
	public void chargeViewInfo() {
		mRecyclerViewTasksAdapter = new RecyclerGenericAdapter(getActivity(),
				AdapterSearchUtil.transform(DataManager.getInstance().notesInfo), MODULE);
		mRecyclerViewTasks.setAdapter(mRecyclerViewTasksAdapter);

	}

	@Override
	public boolean chargeIdPreviousModule() {
		return false;
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
		GlobalClass gc = (GlobalClass) getActivity().getApplicationContext();
		ActionsStrategy.definePermittedActions(this, this.getActivity(), gc);
	}

	/**
	 * Representa una tarea asincrona de obtencion de Notas.
	 */
	public class GetNotesTask extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
			progressDialog.setMessage("Cargando Notas...");
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

				tasks = ControlConnection.getInfo(TypeInfoServer.getNotes, getActivity());

				DataManager.getInstance().notesInfo.clear();

				JSONObject jObj = new JSONObject(tasks);

				JSONArray jArr = jObj.getJSONArray("results");
				for (int i = 0; i < jArr.length(); i++) {
					JSONObject obj = jArr.getJSONObject(i);
					String id = obj.getString("id");
					String name = obj.getString("document_name");
					DataManager.getInstance().notesInfo.add(new Notes(id, name));
				}
				DataManager.getInstance().defSynchronize(MODULE);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			progressDialog.dismiss();

			if (success) {
				if (DataManager.getInstance().notesInfo.size() > 0) {
					chargeViewInfo();
				}
			}
		}

		@Override
		protected void onCancelled() {
		
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
