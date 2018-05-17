package com.yichang.uep.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.yichang.uep.dto.UepUser;

public class BaseController {

	UepUser currentUser(){
		UepUser user = (UepUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	protected boolean hasAuthority(String auth){
		return SecurityContextHolder.getContext().getAuthentication()
		.getAuthorities().stream()
		.filter(f -> f.getAuthority().equalsIgnoreCase(auth))
		.toArray().length > 0
		;
	}
}
