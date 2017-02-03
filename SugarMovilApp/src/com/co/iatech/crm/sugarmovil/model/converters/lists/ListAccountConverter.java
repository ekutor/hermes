package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.List;

import android.util.Log;

import com.co.iatech.crm.sugarmovil.model.Account;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;



public class ListAccountConverter extends ListModelConverter{
	
	public ListAccountConverter (){
		this.typelist = ListsHolderType.ACCOUNTS;
	
	}
	
	public ListAccountConverter (List<Account> data){
		this();
		ListsHolder.saveList(typelist, data);
	}
	
	@Override
	public String convert(String value, DataToGet dataType) {
		if(value == null){
			return "";
		}
		String resp= "";
		boolean  finded = false;
		List<Account> lista = (List<Account>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(Account c : lista){
						if(c.getId().contains(value)){
							finded = true;
							resp = c.getName();
							break;
						}
					}
				}
				break;
			case CODE:
				
				if(lista != null){
					for(Account c : lista){
						if(c.getName().contains(value)){
							finded = true;
							resp = c.getId();
							break;
						}
					}
				}
				break;
			case POS:
				int cont = 0;
				if(lista != null){
					
					for(Account c : lista){
						cont++;
						if(c.getId().equals(value)){
							finded = true;
							Log.d("BusquedaPos","posEnc "+cont+" "+c.getId()+" "+c.getName());
							break;
						}
					}
				}
				if(!finded){
					cont = 0;
				}
				Log.d("BusquedaPos","valor buscado "+value+" pos "+cont);
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
			data.add("SELECCIONAR");
			List<Account> listaTemp = (List<Account>) ListsHolder.getList(typelist);
			for(Account c : listaTemp){
				data.add(c.getName());
			}
			
		}
		return data;
	}
	

}
