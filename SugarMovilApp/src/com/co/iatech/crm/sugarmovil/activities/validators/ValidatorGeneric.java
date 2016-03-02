package com.co.iatech.crm.sugarmovil.activities.validators;

import java.util.Iterator;
import java.util.Map.Entry;

import com.co.iatech.crm.sugarmovil.activities.ui.Message;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ValidatorGeneric extends ValidatorActivities {
	
	private static ValidatorGeneric instance;
	
	private ValidatorGeneric(){}
	
	public static ValidatorGeneric getInstance(){
		if(instance == null){
			instance = new ValidatorGeneric();
		}
		return instance;
	}
	
	public boolean execute (View view, CharSequence message, Context c){
		boolean resp = false;
		if(view instanceof Spinner){
			resp = validate((Spinner)view);
		}else  if(view instanceof EditText){
			resp = validate((EditText)view);
		}
		if(!resp){
			Message.showShort(message, c);
		}
		return resp;
	}

	@Override
	public boolean executeValidations(Context c) {
		if(data == null ){
			return false;
		}
		 Iterator<Entry<View, CharSequence>> it =data.entrySet().iterator();
		 while(it.hasNext()){
			Entry<View, CharSequence> info = it.next();
			if(!execute(info.getKey(),info.getValue(), c)){
					return false;
			}
		 }
		 return true;
	}
	
}
