package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;

public class ListContactConverter extends ListModelConverter{
	
	public ListContactConverter (ListsHolderType typeList){
		this.typelist = typeList;
	
	}
	
	
	@Override
	public String convert(String value, DataToGet dataType) {
		String resp= "";
		boolean  finded = false;
		List<Contacto> lista = (List<Contacto>)ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(Contacto c : lista){
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
					for(Contacto c : lista){
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
					
					for(Contacto c : lista){
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
		return resp;
	}

	@Override
	public List<String> getListInfo() {
		if( hasItems()){
			data = new ArrayList<String>();	
			data.add("SELECCIONAR");
			List<Contacto> listaTemp = (List<Contacto>) ListsHolder.getList(typelist);
			for(Contacto c : listaTemp){
				data.add(c.getName());
			}
			
		}
		return data;
	}
	

}
