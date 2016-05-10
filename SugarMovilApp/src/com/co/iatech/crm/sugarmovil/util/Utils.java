package com.co.iatech.crm.sugarmovil.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
	private static final String DATETIME_FORMAT_FRONTEND = "dd MMMM yyyy HH:mm:ss";
	private static final String DATE_FORMAT_FRONTEND = "dd MMMM yyyy";
	
	private static final String DATETIME_FORMAT_BACKEND = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FORMAT_BACKEND = "yyyy-MM-dd";
	
	private static final int TIME_TO_DIFF_DB_BACKEND = 5;

	public static String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_BACKEND);
		String formattedDate = sdf.format(c.getTime());

		return formattedDate;
	}

	public static String errorToString(Exception exc) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		exc.printStackTrace(pw);

		return sw.getBuffer().toString();
	}

	public static int generateID() {
		double rnd = Math.random();
		// numeros entre 1 y 999
		rnd = rnd * 1000 + 1;
		return (int) Math.floor(rnd);
	}

	public static String convertTimetoStringFrontEnd(int year, int month, int day) {
		return new StringBuilder().append(day).append(" ").append(getMonth(month)).append(" ").append(year).toString();
	}

	public static String transformTimeBakendToUI(String date_closed) {
		String time = null;
		if (date_closed != null && !date_closed.equals("")) {
			try {
				SimpleDateFormat sdin = null;
				SimpleDateFormat sdout  = null;
				if (!date_closed.contains(":")) {
					sdin = new SimpleDateFormat(DATE_FORMAT_BACKEND, new Locale("es", "ES"));
					sdout = new SimpleDateFormat(DATE_FORMAT_FRONTEND);
				} else {
					sdin = new SimpleDateFormat(DATETIME_FORMAT_BACKEND, new Locale("es", "ES"));
					sdout = new SimpleDateFormat(DATETIME_FORMAT_FRONTEND);
				}

				

				Date d = sdin.parse(date_closed);
				Calendar dateCal = Calendar.getInstance();
				dateCal.setTime(d);
				dateCal.add(Calendar.HOUR, -TIME_TO_DIFF_DB_BACKEND);
				time = sdout.format(dateCal.getTime());
			} catch (Exception e) {

			}
		}
		return time;
	}

	public static String transformTimeUItoBackend(String date) {
		String time = null;
		if (date != null && !date.equals("")) {
			try {
				SimpleDateFormat sdin = null;
				SimpleDateFormat sdout  = null;
				if (!date.contains(":")) {
					sdin = new SimpleDateFormat(DATE_FORMAT_FRONTEND, new Locale("es", "ES"));
					sdout = new SimpleDateFormat(DATE_FORMAT_BACKEND);
				} else {
					sdin = new SimpleDateFormat(DATETIME_FORMAT_FRONTEND, new Locale("es", "ES"));
					sdout = new SimpleDateFormat(DATETIME_FORMAT_BACKEND);
				}

				

				Date d = sdin.parse(date);
				Calendar dateCal = Calendar.getInstance();
				dateCal.setTime(d);
				dateCal.add(Calendar.HOUR_OF_DAY, +TIME_TO_DIFF_DB_BACKEND);
				time = sdout.format(dateCal.getTime());
			} catch (Exception e) {

			}
		}
		return time;
	}

	public static Calendar getDate(String text) {
		Calendar c = Calendar.getInstance();
		if (text != null) {
			SimpleDateFormat sdf = null;
			if (text.contains(":")) {
				sdf = new SimpleDateFormat(DATETIME_FORMAT_FRONTEND, new Locale("es", "ES"));
			} else {
				sdf = new SimpleDateFormat(DATE_FORMAT_FRONTEND, new Locale("es", "ES"));
			}
			try {
				Date d = sdf.parse(text);
				c.setTime(d);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}

		}
		return c;
	}
	
	public static CharSequence convertTimetoStringFrontEnd(Calendar c) {
		SimpleDateFormat sdout = new SimpleDateFormat(DATETIME_FORMAT_FRONTEND);
		return sdout.format(c.getTime());
	}

	public static String getMonth(int month) {
		String m = "";
		switch (month) {
		case 0:
			m = "Enero";
			break;
		case 1:
			m = "Febrero";
			break;
		case 2:
			m = "Marzo";
			break;
		case 3:
			m = "Abril";
			break;
		case 4:
			m = "Mayo";
			break;
		case 5:
			m = "Junio";
			break;
		case 6:
			m = "Julio";
			break;
		case 7:
			m = "Agosto";
			break;
		case 8:
			m = "Septiembre";
			break;
		case 9:
			m = "Octubre";
			break;
		case 10:
			m = "Noviembre";
			break;
		case 11:
			m = "Diciembre";
			break;
		}
		return m;
	}

	public static String delSpecialChars(String value) {
		if (value != null) {
			value = value.replace(",", "");
		}
		return value;
	}

	public static String addSepMiles(String value) {
		if (value != null && !value.equals("")) {
			double valueD = Double.parseDouble(value);
			// DecimalFormat myFormatter = new DecimalFormat("#.###");
			// myFormatter.format(value);
			// String s = NumberFormat.getIntegerInstance().format(i);
			value = String.format("%,.0f", valueD);
			// value = myFormatter.format(value);
		}
		return value;
	}

	public static String hideTabs(String value) {
		if (value != null && !value.equals("") && !value.equalsIgnoreCase("null")) {
			value = value.replace("\n", "*.#.");
			value = value.replace("\t", "*##*");
		}
		return value;
	}

	public static String getTabs(String value) {
		if (value != null && !value.equals("") && !value.equalsIgnoreCase("null")) {
			value = value.replace("*.#.", "\n");
			value = value.replace("*##*", "\t");
		}
		return value;
	}

}
