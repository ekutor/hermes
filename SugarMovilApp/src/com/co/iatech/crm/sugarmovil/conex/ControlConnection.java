package com.co.iatech.crm.sugarmovil.conex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;

public class ControlConnection {
	
	public enum Modo {EDITAR, AGREGAR}

	//Produccion
	//public static String URL = "http://crmlaumayer.com/movil/CRMLaumayerWS/index.php/";
	//public static String URL2 = "http://181.143.40.162/movil/CRMLaumayerWS/index.php/";
	//Pruebas
	public static String URL = "http://crmlaumayer.com/pruebas/crm/movil/CRMLaumayerWS/index.php/";
	public static String URL2 = "http://181.143.40.162/pruebas/crm/movil/CRMLaumayerWS/index.php/";
	
	public static String android_id;
	public static String device_id;
	public static String hash, userId;
	
	private static Map<String,String> data;
	 
	
	
	public static String getInfo( TypeInfoServer type, Activity activity){
		return getInfo(type, data, (GlobalClass) activity.getApplicationContext());
	}

	public static String getInfo( TypeInfoServer type, Map<String,String> data, GlobalClass globalClass){
		String resp = "";
		HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL + type.name());

        if(data != null && data.size() > 0){
	        for (Map.Entry<String, String> entry : data.entrySet()) {
	        	httpGet.setHeader(entry.getKey(), entry.getValue());
	        	
	        }
		}
      
        chargeID(httpGet, globalClass);
        
       
        try {
            HttpResponse response = httpClient.execute(httpGet);
            resp = EntityUtils.toString(response.getEntity());
            resp = resp.replace("\n", "").replace("\r", "");
          
            data= null;
            return resp;
        }catch(java.net.UnknownHostException he){
        	if(!URL.equals(URL2)){
        		URL = URL2;
        		resp = getInfo(  type, data, globalClass);
        	}
        	
        }catch (IOException e) {
            e.printStackTrace();
            data= null;
           
        }
        return resp;
	}
	
	private static void chargeID(HttpRequestBase httpGet, GlobalClass global) {
	
		if(device_id == null){
			device_id = global.getDeviceId();
		}
		httpGet.setHeader("deviceID", device_id);
		
		
		try{	
			if(hash == null){
				hash = global.getUsuarioAutenticado().getUser_hash();
		       
		    }
			httpGet.setHeader("hash", hash);
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	public static String putInfo( TypeInfoServer type, Map<String,String> data , Modo modo , Activity activity ){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(URL + type.name());
  

        if(data != null && data.size() > 0){
	        for (Map.Entry<String, String> entry : data.entrySet()) {
	        	httpPut.setHeader(entry.getKey(), entry.getValue());
	        }
		}
        httpPut.setHeader("modo", modo.name().toLowerCase());
        
        
        chargeID(httpPut, (GlobalClass) activity.getApplicationContext());
        
        httpPut.setHeader("deviceID", device_id);
       
        if(hash != null)
        	httpPut.setHeader("hash", hash);
       
        try {
            HttpResponse response = httpClient.execute(httpPut);
            String resp = EntityUtils.toString(response.getEntity());
            resp = resp.replace("\n", "").replace("\r", "");
           
            data= null;
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
            data= null;
            return null;
        }
	}
	
	public static void addHeader(String header, String value, boolean cleanData){
		if(data == null || cleanData){
			data = new HashMap<String,String> ();
		}
		data.put(header, value);
	}
	public static void chargeUser(Activity activity) {
		GlobalClass global = (GlobalClass) activity.getApplicationContext();
		User u = global.getUsuarioAutenticado();
		ControlConnection.userId = u.getId();
		ControlConnection.hash = u.getUser_hash();
	}

	public static void addCurrentUser(Activity activity) {
		ControlConnection.addHeader("currentUser", ControlConnection.userId, false);
	}
	

}
