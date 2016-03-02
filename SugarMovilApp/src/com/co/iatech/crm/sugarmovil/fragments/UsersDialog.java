package com.co.iatech.crm.sugarmovil.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
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

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.listeners.SearchDialogInterface;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListUsersConverter;

public class UsersDialog extends DialogFragment  {

	private Button btnSeleccionar;
	private ListView listUsers;
	private SearchView searchView;
	private ArrayAdapter<String> adapter;
	private List<String> users;
	private ListUsersConverter luc = new ListUsersConverter();
	private User usr;
		
	public UsersDialog(){
		luc = new ListUsersConverter();
		users = luc.getListInfo();
	}

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View rootView = inflater.inflate(R.layout.search_users_dialog, container, false);
	        getDialog().setTitle(R.string.dialog_users_title);
	        
	        btnSeleccionar = (Button) rootView.findViewById(R.id.searchDialog_button);
	        listUsers = (ListView) rootView.findViewById(R.id.listSearchView);
	        searchView = (SearchView) rootView.findViewById(R.id.searchViewUserDialog);
	        
	        
	        adapter = new ArrayAdapter<String>(getActivity(), 
	        		android.R.layout.simple_list_item_1, users);
	        listUsers.setAdapter(adapter);
	        
	        listUsers.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					usr = new User();
					usr.setUser_name(adapter.getItem(position));
					Log.d("UsersDialog", "Nombre Seleccionado: "+ usr.getUser_name());
					String iduser = luc.convert(usr.getUser_name(),
							DataToGet.CODE);
					
					
					usr.setId(iduser);
				
					//Pasar por Listener Pattern 
					SearchDialogInterface listener = (SearchDialogInterface) getActivity();
		            listener.onFinishSearchDialog(usr);
		            dismiss();
				}

	        	
	        });
	        //busquedas
	        searchView.setQueryHint("Digite Usuario..");
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
