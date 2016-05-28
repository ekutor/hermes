package com.co.iatech.crm.sugarmovil.core.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.DetailTask;


public class DataManager  {
	
	private static DataManager instance;
	
	public List<Oportunidad> opportunitiesInfo;
	public List<Cuenta> accountsInfo;
    public List<Call> callsInfo;
    public List<Contacto> contactsInfo;
    public List<Contacto> contactsxAccountsInfo;
    public List<DetailTask> tasksInfo;
    private Map<Modules,Boolean> synchronizedModules;
    
    
	private DataManager(){
		opportunitiesInfo = new ArrayList<Oportunidad>();
		accountsInfo  = new ArrayList<Cuenta>();
		callsInfo = new ArrayList<Call>();
		contactsInfo = new ArrayList<Contacto>();
		contactsxAccountsInfo = new ArrayList<Contacto>();
		tasksInfo = new ArrayList<DetailTask>();
		synchronizedModules = new HashMap<Modules,Boolean>();
		
		for(Modules module: Modules.values()){
			synchronizedModules.put(module, false);
		}
	}
	
	public static DataManager getInstance(){
		if(instance == null){
			instance = new DataManager();
		}
		return instance;
	}

	public boolean IsSynchronized(Modules module) {
		return synchronizedModules.get(module);
	}
	
	public void defSynchronize(Modules module) {
		synchronizedModules.put(module, true);
	}

	public void unDefsynchronize(Modules module) {
		synchronizedModules.put(module, false);
	}

}
