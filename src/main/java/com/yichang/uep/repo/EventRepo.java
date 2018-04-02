package com.yichang.uep.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yichang.uep.model.YEvent;

public interface EventRepo extends JpaRepository<YEvent, Integer>, JpaSpecificationExecutor<YEvent>  {

	String sql_findNew = " from YEvent x where x.name like %?1% "
			+ "and x.idNum like %?2% "
			+ "and x.gender = ?3 "
			+ "and x.eventTime between ?4 and ?5 "
			+ "and x not in( select y.YEvent from YEventReceipt y where y.orgId = ?6 )"
			;
	
	@Query( value = "select x "+sql_findNew,
			countQuery = "select count(x) "+sql_findNew)
	Page<YEvent> findNewEvent(String name, 
			String idNum, 
			String gender, 
			Date beginTime, Date endTime, 
			int userId,
			Pageable pageable);
}
