package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;



public class ListUsersConverter extends ListModelConverter{
	
	public ListUsersConverter (){
		this.typelist = ListsHolderType.USERS;
	
	}
	
	public ListUsersConverter (List<Cuenta> data){
		this();
		ListsHolder.saveList(typelist, data);
	}
	
	@Override
	public String convert(String value, DataToGet dataType) {
		String resp= "";
		boolean  finded = false;
		List<User> lista = (List<User>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(User c : lista){
						if(c.getId().equals(value)){
							finded = true;
							resp = c.getFirst_name() + " "+ c.getLast_name();
							break;
						}
					}
				}
				break;
			case CODE:
				Log.d("ListUsersConvert", "VALOR recibido "+value);
				if(lista != null){
					for(User c : lista){
						if(value.equals(c.getFirst_name() + " "+ c.getLast_name()) ){
							finded = true;
							Log.d("ListUsersConvert", "VALOR encontrado "+c.getFirst_name());
							resp = c.getId();
							break;
						}
					}
				}
				break;
			case POS:
				int cont = 0;
				if(lista != null){
					
					for(User c : lista){
						cont++;
						if(c.getId().equals(value)){
							finded = true;
							break;
						}
					}
				}
				if(!finded){
					cont = 0;
				}
				resp = String.valueOf(cont);
				break;
			default:
				break;
		}
		Log.d("ListUsersConvert", "VALOR retornar "+resp);
		return resp;
	}

	@Override
	public List<String> getListInfo() {
		if(data.size()<= 0){
			List<User> lista = (List<User>) ListsHolder.getList(typelist);
			data.add("SELECCIONAR");
			for(User c : lista){
				data.add(c.getFirst_name() + " "+ c.getLast_name());
			}
		}
		return data;
	}
	

}
