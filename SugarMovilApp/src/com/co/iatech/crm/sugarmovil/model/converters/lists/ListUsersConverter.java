package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;

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
		List<User> lista = (List<User>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(User c : lista){
						if(c.getId().contains(value)){
							resp = c.getFirst_name() + " "+ c.getLast_name();
							break;
						}
					}
				}
				break;
			case CODE:
				
				if(lista != null){
					for(User c : lista){
						if(value.contains(c.getFirst_name())){
							resp = c.getId();
							break;
						}
					}
				}
				break;
			case POS:
				int cont = -1;
				if(lista != null){
					
					for(User c : lista){
						cont++;
						if(c.getId().equals(value)){
							break;
						}
					}
				}
				resp = String.valueOf(cont);
				break;
			default:
				break;
		}
		return resp;
	}

	@Override
	public List<String> getListInfo() {
		List<String> data = new ArrayList<String>();
		List<User> lista = (List<User>) ListsHolder.getList(typelist);
		for(User c : lista){
			data.add(c.getFirst_name() + " "+ c.getLast_name());
		}
		return data;
	}
	

}
