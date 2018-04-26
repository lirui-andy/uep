package com.yichang.uep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(path={"/index","", "/"})
	public String index(){
		return "redirect:/list";
	}
	@RequestMapping(path={"/error", "/500"})
	public String error(){
		return "500";
	}
}
