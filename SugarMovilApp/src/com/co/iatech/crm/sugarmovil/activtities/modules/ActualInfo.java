package com.co.iatech.crm.sugarmovil.activtities.modules;

public class ActualInfo{
	private Modules actualParentModule;
	private String actualParentId;
	private String actualModuleId;
	
	public ActualInfo(){
		actualParentModule = Modules.PREVIOUS_UI;
	}
	
	public ActualInfo(Modules actualParentModule,String actualParentId ){
		this.actualParentModule = actualParentModule;
		this.actualParentId  = actualParentId;
	}

	public Modules getActualParentModule() {
		return actualParentModule;
	}

	public void setActualParentModule(Modules actualParentModule) {
		this.actualParentModule = actualParentModule;
	}

	public String getActualParentId() {
		return actualParentId;
	}

	public void setActualParentId(String actualParentId) {
		this.actualParentId = actualParentId;
	}

	public String getActualModuleId() {
		return actualModuleId;
	}

	public void setActualModuleId(String actualModuleId) {
		this.actualModuleId = actualModuleId;
	}
	
	
}