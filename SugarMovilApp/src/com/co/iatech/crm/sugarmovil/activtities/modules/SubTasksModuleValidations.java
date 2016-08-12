package com.co.iatech.crm.sugarmovil.activtities.modules;


public interface SubTasksModuleValidations extends SubTasksModule, IMovilModuleValidations {
	Modules[] nameVisibleOnPermitedModules = { Modules.ACCOUNTS, Modules.OPPORTUNITIES, Modules.CONTACTS, Modules.TASKS };
}
