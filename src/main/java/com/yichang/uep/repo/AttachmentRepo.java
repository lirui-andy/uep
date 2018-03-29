package com.yichang.uep.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YAttachment;

public interface AttachmentRepo extends JpaRepository<YAttachment, Integer> {

}
