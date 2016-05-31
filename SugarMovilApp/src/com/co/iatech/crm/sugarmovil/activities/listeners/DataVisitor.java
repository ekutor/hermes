package com.co.iatech.crm.sugarmovil.activities.listeners;

import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.DetailTask;

public interface DataVisitor {
	
	public void add(Oportunidad bean);
	public void add(Cuenta bean);
	public void add(Call bean);
	public void add(DetailTask bean);
	
	public int size(Oportunidad bean);
	public int size(Cuenta bean);
	public int size(Call bean);
	public int size(DetailTask bean);
	
	public void clear(Oportunidad bean);
	public void clear(Cuenta bean);
	public void clear(Call bean);
	public void clear(DetailTask bean);
}
