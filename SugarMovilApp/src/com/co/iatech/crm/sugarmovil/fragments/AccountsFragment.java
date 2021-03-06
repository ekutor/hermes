package com.co.iatech.crm.sugarmovil.fragments;

import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerAccountsAdapter;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;

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

public class AccountsFragment extends Fragment {
	/**
     * Debug.
     */
    private static final String TAG = "AccountsFragment";

    /**
     * Tasks.
     */
    private GetAccountsTask mTareaObtenerCuentas = null;

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
    private RecyclerView mRecyclerViewAccounts;
    private RecyclerView.Adapter mRecyclerViewAccountsAdapter;
    private RecyclerView.LayoutManager mRecyclerViewAccountsLayoutManager;
//    private ActionButton mActionButton;

    public AccountsFragment() {
        // Required empty public constructor
    }
    
    /*
     * Nueva Instancia de AccountsFragment.
     */
     public static AccountsFragment newInstance() {
         AccountsFragment fragment = new AccountsFragment();
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
         mRootView = inflater.inflate(R.layout.fragment_accounts, container, false);
         
         // Variable Global
         mGlobalVariable = (GlobalClass) getActivity()
                 .getApplicationContext();

         mGlobalVariable.setSelectedItem(0);
         

         // Main Toolbar
         mMainTextView = ((MainActivity) getActivity()).getMainTextView();
         mMainTextView.setText("CUENTAS");
         mMainTextView.setVisibility(View.VISIBLE);
         mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
         mMainSearchView.setVisibility(View.VISIBLE);

         // Recycler
         mRecyclerViewAccounts = (RecyclerView) mRootView.findViewById(R.id.recycler_view_accounts);
         mRecyclerViewAccounts.setHasFixedSize(true);

         mRecyclerViewAccountsLayoutManager = new LinearLayoutManager(getActivity());
         mRecyclerViewAccounts.setLayoutManager(mRecyclerViewAccountsLayoutManager);
         
         
         // Action Button
//         mActionButton = (ActionButton) mRootView.findViewById(R.id.action_button);

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
                     ((RecyclerAccountsAdapter) mRecyclerViewAccounts.getAdapter()).flushFilter();
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
                     ((RecyclerAccountsAdapter) mRecyclerViewAccounts.getAdapter()).setFilter(query);
                 } catch (Exception e) {
                     Log.d(TAG, "Error a�adiendo el filtro de busqueda");
                 }

                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 try {
                     // Filtro para cuentas
                     ((RecyclerAccountsAdapter) mRecyclerViewAccounts.getAdapter()).setFilter(newText);
                 } catch (Exception e) {
                     Log.d(TAG, "Error a�adiendo el filtro de busqueda");
                 }

                 return false;
             }
         });


         // Tarea para consultar cuentas
         ListsHolder.clear(ListsHolderType.CONTACTS_ACCOUNTS);
         if(!DataManager.getInstance().IsSynchronized(Modules.ACCOUNTS)){
        
        	 mTareaObtenerCuentas = new GetAccountsTask();
        	 mTareaObtenerCuentas.execute();
        	 
         }else{
        	 this.showAccounts();
         }

         
         return mRootView;
     }
     
     public void showAccounts() {
 		mRecyclerViewAccountsAdapter = new RecyclerAccountsAdapter(this.getActivity(),DataManager.getInstance().accountsInfo);
        mRecyclerViewAccounts.setAdapter(mRecyclerViewAccountsAdapter);
 		
 	}
     
     /**
      * Representa una tarea asincrona de obtencion de cuentas.
      */
     public class GetAccountsTask extends AsyncTask<Void, Void, Boolean> {
         private ProgressDialog progressDialog;

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
             progressDialog.setMessage("Cargando cuentas...");
             progressDialog.setIndeterminate(true);
             progressDialog.show();
         }

         @Override
         protected Boolean doInBackground(Void... params) {
             try {
                 // Parametros
                 String resultado = null;

                 // Intento de obtener cuentas
                 Log.d(TAG, "Obteniendo cuentas ");
                 resultado  = ControlConnection.getInfo(TypeInfoServer.getAccounts, getActivity());
              
                 DataManager.getInstance().accountsInfo.clear();

                 JSONObject jObj = new JSONObject(resultado);

                 JSONArray jArr = jObj.getJSONArray("results");
                 for (int i = 0; i < jArr.length(); i++) {
                     JSONObject obj = jArr.getJSONObject(i);
        
                     DataManager.getInstance().accountsInfo.add(new Cuenta(obj));
                 }
                 ListsHolder.saveList(ListsHolderType.ACCOUNTS, DataManager.getInstance().accountsInfo);
                 DataManager.getInstance().defSynchronize(Modules.ACCOUNTS);
                 return true;
             } catch (Exception e) {
                 Log.d(TAG, "Buscar Cuentas Error: "
                         + e.getClass().getName() + ":" + e.getMessage());
                 return false;
             }
         }

         @Override
         protected void onPostExecute(final Boolean success) {
             mTareaObtenerCuentas = null;
             progressDialog.dismiss();

             if (success) {
                 if (DataManager.getInstance().accountsInfo.size() > 0) {
                     showAccounts();
                 } else {
                     Log.d(TAG,
                             "No hay Cuentas: "
                                     + DataManager.getInstance().accountsInfo.size());
                 }
             }
         }

         @Override
         protected void onCancelled() {
             mTareaObtenerCuentas = null;
             Log.d(TAG, "Cancelado ");
         }
     }

	

}
