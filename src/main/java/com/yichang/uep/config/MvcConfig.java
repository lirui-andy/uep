package com.yichang.uep.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/list").setViewName("index");
        registry.addViewController("/list").setViewName("list");
        registry.addViewController("/edit").setViewName("edit");
        registry.addViewController("/view").setViewName("view");
	}

}
