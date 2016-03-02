package com.co.iatech.crm.sugarmovil.activities.ui;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import com.co.iatech.crm.sugarmovil.util.Utils;

public class TimePickerFragment extends DialogFragment implements
TimePickerDialog.OnTimeSetListener {

	private TextView textResponse;
	private Activity actualActivity;
	private boolean modoEdicion;
	private Calendar c ;

	public TimePickerFragment(Activity actualActivity, TextView textResponse,
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
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		return new TimePickerDialog(actualActivity, this, hour, minute, true);
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		c.set(Calendar.HOUR, hourOfDay);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, 0);
		textResponse.setText(Utils.convertTimetoString(c));
	}

}