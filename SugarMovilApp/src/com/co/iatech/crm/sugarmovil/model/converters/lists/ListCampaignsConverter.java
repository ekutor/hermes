package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.List;

import android.util.Log;

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
		boolean  finded = false;
		List<Campana> lista = (List<Campana>) ListsHolder.getList(typelist);
		switch(dataType){
			case VALUE:
				if(lista != null){
					for(Campana c : lista){
						if(c.getId().equals(value)){
							finded = true;
							resp = c.getName();
							break;
						}
					}
				}
				break;
			case CODE:
				
				if(lista != null){
					for(Campana c : lista){
						if(c.getName().equals(value)){
							finded = true;
							resp = c.getId();
							break;
						}
					}
				}
				break;
			case POS:
				int cont = 0;
				
				if(lista != null && value != null){
					
					for(Campana c : lista){
						cont++;
						if(c.getId().equals(value)){
							finded = true;
							Log.d("BusquedaPosCAmp","posEnc "+cont+" "+c.getId()+" "+c.getName());
							break;
						}
					}
				}
				if(!finded){
					cont = 0;
				}
				Log.d("BusquedaPosCAmp","valor buscado "+value+" pos "+cont);
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
			List<Campana> lista = (List<Campana>) ListsHolder.getList(typelist);
			data.add("SELECCIONAR");
			for(Campana c : lista){
				data.add(c.getName());
			}
		}
		return data;
	}
	

}
