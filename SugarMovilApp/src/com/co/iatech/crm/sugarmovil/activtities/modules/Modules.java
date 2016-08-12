package com.co.iatech.crm.sugarmovil.activtities.modules;


public enum Modules {
	
	ACCOUNTS("Accounts","CUENTAS", "CUENTA"),
	CONTACTS("Contacts","CONTACTOS", "CONTACTO"),
	OPPORTUNITIES("Opportunities","OPORTUNIDADES", "OPORTUNIDAD"),
	CALLS("Calls","LLAMADAS", "LLAMADA"),
	PRODUCTS("psg_Productos","PRODUCTOS", "PRODUCTO"),
	TASKS("Tasks","TAREAS", "TAREA"),
	PREVIOUS_UI("PREVIOUS_UI","PREVIOUS_UI", "PREVIOUS_UI"),
	ACTUAL_INFO("ACTUAL_INFO","ACTUAL_INFO", "ACTUAL_INFO"),
	SUBTASKS("Notes","SUBTAREAS", "SUB TAREA"),
	NOTES2("adm_Notas","NOTAS", "NOTA"),
	LEADS("Leads","CLIENTES POTENCIALES", "CLIENTE POTENCIAL"),
	;
	
	private String moduleName,visualName,sugarDBName;
	
	
	Modules(String sugarDBName,String  moduleName, String visualName){
		this.sugarDBName = sugarDBName;
		this.moduleName = moduleName;
		this.visualName = visualName;
	}


	public String getModuleName() {
		return moduleName;
	}


	public String getVisualName() {
		return visualName;
	}


	public String getSugarDBName() {
		return sugarDBName;
	}
	
	public static Modules getModulefromDBName(String category){
		for(Modules m:Modules.values()){
			if(m.sugarDBName.toLowerCase().equals(category.toLowerCase())){
				return m;
			}
		}
		return null;
	}
	public static Modules getModulefromVisualName(String visualName){
		for(Modules m:Modules.values()){
			if(m.moduleName.toLowerCase().equals(visualName.toLowerCase())){
				return m;
			}
		}
		return null;
	}
	
}
