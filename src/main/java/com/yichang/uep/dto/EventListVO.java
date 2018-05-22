package com.yichang.uep.dto;

public class EventListVO extends EventVO {
	private static final long serialVersionUID = 1L;
	boolean signed;//是否签收
	Boolean active;
	
	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
