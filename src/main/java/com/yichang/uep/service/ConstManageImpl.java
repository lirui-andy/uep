package com.yichang.uep.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yichang.uep.model.YConst;
import com.yichang.uep.repo.ConstRepo;

@Component
public class ConstManageImpl implements ConstManage {

	@Autowired
	ConstRepo constRepo;
	
	@Override
	@Transactional
	public void addConst() {
//		constRepo.save(new YConst((short)1, "A","3", "3"));
//		constRepo.save(new YConst((short)1, "B","3", "3"));
		
	}

}
