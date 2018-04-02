package com.yichang.uep.dto.datatables;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageAdapter<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long draw;
	long recordsTotal;
	long recordsFiltered;
	List<T> data;
	
	public static <T> PageAdapter<T> create(Page<T> springPage, int draw){
		PageAdapter<T> p = new PageAdapter<>();
		p.recordsTotal = springPage.getTotalElements();
		p.recordsFiltered = springPage.getTotalElements();
		p.draw  = draw;
		p.data = springPage.getContent();
		
		return p;
	}
	
	
	public long getDraw() {
		return draw;
	}
	public void setDraw(long draw) {
		this.draw = draw;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
