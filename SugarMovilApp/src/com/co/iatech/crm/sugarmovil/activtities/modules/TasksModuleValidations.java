package com.co.iatech.crm.sugarmovil.activtities.modules;


public interface TasksModuleValidations extends TasksModule, IMovilModuleValidations {
	Modules[] nameVisibleOnPermitedModules = { Modules.ACCOUNTS, Modules.OPPORTUNITIES, Modules.CONTACTS };
}
