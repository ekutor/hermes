package com.co.iatech.crm.sugarmovil.core.data;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;


public class DataManager  {
	
	private static DataManager instance;
	
	public List<Oportunidad> opportunitiesInfo;
	public List<Cuenta> accountsInfo;
    public List<Llamada> callsInfo;
    public List<Contacto> contactsInfo;
    public List<TareaDetalle> tasksInfo;
    
    
	private DataManager(){
		opportunitiesInfo = new ArrayList<Oportunidad>();
		accountsInfo  = new ArrayList<Cuenta>();
		callsInfo = new ArrayList<Llamada>();
		contactsInfo = new ArrayList<Contacto>();
		tasksInfo = new ArrayList<TareaDetalle>();
	}
	
	public static DataManager getInstance(){
		if(instance == null){
			instance = new DataManager();
		}
		return instance;
	}

	
}
