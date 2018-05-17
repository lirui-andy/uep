package com.yichang.uep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yichang.uep.model.YEventHis;

public interface EventHisRepo extends JpaRepository<YEventHis, Integer>, JpaSpecificationExecutor<YEventHis>  {

	
}
