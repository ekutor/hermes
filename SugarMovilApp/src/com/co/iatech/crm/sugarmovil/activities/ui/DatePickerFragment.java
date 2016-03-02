package com.co.iatech.crm.sugarmovil.activities.ui;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.co.iatech.crm.sugarmovil.activities.AddCallActivity;
import com.co.iatech.crm.sugarmovil.util.Utils;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private TextView textResponse;
	private Activity actualActivity;
	private boolean modoEdicion;
	private Calendar c;

	public DatePickerFragment(Activity actualActivity, TextView textResponse,
			boolean modoEdicion) {
		this.actualActivity = actualActivity;
		this.textResponse = textResponse;
		this.modoEdicion = modoEdicion;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		c = Calendar.getInstance();

		if (modoEdicion) {
			c = Utils.getDate(textResponse.getText().toString());
		}
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(actualActivity, this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		if( actualActivity instanceof AddCallActivity){
			textResponse.setText(Utils.convertTimetoString(c));
		}else{
			textResponse.setText(Utils.convertTimetoString(year, month, day));
		}
		
	}
}