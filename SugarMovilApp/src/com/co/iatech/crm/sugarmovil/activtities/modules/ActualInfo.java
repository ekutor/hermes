package com.co.iatech.crm.sugarmovil.activtities.modules;

public class ActualInfo{
	private GenericModuleBean actualParentModule;
	private GenericModuleBean actualPrincipalModule;
	
	
	public ActualInfo(){
		actualParentModule = new GenericModuleBean();
		actualPrincipalModule = new GenericModuleBean();
	}
	
	public ActualInfo(GenericModuleBean actualParentModule){
		this.actualParentModule = actualParentModule;
		actualPrincipalModule = new GenericModuleBean();
	}

	
}