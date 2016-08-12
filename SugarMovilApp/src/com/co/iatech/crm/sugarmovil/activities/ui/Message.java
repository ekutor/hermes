package com.co.iatech.crm.sugarmovil.activities.ui;

import com.co.iatech.crm.sugarmovil.activities.ui.ResponseDialogFragment.DialogType;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.fragments.GenericListDialog;
import com.co.iatech.crm.sugarmovil.fragments.UsersDialog;
import com.co.iatech.crm.sugarmovil.model.converters.lists.ListModelConverter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.widget.Toast;

public class Message {
	
	public static void showShort(CharSequence text, Context context){
   	 int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 20);
        toast.show();
	}
	
	public static void showShortExt(CharSequence text, Context context){
	   	 int duration = Toast.LENGTH_LONG;
	        Toast toast = Toast.makeText(context, text, duration);
	        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 80);
	        toast.show();
	}
	
	 public static void showUsersDialog(FragmentManager fm ) {
	        UsersDialog userDialog = new UsersDialog();
	        userDialog.show(fm, "users_dialog");
	  }
	 public static void showGenericDialog(FragmentManager fm, ListModelConverter list, Modules responseModule ) {
	        GenericListDialog dialog = new GenericListDialog(list,responseModule);
	        dialog.show(fm, "accounts_dialog");
	  }
	 
	 public static void showDatePicker(){
		 
	 }
	 
	 public static void showFinalMessage(android.app.FragmentManager fragmentManager,
			 DialogType type, Activity activity , Modules module){
		 ResponseDialogFragment m =  new ResponseDialogFragment(type,activity,module);
		 m.setCancelable(false);
		 m.show(fragmentManager, "createFinalMessage");
	 }
	 public static void showFinalMessage(android.app.FragmentManager fragmentManager, String message, 
			 Activity activity , Modules module){
		 ResponseDialogFragment m =  new ResponseDialogFragment( activity,module, message);
		 m.setCancelable(false);
		 m.show(fragmentManager, "createFinalGenMessage");
	 }
}
