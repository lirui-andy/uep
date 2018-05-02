package com.yichang.uep.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.yichang.uep.model.YEventComment;
import com.yichang.uep.repo.EventCommentRepo;

@Component
public class CommentManageImpl implements CommentManage {

	@Autowired
	EventCommentRepo eventCommentRepo;

	@Override
	public void saveComment(String cmtCode, String cmtVal, int eventId, String userName) {


		final Date now = new Date();
		YEventComment cmt = new YEventComment();
		cmt.setCommentType(cmtCode);
		cmt.setCommentValue(cmtVal);
		cmt.setEventId(eventId);

		cmt.setActive(Boolean.FALSE);
		List<YEventComment> priorCmts = eventCommentRepo
				.findByCommentTypeAndAndEventId(cmtCode, eventId);
		
		List<YEventComment> saveList  = new ArrayList<>(priorCmts.size() + 1);
		Stream
			.of(priorCmts.toArray(new YEventComment[]{}))
			.forEach(c -> { 
				c.setActive(Boolean.FALSE);
				saveList.add(c);
			});
		
		cmt.setActive(Boolean.TRUE);
		cmt.setOperTime(now);
		cmt.setOperUser(userName);
		saveList.add(cmt);
		eventCommentRepo.saveAll(saveList);
	}

}
