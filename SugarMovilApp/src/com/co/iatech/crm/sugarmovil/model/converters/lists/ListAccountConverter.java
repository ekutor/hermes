package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.List;

import android.util.Log;

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
		if(value == null){
			return "";
		}
		String resp= "";
		boolean  finded = false;
		List<Cuenta> lista = (List<Cuenta>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(Cuenta c : lista){
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
					for(Cuenta c : lista){
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
					
					for(Cuenta c : lista){
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
			List<Cuenta> listaTemp = (List<Cuenta>) ListsHolder.getList(typelist);
			for(Cuenta c : listaTemp){
				data.add(c.getName());
			}
			
		}
		return data;
	}
	

}
