package com.yichang.uep.utils;

public class StringUtils extends org.springframework.util.StringUtils {

	public static boolean isBlank(String s){
		return isEmpty(trimWhitespace(s));
	}
	
	public static String trim(String s){
		return trimWhitespace(s);
	}
	
	public static String nullDefault(String s, String defaultVal){
		return s == null ? defaultVal : s;
	}
	
	public static boolean equals(String s1, String s2){
		return s1 != null && s2 != null && s1.equals(s2);
	}
}
