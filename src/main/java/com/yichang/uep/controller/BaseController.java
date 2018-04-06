package com.yichang.uep.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.yichang.uep.dto.UepUser;

public class BaseController {

	UepUser currentUser(){
		UepUser user = (UepUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
