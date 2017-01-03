package com.co.iatech.crm.sugarmovil.activtities.modules;


public interface CalendarModuleValidations extends TasksModule, IMovilModuleValidations {
	Modules[] nameVisibleOnPermitedModules = { Modules.ACCOUNTS, Modules.OPPORTUNITIES, Modules.CONTACTS, Modules.TASKS };
}
