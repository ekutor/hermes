package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.model.Campana;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;



public class ListCampaignsConverter extends ListModelConverter{
	
	public ListCampaignsConverter (){
		this.typelist = ListsHolderType.CAMPAIGNS;
	
	}
	
	public ListCampaignsConverter (List<Cuenta> data){
		this();
		ListsHolder.saveList(typelist, data);
	}
	
	@Override
	public String convert(String value, DataToGet dataType) {
		String resp= "";
		List<Campana> lista = (List<Campana>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(Campana c : lista){
						if(c.getId().contains(value)){
							resp = c.getName();
							break;
						}
					}
				}
				break;
			case CODE:
				
				if(lista != null){
					for(Campana c : lista){
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
					
					for(Campana c : lista){
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
		List<Campana> lista = (List<Campana>) ListsHolder.getList(typelist);
		for(Campana c : lista){
			data.add(c.getName());
		}
		data.add("SELECCIONAR");
		return data;
	}
	

}
