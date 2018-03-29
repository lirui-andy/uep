package com.yichang.uep.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yichang.uep.model.YEvent;

public interface EventRepo extends JpaRepository<YEvent, Integer> {

}
