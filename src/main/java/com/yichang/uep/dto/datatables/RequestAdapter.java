package com.yichang.uep.dto.datatables;

import java.io.Serializable;

public class RequestAdapter<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int draw;
	int length;
	int start;
	T condition;
	
	int page;
	
	
	
	public int getPage() {
		if(length > 0)
		page = (start / length);
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public T getCondition() {
		return condition;
	}
	public void setCondition(T condition) {
		this.condition = condition;
	}

	
	
}
