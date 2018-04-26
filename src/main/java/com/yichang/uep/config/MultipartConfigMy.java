package com.yichang.uep.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import com.yichang.uep.utils.FileUtils;

@Configuration
public class MultipartConfigMy {

	Logger logger = LoggerFactory.getLogger(MultipartConfigMy.class);
    
//	@Value("${spring.servlet.multipart.max-file-size}")
	String fileSize = "50MB";
	
//	@Value("${spring.servlet.multipart.max-request-size}")
	String requestSize = "200MB";
	
//	@Value("${spring.servlet.multipart.location}")
	String tempLocation = "temp";
	
//	@Value("${spring.servlet.multipart.save-to}")
	String saveLocation = "upload";
	
    @Bean
    public MultipartConfigElement multipartConfig(){
    	MultipartConfigFactory f = new MultipartConfigFactory();
    	f.setMaxFileSize(fileSize);
    	f.setMaxRequestSize(requestSize);
    	String tmpPath = 
    			prepareDirectory(tempLocation);
    	prepareDirectory(saveLocation);
    	f.setLocation(tmpPath);
    	return f.createMultipartConfig();
    }

	private String prepareDirectory(String temp) {
		try{
			
			logger.debug(FileUtils.home.toString());
			logger.debug(temp);
    		File file = new File(FileUtils.home, temp);
    		if(!file.exists())
    			file.mkdirs();
    		return file.getAbsolutePath();
    	}catch(Exception e){
    		logger.error("Prepare upload directory faild!["+temp+"]:"+e.getMessage(), e);
    		throw e;
    	}		
	}
}
