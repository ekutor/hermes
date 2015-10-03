package com.co.iatech.crm.sugarmovil.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.model.GenericBean;


public class ListsHolder {
    public enum ListsHolderType { USERS,ACCOUNTS,CAMPAIGNS }
    private final static Map<ListsHolderType, List< ? extends GenericBean> > holder = new HashMap<ListsHolderType, List< ? extends GenericBean>>();
    
    public static List< ? extends GenericBean> getList(ListsHolderType type){
    	return holder.get(type);
    }
	
	public static void saveList(ListsHolderType type, List< ? extends GenericBean> value){
		holder.put(type, value);
	}
	

}
