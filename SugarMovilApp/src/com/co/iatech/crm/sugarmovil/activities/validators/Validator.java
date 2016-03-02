package com.co.iatech.crm.sugarmovil.activities.validators;

import android.widget.EditText;
import android.widget.Spinner;

public interface Validator {
	public boolean validate(Spinner listSpin);
	public boolean validate(EditText listSpin);
}
