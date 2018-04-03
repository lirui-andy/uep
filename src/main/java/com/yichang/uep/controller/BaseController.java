package com.yichang.uep.controller;

import com.yichang.uep.model.YUser;

public class BaseController {

	YUser currentUser(){
		YUser user = new YUser();
		user.setOrgId(1);
		user.setOrgName("宜昌市分局");
		user.setRealName("宜昌分局");
		user.setUserId(1);
		user.setUserName("ycfj");
		return user;
	}
}
