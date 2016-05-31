package com.co.iatech.crm.sugarmovil.activities.tasks;

import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;

public interface ITaskPublisher {
	void register(IObserverTask obj);
	
	void unregister(IObserverTask obj);
	
	void notifyObservers();
	
	ActivityBeanCommunicator getInfo();

	void postMessage(ActivityBeanCommunicator message);
	
}
