package com.co.iatech.crm.sugarmovil.fragments;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.SearchDialogInterface;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListAccountConverter;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;

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

public class AccountsDialog extends DialogFragment  {

	private Button btnSeleccionar;
	private ListView listAccounts;
	private SearchView searchView;
	private ArrayAdapter<String> adapter;
	private ListAccountConverter lac;
	private Cuenta account;
		
	public AccountsDialog(){
		lac = new ListAccountConverter();
	}

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.search_accounts_dialog, container, false);
	        getDialog().setTitle(R.string.dialog_accounts_title);
	        
	        btnSeleccionar = (Button) rootView.findViewById(R.id.searchAccountDialog_button);
	        listAccounts = (ListView) rootView.findViewById(R.id.listSearchAccountView);
	        searchView = (SearchView) rootView.findViewById(R.id.searchViewAccountDialog);
	        
	        
	        adapter = new ArrayAdapter<String>(getActivity(), 
	        		android.R.layout.simple_list_item_1, lac.getListInfo());
	        listAccounts.setAdapter(adapter);
	        
	        listAccounts.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					account = new Cuenta();
					account.setName(adapter.getItem(position));
					
					String accID = lac.convert(account.getName(),
							DataToGet.CODE);
					
					
					account.setId(accID);
				
					//Pasar por Listener Pattern 
					SearchDialogInterface listener = (SearchDialogInterface) getActivity();
		            listener.onFinishSearchDialog(account);
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
