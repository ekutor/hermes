package com.co.iatech.crm.sugarmovil.fragments;


import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.IMovilModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.ProductsModule;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.ControlConnection;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Product;
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

public class ProductsFragment extends Fragment implements IMovilModuleActions,ProductsModule{
    /**
     * Debug.
     */
    private static final String TAG = "ProductsFragment";

    /**
     * Tasks.
     */
    private GetProductsTask getProducts = null;

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
    private RecyclerView mRecyclerViewProducts;
    private RecyclerView.Adapter mRecyclerViewProductsAdapter;
    private RecyclerView.LayoutManager mRecyclerViewProductsLayoutManager;

    public ProductsFragment() {
        // Required empty public constructor
    }

    /*
    * Nueva Instancia de ProductsFragment.
    */
    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
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
        mRootView = inflater.inflate(R.layout.fragment_products, container, false);

        // Variable Global
        mGlobalVariable = (GlobalClass) getActivity()
                .getApplicationContext();

        mGlobalVariable.setSelectedItem(4);

        // Main Toolbar
        mMainTextView = ((MainActivity) getActivity()).getMainTextView();
        mMainTextView.setText("PRODUCTOS");
        mMainTextView.setVisibility(View.VISIBLE);
        mMainSearchView = ((MainActivity) getActivity()).getMainSearchView();
        mMainSearchView.setVisibility(View.VISIBLE);

        // Recycler
        mRecyclerViewProducts = (RecyclerView) mRootView.findViewById(R.id.recycler_view_products);
        mRecyclerViewProducts.setHasFixedSize(true);

        mRecyclerViewProductsLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewProducts.setLayoutManager(mRecyclerViewProductsLayoutManager);

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
                    ((RecyclerGenericAdapter) mRecyclerViewProducts.getAdapter()).flushFilter();
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });

        mMainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {// Filtro para llamadas
                    ((RecyclerGenericAdapter) mRecyclerViewProducts.getAdapter()).setFilter(query);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {// Filtro para llamadas
                    ((RecyclerGenericAdapter) mRecyclerViewProducts.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });
        
        if(!DataManager.getInstance().IsSynchronized(MODULE)){
        	// Tarea para consultar productos
            getProducts = new GetProductsTask();
            getProducts.execute();
            
        }else{
        	Log.d(TAG,"Cargando Llamadas desde MEMORIA");
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
        Log.d(TAG, "onResume Fragment Products");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Representa una tarea asincrona de obtencion de productos.
     */
    public class GetProductsTask extends AsyncTask<Void, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setMessage("Cargando productos...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Parametros
                String productos = null;

                // Intento de obtener productos
                productos  = ControlConnection.getInfo(TypeInfoServer.getProductos, getActivity());
                
                DataManager.getInstance().products.clear();

                JSONObject jObj = new JSONObject(productos);

                JSONArray jArr = jObj.getJSONArray("results");
                for (int i = 0; i < jArr.length(); i++) {
                    JSONObject obj = jArr.getJSONObject(i);
                    DataManager.getInstance().products.add(new Product(obj));
                }
                DataManager.getInstance().IsSynchronized(MODULE);
                return true;
            } catch (Exception e) {
                Log.d(TAG, "Buscar Productos Error: "
                        + e.getClass().getName() + ":" + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
          
            progressDialog.dismiss();

            if (success) {
                if (DataManager.getInstance().products.size() > 0) {
                	chargeViewInfo();
                } else {
                   Message.showShortExt("No hay productos Asociados", ProductsFragment.this.getActivity());
                }
            }
        }

        @Override
        protected void onCancelled() {

            Log.d(TAG, "Cancelado ");
        }
    }

	@Override
	public ActionButton getActionButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageButton getEditButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modules getModule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAssignedUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parcelable getBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean chargeIdPreviousModule() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getInfoFromMediator() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInfo(String serverResp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chargeViewInfo() {
	 /*   mRecyclerViewProductsAdapter = new RecyclerProductsAdapter(getActivity(), DataManager.getInstance().products);
        mRecyclerViewProducts.setAdapter(mRecyclerViewProductsAdapter);*/
        
        mRecyclerViewProductsAdapter = new RecyclerGenericAdapter(getActivity(), 
        		AdapterSearchUtil.transform(DataManager.getInstance().products), MODULE);
        mRecyclerViewProducts.setAdapter(mRecyclerViewProductsAdapter);
		
	}
}
