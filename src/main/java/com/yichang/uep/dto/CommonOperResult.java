package com.yichang.uep.dto;

import java.io.Serializable;

public class CommonOperResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean success;
	String code;
	String message;
	T data;

	public static <T> CommonOperResult<T> success(){
		return success(null);
	}
	public static <T> CommonOperResult<T> success(T data){
		CommonOperResult<T> r = new CommonOperResult<>();
		r.success = true;
		r.data = data;
		return r;
	}
	
	public static <T> CommonOperResult<T> fail(String code, String message){
		CommonOperResult<T> r = new CommonOperResult<>();
		r.success = false;
		r.code = code;
		r.message = message;
		return r;
	}
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
