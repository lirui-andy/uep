package com.yichang.uep.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YEventComment;

public interface EventCommentRepo extends JpaRepository<YEventComment, Integer> {
	
	
	public YEventComment findTop1ByEventIdAndCommentTypeOrderByOperTimeDesc(int eventId, String commentType);
}
