package com.yichang.uep.controller;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AttachmentController {

	Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile[] file){
		Stream.of(file)
		.filter(f -> (f != null && f.getSize() > 0 ) )
		.forEach(f -> {
			if(f != null){
				long size = f.getSize();
				String name = f.getOriginalFilename();
				logger.info(name+"  ,size="+size);
			}
		});
		return "success";
	}
}
