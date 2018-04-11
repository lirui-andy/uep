package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yichang.uep.model.YEventReceipt;

public interface EventReceiptRepo extends JpaRepository<YEventReceipt, Integer> {

	List<YEventReceipt> findByEventIdOrderByReceiptTimeDesc(int eventId);
	
	YEventReceipt findTop1ByEventIdAndOrgId(int eventId, int orgId);

	@Query("select c from YEventReceipt c where c.eventId in ?1 and orgId = ?2 ")
	List<YEventReceipt> findAllByEventIdAndOrgId(Iterable<Integer> eventIds, int orgId);
}
