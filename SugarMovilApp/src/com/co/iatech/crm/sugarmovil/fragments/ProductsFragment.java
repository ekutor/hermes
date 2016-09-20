package com.co.iatech.crm.sugarmovil.fragments;


import org.json.JSONArray;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.MainActivity;
import com.co.iatech.crm.sugarmovil.activities.tasks.GatewayPublisher;
import com.co.iatech.crm.sugarmovil.activities.tasks.GenericTaskPublisher;
import com.co.iatech.crm.sugarmovil.activities.tasks.IObserverTask;
import com.co.iatech.crm.sugarmovil.activities.tasks.ITaskPublisher;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.activtities.modules.ProductsModule;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter.SearchType;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Product;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.content.Context;
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

public class ProductsFragment extends FragmentsModules implements ProductsModule, IObserverTask{
    /**
     * Debug.
     */
    private static final String TAG = "ProductsFragment";


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

	private ITaskPublisher taskPublisher;


	private boolean dataNotFound;

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
        try{
        // Variable Global
        mGlobalVariable = (GlobalClass) getActivity()
                .getApplicationContext();

        mGlobalVariable.setSelectedItem(5);

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
                   // ((RecyclerGenericAdapter) mRecyclerViewProducts.getAdapter()).setFilter(newText);
                } catch (Exception e) {
                    Log.d(TAG, "Error añadiendo el filtro de busqueda");
                }

                return false;
            }
        });
        mMainSearchView.setQuery("", true);
        GatewayPublisher.getInstance().register(this);
        showProducts();
	} catch (Exception e) {
		Message.showShortExt(Utils.errorToString(e), this.getActivity());
	}

        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainSearchView.clearFocus();
        
        Log.d(TAG, "onResume Fragment Products");
    }

    private void showProducts(){
    	try {
        	GenericTaskPublisher getProducts = new GenericTaskPublisher(getActivity(),MODULE, 
        			TypeInfoServer.getProductos, "Buscando productos...");
            getProducts.execute();
            mMainSearchView.setIconified(true);
        } catch (Exception e) {
            Message.showShortExt(Utils.errorToString(e), getActivity());
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }

	
	@Override
	public void chargeViewInfo() {
		if(dataNotFound){
	        	Message.showShortExt("Ningun producto coincide con los parametros de busqueda.", this.getActivity());
	    }
        mRecyclerViewProductsAdapter = new RecyclerGenericAdapter(getActivity(), 
        		AdapterSearchUtil.transform(DataManager.getInstance().products), MODULE,SearchType.REMOTE);
        mRecyclerViewProducts.setAdapter(mRecyclerViewProductsAdapter);
	}

	@Override
	public Modules getModule() {
		return MODULE;
	}

	@Override
	public void update() {
		try{
			ActivityBeanCommunicator response = taskPublisher.getInfo();
			if(response.getModule() != MODULE){
			 return;
			}
			if(response.getAdditionalInfo().length() > 15){
				DataManager.getInstance().products.clear();
				dataNotFound = false;
			}else{
				dataNotFound = true;
			}
			
	        JSONObject jObj = new JSONObject(response.getAdditionalInfo());
	
	        JSONArray jArr = jObj.getJSONArray("results");
	        for (int i = 0; i < jArr.length(); i++) {
	            JSONObject obj = jArr.getJSONObject(i);
	            DataManager.getInstance().products.add(new Product(obj));
	        }
	        //DataManager.getInstance().IsSynchronized(MODULE);
	        chargeViewInfo();
	        mMainSearchView.clearFocus();
	        mMainSearchView.setIconified(true);
	        mRecyclerViewProductsAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			//Message.showFinalMessage(this.getFragmentManager(), Utils.errorToString(e), this.getActivity(), MODULE);
		}
		
	}

	@Override
	public void defineTasktoListen(ITaskPublisher publisher) {
		this.taskPublisher = publisher;
		
	}

	@Override
	public void addInfo(String serverResp) {
		
		
	}
	
}
