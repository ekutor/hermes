package com.co.iatech.crm.sugarmovil.model.converters.lists;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;



public abstract class ListModelConverter extends ListConverter{
	
	protected ListsHolderType typelist;
	protected List<String> data = new ArrayList<String>();
	
	public ListModelConverter (){
	}

	public abstract List<String> getListInfo();
	
	public boolean hasItems() {
		if(ListsHolder.getList(typelist) != null ){
			return ListsHolder.getList(typelist).size() > 0;
		}
		return false;
	}
	
}
