package com.lucasmahl.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	//static pra não precisar instanciar o objeto URL
	public static String decodeParam(String text) {
		try {
			//retorna a string de busca decodificada
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	//static pra não precisar instanciar o objeto URL
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); //horário padrão de greenwich
		
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue; //caso ocorra erro, retorna a data default
		}
	}
}
