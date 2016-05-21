package com.co.iatech.crm.sugarmovil.adapters.search;

public class GenericAdapterSearch extends AdapterSearchItems {
	
	public GenericAdapterSearch(String searchId, String searchName ){
		this.searchId = searchId;
		this.searchName = searchName;
	}
	
	@Override
	public String getSearchId() {
		return searchId;
	}

	@Override
	public String getSearchName() {
		return searchName;
	}

}
