package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.model.converters.lists.ListConverter.DataToGet;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;

import android.content.Context;
import android.widget.ArrayAdapter;


public class ListDefaultSourceConverter extends ListConverter{
	public enum ListOptions {
		PAUTAS("PAUTAS PUBLICITARIAS"),PORTALES("PORTALES WEB"),MARKETING("E-MAIL MARKETING");
		public String value;
		
		ListOptions(String value){
			this.value = value;
		}
	}
	
	private static ListDefaultSourceConverter instance;
	private Map<ListOptions, List<String>> maps;
	private ListDefaultSourceConverter (){
		loadData();
	}
	
	public void loadData(){
		maps = new HashMap<ListOptions, List<String>>();
		dataMap = new LinkedHashMap<String,String>();
		
		dataMap.put(defaultListValue, defaultListValue);
		dataMap.put("BUSQUEDA EN GOOGLE", "BUSQUEDA EN GOOGLE");
		dataMap.put("EQUIPOS Y SOLUCIONES IT", "EQUIPOS Y SOLUCIONES IT");
		dataMap.put("GUIA DE SOLUCIONES TIC", "GUIA DE SOLUCIONES TIC");

		dataMap.put("LANDING PAGE", "LANDING PAGE");
		dataMap.put("PAGINA WEB", "PAGINA WEB");
		dataMap.put("PUBLICAR", "PUBLICAR");
		
		maps.put(ListOptions.PORTALES, new ArrayList<String>(dataMap.values()));
		
		List<String> dataMap2 = new ArrayList<String>();
		dataMap2.add(defaultListValue);
		dataMap2.add("COMPUTERWORLD");
		dataMap2.add("DIRECTORIO DE SEGURIDAD");
		dataMap2.add("DIRECTORIO PETROLERO");
		dataMap2.add("DIRECTORIO MINERO");
		
		maps.put(ListOptions.PAUTAS, dataMap2);
		
		List<String> dataMap3 = new ArrayList<String>();
		dataMap3.add(defaultListValue);
		dataMap3.add("BOLETIN");
		dataMap2.add("EMAIL");
		maps.put(ListOptions.MARKETING,dataMap3);
	}
	
	
	public  ArrayAdapter<String> getListAdatper(Context context, String option){
		for(ListOptions op :ListOptions.values()){
			if(op.value.equals(option)){
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
		                android.R.layout.simple_spinner_item, maps.get(op));
			    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				return adapter;
			}
		}
		return null;
	}
	
	public int getPosItemOnList(String option, String idItem) {
		for(ListOptions op :ListOptions.values()){
			if(op.value.equals(option)){
				List<String> it = maps.get(op);
				for(int i=0 ;i< it.size(); i++ ){
					if(it.get(i).equals(idItem)){
						return i;
					}
				}
			}
		}
		return 0;
	}
	
	public static ListDefaultSourceConverter getInstance(){
		if(instance == null){
			instance = new ListDefaultSourceConverter();
		}	
		return instance;
	}
	
}
