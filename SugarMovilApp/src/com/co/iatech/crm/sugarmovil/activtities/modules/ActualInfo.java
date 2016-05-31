package com.co.iatech.crm.sugarmovil.activtities.modules;

public class ActualInfo{
	private Modules actualParentModule;
	private ActivityBeanCommunicator actualParentInfo;
	private Modules actualPrincipalModule;
	private ActivityBeanCommunicator actualPrincipalInfo;
	
	
	public ActualInfo(){
		actualParentModule = Modules.PREVIOUS_UI;
		actualPrincipalModule = Modules.PREVIOUS_UI;
	}
	
	public ActualInfo(Modules actualParentModule,ActivityBeanCommunicator actualParentInfo ){
		this.actualParentModule = actualParentModule;
		this.actualParentInfo  = actualParentInfo;
	}

	public Modules getActualParentModule() {
		return actualParentModule;
	}

	public void setActualParentModule(Modules actualParentModule) {
		this.actualParentModule = actualParentModule;
	}

	public Modules getActualPrincipalModule() {
		return actualPrincipalModule;
	}

	public void setActualPrincipalModule(Modules actualPrincipalModule) {
		this.actualPrincipalModule = actualPrincipalModule;
	}

	public ActivityBeanCommunicator getActualParentInfo() {
		return actualParentInfo;
	}

	public void setActualParentInfo(ActivityBeanCommunicator actualParentInfo) {
		this.actualParentInfo = actualParentInfo;
	}

	public ActivityBeanCommunicator getActualPrincipalInfo() {
		return actualPrincipalInfo;
	}

	public void setActualPrincipalInfo(ActivityBeanCommunicator actualPrincipalInfo) {
		this.actualPrincipalInfo = actualPrincipalInfo;
	}
	
	
	
}