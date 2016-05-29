package com.co.iatech.crm.sugarmovil.activities.tasks;

public interface IObserverTask {
	void update();
	void defineTasktoListen(ITaskPublisher publisher);
}
