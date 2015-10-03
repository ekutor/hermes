package com.co.iatech.crm.sugarmovil.conex;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class ControlConnection {
	
	public enum Modo {EDITAR, AGREGAR}
	
	public final static String URL = "http://crmlaumayer.com/movil/CRMLaumayerWS/index.php/";
	
	
	public static String android_id;
	public static String device_id;
	public static String hash, userId;
	private static Map<String,String> data;
	 
	
	
	public static String getInfo( TypeInfoServer type){
		return getInfo(type, data);
	}
	public static String getInfo( TypeInfoServer type, Map<String,String> data){
		HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL + type.name());
        Log.d("ControlConnection", "URL: " + URL + type.name());
        if(data != null && data.size() > 0){
	        for (Map.Entry<String, String> entry : data.entrySet()) {
	        	httpGet.setHeader(entry.getKey(), entry.getValue());
	        	Log.d("ControlConnection", entry.getKey()+" "+entry.getValue());
	        }
		}
        
        httpGet.setHeader("deviceID", device_id);
        Log.d("ControlConnection", "deviceID " + device_id);
        if(hash != null)
        	httpGet.setHeader("hash", hash);
        Log.d("ControlConnection", "hash " + hash);
        
       
        try {
            HttpResponse response = httpClient.execute(httpGet);
            String resp = EntityUtils.toString(response.getEntity());
            resp = resp.replace("\n", "").replace("\r", "");
            Log.d("ControlConnection", "Response: "
                    + resp);
            data= null;
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
            data= null;
            return null;
        }
	}
	
	public static String putInfo( TypeInfoServer type, Map<String,String> data , Modo modo ){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(URL + type.name());
  
        Log.d("ControlConnection", "URL: " + URL + type.name());
        if(data != null && data.size() > 0){
	        for (Map.Entry<String, String> entry : data.entrySet()) {
	        	httpPut.setHeader(entry.getKey(), entry.getValue());
	        	Log.d("ControlConnection", entry.getKey()+" "+entry.getValue());
	        }
		}
        httpPut.setHeader("modo", modo.name().toLowerCase());
        
        httpPut.setHeader("deviceID", device_id);
        Log.d("ControlConnection", "deviceID " + device_id);
        if(hash != null)
        	httpPut.setHeader("hash", hash);
        Log.d("ControlConnection", "hash " + hash);
        try {
            HttpResponse response = httpClient.execute(httpPut);
            String resp = EntityUtils.toString(response.getEntity());
            resp = resp.replace("\n", "").replace("\r", "");
            Log.d("ControlConnection", "Response: "
                    + resp);
            data= null;
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
            data= null;
            return null;
        }
	}
	
	public static void addHeader(String header, String value){
		if(data == null){
			data = new HashMap<String,String> ();
		}
		data.put(header, value);
	}
}
