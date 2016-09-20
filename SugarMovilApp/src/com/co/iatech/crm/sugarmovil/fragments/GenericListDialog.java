package com.co.iatech.crm.sugarmovil.fragments;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.SearchDialogInterface;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListModelConverter;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class GenericListDialog extends DialogFragment  {

	private Button btnSeleccionar;
	private ListView listAccounts;
	private SearchView searchView;
	private ArrayAdapter<String> adapter;
	private ListModelConverter lmc;
	private Modules resModule;

	public GenericListDialog(ListModelConverter list, Modules responseModule){
		lmc = list;
		resModule = responseModule;
		
	}

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.search_accounts_dialog, container, false);
	        getDialog().setTitle("Buscar "+resModule.getVisualName().toLowerCase());
	        
	        btnSeleccionar = (Button) rootView.findViewById(R.id.searchAccountDialog_button);
	        listAccounts = (ListView) rootView.findViewById(R.id.listSearchAccountView);
	        searchView = (SearchView) rootView.findViewById(R.id.searchViewAccountDialog);
	        
	        
	        adapter = new ArrayAdapter<String>(getActivity(), 
	        		android.R.layout.simple_list_item_1, lmc.getListInfo());
	        listAccounts.setAdapter(adapter);
	        
	        listAccounts.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					GenericBean bean = null;
					switch(resModule){
						case ACCOUNTS:
							bean = new Cuenta();
							break;
						
						case CONTACTS:
							bean = new Contacto();
							break;
					}
					
					bean.setName(adapter.getItem(position));
					
					String accID = lmc.convert(bean.getName(),
							DataToGet.CODE);
					
					
					bean.id = accID;
				
					//Pasar por Listener Pattern 
					SearchDialogInterface listener = (SearchDialogInterface) getActivity();
		            listener.onFinishSearchDialog(bean, 0);
		            dismiss();
				}

	        	
	        });
	        //busquedas
	        searchView.setQueryHint("Digite Cuenta...");
	        searchView.setOnQueryTextListener(new OnQueryTextListener(){

				@Override
				public boolean onQueryTextChange(String txt) {
					adapter.getFilter().filter(txt);
					return false;
				}

				@Override
				public boolean onQueryTextSubmit(String txt) {
					adapter.getFilter().filter(txt);
					return false;
				}
	        	
	        });
	        
	        btnSeleccionar.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					
					dismiss();
					
				}
	        	
	        });
	        return rootView;
	    }

}
