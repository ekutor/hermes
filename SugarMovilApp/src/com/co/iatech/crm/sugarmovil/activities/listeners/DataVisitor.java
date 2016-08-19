package com.co.iatech.crm.sugarmovil.activities.listeners;

import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.DetailSubTask;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.model.Lead;
import com.co.iatech.crm.sugarmovil.model.Notes;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;

public interface DataVisitor {
	
	public void add(Oportunidad bean);
	public void add(Cuenta bean);
	public void add(Call bean);
	public void add(DetailTask bean);
	public void add(DetailSubTask bean);
	public void add(Notes bean);
	public void add(Lead bean);
	
	public int size(Oportunidad bean);
	public int size(Cuenta bean);
	public int size(Call bean);
	public int size(DetailTask bean);
	
	public void clear(Oportunidad bean);
	public void clear(Cuenta bean);
	public void clear(Call bean);
	public void clear(DetailTask bean);
}
