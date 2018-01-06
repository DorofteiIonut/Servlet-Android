package com.models;

import com.google.gson.Gson;

public class JSONConvertor {

	private static Gson gson = new Gson();
	public static String convert(Object object) {
		
		return gson.toJson(object);
	}


}
