package com.co.iatech.crm.sugarmovil.activities.validators;

import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public abstract class ValidatorActivities implements Validator {

	protected Map<View, CharSequence> data;
	public  static final String SELECT_MESSAGE = "Seleccione Cuenta ..."; 

	@Override
	public boolean validate(Spinner listSpin) {
		 if (listSpin.getSelectedItem() == null || listSpin.getSelectedItemPosition() == 0){
			 return false;
		 }
		return true;
	}
	
	@Override
	public boolean validate(EditText edit) {
		 if (edit.getText() == null || edit.getText().toString().equals("")
				 || SELECT_MESSAGE.equals(edit.getText().toString())){
			 return false;
		 }
		return true;
	}
	
	public void define(Map<View, CharSequence> data) {
		this.data = data;	
	}
	
	public abstract boolean executeValidations(Context c);


}
