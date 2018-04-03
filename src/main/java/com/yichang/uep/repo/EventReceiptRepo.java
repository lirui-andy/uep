package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YEventReceipt;

public interface EventReceiptRepo extends JpaRepository<YEventReceipt, Integer> {

	List<YEventReceipt> findByEventIdOrderByReceiptTimeDesc(int eventId);
	
	YEventReceipt findTop1ByEventIdAndOrgId(int eventId, int orgId);
}
