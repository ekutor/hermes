package com.co.iatech.crm.sugarmovil.activities.tasks;

import java.util.HashSet;
import java.util.Set;

import com.co.iatech.crm.sugarmovil.activtities.modules.ActivityBeanCommunicator;

public class GatewayPublisher implements ITaskPublisher{
	
	private static GatewayPublisher INSTANCE;
	private final Object MUTEX=  new Object();
	
	private Set<IObserverTask> observers;
	private ActivityBeanCommunicator message;
	
	private GatewayPublisher(){
		observers = new HashSet<IObserverTask>();
	}
	
	public static GatewayPublisher getInstance(){
		if(INSTANCE == null){
			INSTANCE = new GatewayPublisher();
		}
		return INSTANCE;
	}
	@Override
	public void register(IObserverTask obj) {
		if(obj != null){
			synchronized (MUTEX) {
				obj.defineTasktoListen(this);
				observers.add(obj);
			}
		}
		
	}

	@Override
	public void unregister(IObserverTask obj) {
		synchronized (MUTEX) {
			observers.remove(obj);
		}
		
	}

	@Override
	public void notifyObservers() {
		synchronized (MUTEX) {
			for(IObserverTask observer: observers){
				observer.update();
			}
		}
		
	}

	@Override
	public ActivityBeanCommunicator getInfo() {
		return message;
	}
	
	@Override
	public void postMessage(ActivityBeanCommunicator message) {
		this.message = message;
		this.notifyObservers();
	}

}
