package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;



public class ListAccountConverter extends ListModelConverter{
	
	public ListAccountConverter (){
		this.typelist = ListsHolderType.ACCOUNTS;
	
	}
	
	public ListAccountConverter (List<Cuenta> data){
		this();
		ListsHolder.saveList(typelist, data);
	}
	
	@Override
	public String convert(String value, DataToGet dataType) {
		String resp= "";
		List<Cuenta> lista = (List<Cuenta>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(Cuenta c : lista){
						if(c.getId().contains(value)){
							resp = c.getName();
							break;
						}
					}
				}
				break;
			case CODE:
				
				if(lista != null){
					for(Cuenta c : lista){
						if(c.getName().contains(value)){
							resp = c.getId();
							break;
						}
					}
				}
				break;
			case POS:
				int cont = -1;
				if(lista != null){
					
					for(Cuenta c : lista){
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
		if(data.size()<= 0){
			List<Cuenta> listaTemp = (List<Cuenta>) ListsHolder.getList(typelist);
			for(Cuenta c : listaTemp){
				data.add(c.getName());
			}
			data.add("SELECCIONAR");
		}
		return data;
	}
	

}
