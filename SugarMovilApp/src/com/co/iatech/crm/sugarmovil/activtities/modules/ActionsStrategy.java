package com.co.iatech.crm.sugarmovil.activtities.modules;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.co.iatech.crm.sugarmovil.activities.ActivitiesMediator;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.core.acl.AccessControl;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;


/**
 * Clase que implementa la estrategia para definir los permisos de cada modulo
 * @author hectsaga
 *
 */
public class ActionsStrategy {
	
	public static void definePermittedActions(Object context,final GlobalClass global) {
		definePermittedActions((IMovilModuleActions) context, (Context) context,global);
	}
	
	public static void definePermittedActions(final IMovilModuleActions actionModule, 
			final Context context,final GlobalClass global) {
		
		if (actionModule.getEditButton() != null) {
			if (AccessControl.validateEdit(actionModule.getModule(), global)) {
				actionModule.getEditButton().setVisibility(View.VISIBLE);
				 Log.d("ActionsStrategy", " EDIIT module "+actionModule.getModule().name()+" visible");
			} else {
				actionModule.getEditButton().setVisibility(View.INVISIBLE);
				 Log.d("ActionsStrategy", "EDIT module "+actionModule.getModule().name()+" invisible");
			}

			// Eventos
			actionModule.getEditButton().setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (AccessControl.validateEditType(actionModule.getModule(),
							actionModule.getAssignedUser(),global)) {
						ActivitiesMediator.getInstance().addObjectInfo( actionModule.getBean() );
						ActivitiesMediator.getInstance().showEditActivity(context, actionModule.getModule());

					} else {
					
						Message.showShortExt(
								"No Autorizado para realizar Editar esta "
										+ actionModule.getModule().getVisualName().toLowerCase(),
										context);
					}
				}
			});
		}
		if (actionModule.getActionButton() != null) {	
			if (true) {
				actionModule.getActionButton().setVisibility(View.VISIBLE);
				Log.d("ActionsStrategy", " actION module "+actionModule.getModule().name()+" Visible");
				actionModule.getActionButton().setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Log.d("ActionsStrategy", " actION module "+actionModule.getModule().name()+" CLICK !");
						ActivitiesMediator.getInstance().showEditActivity(context, actionModule.getModule());
					}
				});
			} else {
				 Log.d("ActionsStrategy", " actION module "+actionModule.getModule().name()+" invisible");
				actionModule.getActionButton().setVisibility(View.INVISIBLE);
			}
		}	
	}
}
		

