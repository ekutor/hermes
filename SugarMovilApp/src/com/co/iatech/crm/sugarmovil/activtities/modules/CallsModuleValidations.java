package com.co.iatech.crm.sugarmovil.activtities.modules;


public interface CallsModuleValidations extends CallsModule, IMovilModuleValidations {
	Modules[] nameVisibleOnPermitedModules = { Modules.ACCOUNTS, Modules.OPPORTUNITIES, Modules.CONTACTS };
}
