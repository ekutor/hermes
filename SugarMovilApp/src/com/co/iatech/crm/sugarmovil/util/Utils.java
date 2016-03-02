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
	
	
	  public static String getCurrentTime(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(c.getTime());
		
		return formattedDate;
	  }
	  
	  public static String errorToString(Exception exc) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			
			exc.printStackTrace(pw);
			
			return sw.getBuffer().toString();
		}
	  
	  	public static int generateID(){
	  		double rnd =Math.random();
	  		//numeros entre 1 y 999
	  		rnd = rnd *1000 + 1;
	  		return (int) Math.floor(rnd);
	  	}
	  	
	  	public static String convertTimetoString(int year, int month, int day){
	  		return new StringBuilder().append(day).append(" ").append(getMonth(month)).append(" ")
            .append(year).toString();
	  	}
	  	
	  	public static String convertTimetoString(String date_closed) {
	  		String time = null;
	  		try{
				if(date_closed != null && !date_closed.equals("")){
					String[] date = date_closed.split("-");
					time = date[2] +" "+ getMonth(Integer.parseInt(date[1])-1) + " "+ date[0];
				}
	  		}catch(Exception e){
	  			
	  		}
			return time;
		}
	  	
	
	  	public static String getTimetoBackend(String date) {
	  		String time = null;
	  		if(date != null && !date.equals("")){	  		
		  		try{
			  		SimpleDateFormat sdin = new SimpleDateFormat("dd MMMM yyyy", new Locale("es", "ES"));
			  		SimpleDateFormat sdout= new SimpleDateFormat("yyyy-MM-dd");
		  	
					Date d = sdin.parse(date);
					time = sdout.format(d);
		  		}catch(Exception e){
		  			
		  		}
	  		}
			return time;
		}
	  	
	  	public static String getMonth(int month){
	  		String m = "";
	  		switch(month){
		  		case 0: m = "Enero";
		  			break;
		  		case 1: m = "Febrero";
	  				break;
		  		case 2: m = "Marzo";
	  				break;
		  		case 3: m = "Abril";
	  				break;
		  		case 4: m = "Mayo";
	  				break;
		  		case 5: m = "Junio";
	  				break;
		  		case 6: m = "Julio";
	  				break;
		  		case 7: m = "Agosto";
	  				break;
		  		case 8: m = "Septiembre";
	  				break;
		  		case 9: m = "Octubre";
	  				break;
		  		case 10: m = "Noviembre";
	  				break;
		  		case 11: m = "Diciembre";
	  				break;
	  		}
	  		return m;
	  	}

		public static Calendar getDate(String text) {
			Calendar c = Calendar.getInstance();
			if(text != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("es", "ES"));
				try {
					Date d = sdf.parse(text);
					c.setTime(d);
				} catch (ParseException e) {
					sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", new Locale("es", "ES"));
					
					try {
						Date d = sdf.parse(text);
						c.setTime(d);
					} catch (ParseException ex) {
					 ex.printStackTrace();
					}

				}
				
			}
			return c;
		}
		

		public static String delSpecialChars(String value) {
	    	if(value != null){
	    		value = value.replace(",", "");
	    	}
			return value;
		}

		public static String addSepMiles(String value) {
			if(value != null && !value.equals("")){
				double valueD = Double.parseDouble(value);
				//DecimalFormat myFormatter = new DecimalFormat("#.###");
				//myFormatter.format(value);
				//String s = NumberFormat.getIntegerInstance().format(i);
				value = String.format( "%,.0f", valueD);
				//value  = myFormatter.format(value);
				}
			return value;
		}
		
		public static String hideTabs(String value){
			if(value != null && !value.equals("") && !value.equalsIgnoreCase("null")){
				value = value.replace("\n", "*.#.");
				value = value.replace("\t", "*##*");
			}
			return value;
		}
		
		public static String getTabs(String value){
			if(value != null && !value.equals("") && !value.equalsIgnoreCase("null")){
				value = value.replace( "*.#.","\n");
				value = value.replace("*##*", "\t");
			}
			return value;
		}

		public static CharSequence convertTimetoString(Calendar c) {
			SimpleDateFormat sdout= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdout.format(c.getTime());
		}
		


}
