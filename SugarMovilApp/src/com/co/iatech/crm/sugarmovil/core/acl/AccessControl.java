package com.co.iatech.crm.sugarmovil.core.acl;

import android.content.Context;
import android.util.Log;

import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;

public class AccessControl {

	public static TypeActions getTypeEdit(Modules module, GlobalClass global) {

		User u = getActualUser(global);
		ActionsInfo actions = u.getAccess().get(module);
		if (actions == null || !actions.hasAction(Actions.edit)) {
			Log.d("AccesControl", " Actions no tiene edit ");
			return TypeActions.OWNER;
		} else {
			Log.d("AccesControl", " Actions tiene Edit ");
			return actions.getType(Actions.edit);
		}
	}

	public static boolean validateEdit(Modules module, GlobalClass global) {
		User u = getActualUser(global);
	
		ActionsInfo actions = u.getAccess().get(module);
		if (actions == null) {
			Log.d("AccesControl", " actions es null ");
			return false;

		}
		if (actions != null && actions.hasAction(Actions.edit)) {
			Log.d("AccesControl", " Actions contiene Edit ");
			return true;
		}
		Log.d("AccesControl", " Actions NOOO contiene edit ");
		return false;
	}

	public static User getActualUser(GlobalClass global) {
		User u = global.getUsuarioAutenticado();
		return u;
	}

	public static boolean validateEditType(Modules module,
			String assigned_user_id, GlobalClass global) {
		TypeActions type = getTypeEdit(module, global) ;
		if (TypeActions.OWNER.equals(type)) {
			User u = getActualUser(global);
			if (!u.getId().equals(assigned_user_id)) {
				return false;
			}
		}
		return true;
	}

}
