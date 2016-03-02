package com.co.iatech.crm.sugarmovil.core.data;

import java.util.ArrayList;

import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.Tarea;


public class DataManager  {
	
	private static DataManager instance;
	
	public ArrayList<Oportunidad> opportunitiesInfo;
	public ArrayList<Cuenta> accountsInfo;
    public ArrayList<Llamada> callsInfo;
    public ArrayList<Contacto> contactsInfo;
    public ArrayList<Tarea> tasksInfo;
    
    
	private DataManager(){
		opportunitiesInfo = new ArrayList<Oportunidad>();
		accountsInfo  = new ArrayList<Cuenta>();
		callsInfo = new ArrayList<Llamada>();
		contactsInfo = new ArrayList<Contacto>();
		tasksInfo = new ArrayList<Tarea>();
	}
	
	public static DataManager getInstance(){
		if(instance == null){
			instance = new DataManager();
		}
		return instance;
	}

	
}
