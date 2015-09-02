package com.co.iatech.crm.sugarmovil.util;

public class ListsConversor {
	
	public static String convertDepto(String code){
		String res= code;
		switch(code){
			case "91" : res = "AMAZONAS";break;
			case "05" : res =  "ANTIOQUIA"; break;
			case "81" : res =  "ARAUCA"; break;
			case "08" : res =  "ATLANTICO"; break;
			case "13" : res =  "BOLIVAR"; break;
			case "15" : res =  "BOYACA"; break;
			case "17" : res =  "CALDAS"; break;
			case "18" : res =  "CAQUETA"; break;
			case "85" : res =  "CASANARE"; break;
			case "19" : res =  "CAUCA"; break;
			case "20" : res =  "CESAR"; break;
			case "27" : res =  "CHOCO"; break;
			case "23" : res =  "CORDOBA"; break;
			case "25" : res =  "CUNDINAMARCA"; break;
			case "11" : res =  "DISTRITO CAPITAL DE BOGOTA"; break;
			case "94" : res =  "GUAINIA"; break;
			case "44" : res =  "GUAJIRA"; break;
			case "95" : res =  "GUAVIARE"; break;
			case "41" : res =  "HUILA"; break;
			case "47" : res =  "MAGDALENA"; break;
			case "50" : res =  "META"; break;
			case "52" : res =  "NARINO"; break;
			case "54" : res =  "NORTE DE SANTANDER"; break;
			case "86" : res =  "PUTUMAYO"; break;
			case "63" : res =  "QUINDIO"; break;
			case "66" : res =  "RISARALDA"; break;
			case "88" : res =  "SAN ANDRES"; break;
			case "68" : res =  "SANTANDER"; break;
			case "70" : res =  "SUCRE"; break;
			case "73" : res =  "TOLIMA"; break;
			case "76" : res =  "VALLE"; break;
			case "97": res =  "VAUPES"; break;
			case "99" : res =  "VICHADA"; break;
			case "0" : res =  "VARIOS"; break;

		}
		return res;
	}
	
	public static String convertZone(String code){
		String res= code;
		switch(code){
			case "1" : res =  "ANTIOQUIA"; break;
			case "2" : res =  "BOGOTA CENTRO"; break;
			case "3" : res =  "CALI OCCIDENTE"; break;
			case "4" : res =  "COSTA NORTE"; break;
			case "5" : res =  "SANTANDER"; break;
			case "6" : res =  "EJE CAFETERO"; break;

		}
		return res;
	}
	
	public static String convertChannel(String code){
		String res= code;
		switch(code){
			case "01" : res =  "ALMACEN"; break;
			case "02" : res =  "TABLERISTA"; break;
			case "03" : res =  "BOMBERO"; break;
			case "04" : res =  "ENSAMBLADOR"; break;
			case "05" : res =  "FIRMA DE INGENIERIA"; break;
			case "06" : res =  "USUARIO DIRECTO"; break;
			case "07" : res =  "FERRETERO"; break;
			case "08" : res =  "INTEGRADOR AUTOMATIZACION"; break;
			case "09" : res =  "PLANTERO"; break;
			case "10" : res =  "CONSTRUCTORA"; break;
			case "11" : res =  "INTEGRADOR COMUNICACIO"; break;
			case "12" : res =  "EMPRESA DE TELECOMUNICACION"; break;
			case "13" : res =  "INTEGRADOR DOMOTICO"; break;
			case "14" : res =  "DISEÑADOR DE ESPACIOS"; break;
			case "15" : res =  "EMPRESAS DE SEGURIDAD"; break;
			case "16" : res =  "UNION TEMPORAL"; break;
			case "17" : res =  "CONSORCIO"; break;
			case "18" : res =  "USUARIO FINAL"; break;
			case "19" : res =  "PROVEEDOR"; break;
			case "99" : res =  "EMPLEADO"; break;
		}
		return res;
	}

}
