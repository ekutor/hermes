package com.co.iatech.crm.sugarmovil.activities.listeners;

import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;

public class DataVisitorsManager implements DataVisitor{
	
	
	@Override
	public void add(Oportunidad bean) {
		DataManager.getInstance().opportunitiesInfo.add(bean);
	}

	@Override
	public void add(Cuenta bean) {
	
	}
	
	@Override
	public int size(Oportunidad bean) {
		return DataManager.getInstance().opportunitiesInfo.size();
	}

	@Override
	public int size(Cuenta bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear(Oportunidad bean) {
		DataManager.getInstance().opportunitiesInfo.clear();
		
	}
	
	@Override
	public void add(TareaDetalle bean) {
		DataManager.getInstance().tasksInfo.add(bean);
		
	}

	@Override
	public int size(TareaDetalle bean) {
		return DataManager.getInstance().tasksInfo.size();
	}
	
	@Override
	public void clear(TareaDetalle bean) {
		DataManager.getInstance().tasksInfo.clear();
	}

	@Override
	public void clear(Cuenta bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Llamada bean) {
		DataManager.getInstance().callsInfo.add(bean);
		
	}

	@Override
	public int size(Llamada bean) {
		return DataManager.getInstance().callsInfo.size();
	}

	@Override
	public void clear(Llamada bean) {
		DataManager.getInstance().callsInfo.clear();
		
	}

}
