package model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {

	public static Date strToDate(String fecha){
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fechaUtil = null;
		
		try{
			fechaUtil = formatoDelTexto.parse(fecha);
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		return new java.sql.Date(fechaUtil.getTime());
	}
	
	public static String dateToStr(java.util.Date fecha){
		return fecha.getDay()+"/"+fecha.getMonth()+"/"+fecha.getYear();
	}

}
