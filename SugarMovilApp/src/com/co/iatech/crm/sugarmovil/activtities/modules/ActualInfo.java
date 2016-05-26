package com.co.iatech.crm.sugarmovil.activtities.modules;

public class ActualInfo{
	private Modules actualParentModule;
	private String actualParentId;
	private Modules actualPrincipalModule;
	private String actualPrincipalId;
	
	
	public ActualInfo(){
		actualParentModule = Modules.PREVIOUS_UI;
		actualPrincipalModule = Modules.PREVIOUS_UI;
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

	public Modules getActualPrincipalModule() {
		return actualPrincipalModule;
	}

	public void setActualPrincipalModule(Modules actualPrincipalModule) {
		this.actualPrincipalModule = actualPrincipalModule;
	}

	public String getActualPrincipalId() {
		return actualPrincipalId;
	}

	public void setActualPrincipalId(String actualPrincipalId) {
		this.actualPrincipalId = actualPrincipalId;
	}
	
	
}