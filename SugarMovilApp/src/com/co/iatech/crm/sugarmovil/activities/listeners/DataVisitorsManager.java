package com.co.iatech.crm.sugarmovil.activities.listeners;

import java.util.List;

import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Call;
import com.co.iatech.crm.sugarmovil.model.Cuenta;
import com.co.iatech.crm.sugarmovil.model.DetailSubTask;
import com.co.iatech.crm.sugarmovil.model.DetailTask;
import com.co.iatech.crm.sugarmovil.model.GenericBean;
import com.co.iatech.crm.sugarmovil.model.Notes;
import com.co.iatech.crm.sugarmovil.model.Oportunidad;

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
	public void add(DetailTask bean) {
		this.addObject(DataManager.getInstance().tasksInfo, bean);
		
	}

	@Override
	public int size(DetailTask bean) {
		return DataManager.getInstance().tasksInfo.size();
	}
	
	@Override
	public void clear(DetailTask bean) {
		DataManager.getInstance().tasksInfo.clear();
	}
	
	@Override
	public void add(DetailSubTask bean) {
		this.addObject(DataManager.getInstance().subtasksInfo, bean);
		
	}
	@Override
	public void add(Notes bean) {
		this.addObject(DataManager.getInstance().notesInfo, bean);
	}

	@Override
	public void clear(Cuenta bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Call bean) {
		DataManager.getInstance().callsInfo.add(bean);
		
	}

	@Override
	public int size(Call bean) {
		return DataManager.getInstance().callsInfo.size();
	}

	@Override
	public void clear(Call bean) {
		DataManager.getInstance().callsInfo.clear();
		
	}
	
	private void addObject(List data, GenericBean otherObj){
		boolean finded = false;
		for(int i= 0; i< data.size(); i++){
			GenericBean obj = (GenericBean) data.get(i);
			if(obj.id.equals(otherObj.id)){
				finded = true;
				data.remove(obj);
				data.add(otherObj);
			}
		}
		
		if(!finded){
			data.add(otherObj);
		}
	}

}
