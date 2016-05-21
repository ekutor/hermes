package com.co.iatech.crm.sugarmovil.adapters.search;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.model.GenericBean;

public class AdapterSearchUtil {
	
	public static List<IAdapterItems> transform(List<? extends GenericBean> data){
		List<IAdapterItems> items = new ArrayList<IAdapterItems>();
		
		for(GenericBean d : data){
			items.add(new GenericAdapterSearch(d.id, d.getName()));
		}
		
		return items;
	}
}
