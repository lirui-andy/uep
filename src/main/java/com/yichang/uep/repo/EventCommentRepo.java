package com.yichang.uep.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yichang.uep.model.YEventComment;

public interface EventCommentRepo extends JpaRepository<YEventComment, Integer> {
	
	
	public YEventComment findTop1ByEventIdAndCommentTypeOrderByOperTimeDesc(int eventId, String commentType);

	@Query(value="select * from y_event_comment where comment_id in (select max(comment_id) as comment_id from  y_event_comment where event_id=?1 group by comment_type)"
			,nativeQuery=true)
	public List<YEventComment> findLatestByEventId(Integer eventId);
}
