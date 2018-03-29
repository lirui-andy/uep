package com.yichang.uep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yichang.uep.model.YConst;
import com.yichang.uep.repo.ConstRepo;

@RestController
@EnableAutoConfiguration
public class ConstController {

	@Autowired
	ConstRepo constRepo;
	
	@RequestMapping("/const/{group}")
    @ResponseBody
    List<YConst> list(@PathVariable("group")String group) {
		List<YConst> list = constRepo.findByConstGroup(group);
        return list;
    }

}
