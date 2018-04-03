package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YAttachment;

public interface AttachmentRepo extends JpaRepository<YAttachment, Integer> {

	public List<YAttachment> findByEventIdOrderByAttIdDesc(int eventId);

}
