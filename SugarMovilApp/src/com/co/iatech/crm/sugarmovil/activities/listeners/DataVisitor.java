package com.co.iatech.crm.sugarmovil.activities.listeners;

import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.Llamada;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;
import com.co.iatech.crm.sugarmovil.model.TareaDetalle;

public interface DataVisitor {
	
	public void add(Oportunidad bean);
	public void add(Cuenta bean);
	public void add(Llamada bean);
	public void add(TareaDetalle bean);
	
	public int size(Oportunidad bean);
	public int size(Cuenta bean);
	public int size(Llamada bean);
	public int size(TareaDetalle bean);
	
	public void clear(Oportunidad bean);
	public void clear(Cuenta bean);
	public void clear(Llamada bean);
	public void clear(TareaDetalle bean);
}
