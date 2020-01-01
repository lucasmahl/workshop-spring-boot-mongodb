package com.lucasmahl.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
}
